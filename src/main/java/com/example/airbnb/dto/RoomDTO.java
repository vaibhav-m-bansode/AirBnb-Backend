package com.example.airbnb.dto;

import java.math.BigDecimal;

public record RoomDTO(
        Long id,
        Long hotelId,
        String type,
        BigDecimal price,
        String[] photos,
        String[] amenities,
        Integer totalCount,
        Integer capacity
) {
}

