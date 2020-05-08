package com.rajesh.kotlin.stockclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;

class WebStockClientIntegrationTest {

    private WebClient webClient = WebClient.builder().build();

    @Test
    void shouldWork(){
        WebStockClient webStockClient=new WebStockClient(webClient);
        String symbol = "SYMB";
        Flux<StockPrices> stockPricesFlux = webStockClient.pricesFor(symbol);
        Assertions.assertEquals(5, stockPricesFlux.take(5).count().block());
    }
}