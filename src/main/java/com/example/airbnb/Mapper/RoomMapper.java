package com.example.airbnb.Mapper;

import com.example.airbnb.dto.RoomDTO;
import com.example.airbnb.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(target = "hotelId", source = "hotel.id")
    RoomDTO toRoomDTO(Room room);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "hotel", ignore = true)
    Room toRoom(RoomDTO roomDTO);
}
