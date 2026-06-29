package com.example.airbnb.service;

import com.example.airbnb.dto.HotelDTO;
import com.example.airbnb.dto.HotelSearchRequestDTO;
import com.example.airbnb.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {

    void initializeRoomForYear(Room room);


    void deleteFutureInventories(Room room);

    void deleteAllByRoom(Room room);

    Page<HotelDTO> searchHotels(HotelSearchRequestDTO hotelSearchRequestDTO);
}
