package com.coinskash.controller;

import com.coinskash.exception.GlobalRequestException;
import com.coinskash.model.ResponseDataFormat;
import com.coinskash.model.payout.beneficiary.Beneficiary;
import com.coinskash.repository.BeneficiaryRepository;
import com.coinskash.repository.UserRepository;
import com.coinskash.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BeneficiaryController {
    private BeneficiaryService beneficiaryService;
    private UserRepository userRepository;

    @Autowired
    public BeneficiaryController(BeneficiaryService beneficiaryService, UserRepository userRepository) {
        this.beneficiaryService = beneficiaryService;
        this.userRepository = userRepository;
    }

    @GetMapping("/users/{userId}/beneficiary")
    public ResponseEntity<Beneficiary> getBeneficiary(@PathVariable("userId") Long userId) {
      Beneficiary beneficiary = beneficiaryService.getBeneficiaryByUserId(userId);
      return new ResponseEntity<>(beneficiary,HttpStatus.OK );
    }

    @PostMapping("/users/{userId}/beneficiary")
    public ResponseEntity<Beneficiary> addBeneficiary(@PathVariable("userId") Long userId, @RequestBody Beneficiary beneficiary) {
        Optional<Beneficiary> optionalBeneficiary =  beneficiaryService.addBeneficiary(beneficiary,userId);
       if (optionalBeneficiary.isPresent()) {
           Beneficiary newBeneficiary = optionalBeneficiary.get();
           return new ResponseEntity<>(newBeneficiary,HttpStatus.OK );
       }
       throw new GlobalRequestException("400","Not able to add user data",  HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/users/{userId}/beneficiary/{beneficiaryId}")
    public ResponseEntity<ResponseDataFormat> deleteBeneficiary(@PathVariable("userId") Long userId, @PathVariable("beneficiaryId") Long beneficiaryId) {
        beneficiaryService.deleteBeneficiary(userId,beneficiaryId);
        return new ResponseEntity<>(new ResponseDataFormat(true,"Account has been deleted", HttpStatus.OK), HttpStatus.OK);

    }
}
