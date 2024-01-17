package com.eternal_search.user_service_exercise.model.mapper;

import com.eternal_search.user_service_exercise.model.dto.UserCreateDTO;
import com.eternal_search.user_service_exercise.model.dto.UserInfoDTO;
import com.eternal_search.user_service_exercise.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
	/**
	 * Convertit une entité utilisateur existante en une vue pour l'utilisateur
	 * @param entity Une entité
	 * @return Un DTO
	 */
	UserInfoDTO toDto(User entity);
	
	/**
	 * Convertit une demande de création d'un utilisateur en une nouvelle entité
	 * pouvant être enregistrée dans la base de données
	 * @param dto Une requête
	 * @return Une entité
	 */
	@Mapping(target = "id", ignore = true)
	User toEntity(UserCreateDTO dto);
}
