package com.eternal_search.user_service_exercise.service;

import com.eternal_search.user_service_exercise.model.dto.UserCreateDTO;
import com.eternal_search.user_service_exercise.model.dto.UserInfoDTO;
import com.eternal_search.user_service_exercise.model.entity.User;
import com.eternal_search.user_service_exercise.model.mapper.UserMapper;
import com.eternal_search.user_service_exercise.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Logique de gestion des utilisateurs
 */
@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	/**
	 * @param dto La demande de création d'utilisateur
	 * @return Informations sur l'utilisateur créé
	 */
	public UserInfoDTO create(UserCreateDTO dto) {
		User user = userMapper.toEntity(dto);
		user = userRepository.save(user);
		return userMapper.toDto(user);
	}
	
	/**
	 * @param id L'identifiant d'utilisateur
	 * @return  Informations sur l'utilisateur s'il exist
	 */
	public Optional<UserInfoDTO> findById(long id) {
		return userRepository.findById(id)
				.map(userMapper::toDto);
	}
}
