package com.coinskash.payout;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayoutResponseData {
    @JsonProperty("id")
    private String id;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("customerReference")
    private String customerReference;
    @JsonProperty("status")
    private String status;
    @JsonProperty("documentsRequired")
    private String[] documentsRequired;

}
