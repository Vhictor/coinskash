package com.coinskash.model.payout;

import com.coinskash.model.payout.beneficiary.Beneficiary;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PayoutData {
    @JsonProperty("sourceCurrency")
    private String sourceCurrency;
    @JsonProperty("destinationCurrency")
    private String destinationCurrency;
    @JsonProperty("beneficiary")
    private Beneficiary beneficiary;
    @JsonProperty("paymentDestination")
    private String paymentDestination;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("business")
    private String business;
    @JsonProperty("customerReference")
    private String customerReference;
    @JsonProperty("paymentScheme")
    private String paymentScheme;
    @JsonProperty("quoteReference")
    private String quoteReference;
}
