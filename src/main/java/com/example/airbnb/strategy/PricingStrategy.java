package com.example.airbnb.strategy;

import com.example.airbnb.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {

    BigDecimal calculatePrice(Inventory inventory);
}
