package com.example.airbnb.repository;

import com.example.airbnb.entity.Hotel;
import com.example.airbnb.entity.Inventory;
import com.example.airbnb.entity.Room;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    void deleteInventoriesByDateAfterAndRoom(LocalDate dateAfter, Room room);

    void deleteByRoom(Room room);

    @Query("""
            SELECT DISTINCT i.hotel
            FROM Inventory i
            WHERE i.city = :city
              AND i.date BETWEEN :startDate AND :endDate
              AND i.closed = false
              AND (i.totalCount - i.bookedCount) >= :roomCount
            GROUP BY i.hotel, i.room
            HAVING COUNT(i.date) = :dateCount
            """)
    Page<Hotel> findHotelsWithAvailableInventory(
            @Param("city") String city,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("roomCount") Integer roomCount,
            @Param("dateCount") Long dateCount,
            Pageable pageable
    );

    @Query(""" 
        SELECT i
        FROM Inventory i
        WHERE i.room.id =:roomId
        and i.date between :startDate and :endDate
        and i.closed = false
        and (i.totalCount-i.bookedCount) >= :roomsCount
        """)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Inventory> findAndLockAvailableInventory(Long roomId, LocalDate startDate, LocalDate endDate, Integer roomsCount);

    List<Inventory> findByHotelAndDateBetween(Hotel hotel, LocalDate startDate, LocalDate endDate);
}