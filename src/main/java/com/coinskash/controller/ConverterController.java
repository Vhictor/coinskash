package com.coinskash.controller;

import com.coinskash.converter.Converter;
import com.coinskash.response.ConverterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping
public class ConverterController {
    @Autowired
    private Converter converter;
    @GetMapping(value = "rates")
   public Flux<ConverterResponse> getRate(){
       return converter.crytoToFiat();
   }
    //rate end point;
   // https://sandbox-api.payercoins.com/api/v1/live/payment/crypto/rate?cryptos=BTC,ETH,USDT,BUSD,USDC
}
