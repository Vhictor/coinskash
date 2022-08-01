package com.coinskash.validation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankData {
    @JsonProperty("name")
    String name;
    @JsonProperty("code")
    String code;
}