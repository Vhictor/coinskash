package com.coinskash.controller;

import com.coinskash.model.payout.beneficiary.Beneficiary;
import com.coinskash.repository.BeneficiaryRepository;
import com.coinskash.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BeneficiaryController {
    private BeneficiaryRepository beneficiaryRepository;
    private UserRepository userRepository;

    @GetMapping("/users/{userId}/beneficiary")
    public Beneficiary getBeneficiary(@PathVariable("userId") Long userId) {
        return beneficiaryRepository.findByAppUserId(userId);
    }

    @PostMapping("/users/{userId}/beneficiary")
    public Optional<Beneficiary> addBeneficiary(@PathVariable("userId") Long userId, @RequestBody Beneficiary beneficiary) {
        return userRepository.findById(userId).map(appUser -> {
            beneficiary.setAppUser(appUser);
            return beneficiaryRepository.save(beneficiary);
        });
    }

    @DeleteMapping("/users/{userId}/beneficiary/{beneficiaryId}")
    public ResponseEntity<Object> deleteBeneficiary(@PathVariable("userId") Long userId, @PathVariable("beneficiaryId") Long beneficiaryId) {
        return beneficiaryRepository.findByIdAndAppUserId(beneficiaryId, userId).map(beneficiary ->
        {
            beneficiaryRepository.delete(beneficiary);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.badRequest().build());

    }
}
