package com.coinskash.repository;

import com.coinskash.model.payout.beneficiary.BeneficiaryPayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BeneficiaryPayoutRepository extends JpaRepository<BeneficiaryPayout,Long> {
    BeneficiaryPayout findByAppUserId(Long userId);
    Optional<BeneficiaryPayout> findByIdAndAppUserId(Long id, Long userId);
}
