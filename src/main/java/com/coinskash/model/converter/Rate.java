package com.coinskash.model.converter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Rate {

    @JsonProperty("BTC")
    private Btc btc;
    @JsonProperty("BUSD")
    private Busd busd;
    @JsonProperty("ETH")
    private Eth eth;
    @JsonProperty("USDC")
    private Usdc usdc;
    @JsonProperty("USDT")
    private Usdt usdt;

}
