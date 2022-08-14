package com.coinskash.payout;

import com.coinskash.config.PropertiesConfig;
import com.coinskash.model.response.TransactionRecord;
import com.coinskash.helper.UserHelper;
import com.coinskash.model.AppUser;
import com.coinskash.model.payout.PayoutData;
import com.coinskash.model.payout.beneficiary.BeneficiaryPayout;
import com.coinskash.repository.BeneficiaryPayoutRepository;
import com.coinskash.model.response.PayoutResponse;
import com.coinskash.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@Slf4j
public class Payout {

    private WebClient webClient;
    private TransactionService transactionService;
    private BeneficiaryPayoutRepository beneficiaryPayoutRepository;
    private PayoutData payoutData;
    private PropertiesConfig propertiesConfig;
    private UserHelper userHelper;

    @Autowired
    public Payout(
            WebClient webClient,
            TransactionService transactionService,
            BeneficiaryPayoutRepository beneficiaryPayoutRepository,
            PropertiesConfig propertiesConfig
    ) {
        this.webClient = webClient;
        this.transactionService = transactionService;
        this.beneficiaryPayoutRepository = beneficiaryPayoutRepository;
        this.propertiesConfig = propertiesConfig;
    }

    public void payout() {
        /*
         *  get the payout data from a utility
         *  that returns the transaction to process
         *  from the database
         *  remember this utility must return only a single
         *  transaction to process each time the
         *  scheduler runs
         * */
        log.info("checking for unprocessed pay out...");


        if (null==getPayoutData()) {
            log.info("no transaction to process a this moment.");
            return;
        }
        PayoutData payoutData = getPayoutData().get();
        PayoutResponse payoutResponse = webClient.post()
                .uri(propertiesConfig.getPayoutUrl())
                .accept(MediaType.APPLICATION_JSON)
                .header("api-key", propertiesConfig.getFincraAccountApiKey())
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(payoutData), PayoutData.class)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(PayoutResponse.class);
                    } else {
                        return clientResponse.createException().flatMap(Mono::error);
                    }
                }).block();
        transactionService.updateTransactionPayoutStatus(payoutData.getCustomerReference());

    }

    private Optional<TransactionRecord> getUnpaidTransaction() {
        return transactionService.getUnpaidTransaction();
    }

    private Optional<PayoutData> getPayoutData() {
        //connect to the database and return the payout data
        if (getUnpaidTransaction().isPresent()) {
            log.info("unprocessed payout found");
            TransactionRecord transactionRecord = getUnpaidTransaction().get();
            AppUser appUserDetails = transactionRecord.getAppUser();
            BeneficiaryPayout beneficiaryPayout = beneficiaryPayoutRepository.findByAppUserId(appUserDetails.getId());

            //return appUserDetails;
            payoutData.setAmount(transactionRecord.getAmountInFiat());
            payoutData.setBeneficiary(beneficiaryPayout);
            payoutData.setCustomerReference(transactionRecord.getTransactionReference());
            return Optional.ofNullable(payoutData);
        }

        log.info("no unprocessed payout found");
        return null;
    }
}
