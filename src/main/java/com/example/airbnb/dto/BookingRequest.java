package com.example.airbnb.dto;

import java.time.LocalDate;

public record BookingRequest(
        Long hotelId,
        Long roomId,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        Integer roomsCount) {
}
