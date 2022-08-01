package com.coinskash.crypto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AcceptCryptoPaymentResponseData {
    @JsonProperty("id")
    private String id;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("redirectUrl")
    private String redirectUrl;
    @JsonProperty("logo")
    private String logo;
    @JsonProperty("type")
    private String type;
    @JsonProperty("network")
    private String network;
    @JsonProperty("status")
    private String status;
    @JsonProperty("paymentUrl")
    private String paymentUrl;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("updatedAt")
    private String updatedAt;
}
