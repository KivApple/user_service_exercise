package com.eternal_search.user_service_exercise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Une exception pour la situation où l'utilisateur existe déjà avec ce nom
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class UserNameExistsException extends RuntimeException {
	public UserNameExistsException() {
		super("User name is already in use");
	}
}
