package com.example.airbnb.dto;

import com.example.airbnb.entity.User;
import com.example.airbnb.entity.enums.Gender;

import java.io.Serializable;

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