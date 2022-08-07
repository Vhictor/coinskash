package com.coinskash.controller;

import com.coinskash.model.ResponseDataFormat;
import com.coinskash.model.payout.beneficiary.Beneficiary;
import com.coinskash.repository.BeneficiaryRepository;
import com.coinskash.repository.UserRepository;
import com.coinskash.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Beneficiary getBeneficiary(@PathVariable("userId") Long userId) {
        return beneficiaryService.getBeneficiaryByUserId(userId);
    }

    @PostMapping("/users/{userId}/beneficiary")
    public Optional<Beneficiary> addBeneficiary(@PathVariable("userId") Long userId, @RequestBody Beneficiary beneficiary) {
        return beneficiaryService.addBeneficiary(beneficiary,userId);
    }

    @DeleteMapping("/users/{userId}/beneficiary/{beneficiaryId}")
    public ResponseEntity<Object> deleteBeneficiary(@PathVariable("userId") Long userId, @PathVariable("beneficiaryId") Long beneficiaryId) {
        beneficiaryService.deleteBeneficiary(userId,beneficiaryId);
        return ResponseEntity.ok().build();
    }
}
