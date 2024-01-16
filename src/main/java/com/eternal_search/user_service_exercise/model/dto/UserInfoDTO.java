package com.eternal_search.user_service_exercise.model.dto;

import com.eternal_search.user_service_exercise.model.enumeration.Gender;

import java.time.LocalDate;

/**
 * Informations sur l'utilisateur existant
 * @param id L'identifiant d'utilisateur
 * @param userName Le nom d'utilisateur
 * @param birthdate La date de naissance
 * @param countryOfResidence Le pays de résidence
 * @param phoneNumber Le numéro de téléphone
 * @param gender Le sexe
 */
public record UserInfoDTO(
	long id,
	String userName,
	LocalDate birthdate,
	String countryOfResidence,
	String phoneNumber,
	Gender gender
) {
}
