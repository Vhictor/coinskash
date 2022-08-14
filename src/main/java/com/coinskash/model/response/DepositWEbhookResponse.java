package com.coinskash.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DepositWEbhookResponse {
    @JsonProperty("id")
    private String id;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("senderAddress")
    private String senderAddress;
    @JsonProperty("recipientAddress")
    private String recipientAddress;
    @JsonProperty("actualAmount")
    private Double actualAmount;
    @JsonProperty("amountPaid")
    private Double amountPaid;
    @JsonProperty("amountPaidFiat")
    private Double amountPaidFiat;
    @JsonProperty("fiatAmount")
    private Double fiatAmount;
    @JsonProperty("amountReceived")
    private Double amountReceived;
    @JsonProperty("amountReceivedFiat")
    private Double amountReceivedFiat;
    @JsonProperty("coin")
    private String coin;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("hash")
    private String hash;
    @JsonProperty("blockNumber")
    private String blockNumber;
    @JsonProperty("type")
    private String type;
    @JsonProperty("acceptPartialPayment")
    private String acceptPartialPayment;
    @JsonProperty("status")
    private String status;
    @JsonProperty("network")
    private String network;
    @JsonProperty("blockchain")
    private String blockchain;
    @JsonProperty("fiatRate")
    private String fiatRate;
    @JsonProperty("cryptoRate")
    private String cryptoRate;
    @JsonProperty("metadata")
    private Metadata metadata;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("paymentLink")
    private PaymentLink paymentLink;
    @JsonProperty("paymentButton")
    private PaymentButton paymentButton;
    @JsonProperty("customer")
    private Customer customer;
    @JsonProperty("merchantAddress")
    private String merchantAddress;
    @JsonProperty("feeInCrypto")
    private String feeInCrypto;
    @JsonProperty("webhookType")
    private String webhookType;

}
