package com.eternal_search.user_service_exercise.model.dto;

import com.eternal_search.user_service_exercise.model.enumeration.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * La demande de création d'utilisateur
 * @param userName Le nom d'utilisateur
 * @param birthdate La date de naissance
 * @param countryOfResidence Le pays de résidence
 * @param phoneNumber Le numéro de téléphone
 * @param gender Le sexe
 */
public record UserCreateDTO(
		@NotBlank
		String userName,
		
		@NotNull
		LocalDate birthdate,
		
		@NotBlank
		String countryOfResidence,
		
		String phoneNumber,
		
		Gender gender
) {
}
