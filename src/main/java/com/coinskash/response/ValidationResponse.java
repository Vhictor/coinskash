package com.coinskash.response;

import com.coinskash.validation.ValidationResponseData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidationResponse {
    @JsonProperty("success")
private boolean success;
    @JsonProperty("message")
private String message;
    @JsonProperty("data")
private ValidationResponseData data;
}
