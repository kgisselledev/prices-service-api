package com.intx.prices.service;

import com.intx.prices.model.Prices;
import com.intx.prices.repository.PricesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class PricesService {
    private static final Logger log = LoggerFactory.getLogger(PricesService.class);

    @Autowired
    private PricesRepository priceRepository;

    public List<Prices> getPrices(Long brandId, Long productId, ZonedDateTime startDateTime, ZonedDateTime endDateTime) {
        log.info("Buscando precios para brandId: {}, productId: {}, startDate: {}, endDate: {}", brandId, productId, startDateTime, endDateTime);

        LocalDateTime startOfDay = startDateTime.toLocalDateTime().with(LocalTime.MIN);
        LocalDateTime endOfDay = endDateTime.toLocalDateTime().with(LocalTime.MAX);

        List<Prices> prices = priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
                brandId, productId, startOfDay, endOfDay);

        log.info("Resultados encontrados: {}", prices);
        return prices;
    }
}
