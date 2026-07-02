package com.example.airbnb.service;

import com.example.airbnb.Exceptions.ResourceNotFoundException;
import com.example.airbnb.Mapper.HotelMapper;
import com.example.airbnb.Mapper.RoomMapper;
import com.example.airbnb.dto.HotelDTO;
import com.example.airbnb.dto.HotelInfoDTO;
import com.example.airbnb.dto.RoomDTO;
import com.example.airbnb.entity.Hotel;
import com.example.airbnb.entity.Room;
import com.example.airbnb.repository.HotelRepository;
import com.example.airbnb.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final RoomMapper roomMapper;


    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final InventoryService inventoryService;
    private final RoomRepository roomRepository;

    @Override
    public HotelDTO createHotel(HotelDTO hotelDTO) {
        log.info("creating hotel with hotelDTO {}", hotelDTO.toString());
        Hotel hotel = hotelMapper.toHotel(hotelDTO);
        hotel.setActive(false);
        hotel.setCreatedAt(LocalDateTime.now());
       hotel =  hotelRepository.save(hotel);
        return hotelMapper.toHotelDTO(hotel);
    }

    @Override
    public HotelDTO getHotelById(Long id) {
        log.info("getting hotel with id {}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("hotel with id " + id + " not found"));
        return hotelMapper.toHotelDTO(hotel);
    }

    @Override
    public HotelDTO updateHotelById(Long hotelId, HotelDTO hotelDTO) {

        log.info("updating hotel with hotelDTO {}", hotelDTO.toString());
        isHotelExist(hotelId);
        Hotel hotel =  hotelMapper.toHotel(hotelDTO);
        hotel.setId(hotelId);
        hotel = hotelRepository.save(hotel);
        return hotelMapper.toHotelDTO(hotel);
    }

    @Override
    public void deleteHotelById(Long hotelId) {
       Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(
               () -> new ResourceNotFoundException("hotel with id " + hotelId + " not found"));

        for(Room room : hotel.getRooms()) {
            roomRepository.delete(room);
            inventoryService.deleteAllByRoom(room);
        }
        hotelRepository.deleteById(hotelId);

    }

    @Override
    public void activateHotelById(Long hotelId) {
        isHotelExist(hotelId);
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        hotel.get().setActive(true);
        hotelRepository.save(hotel.get());

        for(Room room : hotel.get().getRooms()) {
            inventoryService.initializeRoomForYear(room);
        }
    }

    public void isHotelExist(Long hotelId){
        if(hotelRepository.existsHotelById(hotelId) ==  false) {
            throw new ResourceNotFoundException("hotel with id " + hotelId + " not found");
        }
    }

    @Override
    public ResponseEntity<HotelInfoDTO> getHotelInfoById(Long hotelId) {

        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(
                () -> new ResourceNotFoundException("hotel with id " + hotelId + " not found"));
        List<RoomDTO> rooms = hotel.getRooms().stream().map(roomMapper::toRoomDTO).collect(Collectors.toList());

        return  ResponseEntity.ok().body( new HotelInfoDTO(hotelMapper.toHotelDTO(hotel),rooms));
    }

}
