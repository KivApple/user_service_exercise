package com.eternal_search.user_service_exercise.service;

import com.eternal_search.user_service_exercise.model.dto.UserCreateDTO;
import com.eternal_search.user_service_exercise.model.dto.UserInfoDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Logique de gestion des utilisateurs
 */
@Service
public class UserService {
	/**
	 * @param dto La demande de création d'utilisateur
	 * @return Informations sur l'utilisateur créé
	 */
	public UserInfoDTO create(UserCreateDTO dto) {
		// TODO
		return null;
	}
	
	/**
	 * @param id L'identifiant d'utilisateur
	 * @return  Informations sur l'utilisateur s'il exist
	 */
	public Optional<UserInfoDTO> findById(long id) {
		// TODO
		return Optional.empty();
	}
}
