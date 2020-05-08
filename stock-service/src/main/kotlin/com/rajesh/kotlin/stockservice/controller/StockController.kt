package com.rajesh.kotlin.stockservice.controller

import com.rajesh.kotlin.stockservice.model.StockPrice
import com.rajesh.kotlin.stockservice.service.PriceService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class StockController(val priceService: PriceService){

    @GetMapping(value = ["/stocks/{symbol}"],
                produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun priceFor(symbol: String)=  priceService.getPrice(symbol)

}