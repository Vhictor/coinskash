package com.coinskash.model.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CryptoCurrency {
    @JsonProperty("cryptoCurrency")
    private String cryptoCurrency;
    @JsonProperty("quantity")
    private Double quantity;
}
