package com.rajesh.kotlin.stockclient;

import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;

public class RSocketStockClient implements StockClient {
    private RSocketRequester rSocketRequester;

    public RSocketStockClient(RSocketRequester rSocketRequester) {
        this.rSocketRequester = rSocketRequester;
    }

    @Override
    public Flux<StockPrices> pricesFor(String symbol) {
        return rSocketRequester.route("stockPrices")
                .data(symbol)
                .retrieveFlux(StockPrices.class)
                .log("rsocket");
    }
}
