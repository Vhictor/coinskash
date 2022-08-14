package com.coinskash.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    @JsonProperty("id")
    private String id;
    @JsonProperty("customerName")
    private String customerName;
    @JsonProperty("customerEmail")
    private String customerEmail;
    @JsonProperty("customerPhone")
    private String customerPhone;
}
