package com.coinskash.controller;

import com.coinskash.model.app.AccountNoAndBankCode;
import com.coinskash.model.app.AccountNoAndBankName;
import com.coinskash.response.ValidationResponse;
import com.coinskash.validation.BankData;
import com.coinskash.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountVerificationController {
    private AccountNoAndBankName accountNoAndBankName;
    private Validation accountValidation;
    private WebClient webClient;

    @Autowired
    public AccountVerificationController(WebClient webClient, Validation accountValidation) {
        this.webClient = webClient;
        this.accountValidation = accountValidation;
    }

    @PostMapping(value = "/verifyAccount")
    public ValidationResponse isverified(@RequestBody AccountNoAndBankName accountNoAndBankName) {
        /* call a method that can fetch the bank code before
         * and update the bank code for AppAccount
         * before making request to validate the account
         * */
        //bank name; accout no
        BankData getBankCode = accountValidation.getBankCode(accountNoAndBankName);
        AccountNoAndBankCode account = new AccountNoAndBankCode(accountNoAndBankName.getAccountNumber(), getBankCode.getCode());
        return accountValidation.isValidateAccountAccount(account);
    }

    @PostMapping(value = "/bankcode")
    public BankData bankcode(@RequestBody AccountNoAndBankName accountNoAndBankName) {
        return accountValidation.getBankCode(accountNoAndBankName);
    }

    @GetMapping("/allBanks")
    public List<BankData> getAllBanks() {
        return accountValidation.getAllBanks();
    }
}
