package com.coinskash.controller;

import com.coinskash.config.PropertiesConfig;
import com.coinskash.converter.Converter;
import com.coinskash.crypto.AcceptCrypto;
import com.coinskash.crypto.AcceptCryptoPaymentData;
import com.coinskash.crypto.AcceptCryptoPaymentResponse;
import com.coinskash.helper.UserHelper;
import com.coinskash.model.ResponseDataFormat;
import com.coinskash.model.app.CryptoCurrency;
import com.coinskash.response.DepositWEbhookResponse;
import com.coinskash.service.ImplTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@Slf4j
public class AcceptCryptoController {
    private String uuid = UUID.randomUUID().toString();
    private ImplTransactionService implTransactionService;
    private PropertiesConfig propertiesConfig;
    private AcceptCrypto acceptCrypto;
    private UserHelper userHelper;
    private Converter converter;

    //initialise this field with data from the database;
    @Autowired
    public AcceptCryptoController(
            ImplTransactionService implTransactionService,
            PropertiesConfig propertiesConfig,
            AcceptCrypto acceptCrypto,
            UserHelper userHelper,
            Converter converter
    ) {
        this.implTransactionService = implTransactionService;
        this.propertiesConfig = propertiesConfig;
        this.acceptCrypto = acceptCrypto;
        this.userHelper = userHelper;
        this.converter = converter;
    }

    @PostMapping("/acceptCryptoCallbackUrl/{payment_uuid}")
    public ResponseDataFormat webhookUrlCallback(@RequestBody DepositWEbhookResponse depositWEbhookResponse, @PathVariable("payment_uuid") String uuid) {
        if (depositWEbhookResponse.getStatus().equals("confirmed")) {
            log.info("Successful crypto payment detected, taking appropriate steps...");
            log.info("Attempting to convert the crypto amount to fiat amount...");
            CryptoCurrency cryptoCurrency = new CryptoCurrency(depositWEbhookResponse.getCoin(), depositWEbhookResponse.getAmountPaid());
            Double amountInCrypto = converter.crytoToFiat(cryptoCurrency).getCurrency();
            log.info("Successfully converted the crypto amount to fiat amount, now updating the transaction record");
            implTransactionService.updateTransactionAcceptCryptoStatus(
                    userHelper.getUserId(),
                    uuid,
                    depositWEbhookResponse.getAmountPaid(),
                    amountInCrypto,
                    depositWEbhookResponse.getSenderAddress(),
                    depositWEbhookResponse.getCoin()
            );
        }
        log.warn("unconfirmed transaction detected with reference number ", depositWEbhookResponse.getReference());
        /*
         * check the deposit webhook response status and see if the transaction is confirmed
         * if transaction status is confirmed then proceed for settlement
         * check the payment uuid against records you have in the database
         * if the uuid is present and the payment status to true and proceed to pay fiat to user account detail
         */

        return new ResponseDataFormat("Transaction confirmed", HttpStatus.OK);
    }

    @GetMapping("/cryptoPaymentCall")
    public AcceptCryptoPaymentResponse makeCryptoPaymentCall() {
        log.info("request to make crypto payment received");
        return acceptCrypto.createPaymentUrl(new AcceptCryptoPaymentData(
                propertiesConfig.getTitle(),
                propertiesConfig.getDescription(),
                propertiesConfig.getAmount(),
                propertiesConfig.getType(),
                propertiesConfig.getLogo(),
                propertiesConfig.getCurrency(),
                propertiesConfig.getRedirect_url() + uuid
        ), uuid);
    }


}
