package com.example.airbnb.repository;

import com.example.airbnb.entity.Hotel;
import com.example.airbnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsRoomById(Long id);

    List<Room> findAllByHotelIs(Hotel hotel);

    List<Room> findAllByHotel_Id(Long hotelId);
}