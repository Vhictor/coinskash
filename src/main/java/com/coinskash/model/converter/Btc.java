package com.coinskash.model.converter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Btc {
    @JsonProperty("USD")
    private  String usd;
    @JsonProperty("NGN")
    private  String ngn;
}
