package com.example.airbnb.service;

import com.example.airbnb.entity.Room;

public interface InventoryService {

    void initializeRoomForYear(Room room);


    void deleteFutureInventories(Room room);

    void deleteAllByRoom(Room room);
}
