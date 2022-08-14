package com.coinskash.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AcceptCryptoPaymentResponse {
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private AcceptCryptoPaymentResponseData data;
    @JsonProperty("statusCode")
    private String statusCode;
    @JsonProperty("status")
    private String status;
}
