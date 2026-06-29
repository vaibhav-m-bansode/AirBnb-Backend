package com.example.airbnb.Mapper;

import com.example.airbnb.dto.BookingDto;
import com.example.airbnb.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {
    BookingDto toDto(Booking booking);
}