package com.coinskash.service;

import com.coinskash.model.response.TransactionRecord;
import com.coinskash.exception.GlobalRequestException;
import com.coinskash.repository.TransactionRepository;
import com.coinskash.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        try {
            return transactionRepository.findByAppUserId(userId);
        }catch (Exception ex){
            log.error(ex.getMessage());
            throw new GlobalRequestException("404","Not found",HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Optional<TransactionRecord> saveTransactionRecord(Long userId, TransactionRecord transactionRecord) {
        try {
            return userRepository.findById(userId).map(user -> {
                transactionRecord.setAppUser(user);
                return transactionRepository.save(transactionRecord);
            });
        }catch (Exception ex){
            log.error(ex.getMessage());
            throw new GlobalRequestException("400",ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Optional<TransactionRecord> updateTransactionPayoutStatus(String transactionReference) {

        try {
            return transactionRepository.findByTransactionReferenceAndHasPaidCrypto(transactionReference, true).map(transactionRecord -> {
                transactionRecord.setHasPayout(true);
                return transactionRepository.save(transactionRecord);

            });
        }catch (Exception ex){
            log.error(ex.getMessage());
            throw new GlobalRequestException("400", ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Optional<TransactionRecord> updateTransactionAcceptCryptoStatus(
            String transactionReference,
            Double amountInCrypto,
            Double amountInFiat,
            String senderAddress,
            String coin
    ) {
        return transactionRepository.findByTransactionReferenceAndHasPaidCrypto(transactionReference, false).map(transactionRecord -> {
            transactionRecord.setHasPaidCrypto(true);
            transactionRecord.setAmountInCoin(amountInCrypto);
            transactionRecord.setAmountInFiat(amountInFiat);
            transactionRecord.setSenderAddress(senderAddress);
            transactionRecord.setCoin(coin);
            log.info("transaction record updated successfully, the payout scheduler will process the payout soon");
            try {
                return transactionRepository.save(transactionRecord);
            } catch (Exception ex) {
                log.info(ex.getMessage());
                throw new GlobalRequestException("409",ex.getMessage(), HttpStatus.BAD_REQUEST);
            }
        });
    }

    @Override
    public void deleteTransactionRecord(Long transactionId, Long userId) {
        try {
            transactionRepository.findByIdAndAppUserId(transactionId, userId).map(transactionRecord -> {
                transactionRepository.delete(transactionRecord);
                return true;
            });
        }catch (Exception ex){
            log.error(ex.getMessage());
            throw new GlobalRequestException("400",ex.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @Override
    public Optional<TransactionRecord> getUnpaidTransaction(){
        return transactionRepository.
                findByHasPaidCryptoAndHasPayout(true, false).
                stream().
                findFirst();
    }
}
