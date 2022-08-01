package com.coinskash.service;

import com.coinskash.crypto.TransactionRecord;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Optional<List<TransactionRecord>> getAllTransactionByUserId(Long userId);

    Optional<TransactionRecord> saveTransactionRecord(Long id, TransactionRecord transactionRecord);

    Optional<TransactionRecord> updateTransactionPayoutStatus(Long userId, String transactionReference);

    Optional<TransactionRecord> updateTransactionAcceptCryptoStatus(
            Long userId,
            String transactionReference,
            Double amountInCrypto,
            Double amountInFiat,
            String senderAddress,
            String coin
    );

    void deleteTransactionRecord(Long transactionId, Long userId);


}
