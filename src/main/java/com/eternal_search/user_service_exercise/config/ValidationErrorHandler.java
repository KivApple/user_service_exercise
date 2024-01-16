package com.eternal_search.user_service_exercise.config;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Une classe qui contient un gestionnaire d'erreurs de validation
 * pour fournir à un utilisateur des détails sur ce qui n'allait pas exactement
 */
@RestControllerAdvice
public class ValidationErrorHandler {
	/**
	 * @param ex Une exception de validation
	 * @return Détails pouvant être montrés à l'utilisateur
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getAllErrors().stream()
				.collect(Collectors.toMap(
						error -> ((FieldError) error).getField(),
						error -> Optional.ofNullable(error.getDefaultMessage())
								.orElse("Unknown error")
				));
	}
}
