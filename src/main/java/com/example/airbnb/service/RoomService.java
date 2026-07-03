package com.example.airbnb.service;

import com.example.airbnb.dto.RoomDTO;

import java.util.List;

public interface RoomService {
    RoomDTO CreateRoom(Long hotelId, RoomDTO roomDTO);

    RoomDTO UpdateRoom(RoomDTO roomDTO);

    RoomDTO FindRoomById(Long id);

    List<RoomDTO> getAllRoomsInHotel(Long hotelId);

    RoomDTO getRoomById(Long roomId);

    void deleteRoomById(Long roomId);
}
