package com.example.airbnb.dto;

import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RoomDTO(
        Long id,
        Long hotelId,
        String type,
        BigDecimal price,
        String[] photos,
        String[] amenities,
        Integer totalCount,
        Integer capacity
) {}

