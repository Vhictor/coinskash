package com.coinskash.model.payout.beneficiary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
    @JsonProperty("country")
    private String country;
    @JsonProperty("state")
    private String state;
    @JsonProperty("street")
    private String street;
    @JsonProperty("city")
    private String city;
    @JsonProperty("zip")
    private String zip;
}
