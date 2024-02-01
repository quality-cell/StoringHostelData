package com.example.StoringHostelData.service;

import com.example.StoringHostelData.dto.GuestDTO;
import com.example.StoringHostelData.entity.Guest;;
import com.example.StoringHostelData.repository.GuestRepository;
import com.example.StoringHostelData.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GuestService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(RoomRepository roomRepository, GuestRepository guestRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;

    }

    public List<GuestDTO> getAllGuest() {
        List<GuestDTO> res = new ArrayList<>();
        guestRepository.findAll().forEach(guest -> res.add(new GuestDTO(guest)));

        if (res.isEmpty()) {
            throw new EntityNotFoundException("Постояльцев не найдено");
        }

        return res;
    }

    public GuestDTO getGuestById(Integer guestId) {
        Optional<Guest> guest = guestRepository.findById(guestId);

        if (guest.isPresent()) {
            return new GuestDTO(guest.get());
        }

        throw new EntityNotFoundException("Постоялец с id: " + guestId + " не найден");
    }

    public List<GuestDTO> getByRoomId(Integer id) {
        List<GuestDTO> guestDTOList = guestRepository.findByRoomId(id).stream().map(GuestDTO::new).toList();

        if (guestDTOList.isEmpty()) {
            throw new EntityNotFoundException("Комната с id: " + id + " пуста");
        }

        return guestDTOList;
    }

    public void createGuest(Integer id, GuestDTO guestDTO) {
        roomRepository.findById(id)
                .ifPresent(room -> guestRepository
                        .save(new Guest(room,
                                guestDTO.getSecondName(),
                                guestDTO.getName(),
                                guestDTO.getSurname(),
                                guestDTO.getGender(),
                                LocalDate.now(),
                                LocalDate.now())));
    }

    public void updateGuest(Integer id, GuestDTO guestDTO) {
        Optional<Guest> guestOptional = guestRepository.findById(id);

        if (guestOptional.isEmpty()) {
            throw new EntityNotFoundException("Постоялец с id: " + id + " не найден");
        }

        Guest guest = guestOptional.get();
        guest.setGender(guestDTO.getGender());
        guest.setSurname(guestDTO.getSurname());
        guest.setSecondName(guestDTO.getSecondName());
        guest.setName(guestDTO.getName());
        guest.setDateOfChange(LocalDate.now());

        guestRepository.save(guest);
    }

    public void deleteGuest(Integer guestId) {
        Optional<Guest> guestOptional = guestRepository.findById(guestId);

        if (guestOptional.isEmpty()) {
            throw new EntityNotFoundException("Постоялец с id: " + guestId + " не найден");
        }

        guestRepository.deleteById(guestId);
    }
}
