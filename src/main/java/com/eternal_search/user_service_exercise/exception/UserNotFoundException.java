package com.eternal_search.user_service_exercise.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception lorsque l'utilisateur avec l'identifiant fourni n'est pas trouvé
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
@RequiredArgsConstructor
@Getter
public class UserNotFoundException extends RuntimeException {
	private final long id;
	
	@Override
	public String getMessage() {
		return "User with id=" + id + " not found";
	}
}
