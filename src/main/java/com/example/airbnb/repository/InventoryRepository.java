package com.example.airbnb.repository;

import com.example.airbnb.entity.Inventory;
import com.example.airbnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    void deleteInventoriesByDateAfterAndRoom(LocalDate dateAfter, Room room);

    void deleteByRoom(Room room);
}