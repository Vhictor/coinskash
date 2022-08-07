package com.coinskash.validation;

import com.coinskash.config.PropertiesConfig;
import com.coinskash.exception.GlobalRequestException;
import com.coinskash.model.app.AccountNoAndBankCode;
import com.coinskash.model.app.AccountNoAndBankName;
import com.coinskash.response.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Validation {
    private WebClient webClient;
    private PropertiesConfig propertiesConfig;

    @Autowired
    public Validation(WebClient webClient, PropertiesConfig propertiesConfig) {
        this.webClient = webClient;
        this.propertiesConfig = propertiesConfig;
    }

    public ValidationResponse isValidateAccountAccount(AccountNoAndBankCode account) {
       try {
           ValidationResponse account1 = webClient.post()
                   .uri(propertiesConfig.getVerifyAccountUrl())
                   .accept(MediaType.APPLICATION_JSON)
                   .header("api-key", propertiesConfig.getFincraAccountApiKey())
                   .contentType(MediaType.APPLICATION_JSON)
                   .body(Mono.just(account), AccountNoAndBankCode.class)
                   .exchangeToMono(clientResponse -> {
                       if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                           return clientResponse.bodyToMono(ValidationResponse.class);
                       } else {
                           return clientResponse.createException().flatMap(Mono::error);
                       }
                   }).block();

           return account1;
       }catch (Exception ex){
           throw new GlobalRequestException("400",ex.getMessage(),HttpStatus.BAD_REQUEST);
       }
//        if(account1.equals(account))return true;
//        else return false;
    }

    public BankData getBankCode(AccountNoAndBankName accountNoAndBankName) {
       try {
           Banks bank = webClient.get()
                   .uri(propertiesConfig.getBanksAndBankcodeUrl())
                   .accept(MediaType.APPLICATION_JSON)
                   .retrieve().bodyToMono(Banks.class).log().block();
           BankData[] bankData = bank.getData();
           return Arrays.stream(bankData).filter(bankData1 ->
                   bankData1.name.equals(accountNoAndBankName.getBankName())
           ).findFirst().get();
       }catch (Exception ex){
           throw new GlobalRequestException("400",ex.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }

    public List<BankData> getAllBanks() {
       try {
           Banks bank = webClient.get()
                   .uri(propertiesConfig.getBanksAndBankcodeUrl())
                   .accept(MediaType.APPLICATION_JSON)
                   .retrieve().bodyToMono(Banks.class).log().block();

           BankData[] bankData = bank.getData();
           return Arrays.stream(bankData).collect(Collectors.toList());
       }catch (Exception ex){
           throw new GlobalRequestException("400",ex.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }
}
