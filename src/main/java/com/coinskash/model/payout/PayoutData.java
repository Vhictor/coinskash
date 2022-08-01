package com.coinskash.model.payout;

import com.coinskash.model.payout.beneficiary.BeneficiaryPayout;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
public class PayoutData {
    @Value("${payout.source.currency}")
    private String sourceCurrency;
    @Value("${payout.destination.currency}")
    private String destinationCurrency;
    private BeneficiaryPayout beneficiary;
    @Value("${payout.payment.destination}")
    private String paymentDestination;
    private Double amount;
    @Value("${payout.business.id}")
    private String business;
    private String customerReference;
    @Value("${payout.description}")
    private String description;
//    @JsonIgnore
//    private String paymentScheme;
//    @JsonIgnore
//    private String quoteReference;
}
