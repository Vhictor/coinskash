package com.coinskash.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentLink {
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
    @JsonProperty("websiteUrl")
    private String websiteUrl;
    @JsonProperty("logo")
    private String logo;
    @JsonProperty("type")
    private String type;
    @JsonProperty("network")
    private String network;
    @JsonProperty("twitterHandle")
    private String twitterHandle;
    @JsonProperty("instagramHandle")
    private String instagramHandle;
    @JsonProperty("status")
    private String status;
}
