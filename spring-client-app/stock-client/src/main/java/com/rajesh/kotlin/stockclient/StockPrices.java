package com.rajesh.kotlin.stockclient;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockPrices {
    private String name;
    private Double price;
    private LocalDateTime time;
}
