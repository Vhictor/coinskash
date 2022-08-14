package com.coinskash.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class AcceptCryptoPaymentData {
    private String title;
    private String description;
    @JsonIgnore
    private String amount;
    private String type;
    private String logo;
    @JsonIgnore
    private String currency;
    private String redirect_url;

}
