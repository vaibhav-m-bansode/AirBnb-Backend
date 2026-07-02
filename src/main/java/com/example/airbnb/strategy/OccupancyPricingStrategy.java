package com.example.airbnb.strategy;

import com.example.airbnb.entity.Inventory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class OccupancyPricingStrategy implements PricingStrategy {

    private final PricingStrategy pricingStrategy;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = pricingStrategy.calculatePrice(inventory);
        double ratio = inventory.getBookedCount() / inventory.getTotalCount();
        if (ratio > 0.8) {
            price = price.multiply(BigDecimal.valueOf(1.25));
        }
        return price;
    }
}
