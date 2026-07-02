package com.example.airbnb.repository;

import com.example.airbnb.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {


    Boolean existsHotelById(Long id);

    Hotel getHotelById(Long id);
}