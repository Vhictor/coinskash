package com.coinskash.model.payout.beneficiary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Beneficiary {
    @JsonProperty("country")
    private String country;
    @JsonProperty("address")
    private Address address;
    @JsonProperty("document")
    private Document document;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("type")
    private String type;
    @JsonProperty("accountHolderName")
    private String accountHolderName;
    @JsonProperty("accountNumber")
    private String accountNumber;
    @JsonProperty("mobileMoneyCode")
    private String mobileMoneyCode;
    @JsonProperty("bankCode")
    private String bankCode;
    @JsonProperty("bankSwiftCode")
    private String bankSwiftCode;
    @JsonProperty("sortCode")
    private String sortCode;
    @JsonProperty("registrationNumber")
    private String registrationNumber;


}
