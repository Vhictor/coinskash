package com.coinskash.converter;

import com.coinskash.response.ConverterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class Converter {
@Autowired
private WebClient webClient;



    public Flux<ConverterResponse> crytoToFiat() {
        Flux<ConverterResponse> converterResponse = webClient.get()
                .uri("https://sandbox-api.payercoins.com/api/v1/live/payment/crypto/rate?cryptos=BTC,ETH,USDT,BUSD,USDC")
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToFlux(ConverterResponse.class);
                    } else {
                        return response.createException().flatMapMany(Mono::error);
                    }
                });

        return converterResponse;
    }
}
