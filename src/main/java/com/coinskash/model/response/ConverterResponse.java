package com.coinskash.model.response;

import com.coinskash.model.converter.Rate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class ConverterResponse implements Serializable {
    @JsonProperty("rates")
    private Rate rate;
    @JsonProperty("message")
    private String message;
}
