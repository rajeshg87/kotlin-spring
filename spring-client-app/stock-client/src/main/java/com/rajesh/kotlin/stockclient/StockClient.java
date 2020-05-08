package com.rajesh.kotlin.stockclient;

import reactor.core.publisher.Flux;

public interface StockClient {
    Flux<StockPrices> pricesFor(String symbol);
}
