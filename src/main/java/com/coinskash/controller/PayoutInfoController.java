package com.coinskash.controller;

import com.coinskash.model.payout.beneficiary.BeneficiaryPayout;
import com.coinskash.repository.BeneficiaryPayoutRepository;
import com.coinskash.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PayoutInfoController {
    private BeneficiaryPayoutRepository beneficiaryPayoutRepository;
    private UserRepository userRepository;

    @PostMapping("/users/{userId}/beneficiaryPayout")
    public Optional<BeneficiaryPayout> addBeneficiary(@PathVariable("userId") Long userId, @RequestBody BeneficiaryPayout beneficiaryPayout) {
        return userRepository.findById(userId).map(appUser -> {
            beneficiaryPayout.setAppUser(appUser);
            return beneficiaryPayoutRepository.save(beneficiaryPayout);
        });
    }
    @GetMapping("/users/{userId}/beneficiaryPayout")
    public BeneficiaryPayout getBeneficiary(@PathVariable("userId") Long userId) {
        return beneficiaryPayoutRepository.findByAppUserId(userId);
    }
    @DeleteMapping("/users/{userId}/beneficiaryPayout/{beneficiaryId}")
    public ResponseEntity<Object> deleteBeneficiary(@PathVariable("userId") Long userId, @PathVariable("beneficiaryId") Long beneficiaryId) {
        return beneficiaryPayoutRepository.findByIdAndAppUserId(beneficiaryId, userId).map(beneficiary ->
        {
            beneficiaryPayoutRepository.delete(beneficiary);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.badRequest().build());

    }


}
