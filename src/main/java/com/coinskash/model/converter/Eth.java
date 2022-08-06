package com.coinskash.model.converter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Eth {
    @JsonProperty("USD")
    private  String usd;
    @JsonProperty("NGN")
    private  String ngn;
}
