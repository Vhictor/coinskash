package com.coinskash.model.payout.beneficiary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Document {
    @JsonProperty("type")
    private String type;
    @JsonProperty("issueCountryCode")
    private String issueCountryCode;
    @JsonProperty("number")
    private String number;
    @JsonProperty("issuedBy")
    private String issuedBy;
    @JsonProperty("issuedDate")
    private String issuedDate;
    @JsonProperty("expirationDate")
    private String expirationDate;
}
