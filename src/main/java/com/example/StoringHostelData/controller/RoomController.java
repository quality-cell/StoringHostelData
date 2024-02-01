package com.example.StoringHostelData.controller;

import com.example.StoringHostelData.Enum.ComfortType;
import com.example.StoringHostelData.dto.RoomDTO;
import com.example.StoringHostelData.entity.Room;
import com.example.StoringHostelData.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    public List<RoomDTO> getRooms(@RequestParam(name = "floor", required = false) Integer floor,
                                  @RequestParam(name = "roomNumber", required = false) Integer roomNumber,
                                  @RequestParam(name = "roomType", required = false) Boolean roomType,
                                  @RequestParam(name = "comfortType", required = false) String comfortType,
                                  @RequestParam(name = "numberOfSeats", required = false) Boolean numberOfSeats) {

        return roomService.getAllRooms()
                .stream()
                .filter(floor != null ? roomDTO -> Objects.equals(roomDTO.getFloor(), floor) : roomDTO -> true)
                .filter(roomNumber != null ? roomDTO -> Objects.equals(roomDTO.getRoomNumber(), roomNumber) : roomDTO -> true)
                .filter(roomType != null ? roomDTO -> Objects.equals(roomDTO.getRoomType(), roomType) : roomDTO -> true)
                .filter(comfortType != null ? roomDTO -> Objects.equals(roomDTO.getComfortType(), ComfortType.valueOf(comfortType)) : roomDTO -> true)
                .filter(numberOfSeats != null ? roomDTO -> roomDTO.getNumberOfSeats() > 0 : roomDTO -> true)
                .sorted((a, b) -> numberOfSeats != null ? Integer.compare(b.getNumberOfSeats(), a.getNumberOfSeats()) : 0)
                .collect(Collectors.toList());
    }

    @PostMapping("/add/room")
    public void addRoom(@RequestBody RoomDTO roomDTO) {
        roomService.addRoom(new Room(
                roomDTO.getFloor(),
                roomDTO.getRoomNumber(),
                roomDTO.getRoomType(),
                roomDTO.getComfortType(),
                roomDTO.getNumberOfSeats(),
                LocalDate.now(),
                LocalDate.now()));
    }

    @PatchMapping("/room/{id}")
    public void updateRoom(@PathVariable Integer id, @RequestBody RoomDTO roomDTO) {
        roomService.updateRoom(id, roomDTO);
    }

    @DeleteMapping("/room/{id}")
    public void deleteRoom(@PathVariable Integer id) {
        roomService.deleteRoom(id);
    }
}
