package com.coinskash.payout;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PayoutResponseData {
    @JsonProperty("id")
    private String id;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("customerReference")
    private String customerReference;
    @JsonProperty("status")
    private String status;
    @JsonProperty("documentRequired")
    private String documentRequired;

}
