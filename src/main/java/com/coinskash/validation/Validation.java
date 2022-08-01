package com.coinskash.validation;

import com.coinskash.model.app.AppAccount;
import com.coinskash.response.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class Validation {
    @Autowired
    private WebClient webClient;
    public Mono<ValidationResponse> isValidateAccountAccount(AppAccount account){
        Mono<ValidationResponse> account1 = webClient.post()
                .uri("https://sandboxapi.fincra.com/core/accounts/resolve")
                .accept(MediaType.APPLICATION_JSON)
                .header("api-key","Bvgt8zLovHrjt4cRwkSSxNa5ppQ9xazf")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(account),AppAccount.class)
                .exchangeToMono(clientResponse -> {
                    if(clientResponse.statusCode().equals(HttpStatus.OK)){
                        return clientResponse.bodyToMono(ValidationResponse.class);
                    }else {
                       return clientResponse.createException().flatMap(Mono::error);
                    }
                });
       return account1;
//        if(account1.equals(account))return true;
//        else return false;
    }
}
