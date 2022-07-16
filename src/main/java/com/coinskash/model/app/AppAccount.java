package com.coinskash.model.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AppAccount {
    @JsonProperty("accountNumber")
    private String accountNumber;
    @JsonProperty("bankCode")
    private String bankCode;
}
