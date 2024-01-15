package com.intx.prices.repository;

import com.intx.prices.model.Prices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PricesRepository extends JpaRepository<Prices, Long> {
    List<Prices> findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
            Long brandId, Long productId, LocalDateTime startDate, LocalDateTime endDate);
}
