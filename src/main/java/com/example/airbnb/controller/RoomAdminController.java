package com.example.airbnb.controller;

import com.example.airbnb.dto.RoomDTO;
import com.example.airbnb.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomAdminController {

    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRoomsInHotel(@PathVariable Long hotelId) {
        List<RoomDTO> rooms = roomService.getAllRoomsInHotel(hotelId);
        return ResponseEntity.ok().body(rooms);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long roomId, @PathVariable String hotelId) {
        RoomDTO room = roomService.getRoomById(roomId);
        return ResponseEntity.ok().body(room);
    }

    @PostMapping
    public ResponseEntity<RoomDTO> createNewRoom(@RequestBody RoomDTO roomDTO, @PathVariable Long hotelId) {
        RoomDTO createdRoom = roomService.CreateRoom(hotelId, roomDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoom);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<RoomDTO> deleteRoom(@PathVariable Long hotelId, @PathVariable Long roomId) {
        roomService.deleteRoomById(roomId);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/")
    public ResponseEntity<RoomDTO> updateRoom(@RequestBody RoomDTO roomDTO) {
        RoomDTO room = roomService.UpdateRoom(roomDTO);
        return ResponseEntity.ok().body(room);
    }
}
