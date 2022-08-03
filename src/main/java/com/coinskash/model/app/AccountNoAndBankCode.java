package com.coinskash.model.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountNoAndBankCode {
    @JsonProperty("accountNumber")
    private String accountNumber;
    @JsonProperty("bankCode")
    private String bankCode;

}
