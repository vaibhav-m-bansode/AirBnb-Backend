package com.example.airbnb.Mapper;

import com.example.airbnb.dto.GuestDto;
import com.example.airbnb.entity.Guest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GuestDtoMapper {
    Guest toEntity(GuestDto guestDto);
}