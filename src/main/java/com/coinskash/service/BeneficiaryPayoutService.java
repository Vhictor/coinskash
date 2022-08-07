package com.coinskash.service;

import com.coinskash.model.payout.beneficiary.BeneficiaryPayout;

import java.util.Optional;

public interface BeneficiaryPayoutService {
    Optional<BeneficiaryPayout> updatePayout(Long userId, BeneficiaryPayout beneficiaryPayout);
    BeneficiaryPayout getPayout(Long userId);
    void deletePayout(Long beneficiaryId, Long userId);

}
