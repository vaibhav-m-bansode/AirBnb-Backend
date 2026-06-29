package com.example.airbnb.controller;


import com.example.airbnb.dto.HotelDTO;
import com.example.airbnb.dto.HotelInfoDTO;
import com.example.airbnb.dto.HotelSearchRequestDTO;
import com.example.airbnb.service.HotelService;
import com.example.airbnb.service.HotelServiceImpl;
import com.example.airbnb.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelBrowseController {
    private final InventoryService inventoryService;
    private final HotelService hotelService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelDTO>> getHotelsByRoom(@RequestBody HotelSearchRequestDTO hotelSearchRequestDTO) {

        Page<HotelDTO> page = inventoryService.searchHotels(hotelSearchRequestDTO);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDTO> getHotelInfo(@PathVariable Long hotelId) {
        return hotelService.getHotelInfoById(hotelId);
    }
}
