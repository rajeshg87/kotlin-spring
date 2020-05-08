package com.rajesh.kotlin.stockclient;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

public class WebStockClient implements StockClient {

    private WebClient webClient;

    public WebStockClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<StockPrices> pricesFor(String symbol) {
        return webClient.get()
                .uri("localhost:8080/stocks/{symbol}", symbol)
                .retrieve()
                .bodyToFlux(StockPrices.class);
    }
}
