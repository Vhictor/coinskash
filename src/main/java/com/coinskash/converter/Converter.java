package com.coinskash.converter;

import com.coinskash.exception.GlobalRequestException;
import com.coinskash.model.app.CryptoCurrency;
import com.coinskash.model.response.ConverterResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Data
public class Converter {
    private double currency;
    private double rate;
    @JsonIgnore
    @Autowired
    private WebClient webClient;


    public Converter crytoToFiat(CryptoCurrency cryptoCurrency) {
        Converter converter = new Converter();
        try {
            ConverterResponse converterResponse = webClient.get()
                    .uri("https://sandbox-api.payercoins.com/api/v1/live/payment/crypto/rate?cryptos=BTC,ETH,USDT,BUSD,USDC")
                    .accept(MediaType.APPLICATION_JSON)
                    .exchangeToFlux(response -> {
                        if (response.statusCode().equals(HttpStatus.OK)) {
                            return response.bodyToFlux(ConverterResponse.class);
                        } else {
                            return response.createException().flatMapMany(Mono::error);
                        }
                    }).blockFirst();

        switch (cryptoCurrency.getCryptoCurrency()) {
            case "usdt":
                converter.setRate(Double.parseDouble(converterResponse.getRate().getUsdc().getNgn()));

                break;
            case "busd":
                converter.setRate(Double.parseDouble(converterResponse.getRate().getBusd().getNgn()));

                break;
            default:
                converter.setRate(Double.parseDouble(converterResponse.getRate().getUsdt().getNgn()));

        }
        converter.setCurrency(converter.rate * cryptoCurrency.getQuantity());
        return converter;
        }catch (Exception ex){
            throw  new GlobalRequestException("400",ex.getMessage(),HttpStatus.BAD_REQUEST);

        }
    }
}
