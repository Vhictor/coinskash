package com.coinskash.crypto;

import com.coinskash.config.PropertiesConfig;
import com.coinskash.exception.GlobalRequestException;
import com.coinskash.helper.UserHelper;
import com.coinskash.service.ImplTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class AcceptCrypto {
    private ImplTransactionService implTransactionService;

    private WebClient webClient;
    private UserHelper userHelper;
    private PropertiesConfig propertiesConfig;

    @Autowired
    public AcceptCrypto(
            ImplTransactionService implTransactionService,
            WebClient webClient,
            UserHelper userHelper,
            PropertiesConfig propertiesConfig
    ) {
        this.implTransactionService = implTransactionService;
        this.webClient = webClient;
        this.userHelper = userHelper;
        this.propertiesConfig = propertiesConfig;
    }

    public Mono<AcceptCryptoPaymentResponse> createPaymentUrl(AcceptCryptoPaymentData acceptCryptoPaymentData, String uuid) {
       try {
           log.info("attempting to create payment link for this customer on Lazer pay");
           Mono<AcceptCryptoPaymentResponse> acceptCryptoPaymentResponse = webClient
                   .post()
                   .uri(propertiesConfig.getLazerpayCreateCryptoPaymentLinkUrl())
                   .accept(MediaType.APPLICATION_JSON)
                   .header("Authorization", propertiesConfig.getLazerpayAuthenticationBearer())
                   .contentType(MediaType.APPLICATION_JSON)
                   .body(Mono.just(acceptCryptoPaymentData), AcceptCryptoPaymentData.class)
                   .exchangeToMono(clientResponse -> {
                       if (clientResponse.statusCode().is2xxSuccessful()) {
                           implTransactionService.saveTransactionRecord(
                                   userHelper.getUserId(),
                                   new TransactionRecord(
                                           uuid,
                                           false,
                                           false
                                   )
                           );

                           /*
                            *after creating payment link create a record in the database.
                            *set payment status to false.
                            * so that when the payment is confirmed in the call back url
                            * we can update payment status to false and process the fiat payment
                            * the UUID is used in the call back url to identify the payment
                            * and must be unique for each transaction
                            * */
                           log.info("successfully created payment link for this customer");
                           return clientResponse.bodyToMono(AcceptCryptoPaymentResponse.class);
                       } else {
                           log.error("there was an error creating payment link for this customer");
                           throw new GlobalRequestException("409", "Could not create payment link", HttpStatus.FORBIDDEN);
                           //return clientResponse.createException().flatMap(Mono::error);
                       }
                   });

           return acceptCryptoPaymentResponse;
       }catch (Exception ex){
           log.error("there was an error creating payment link for this customer");
           throw new GlobalRequestException("400",ex.getMessage(),HttpStatus.BAD_REQUEST);
       }

    }

}
