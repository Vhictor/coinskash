package com.coinskash.controller;

import com.coinskash.model.payout.PayoutData;
import com.coinskash.payout.Payout;
import com.coinskash.response.PayoutResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class PayoutController {
    @Autowired
    private Payout payout;
    @PostMapping(value = "payout")
    private Mono<PayoutResponse> fincraPayout(@RequestBody PayoutData payoutData) {
        return payout.payout(payoutData);
    }
}
