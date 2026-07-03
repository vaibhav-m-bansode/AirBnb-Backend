package com.example.airbnb.service;

import com.example.airbnb.Exceptions.ResourceNotFoundException;
import com.example.airbnb.Exceptions.UnAuthorizedException;
import com.example.airbnb.Mapper.RoomMapper;
import com.example.airbnb.dto.RoomDTO;
import com.example.airbnb.entity.Hotel;
import com.example.airbnb.entity.Room;
import com.example.airbnb.entity.User;
import com.example.airbnb.repository.HotelRepository;
import com.example.airbnb.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final HotelRepository hotelRepository;
    private final InventoryServiceImpl inventoryService;

    public RoomDTO FindRoomById(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room not found with id " + id));
        return roomMapper.toRoomDTO(room);
    }

    @Override
    public RoomDTO getRoomById(Long roomId) {
        return null;
    }

    @Override
    @Transactional
    public RoomDTO CreateRoom(Long hotelId, RoomDTO roomDTO) {
        log.info("Creating room with hotelId {} and roomId {}", hotelId, roomDTO);
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id " + hotelId));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!user.equals(hotel.getOwner())) {
            throw new UnAuthorizedException("User is not owner of this hotel with id " + user.getId());
        }
        Room room = roomMapper.toRoom(roomDTO);
        room.setHotel(hotel);

        Room savedRoom = roomRepository.save(room);

        if (hotel.getActive()) {
            inventoryService.initializeRoomForYear(savedRoom);
        }
        return roomMapper.toRoomDTO(savedRoom);
    }

    public RoomDTO UpdateRoom(RoomDTO roomDTO) {
        Room room = roomMapper.toRoom(roomDTO);
        room.setId(roomDTO.id());
        Room savedRoom = roomRepository.save(room);
        return roomMapper.toRoomDTO(savedRoom);
    }

    @Override
    public List<RoomDTO> getAllRoomsInHotel(Long hotelId) {
        log.info("Getting all rooms in hotel with id {}", hotelId);
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id " + hotelId));

        List<Room> rooms = hotel.getRooms();
        return rooms.stream().map(roomMapper::toRoomDTO).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void deleteRoomById(Long roomId) {
        log.info("Deleting room with id {}", roomId);
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not found with id " + roomId));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!user.equals(room.getHotel().getOwner())) {
            throw new UnAuthorizedException("User is not owner of this room with id " + user.getId());
        }
        inventoryService.deleteAllByRoom(room);
        roomRepository.deleteById(roomId);

    }


}
