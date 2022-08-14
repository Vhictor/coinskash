package com.coinskash.model.response;

import com.coinskash.payout.PayoutResponseData;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PayoutResponse {
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private PayoutResponseData data;

}
