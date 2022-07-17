package com.coinskash.config;

import com.coinskash.response.ConverterResponse;
import io.netty.channel.ChannelOption;
import org.junit.jupiter.api.Test;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

import static java.lang.String.format;

class WebClientConfigTest {
    @Test
    void test() {
        var host = "localhost";
        var endpoint = "/testrates";
        var port = 8080;
        var timeout = Duration.ofSeconds(3);


        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000);
        WebClient webClient = WebClient.builder()
                //.baseUrl("https://sandbox-api.payercoins.com/api/v1/live/payment/crypto/rate?cryptos=BTC,ETH,USDT,BUSD,USDC")
                .clientConnector(new ReactorClientHttpConnector(httpClient)).build();

        webClient.get().uri("https://sandbox-api.payercoins.com/api/v1/live/payment/crypto/rate?cryptos=BTC,ETH,USDT,BUSD,USDC").retrieve().bodyToMono(ConverterResponse.class).block();
    }

}