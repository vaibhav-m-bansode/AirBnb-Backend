package com.example.airbnb.controller;

import com.example.airbnb.dto.RoomDTO;
import com.example.airbnb.globalAdvice.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<RoomDTO>>> getAllRoomsInHotel(@PathVariable Long hotelId) {
        List<RoomDTO> rooms = roomService.getAllRoomsInHotel(hotelId);
        return ResponseEntity.ok().body(new ApiResponse<>(true , rooms , null));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<ApiResponse<RoomDTO>> getRoomById(@PathVariable Long roomId, @PathVariable String hotelId) {
        RoomDTO room = roomService.getRoomById(roomId);
        return ResponseEntity.ok().body(new ApiResponse<>(true , room , null));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RoomDTO>> createNewRoom(@RequestBody RoomDTO roomDTO , @PathVariable Long hotelId) {
        RoomDTO createdRoom = roomService.CreateRoom(hotelId ,roomDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true , createdRoom , null));
    }
    @DeleteMapping("/{roomId}")
    public ResponseEntity<ApiResponse<RoomDTO>> deleteRoom(@PathVariable Long hotelId ,@PathVariable Long roomId) {
        roomService.deleteRoomById(roomId);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/")
    public ResponseEntity<ApiResponse<RoomDTO>> updateRoom(@RequestBody RoomDTO roomDTO) {
        RoomDTO room = roomService.UpdateRoom(roomDTO);
        return ResponseEntity.ok().body(new ApiResponse<>(true , room , null));
    }
}
