package com.example.StoringHostelData;

import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class RoomControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllRooms() throws Exception {
        var requestBuilder = get("/rooms");
        String rooms = """
                [
                    {
                        "id": 1,
                        "floor": 1,
                        "roomNumber": 1,
                        "roomType": true,
                        "comfortType": "STANDARD",
                        "numberOfSeats": 1,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    },
                    {
                        "id": 2,
                        "floor": 2,
                        "roomNumber": 2,
                        "roomType": true,
                        "comfortType": "INCREASED_COMFORT",
                        "numberOfSeats": 3,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    },
                    {
                        "id": 3,
                        "floor": 3,
                        "roomNumber": 3,
                        "roomType": true,
                        "comfortType": "LUX",
                        "numberOfSeats": 3,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    },
                    {
                        "id": 4,
                        "floor": 4,
                        "roomNumber": 4,
                        "roomType": true,
                        "comfortType": "STANDARD",
                        "numberOfSeats": 3,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    },
                    {
                        "id": 5,
                        "floor": 1,
                        "roomNumber": 5,
                        "roomType": false,
                        "comfortType": "STANDARD",
                        "numberOfSeats": 1,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    }
                ]
                """.formatted(LocalDate.now(), LocalDate.now(), LocalDate.now(),
                LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(),
                LocalDate.now(), LocalDate.now(), LocalDate.now());

        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(rooms)
                );
    }

    @Test
    void getAllRoomsOnTheFloor() throws Exception {
        var requestBuilder = get("/rooms?floor=1");
        String rooms = """
                [
                    {
                        "id": 1,
                        "floor": 1,
                        "roomNumber": 1,
                        "roomType": true,
                        "comfortType": "STANDARD",
                        "numberOfSeats": 1,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    },
                    {
                        "id": 5,
                        "floor": 1,
                        "roomNumber": 5,
                        "roomType": false,
                        "comfortType": "STANDARD",
                        "numberOfSeats":1,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    }
                ]
                """.formatted(LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now());

        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(rooms)
                );
    }

    @Test
    void getAllRoomsOnTheFloorAndRoomNumber() throws Exception {
        var requestBuilder = get("/rooms?floor=1&roomNumber=1");
        String rooms = """
                [
                    {
                        "id": 1,
                        "floor": 1,
                        "roomNumber": 1,
                        "roomType": true,
                        "comfortType": "STANDARD",
                        "numberOfSeats": 1,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    }
                ]
                """.formatted(LocalDate.now(), LocalDate.now());

        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(rooms)
                );
    }

    @Test
    void getAllRoomsByRoomType() throws Exception {
        var requestBuilder = get("/rooms?roomType=false");
        String rooms = """
                [
                    {
                        "id": 5,
                        "floor": 1,
                        "roomNumber": 5,
                        "roomType": false,
                        "comfortType": "STANDARD",
                        "numberOfSeats": 1,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    }
                ]
                """.formatted(LocalDate.now(), LocalDate.now());

        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(rooms)
                );
    }

    @Test
    void getAllRoomsByComfortType() throws Exception {
        var requestBuilder = get("/rooms?comfortType=STANDARD");
        String rooms = """
                [
                    {
                        "id": 1,
                        "floor": 1,
                        "roomNumber": 1,
                        "roomType": true,
                        "comfortType": "STANDARD",
                        "numberOfSeats": 1,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    },
                    {
                        "id": 4,
                        "floor": 4,
                        "roomNumber": 4,
                        "roomType": true,
                        "comfortType": "STANDARD",
                        "numberOfSeats": 3,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    },
                    {
                        "id": 5,
                        "floor": 1,
                        "roomNumber": 5,
                        "roomType": false,
                        "comfortType": "STANDARD",
                        "numberOfSeats": 1,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    }
                ]
                """.formatted(LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now());

        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(rooms)
                );
    }

    @Test
    void getAllRoomsByNumberOfSeats() throws Exception {
        var requestBuilder = get("/rooms?numberOfSeats=true");
        String rooms = """
                [
                       {
                           "id": 2,
                           "floor": 2,
                           "roomNumber": 2,
                           "roomType": true,
                           "comfortType": "INCREASED_COMFORT",
                           "numberOfSeats": 3,
                           "dateAdded": "%s",
                           "dateOfChange": "%s"
                       },
                       {
                           "id": 3,
                           "floor": 3,
                           "roomNumber": 3,
                           "roomType": true,
                           "comfortType": "LUX",
                           "numberOfSeats": 3,
                           "dateAdded": "%s",
                           "dateOfChange": "%s"
                       },
                       {
                           "id": 4,
                           "floor": 4,
                           "roomNumber": 4,
                           "roomType": true,
                           "comfortType": "STANDARD",
                           "numberOfSeats": 3,
                           "dateAdded": "%s",
                           "dateOfChange": "%s"
                       },
                       {
                           "id": 1,
                           "floor": 1,
                           "roomNumber": 1,
                           "roomType": true,
                           "comfortType": "STANDARD",
                           "numberOfSeats": 1,
                           "dateAdded": "%s",
                           "dateOfChange": "%s"
                       },
                       {
                           "id": 5,
                           "floor": 1,
                           "roomNumber": 5,
                           "roomType": false,
                           "comfortType": "STANDARD",
                           "numberOfSeats": 1,
                           "dateAdded": "%s",
                           "dateOfChange": "%s"
                       }
                ]
                """.formatted(LocalDate.now(), LocalDate.now(), LocalDate.now(),
                LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(),
                LocalDate.now(), LocalDate.now(), LocalDate.now());

        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(rooms)
                );
    }

    @Test
    void addRoom() throws Exception {
        String room = """
                {
                    "floor": 1,
                    "roomNumber": 6,
                    "roomType": false,
                    "comfortType": "STANDARD",
                    "numberOfSeats": 4
                }
                """;
        var requestPostBuilder = post("/add/room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(room);


        this.mockMvc.perform(requestPostBuilder)
                .andExpect(
                        status().isOk()
                );

        var requestGetBuilder = get("/rooms");
        String rooms = """
                [
                    {
                        "id": 1,
                        "floor": 1,
                        "roomNumber": 1,
                        "roomType": true,
                        "comfortType": "STANDARD",
                        "numberOfSeats": 1,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    },
                    {
                        "id": 2,
                        "floor": 2,
                        "roomNumber": 2,
                        "roomType": true,
                        "comfortType": "INCREASED_COMFORT",
                        "numberOfSeats": 3,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    },
                    {
                        "id": 3,
                        "floor": 3,
                        "roomNumber": 3,
                        "roomType": true,
                        "comfortType": "LUX",
                        "numberOfSeats": 3,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    },
                    {
                        "id": 4,
                        "floor": 4,
                        "roomNumber": 4,
                        "roomType": true,
                        "comfortType": "STANDARD",
                        "numberOfSeats": 3,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    },
                    {
                        "id": 5,
                        "floor": 1,
                        "roomNumber": 5,
                        "roomType": false,
                        "comfortType": "STANDARD",
                        "numberOfSeats": 1,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    },
                    {
                        "id": 6,
                        "floor": 1,
                        "roomNumber": 6,
                        "roomType": false,
                        "comfortType": "STANDARD",
                        "numberOfSeats": 4,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    }
                ]
                """.formatted(LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(),
                LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(),
                LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now());

        this.mockMvc.perform(requestGetBuilder)
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(rooms)
                );
    }

    @Test
    void updateRoomWithEntityExistsException() {
        int roomId = 1;
        String room = """
                {
                    "floor": 1,
                    "roomNumber": 6,
                    "roomType": true,
                    "comfortType": "STANDARD",
                    "numberOfSeats": 4
                }
                """;

        try {
            var requestPatchBuilder = patch("/room/" + roomId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(room);
        } catch (EntityExistsException e) {
            assertEquals("Комната с id: " + roomId + " ещё занята и ее нельзя обновить", e.getCause().getMessage());
        }
    }

    @Test
    void updateRoom() throws Exception {
        int roomId = 7;
        String room = """
                {
                    "floor": 1,
                    "roomNumber": 6,
                    "roomType": false,
                    "comfortType": "STANDARD",
                    "numberOfSeats": 4
                }
                """;
        var requestPostBuilder = post("/add/room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(room);


        this.mockMvc.perform(requestPostBuilder)
                .andExpect(
                        status().isOk()
                );

        String roomUpdate = """
                {
                    "floor": 2,
                    "roomNumber": 6,
                    "roomType": false,
                    "comfortType": "STANDARD",
                    "numberOfSeats": 4
                }
                """;

        var requestPatchBuilder = patch("/room/" + roomId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(roomUpdate);

        this.mockMvc.perform(requestPatchBuilder)
                .andExpect(
                        status().isOk()
                );
    }

    @Test
    void deleteRoom() throws Exception {
        int roomId = 8;
        String room = """
                {
                    "floor": 1,
                    "roomNumber": 6,
                    "roomType": false,
                    "comfortType": "STANDARD",
                    "numberOfSeats": 4
                }
                """;
        var requestPostBuilder = post("/add/room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(room);


        this.mockMvc.perform(requestPostBuilder)
                .andExpect(
                        status().isOk()
                );

        var requestDeleteBuilder = delete("/room/" + roomId);

        this.mockMvc.perform(requestDeleteBuilder).andExpect(status().isOk());

        var requestBuilder = get("/rooms");
        String rooms = """
                [
                    {
                        "id": 1,
                        "floor": 1,
                        "roomNumber": 1,
                        "roomType": true,
                        "comfortType": "STANDARD",
                        "numberOfSeats": 1,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    },
                    {
                        "id": 2,
                        "floor": 2,
                        "roomNumber": 2,
                        "roomType": true,
                        "comfortType": "INCREASED_COMFORT",
                        "numberOfSeats": 3,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    },
                    {
                        "id": 3,
                        "floor": 3,
                        "roomNumber": 3,
                        "roomType": true,
                        "comfortType": "LUX",
                        "numberOfSeats": 3,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    },
                    {
                        "id": 4,
                        "floor": 4,
                        "roomNumber": 4,
                        "roomType": true,
                        "comfortType": "STANDARD",
                        "numberOfSeats": 3,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    },
                    {
                        "id": 5,
                        "floor": 1,
                        "roomNumber": 5,
                        "roomType": false,
                        "comfortType": "STANDARD",
                        "numberOfSeats": 1,
                        "dateAdded": "%s",
                        "dateOfChange": "%s"
                    }
                ]
                """.formatted(LocalDate.now(), LocalDate.now(), LocalDate.now(),
                LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(),
                LocalDate.now(), LocalDate.now(), LocalDate.now());

        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(rooms)
                );
    }
}
