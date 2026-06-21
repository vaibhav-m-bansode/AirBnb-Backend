package com.example.airbnb.Mapper;

import com.example.airbnb.dto.HotelDTO;
import com.example.airbnb.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    Hotel toHotel(HotelDTO hotelDTO);

    HotelDTO toHotelDTO(Hotel hotel);
}
