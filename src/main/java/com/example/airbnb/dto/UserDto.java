package com.example.airbnb.dto;

import com.example.airbnb.entity.User;

/**
 * DTO for {@link User}
 */
public record UserDto(Long id, String username) {
}