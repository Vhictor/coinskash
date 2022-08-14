package com.coinskash.controller;

import com.coinskash.model.payout.beneficiary.BeneficiaryPayout;
import com.coinskash.repository.BeneficiaryPayoutRepository;
import com.coinskash.repository.UserRepository;
import com.coinskash.service.BeneficiaryPayoutService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PayoutInfoController {
    @Autowired
    private BeneficiaryPayoutService beneficiaryPayoutService;
    @Autowired
    private UserRepository userRepository;

    @Parameters
    @PostMapping("/users/{userId}/beneficiaryPayout")
    public Optional<BeneficiaryPayout> addBeneficiary(@PathVariable("userId") Long userId, @RequestBody BeneficiaryPayout beneficiaryPayout) {
        return beneficiaryPayoutService.updatePayout(userId,beneficiaryPayout);
    }


//    @Parameters({@Parameter(name = "Authorization", value = "Access Token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")})
    @GetMapping("/users/{userId}/beneficiaryPayout")
    public BeneficiaryPayout getBeneficiary(@PathVariable("userId") Long userId) {
       return beneficiaryPayoutService.getPayout(userId);
    }


    @DeleteMapping("/users/{userId}/beneficiaryPayout/{beneficiaryId}")
    public ResponseEntity<Object> deleteBeneficiary(@PathVariable("userId") Long userId, @PathVariable("beneficiaryId") Long beneficiaryId) {
        beneficiaryPayoutService.deletePayout(beneficiaryId,userId);
        return ResponseEntity.ok().build();
    }


}
