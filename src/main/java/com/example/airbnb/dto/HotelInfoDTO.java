package com.example.airbnb.dto;

import java.util.List;

public record HotelInfoDTO(
        HotelDTO hotel,
        List<RoomDTO> rooms
) {
}
