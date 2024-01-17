package com.eternal_search.user_service_exercise;

import com.eternal_search.user_service_exercise.model.dto.UserCreateDTO;
import com.eternal_search.user_service_exercise.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

@SpringBootTest
public class UserServiceUnitTests {
	@Autowired
	private UserService userService;
	
	/**
	 * Test pour la situation où l'utilisateur est un adulte résidant en France
	 */
	@Test
	void testEligible() {
		UserCreateDTO dto = UserCreateDTO.builder()
				.userName("test")
				.birthdate(LocalDate.now().minusYears(18))
				.countryOfResidence("France")
				.build();
		assertThat(userService.isEligible(dto)).isTrue();
	}
	
	/**
	 * Test pour la situation où l'utilisateur est mineur résidant en France
	 */
	@Test
	void testNotEligibleAge() {
		UserCreateDTO dto = UserCreateDTO.builder()
				.userName("test")
				.birthdate(LocalDate.now().minusYears(18).plusDays(1))
				.countryOfResidence("France")
				.build();
		assertThat(userService.isEligible(dto)).isFalse();
	}
	
	/**
	 * Test pour la situation où l'utilisateur est majeur résidant hors de France
	 */
	@Test
	void testNotEligibleCountry() {
		UserCreateDTO dto = UserCreateDTO.builder()
				.userName("test")
				.birthdate(LocalDate.now().minusYears(20))
				.countryOfResidence("Italy")
				.build();
		assertThat(userService.isEligible(dto)).isFalse();
	}
}
