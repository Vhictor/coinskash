package com.coinskash.service;

import com.coinskash.model.payout.beneficiary.Beneficiary;

import java.util.Optional;

public interface BeneficiaryService {
    Beneficiary getBeneficiaryByUserId(Long id);

    Optional<Beneficiary> addBeneficiary(Beneficiary beneficiary, Long userId);

    void deleteBeneficiary(Long userId,Long beneficiaryId);
}
