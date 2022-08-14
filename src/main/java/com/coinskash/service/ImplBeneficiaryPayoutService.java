package com.coinskash.service;

import com.coinskash.exception.GlobalRequestException;
import com.coinskash.model.payout.beneficiary.BeneficiaryPayout;
import com.coinskash.repository.BeneficiaryPayoutRepository;
import com.coinskash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ImplBeneficiaryPayoutService implements BeneficiaryPayoutService{
    @Autowired
    BeneficiaryPayoutRepository beneficiaryPayoutRepository;
    UserRepository userRepository;

    @Override
    public Optional<BeneficiaryPayout> updatePayout(Long userId, BeneficiaryPayout beneficiaryPayout) {
        try {
            return userRepository.findById(userId).map(appUser -> {
                beneficiaryPayout.setAppUser(appUser);
                return beneficiaryPayoutRepository.save(beneficiaryPayout);
            });
        }catch (Exception ex){
            throw new GlobalRequestException("404",ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public BeneficiaryPayout getPayout(Long userId) {
        try {
            return beneficiaryPayoutRepository.findByAppUserId(userId);
        }catch (Exception ex){
            throw new GlobalRequestException("404",ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void deletePayout(Long beneficiaryId, Long userId) {
       try {
            beneficiaryPayoutRepository.findByIdAndAppUserId(beneficiaryId, userId).map(beneficiary ->
           {
               beneficiaryPayoutRepository.delete(beneficiary);
               return true;
           });
       }catch (Exception ex){
           throw new GlobalRequestException("404",ex.getMessage(),HttpStatus.BAD_REQUEST);

       }
    }
}
