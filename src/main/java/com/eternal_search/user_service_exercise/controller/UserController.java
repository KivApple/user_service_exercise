package com.eternal_search.user_service_exercise.controller;

import com.eternal_search.user_service_exercise.exception.UserNotFoundException;
import com.eternal_search.user_service_exercise.model.dto.UserCreateDTO;
import com.eternal_search.user_service_exercise.model.dto.UserInfoDTO;
import com.eternal_search.user_service_exercise.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Un contrôleur qui contient des endpoints pour la gestion des utilisateurs
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
	/**
	 * @param dto La demande de création d'utilisateur
	 * @return Informations sur l'utilisateur créé
	 */
	@PostMapping
	public UserInfoDTO create(@RequestBody @Valid UserCreateDTO dto) {
		return userService.create(dto);
	}
	
	/**
	 * @param id L'identifiant d'utilisateur
	 * @return Informations sur l'utilisateur existant
	 * @throws UserNotFoundException si l'utilisateur est introuvable
	 */
	@GetMapping("/{id}")
	public UserInfoDTO findById(@PathVariable long id) {
		return userService.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
	}
}
