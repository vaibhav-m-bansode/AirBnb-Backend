package com.example.airbnb.service;

import com.example.airbnb.entity.Hotel;
import com.example.airbnb.entity.HotelMinPrice;
import com.example.airbnb.entity.Inventory;
import com.example.airbnb.repository.HotelMinPriceRepository;
import com.example.airbnb.repository.HotelRepository;
import com.example.airbnb.repository.InventoryRepository;
import com.example.airbnb.strategy.PricingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PricingUpdateService {

    //Schedular to update the inventory and HotelMinPrice tables every hour

    private final HotelRepository hotelRepository;
    private final InventoryRepository inventoryRepository;
    private final PricingService pricingService;
    private final HotelMinPriceRepository hotelMinPriceRepository;


    //@Scheduled(cron = "0 0/5 * * * *")
    public void updatePrices() {
        log.info("Updating prices for hotels!");
        int page = 0;
        int batchSize = 100;

        while (true) {
            Page<Hotel> hotelPage = hotelRepository.findAll(PageRequest.of(page, batchSize));
            if (hotelPage.isEmpty()) {
                break;
            }
            page++;

            hotelPage.getContent().forEach(this::updateHotelPrices);
        }

    }

    private void updateHotelPrices(Hotel hotel) {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(1);

        List<Inventory> inventoryList = inventoryRepository.findByHotelAndDateBetween(hotel, startDate, endDate);

        updateInventoryPrices(inventoryList);
        updateHotelMinPrice(hotel, inventoryList, startDate, endDate);
    }

    private void updateInventoryPrices(List<Inventory> inventory) {
        inventory.forEach(inventoryItem -> {
            BigDecimal dynamicPrice = pricingService.calculatePrice(inventoryItem);
            inventoryItem.setPrice(dynamicPrice);
        });
        inventoryRepository.saveAll(inventory);
    }

    private void updateHotelMinPrice(Hotel hotel, List<Inventory> inventoryList, LocalDate startDate, LocalDate endDate) {

        Map<LocalDate, BigDecimal> mp = new HashMap<>();

        inventoryList.forEach(inventoryItem -> {
            BigDecimal price = inventoryItem.getPrice();
            BigDecimal previousPrice = mp.get(inventoryItem.getDate());

            if (previousPrice == null) {
                // No previous price, just put current
                mp.put(inventoryItem.getDate(), price);
            } else {
                // Keep the minimum of the two
                mp.put(inventoryItem.getDate(), price.min(previousPrice));
            }
        });


        List<HotelMinPrice> hotelMinPriceList = new ArrayList<>();
        mp.forEach((date, price) -> {
            HotelMinPrice hotelMinPrice = hotelMinPriceRepository.findByHotelAndDate(hotel, date)
                    .orElse(new HotelMinPrice(hotel, date));
            hotelMinPrice.setPrice(price);
            hotelMinPriceList.add(hotelMinPrice);
        });

        hotelMinPriceRepository.saveAll(hotelMinPriceList);
    }

}
