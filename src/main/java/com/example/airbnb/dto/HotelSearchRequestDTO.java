package com.example.airbnb.dto;

import lombok.Data;

import java.time.LocalDate;


public record HotelSearchRequestDTO(
        String city,
        LocalDate startDate,
        LocalDate endDate,
        int roomsCount,
        Integer page,
        Integer limit
) {
}
