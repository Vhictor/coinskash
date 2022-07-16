package com.coinskash.payout;

import com.coinskash.model.payout.PayoutData;
import com.coinskash.response.PayoutResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Component
public class Payout {
    @Autowired
    private WebClient webClient;
    public Mono<PayoutResponse> payout(PayoutData payoutData){
        Mono<PayoutResponse> payoutResponse = webClient.post()
                .uri("https://sandboxapi.fincra.com/disbursements/payouts")
                .accept(MediaType.APPLICATION_JSON)
                .header("api-key","Bvgt8zLovHrjt4cRwkSSxNa5ppQ9xazf")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(payoutData), PayoutData.class)
                .exchangeToMono(clientResponse -> {
                    if(clientResponse.statusCode().equals(HttpStatus.OK)){
                        return clientResponse.bodyToMono(PayoutResponse.class);
                    }else {
                        return clientResponse.createException().flatMap(Mono::error);
                    }
                });
        return payoutResponse;

    }
}
