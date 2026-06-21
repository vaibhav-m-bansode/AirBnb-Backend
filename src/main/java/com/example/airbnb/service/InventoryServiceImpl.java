package com.example.airbnb.service;

import com.example.airbnb.entity.Inventory;
import com.example.airbnb.entity.Room;
import com.example.airbnb.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private  final InventoryRepository inventoryRepository;

    @Override
    public void initializeRoomForYear(Room room) {

        LocalDate today = LocalDate.now();
        java.time.LocalDate endDate =  today.plusYears(1);

        LocalDate date = today;
        while(date.isBefore(endDate)) {
            Inventory inventory = Inventory.builder()
                    .room(room)
                    .date(date)
                    .hotel(room.getHotel())
                    .bookedCount(0)
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .city(room.getHotel().getCity())
                    .price(room.getPrice())
                    .build();
            inventoryRepository.save(inventory);
            date = date.plusDays(1);
        }

    }

    @Override
    public void deleteFutureInventories(Room room) {
        LocalDate today = LocalDate.now();
        inventoryRepository.deleteInventoriesByDateAfterAndRoom(today, room);
    }

    @Override
    public void deleteAllByRoom(Room room) {
        inventoryRepository.deleteByRoom(room);
    }
}
