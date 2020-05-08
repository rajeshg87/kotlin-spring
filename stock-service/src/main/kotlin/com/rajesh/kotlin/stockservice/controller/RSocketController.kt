package com.rajesh.kotlin.stockservice.controller

import com.rajesh.kotlin.stockservice.model.StockPrice
import com.rajesh.kotlin.stockservice.service.PriceService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import reactor.core.publisher.Flux

@Controller
class RSocketController(val priceService: PriceService){

    @MessageMapping("stockPrices")
    fun priceFor(@PathVariable symbol: String) = priceService.getPrice(symbol)
}