package com.example.StoringHostelData.controller;

import com.example.StoringHostelData.Enum.ComfortType;
import com.example.StoringHostelData.dto.GuestDTO;
import com.example.StoringHostelData.seervice.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class GuestController {
    private final GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping("/guests")
    public List<GuestDTO> getAllGuests(@RequestParam(name = "roomId", required = false) Integer roomId,
                                       @RequestParam(name = "secondName", required = false) String secondName,
                                       @RequestParam(name = "name", required = false) String name,
                                       @RequestParam(name = "surname", required = false) String surname,
                                       @RequestParam(name = "gender", required = false) Boolean gender,
                                       @RequestParam(name = "comfortType", required = false) String comfortType) {
        return guestService.getAllGuest()
                .stream()
                .filter(roomId != null ? guestDTO -> Objects.equals(guestDTO.getRoomId(), roomId) : guestDTO -> true)
                .filter(secondName != null ? guestDTO -> Objects.equals(guestDTO.getSecondName(), secondName) : guestDTO -> true)
                .filter(name != null ? guestDTO -> Objects.equals(guestDTO.getName(), name) : guestDTO -> true)
                .filter(surname != null ? guestDTO -> Objects.equals(guestDTO.getSurname(), surname) : guestDTO -> true)
                .filter(comfortType != null ? guestDTO -> Objects.equals(guestDTO.getRoom().getComfortType(), ComfortType.valueOf(comfortType)) : guestDTO -> true)
                .filter(gender != null ? guestDTO -> Objects.equals(guestDTO.getGender(), gender) : guestDTO -> true)
                .collect(Collectors.toList());

    }


    @PostMapping("/add/guest/{roomId}")
    public void addGuest(@PathVariable Integer roomId, @RequestBody GuestDTO guestDTO) {
        guestService.createGuest(roomId, guestDTO);
    }

    @PatchMapping("/guest/{id}")
    public void updateGuest(@PathVariable Integer id, @RequestBody GuestDTO guestDTO) {
        guestService.updateGuest(id, guestDTO);
    }

    @DeleteMapping("/guest/{id}")
    public void deleteGuest(@PathVariable Integer id) {
        guestService.deleteGuest(id);
    }
}
