package com.example.airbnb.service;

import com.example.airbnb.Mapper.HotelMapper;
import com.example.airbnb.dto.HotelPriceDto;
import com.example.airbnb.dto.HotelSearchRequestDTO;
import com.example.airbnb.entity.Inventory;
import com.example.airbnb.entity.Room;
import com.example.airbnb.repository.HotelMinPriceRepository;
import com.example.airbnb.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final HotelMapper hotelMapper;
    private final HotelMinPriceRepository hotelMinPriceRepository;

    @Override
    public void initializeRoomForYear(Room room) {

        LocalDate today = LocalDate.now();
        java.time.LocalDate endDate = today.plusYears(1);

        LocalDate date = today;
        while (date.isBefore(endDate)) {
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

    @Override
    public Page<HotelPriceDto> searchHotels(HotelSearchRequestDTO hotelSearchRequestDTO) {
        Pageable pageable = PageRequest.of(hotelSearchRequestDTO.page(), hotelSearchRequestDTO.limit());

        long dateCount = ChronoUnit.DAYS.between(
                hotelSearchRequestDTO.startDate(),
                hotelSearchRequestDTO.endDate()
        ) + 1; // inclusive count

        return hotelMinPriceRepository.findHotelsWithAvailableInventory(
                hotelSearchRequestDTO.city(),
                hotelSearchRequestDTO.startDate(),
                hotelSearchRequestDTO.endDate(),
                hotelSearchRequestDTO.roomsCount(),
                dateCount,
                pageable
        );
    }

}
