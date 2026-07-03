package com.example.airbnb.controller;

import com.example.airbnb.dto.HotelDTO;
import com.example.airbnb.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/hotels")
@AllArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping("/")
    public ResponseEntity<HotelDTO> createHotel(@RequestBody HotelDTO hotelDTO) {
        HotelDTO createdHotel = hotelService.createHotel(hotelDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);

    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long hotelId) {
        HotelDTO hotel = hotelService.getHotelById(hotelId);
        return ResponseEntity.status(HttpStatus.OK).body(hotel);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<HotelDTO> updateHotel(@PathVariable Long hotelId, @RequestBody HotelDTO hotelDTO) {
        HotelDTO updatedHotel = hotelService.updateHotelById(hotelId, hotelDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedHotel);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotelById(@PathVariable Long hotelId) {
        hotelService.deleteHotelById(hotelId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{hotelId}/activate")
    public ResponseEntity<Void> activateHotelById(@PathVariable Long hotelId) {
        hotelService.activateHotelById(hotelId);
        return ResponseEntity.ok().build();
    }

}
