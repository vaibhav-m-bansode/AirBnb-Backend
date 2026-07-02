package com.example.airbnb.controller;

import com.example.airbnb.dto.HotelDTO;
import com.example.airbnb.globalAdvice.ApiResponse;
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
    public ResponseEntity<ApiResponse<HotelDTO>> createHotel(@RequestBody HotelDTO hotelDTO) {
        HotelDTO createdHotel  = hotelService.createHotel(hotelDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true,createdHotel,null));

    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<ApiResponse<HotelDTO>>  getHotelById(@PathVariable Long hotelId) {
        HotelDTO hotel = hotelService.getHotelById(hotelId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true,hotel,null));
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<ApiResponse<HotelDTO>>  updateHotel(@PathVariable Long hotelId, @RequestBody HotelDTO hotelDTO) {
        HotelDTO updatedHotel  = hotelService.updateHotelById(hotelId,hotelDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true,updatedHotel,null));
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<ApiResponse<HotelDTO>> deleteHotelById(@PathVariable Long hotelId) {
        hotelService.deleteHotelById(hotelId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{hotelId}/activate")
    public ResponseEntity<Void> activateHotelById(@PathVariable Long hotelId) {
        hotelService.activateHotelById(hotelId);
        return ResponseEntity.ok().build();
    }

}
