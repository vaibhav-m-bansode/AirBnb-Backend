package com.example.airbnb.dto;

import com.example.airbnb.entity.enums.Gender;

/**
 * DTO for {@link com.example.airbnb.entity.Guest}
 */
public record GuestDto(
        Long id,
        String name,
        Gender gender,
        Integer age
)  {
}