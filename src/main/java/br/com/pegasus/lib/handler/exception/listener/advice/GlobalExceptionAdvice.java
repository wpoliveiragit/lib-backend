package br.com.pegasus.lib.handler.exception.listener.advice;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import br.com.pegasus.lib.handler.exception.SimpleException;
import br.com.pegasus.lib.handler.exception.listener.ExceptionListener;

public abstract class GlobalExceptionAdvice {

	@Bean
	private final ExceptionListener createExceptionListener(ApplicationEventPublisher value) {
		return new ExceptionListener(value);
	}

	/**
	 * Intercepta e processa exceções do tipo {@link java.lang.Exception} e
	 * {@link java.lang.RuntimeException}.
	 * 
	 * @param ex A exceção capturada, que pode ser uma instância de
	 *           {@link java.lang.Exception} ou {@link java.lang.RuntimeException}.
	 */
	@EventListener
	public abstract void handleException(SimpleException ex);

}
