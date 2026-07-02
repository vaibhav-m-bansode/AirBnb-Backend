package com.example.airbnb.strategy;

import com.example.airbnb.entity.Inventory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PricingService {

    public BigDecimal calculatePrice(Inventory inventory) {
        PricingStrategy pricingStrategy = new BasePricingStrategy();
        pricingStrategy = new HolidayPricingStrategy(pricingStrategy);
        pricingStrategy = new SurgePricingStrategy(pricingStrategy);
        pricingStrategy = new OccupancyPricingStrategy(pricingStrategy);
        pricingStrategy = new UrgencyPricingStrategy(pricingStrategy);

        return pricingStrategy.calculatePrice(inventory);
    }
}
