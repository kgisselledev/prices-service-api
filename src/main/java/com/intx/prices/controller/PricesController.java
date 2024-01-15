package com.intx.prices.controller;

import com.intx.prices.model.Prices;
import com.intx.prices.service.PricesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PricesController {

    private static final Logger log = LoggerFactory.getLogger(PricesController.class);

    @Autowired
    private PricesService pricesService;

    @GetMapping("/prices")
    public ResponseEntity<List<Prices>> getPrices(
            @RequestParam Long brandId,
            @RequestParam Long productId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String endDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        ZonedDateTime startDateTime = ZonedDateTime.parse(startDate, formatter);
        ZonedDateTime endDateTime = ZonedDateTime.parse(endDate, formatter);

        List<Prices> prices = pricesService.getPrices(brandId, productId, startDateTime, endDateTime);
        return ResponseEntity.ok(prices);
    }
}
