package com.coinskash.response;

import com.coinskash.model.fincra.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VerificationResponse {
    @JsonProperty("success")
private boolean success;
    @JsonProperty("message")
private String message;
    @JsonProperty("data")
private Account data;
}
