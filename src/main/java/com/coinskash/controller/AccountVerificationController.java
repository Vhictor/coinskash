package com.coinskash.controller;

import com.coinskash.model.app.AppAccount;
import com.coinskash.response.VerificationResponse;
import com.coinskash.validation.AccountValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class AccountVerificationController {
    private AppAccount appAccount;
    private AccountValidation accountValidation;
    private WebClient webClient;
@Autowired
    public AccountVerificationController(WebClient webClient, AccountValidation accountValidation) {
        this.webClient = webClient;
        this.accountValidation = accountValidation;
    }

    @PostMapping(value = "verifyAccount")
    public Mono<VerificationResponse> isverified(@RequestBody AppAccount appAccount) {
        return accountValidation.isValidateAccountAccount(appAccount);
    }
}
