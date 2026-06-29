package com.example.airbnb.dto;

import com.example.airbnb.entity.*;
import com.example.airbnb.entity.enums.BookingStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link Booking}
 */
public record BookingDto(
        Long id,
        HotelDTO hotel,
        RoomDTO room,
        User user,
        Integer roomsCount,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        BookingStatus bookingStatus,
        List<GuestDto> guests
)  {
}