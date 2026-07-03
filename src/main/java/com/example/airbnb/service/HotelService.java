package com.example.airbnb.service;

import com.example.airbnb.dto.HotelDTO;
import com.example.airbnb.dto.HotelInfoDTO;
import org.springframework.http.ResponseEntity;

public interface HotelService {
    HotelDTO createHotel(HotelDTO hotelDTO);

    HotelDTO getHotelById(Long id);

    HotelDTO updateHotelById(Long hotelId, HotelDTO hotelDTO);

    void deleteHotelById(Long hotelId);

    void activateHotelById(Long hotelId);

    void isHotelExist(Long hotelId);

    ResponseEntity<HotelInfoDTO> getHotelInfoById(Long hotelId);
}
