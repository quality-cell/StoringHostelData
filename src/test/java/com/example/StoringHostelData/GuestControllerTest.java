package com.example.StoringHostelData;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class GuestControllerTest {
	@Autowired
	MockMvc mockMvc;

	@Test
	void getAllGuests() throws Exception {
		var requestBuilder = get("/guests");
		String guests = """
				[
				    {
				        "id": 1,
				        "roomId": 1,
				        "secondName": "Романов",
				        "name": "Максим",
				        "surname": "Юрьевич",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 2,
				        "roomId": 1,
				        "secondName": "Человек",
				        "name": "Паук",
				        "surname": "Бетменович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 3,
				        "roomId": 1,
				        "secondName": "Железный",
				        "name": "Человек",
				        "surname": "Рахманович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 4,
				        "roomId": 2,
				        "secondName": "Крюгер",
				        "name": "Иван",
				        "surname": "Михайлович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 5,
				        "roomId": 3,
				        "secondName": "Антонов",
				        "name": "Антон",
				        "surname": "Антонович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 6,
				        "roomId": 4,
				        "secondName": "Иванов",
				        "name": "Иван",
				        "surname": "Иванович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 7,
				        "roomId": 5,
				        "secondName": "Романова",
				        "name": "Вдова",
				        "surname": "Игоревна",
				        "gender": false,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 8,
				        "roomId": 5,
				        "secondName": "Белова",
				        "name": "Ванда",
				        "surname": "Максимовна",
				        "gender": false,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 9,
				        "roomId": 5,
				        "secondName": "Женщина",
				        "name": "Паук",
				        "surname": "Бетменовна",
				        "gender": false,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    }
				]""".formatted(LocalDate.now(), LocalDate.now(), LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now());

		this.mockMvc.perform(requestBuilder)
				.andExpectAll(
						status().isOk(),
						content().contentType(MediaType.APPLICATION_JSON),
						content().json(guests)
				);
	}

	@Test
	void getAllGuestsByGender() throws Exception {
		var requestBuilder = get("/guests?gender=false");
		String guests = """
				[
				    {
				        "id": 7,
				        "roomId": 5,
				        "secondName": "Романова",
				        "name": "Вдова",
				        "surname": "Игоревна",
				        "gender": false,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 8,
				        "roomId": 5,
				        "secondName": "Белова",
				        "name": "Ванда",
				        "surname": "Максимовна",
				        "gender": false,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 9,
				        "roomId": 5,
				        "secondName": "Женщина",
				        "name": "Паук",
				        "surname": "Бетменовна",
				        "gender": false,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    }
				]""".formatted(LocalDate.now(), LocalDate.now(), LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now());

		this.mockMvc.perform(requestBuilder)
				.andExpectAll(
						status().isOk(),
						content().contentType(MediaType.APPLICATION_JSON),
						content().json(guests)
				);
	}

	@Test
	void getAllGuestsByRoom() throws Exception {
		var requestBuilder = get("/guests?roomId=1");
		String guests = """
                [
                    {
				        "id": 1,
				        "roomId": 1,
				        "secondName": "Романов",
				        "name": "Максим",
				        "surname": "Юрьевич",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 2,
				        "roomId": 1,
				        "secondName": "Человек",
				        "name": "Паук",
				        "surname": "Бетменович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 3,
				        "roomId": 1,
				        "secondName": "Железный",
				        "name": "Человек",
				        "surname": "Рахманович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    }
                ]
                """.formatted(LocalDate.now(), LocalDate.now(), LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now());

		this.mockMvc.perform(requestBuilder)
				.andExpectAll(
						status().isOk(),
						content().contentType(MediaType.APPLICATION_JSON),
						content().json(guests)
				);
	}

	@Test
	void getAllGuestsByComfortType() throws Exception {
		var requestBuilder = get("/guests?comfortType=LUX");
		String rooms = """
                [
                	{
				        "id": 5,
				        "roomId": 3,
				        "secondName": "Антонов",
				        "name": "Антон",
				        "surname": "Антонович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    }
                ]""".formatted(LocalDate.now(), LocalDate.now());

		this.mockMvc.perform(requestBuilder)
				.andExpectAll(
						status().isOk(),
						content().contentType(MediaType.APPLICATION_JSON),
						content().json(rooms)
				);
	}

	@Test
	void addGuest() throws Exception {
		int roomId = 1;
		String guest = """
                {
				    "secondName": "Антонов",
				    "name": "Антон",
				    "surname": "Антонович",
				    "gender": true
                }
                """;
		var requestPostBuilder = post("/add/guest/" + roomId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(guest);

		this.mockMvc.perform(requestPostBuilder)
				.andExpect(
						status().isOk()
				);

		var requestGetBuilder = get("/guests");
		String guests = """
				[
				    {
				        "id": 1,
				        "roomId": 1,
				        "secondName": "Романов",
				        "name": "Максим",
				        "surname": "Юрьевич",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 2,
				        "roomId": 1,
				        "secondName": "Человек",
				        "name": "Паук",
				        "surname": "Бетменович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 3,
				        "roomId": 1,
				        "secondName": "Железный",
				        "name": "Человек",
				        "surname": "Рахманович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 4,
				        "roomId": 2,
				        "secondName": "Крюгер",
				        "name": "Иван",
				        "surname": "Михайлович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 5,
				        "roomId": 3,
				        "secondName": "Антонов",
				        "name": "Антон",
				        "surname": "Антонович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 6,
				        "roomId": 4,
				        "secondName": "Иванов",
				        "name": "Иван",
				        "surname": "Иванович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 7,
				        "roomId": 5,
				        "secondName": "Романова",
				        "name": "Вдова",
				        "surname": "Игоревна",
				        "gender": false,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 8,
				        "roomId": 5,
				        "secondName": "Белова",
				        "name": "Ванда",
				        "surname": "Максимовна",
				        "gender": false,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 9,
				        "roomId": 5,
				        "secondName": "Женщина",
				        "name": "Паук",
				        "surname": "Бетменовна",
				        "gender": false,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 10,
				        "roomId": %d,
				        "secondName": "Антонов",
				    	"name": "Антон",
				    	"surname": "Антонович",
				    	"gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    }
				]""".formatted(LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(),
				LocalDate.now(), LocalDate.now(), roomId, LocalDate.now(), LocalDate.now());

		this.mockMvc.perform(requestGetBuilder)
				.andExpectAll(
						status().isOk(),
						content().contentType(MediaType.APPLICATION_JSON),
						content().json(guests)
				);
	}

	@Test
	void updateGuest() throws Exception {
		int id = 1;
		String guest = """
                {
				    "secondName": "Трамп",
				    "name": "Дональд",
				    "surname": "Дак",
				    "gender": true
                }
                """;
		var requestPatchBuilder = patch("/guest/" + id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(guest);


		this.mockMvc.perform(requestPatchBuilder)
				.andExpect(
						status().isOk()
				);

		String guests = """
				[
				    {
				        "id": 1,
				        "roomId": 1,
				        "secondName": "Трамп",
				    	"name": "Дональд",
				    	"surname": "Дак",
				    	"gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 2,
				        "roomId": 1,
				        "secondName": "Человек",
				        "name": "Паук",
				        "surname": "Бетменович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 3,
				        "roomId": 1,
				        "secondName": "Железный",
				        "name": "Человек",
				        "surname": "Рахманович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 4,
				        "roomId": 2,
				        "secondName": "Крюгер",
				        "name": "Иван",
				        "surname": "Михайлович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 5,
				        "roomId": 3,
				        "secondName": "Антонов",
				        "name": "Антон",
				        "surname": "Антонович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 6,
				        "roomId": 4,
				        "secondName": "Иванов",
				        "name": "Иван",
				        "surname": "Иванович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 7,
				        "roomId": 5,
				        "secondName": "Романова",
				        "name": "Вдова",
				        "surname": "Игоревна",
				        "gender": false,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 8,
				        "roomId": 5,
				        "secondName": "Белова",
				        "name": "Ванда",
				        "surname": "Максимовна",
				        "gender": false,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 9,
				        "roomId": 5,
				        "secondName": "Женщина",
				        "name": "Паук",
				        "surname": "Бетменовна",
				        "gender": false,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    }
				]""".formatted(LocalDate.now(), LocalDate.now(), LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now());

		var requestGetBuilder = get("/guests");

		this.mockMvc.perform(requestGetBuilder)
				.andExpectAll(
						status().isOk(),
						content().json(guests)
				);
	}

	@Test
	void deleteGuest() throws Exception {
		int id = 1;
		var requestDeleteBuilder = delete("/guest/" + id);

		this.mockMvc.perform(requestDeleteBuilder)
				.andExpect(
						status().isOk()
				);

		String guests = """
				[
				    {
				        "id": 2,
				        "roomId": 1,
				        "secondName": "Человек",
				        "name": "Паук",
				        "surname": "Бетменович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 3,
				        "roomId": 1,
				        "secondName": "Железный",
				        "name": "Человек",
				        "surname": "Рахманович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 4,
				        "roomId": 2,
				        "secondName": "Крюгер",
				        "name": "Иван",
				        "surname": "Михайлович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 5,
				        "roomId": 3,
				        "secondName": "Антонов",
				        "name": "Антон",
				        "surname": "Антонович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 6,
				        "roomId": 4,
				        "secondName": "Иванов",
				        "name": "Иван",
				        "surname": "Иванович",
				        "gender": true,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 7,
				        "roomId": 5,
				        "secondName": "Романова",
				        "name": "Вдова",
				        "surname": "Игоревна",
				        "gender": false,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 8,
				        "roomId": 5,
				        "secondName": "Белова",
				        "name": "Ванда",
				        "surname": "Максимовна",
				        "gender": false,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    },
				    {
				        "id": 9,
				        "roomId": 5,
				        "secondName": "Женщина",
				        "name": "Паук",
				        "surname": "Бетменовна",
				        "gender": false,
				        "dateAdded": "%s",
				        "dateOfChange": "%s"
				    }
				]""".formatted(LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now());

		var requestGetBuilder = get("/guests");

		this.mockMvc.perform(requestGetBuilder)
				.andExpectAll(
						status().isOk(),
						content().json(guests)
				);
	}
}
