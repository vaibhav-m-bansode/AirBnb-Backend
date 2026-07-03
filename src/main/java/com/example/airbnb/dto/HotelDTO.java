package com.example.airbnb.dto;

import com.example.airbnb.entity.HotelContactInfo;

public record HotelDTO(
        Long id,
        String name,
        String city,
        String[] photos,
        String[] amenities,
        HotelContactInfo contactInfo,
        Boolean active
) {
}
