package com.coinskash.repository;

import com.coinskash.model.payout.beneficiary.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary,Long> {
Beneficiary findByAppUserId(Long userId);
Optional<Beneficiary> findByIdAndAppUserId(Long id, Long userId);
}
