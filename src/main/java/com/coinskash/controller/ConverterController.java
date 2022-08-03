package com.coinskash.controller;

import com.coinskash.converter.Converter;
import com.coinskash.model.app.CryptoCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ConverterController {
    @Autowired
    private Converter converter;
    @PostMapping(value = "/rates")
   public Converter getRate(@RequestBody CryptoCurrency cryptoCurrency){
       return converter.crytoToFiat(cryptoCurrency);
   }
    //rate end point;
   // https://sandbox-api.payercoins.com/api/v1/live/payment/crypto/rate?cryptos=BTC,ETH,USDT,BUSD,USDC
}
