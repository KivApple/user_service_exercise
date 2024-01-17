package com.eternal_search.user_service_exercise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception levée si l'utilisateur n'est pas éligible à l'inscription
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserNotEligibleException extends RuntimeException {
	public UserNotEligibleException() {
		super("User is not eligible for registration");
	}
}
