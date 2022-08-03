package com.coinskash.repository;

import com.coinskash.model.payout.beneficiary.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary,Long> {
Beneficiary findByAppUserId(Long userId);
Optional<Beneficiary> findByIdAndAppUserId(Long id, Long userId);
}
