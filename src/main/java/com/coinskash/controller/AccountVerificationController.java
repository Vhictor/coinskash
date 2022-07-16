package com.coinskash.controller;

import com.coinskash.model.app.AppAccount;
import com.coinskash.response.ValidationResponse;
import com.coinskash.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class AccountVerificationController {
    private AppAccount appAccount;
    private Validation accountValidation;
    private WebClient webClient;
@Autowired
    public AccountVerificationController(WebClient webClient, Validation accountValidation) {
        this.webClient = webClient;
        this.accountValidation = accountValidation;
    }

    @PostMapping(value = "verifyAccount")
    public Mono<ValidationResponse> isverified(@RequestBody AppAccount appAccount) {
        return accountValidation.isValidateAccountAccount(appAccount);
    }
}
