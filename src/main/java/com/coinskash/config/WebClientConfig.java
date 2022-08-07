package com.coinskash.config;

import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {



    private String iswCoreBaseUrl="";

    @Bean
    public WebClient iswCoreWebClient() {
        HttpClient httpClient = HttpClient.create()
                .wiretap(true);
        return WebClient.builder().baseUrl(iswCoreBaseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

}
