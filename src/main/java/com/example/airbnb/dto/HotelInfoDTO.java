package com.example.airbnb.dto;

import com.example.airbnb.entity.Room;

import java.util.List;

public record HotelInfoDTO(
        HotelDTO hotel,
        List<RoomDTO> rooms
) {
}
