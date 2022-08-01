package com.coinskash.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class PropertiesConfig {
    /*accept crypto payment link*/
    @Value("${crypto.payment.link.data.title}")
    private String title;
    @Value("${crypto.payment.link.data.description}")
    private String description;
    @Value("${crypto.payment.link.data.amount}")
    private String amount;
    @Value("${crypto.payment.link.data.type}")
    private String type;
    @Value("${crypto.payment.link.data.logo}")
    private String logo;
    @Value("${crypto.payment.link.data.currency}")
    private String currency;
    @Value("${crypto.payment.link.data.redirect_url}")
    private String redirect_url;
    @Value("${verify.account.number.url}")
    private String verifyAccountUrl;
    @Value("${fincra.account.api-key}")
    private String fincraAccountApiKey;
    @Value("${paystack.all-banks.and.bank-code.url}")
    private String banksAndBankcodeUrl;
    @Value("${fincra.payout.url}")
    private String payoutUrl;
    @Value("${lazerpay.accept.crypto.payment.url}")
    private String lazerpayCreateCryptoPaymentLinkUrl;
    @Value("${lazerpay.authorization.bearer}")
    private String lazerpayAuthenticationBearer;
}
