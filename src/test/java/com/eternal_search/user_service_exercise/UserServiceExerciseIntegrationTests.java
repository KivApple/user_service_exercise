package com.eternal_search.user_service_exercise;

import com.eternal_search.user_service_exercise.model.dto.UserCreateDTO;
import com.eternal_search.user_service_exercise.model.dto.UserInfoDTO;
import com.eternal_search.user_service_exercise.model.enumeration.Gender;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class UserServiceExerciseIntegrationTests {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	/**
	 * Création d'utilisateurs réussie. Vérifiez que les mêmes informations utilisateur sont renvoyées comme demandé
	 */
	@Test
	void createEligibleUser() throws Exception {
		// Créer une demande
		UserCreateDTO request = UserCreateDTO.builder()
				.userName("test")
				.birthdate(LocalDate.now().minusYears(18))
				.countryOfResidence("France")
				.phoneNumber("33123456789")
				.gender(Gender.MALE)
				.build();
		// Effectuer la demande
		String responseStr = mockMvc.perform(
				MockMvcRequestBuilders.post("/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request))
		).andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		UserInfoDTO response = objectMapper.readValue(responseStr, UserInfoDTO.class);
		// Valider la réponse
		assertThat(response.userName()).isEqualTo(request.userName());
		assertThat(response.birthdate()).isEqualTo(request.birthdate());
		assertThat(response.countryOfResidence()).isEqualTo(request.countryOfResidence());
		assertThat(response.phoneNumber()).isEqualTo(request.phoneNumber());
		assertThat(response.gender()).isEqualTo(request.gender());
	}
	
	/**
	 * Création d'utilisateurs réussie. Vérifiez que nous pouvons trouver les informations utilisateur plus tard
	 * et les bonnes données stockées dans la base de données.
	 */
	@Test
	void createAndFetchUser() throws Exception {
		// Créer une demande
		UserCreateDTO request = UserCreateDTO.builder()
				.userName("test")
				.birthdate(LocalDate.now().minusYears(18))
				.countryOfResidence("France")
				.phoneNumber("33123456789")
				.gender(Gender.MALE)
				.build();
		// Effectuer la demande
		String responseStr = mockMvc.perform(
				MockMvcRequestBuilders.post("/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request))
		).andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		UserInfoDTO response = objectMapper.readValue(responseStr, UserInfoDTO.class);
		// Récupérer l'utilisateur nouvellement créé par identifiant
		responseStr = mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", response.id()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		response = objectMapper.readValue(responseStr, UserInfoDTO.class);
		// Valider la réponse
		assertThat(response.userName()).isEqualTo(request.userName());
		assertThat(response.birthdate()).isEqualTo(request.birthdate());
		assertThat(response.countryOfResidence()).isEqualTo(request.countryOfResidence());
		assertThat(response.phoneNumber()).isEqualTo(request.phoneNumber());
		assertThat(response.gender()).isEqualTo(request.gender());
	}
	
	/**
	 * La situation où nous demandons un utilisateur avec un identifiant inexistant.
	 * Devrait répondre à l'erreur 404.
	 */
	@Test
	void fetchNonExistingUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 0))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	/**
	 * Tentative de création d'un utilisateur non éligible (mineur non résident français).
	 * Devrait répondre à une erreur 403.
	 */
	@Test
	void createNonEligibleUser() throws Exception {
		UserCreateDTO request = UserCreateDTO.builder()
				.userName("test")
				.birthdate(LocalDate.now().minusYears(18).plusDays(1))
				.countryOfResidence("Italy")
				.build();
		mockMvc.perform(
				MockMvcRequestBuilders.post("/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request))
		).andExpect(MockMvcResultMatchers.status().isForbidden());
	}
	
	/**
	 * Tentative de créer un utilisateur avec un numéro de téléphone invalide.
	 * Devrait répondre une erreur 400.
	 */
	@Test
	void createUserWithInvalidPhoneNumber() throws Exception {
		UserCreateDTO request = UserCreateDTO.builder()
				.userName("test")
				.birthdate(LocalDate.now().minusYears(18))
				.countryOfResidence("France")
				.phoneNumber("INVALID")
				.build();
		mockMvc.perform(
				MockMvcRequestBuilders.post("/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request))
		).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
}
