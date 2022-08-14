package com.coinskash.service;

import com.coinskash.exception.GlobalRequestException;
import com.coinskash.model.payout.beneficiary.Beneficiary;
import com.coinskash.repository.BeneficiaryRepository;
import com.coinskash.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ImplBeneficiaryService implements BeneficiaryService{

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  BeneficiaryRepository beneficiaryRepository;

    @Override
    public Beneficiary getBeneficiaryByUserId(Long userId) {
       try {
           return beneficiaryRepository.findByAppUserId(userId);
       }catch (Exception ex){
           throw new GlobalRequestException("404",ex.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }

    @Override
    public Optional<Beneficiary> addBeneficiary(Beneficiary beneficiary, Long userId) {
       if (userRepository==null){
           log.info("User repo seems to be null");
       }

        try {
            log.info("Trying to add beneficiary now");
           return userRepository.findById(userId).map(appUser -> {
               beneficiary.setAppUser(appUser);
               return beneficiaryRepository.save(beneficiary);
           });
       }catch (Exception ex){
           throw new GlobalRequestException("400",ex.getMessage(), HttpStatus.BAD_REQUEST);
       }
    }

    public void deleteBeneficiary(Long userId, Long beneficiaryId) {
        try { beneficiaryRepository.findByIdAndAppUserId(beneficiaryId, userId).map(beneficiary ->
        {
             beneficiaryRepository.delete(beneficiary);
            return true;
        });

    }catch (Exception ex){
            throw new GlobalRequestException("404",ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
