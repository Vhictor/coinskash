package com.coinskash.model.converter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usdt {
    @JsonProperty("USD")
    private  String usd;
    @JsonProperty("NGN")
    private  String ngn;
}
