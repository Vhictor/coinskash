package com.coinskash.repository;

import com.coinskash.crypto.TransactionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TransactionRepository extends JpaRepository<TransactionRecord,Long> {
    Optional<List<TransactionRecord>> findByAppUserId(Long userId);
    Optional<TransactionRecord> findByIdAndAppUserId(Long id,Long userId);
    Optional<TransactionRecord> findByTransactionReferenceAndHasPaidCrypto(String transactionReference,Boolean hasPaidCrypto);
    Optional<TransactionRecord> findByHasPaidCryptoAndHasPayout(Boolean hasPaidCrypto,Boolean hasPayout);
}
