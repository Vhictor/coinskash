package com.coinskash.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResponseData {
    @JsonProperty("_id")
    private String _id;
    @JsonProperty("accountName")
    private String accountName;
    @JsonProperty("accountNumber")
    private String accountNumber;
    @JsonProperty("bankCode")
    private String bankCode;

}
