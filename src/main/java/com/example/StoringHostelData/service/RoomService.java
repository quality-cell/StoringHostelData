package com.example.StoringHostelData.service;

import com.example.StoringHostelData.dto.RoomDTO;
import com.example.StoringHostelData.entity.Guest;
import com.example.StoringHostelData.entity.Room;
import com.example.StoringHostelData.repository.GuestRepository;
import com.example.StoringHostelData.repository.RoomRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, GuestRepository guestRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
    }

    public List<RoomDTO> getAllRooms() {
        List<RoomDTO> res = new ArrayList<>();
        roomRepository.findAll().forEach(room -> res.add(new RoomDTO(room)));

        if (res.isEmpty()) {
            throw new EntityNotFoundException("Комнат не найдено");
        }

        return res;
    }

    public RoomDTO getRoomById(Integer roomId) {
        Optional<Room> room = roomRepository.findById(roomId);

        if (room.isPresent()) {
            return new RoomDTO(room.get());
        }

        throw new EntityNotFoundException("Комната с id: " + roomId + " не найдена");
    }

    public void addRoom(Room room) {
        roomRepository.save(room);
    }

    public void updateRoom(Integer id, RoomDTO roomDTO) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        List<Guest> guestOptional = guestRepository.findByRoomId(id);

        if (roomOptional.isEmpty()) {
            throw new EntityNotFoundException("Комната с id: " + id + " не найдена");
        }

        if (!guestOptional.isEmpty()) {
            throw new EntityExistsException("Комната с id: " + id + " ещё занята и ее нельзя обновить");
        }

        Room room = roomOptional.get();
        room.setRoomType(roomDTO.getRoomType());
        room.setComfortType(roomDTO.getComfortType());
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setNumberOfSeats(roomDTO.getNumberOfSeats());
        room.setDateOfChange(LocalDate.now());

        roomRepository.save(room);
    }

    public void deleteRoom(Integer roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        List<Guest> guestList = guestRepository.findByRoomId(roomId);

        if (roomOptional.isEmpty()) {
            throw new EntityNotFoundException("Комната с id: " + roomId + " не найдена");
        }

        if (!guestList.isEmpty()) {
            throw new EntityExistsException("Комната с id: " + roomId + " ещё занята и ее нельзя удалять");
        }

        roomRepository.deleteById(roomId);
    }
}
