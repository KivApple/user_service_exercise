package com.eternal_search.user_service_exercise.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CallLogger {
	/**
	 * Un gestionnaire qui enregistre les entrées et sorties de la méthode interceptée et le temps écoulé
	 * @param joinPoint Informations sur la méthode interceptée
	 * @return Résultat de la méthode interceptée
	 * @throws Throwable Une exception levée par la méthode interceptée
	 */
	@Around("measuredMethod()")
	public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
		// Écrire dans le journal des arguments de la méthode
		log.info(
				"Calling {}.{} with arguments {}",
				joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(),
				joinPoint.getArgs()
		);
		
		long time = System.currentTimeMillis();
		try {
			// Invoquer la méthode
			Object result = joinPoint.proceed();
			long elapsedTime = System.currentTimeMillis() - time;
			// Écrire dans le journal la valeur de retour et le temps écoulé de la méthode
			log.info(
					"Returning from {}.{} with result {} (elapsed {} ms)",
					joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(),
					result,
					elapsedTime
			);
			// Renvoyez ensuite la valeur de la méthode
			return result;
		} catch (Throwable e) {
			long elapsedTime = System.currentTimeMillis() - time;
			// Écrire dans le journal, exception levée par la méthode
			log.info(
					"Returning from {}.{} with exception {} (elapsed {} ms)",
					joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(),
					e.getClass().getName(),
					elapsedTime
			);
			// Relancer une exception
			throw e;
		}
	}
	
	/**
	 * Un modèle correspondant à la méthode à intercepter
	 */
	@Pointcut("execution(@com.eternal_search.user_service_exercise.config.LoggingCall * *(..))")
	public void measuredMethod() {
	}
}
