package com.example.airbnb.dto;

import com.example.airbnb.entity.Hotel;

public record HotelPriceDto(
        Hotel Hotel,
        Double price)  {
}