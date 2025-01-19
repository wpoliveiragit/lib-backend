package br.com.pegasus.lib.other.listener.monitor;

import org.springframework.context.ApplicationEventPublisher;

import br.com.pegasus.lib.other.listener.monitor.exception.SimpleException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WatcherHandler {

	private final ApplicationEventPublisher appEventPublisher;

	/**
	 * Executa o {@link ThrowingHandler} e publica eventos para exceções capturadas.
	 * Exceções de {@link java.lang.Exception} e {@link java.lang.RuntimeException}
	 * geram um evento {@link SimpleException}. Outras exceções são publicadas
	 * diretamente.
	 * 
	 * @param runnable O {@link ThrowingHandler} a ser executado.
	 */
	public void listening(ThrowingHandler runnable) {
		try {
			runnable.run();
		} catch (Exception ex) {
			if ((ex.getClass() == Exception.class) || (ex.getClass() == RuntimeException.class)) {
				appEventPublisher.publishEvent(new SimpleException(ex));
				return;
			}
			appEventPublisher.publishEvent(ex);
		}
	}
}
