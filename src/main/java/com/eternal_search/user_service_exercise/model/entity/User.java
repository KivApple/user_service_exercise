package com.eternal_search.user_service_exercise.model.entity;

import com.eternal_search.user_service_exercise.model.enumeration.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entité utilisateur à stocker dans la base de données
 */
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	/**
	 * L'identifiant d'utilisateur (AUTO_INCREMENT)
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Le nom d'utilisateur
	 */
	private String userName;
	
	/**
	 *La date de naissance
	 */
	private LocalDate birthdate;
	
	/**
	 * Le pays de résidence
	 */
	private String countryOfResidence;
	
	/**
	 * Le numéro de téléphone
	 */
	private String phoneNumber;
	
	/**
	 * Le sexe
	 * (Enregistré dans la base de données sous forme de chaîne pour l'extensibilité)
	 */
	@Enumerated(EnumType.STRING)
	private Gender gender;
}
