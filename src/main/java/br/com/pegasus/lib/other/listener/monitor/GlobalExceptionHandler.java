package br.com.pegasus.lib.other.listener.monitor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import br.com.pegasus.lib.other.listener.monitor.exception.SimpleException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class GlobalExceptionHandler {

	/**
	 * Cria um {@link WatcherHandler} para interceptar exceções e publicar eventos.
	 * 
	 * @param value O {@link ApplicationEventPublisher} usado para publicar eventos.
	 * @return Uma instância de {@link WatcherHandler}.
	 */
	@Bean
	private final WatcherHandler createSniffer(ApplicationEventPublisher value) {
		return new WatcherHandler(value);
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
