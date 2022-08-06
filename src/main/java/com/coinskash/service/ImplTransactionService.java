package com.coinskash.service;

import com.coinskash.crypto.TransactionRecord;
import com.coinskash.repository.TransactionRepository;
import com.coinskash.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ImplTransactionService implements TransactionService {
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;

    @Autowired
    public ImplTransactionService(
            TransactionRepository transactionRepository,
            UserRepository userRepository
    ) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }



    @Override
    public Optional<List<TransactionRecord>> getAllTransactionByUserId(Long userId) {

        return transactionRepository.findByAppUserId(userId);
    }

    @Override
    public Optional<TransactionRecord> saveTransactionRecord(Long userId, TransactionRecord transactionRecord) {
        return userRepository.findById(userId).map(user -> {
            transactionRecord.setAppUser(user);
            return transactionRepository.save(transactionRecord);
        });
    }

    @Override
    public Optional<TransactionRecord> updateTransactionPayoutStatus(Long userId, String transactionReference) {
        if (!userRepository.existsById(userId)) {
            log.error("UnAuthorized access");
        }
        return transactionRepository.findByTransactionReference(transactionReference).map(transactionRecord -> {
            transactionRecord.setHasPayout(true);
            return transactionRepository.save(transactionRecord);

        });
    }

    @Override
    public Optional<TransactionRecord> updateTransactionAcceptCryptoStatus(
            Long userId,
            String transactionReference,
            Double amountInCrypto,
            Double amountInFiat,
            String senderAddress,
            String coin
    ) {
        if (!userRepository.existsById(userId)) {
            log.warn("UnAuthorized access beware!");
        }
        return transactionRepository.findByTransactionReference(transactionReference).map(transactionRecord -> {
            transactionRecord.setHasPaidCrypto(true);
            transactionRecord.setAmountInCoin(amountInCrypto);
            transactionRecord.setAmountInFiat(amountInFiat);
            transactionRecord.setSenderAddress(senderAddress);
            transactionRecord.setCoin(coin);
            log.info("transaction record updated successfully, the payout scheduler will process the payout soon");
            return transactionRepository.save(transactionRecord);
        });
    }

    @Override
    public void deleteTransactionRecord(Long transactionId, Long userId) {
        transactionRepository.findByIdAndAppUserId(transactionId, userId).map(transactionRecord -> {
            transactionRepository.delete(transactionRecord);
            return true;
        });

    }
}
