package com.rajesh.kotlin.stockclient;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientAutoConfiguration {

    @Bean
    @Profile("sse")
    public StockClient webStockClient(WebClient webClient){
        return new WebStockClient(webClient);
    }

    @Bean
    @Profile("rsocket")
    public StockClient rSocketStockClient(RSocketRequester rSocketRequester){
        return new RSocketStockClient(rSocketRequester);
    }

    @Bean
    @ConditionalOnMissingBean
    public WebClient webClient(){
        return WebClient.builder().build();
    }

    @Bean
    public RSocketRequester rSocketRequester(RSocketRequester.Builder builder){
        return builder.connectTcp("localhost", 7000)
                .block();
    }
}
