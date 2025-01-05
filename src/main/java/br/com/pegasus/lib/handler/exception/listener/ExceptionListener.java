package br.com.pegasus.lib.handler.exception.listener;

import org.springframework.context.ApplicationEventPublisher;

import br.com.pegasus.lib.handler.exception.SimpleException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExceptionListener {

	private final ApplicationEventPublisher appEventPublisher;

	/**
	 * Executa o {@link FunctionalMethodListener} e publica eventos para exceções
	 * capturadas. Exceções de {@link java.lang.Exception} e
	 * {@link java.lang.RuntimeException} geram um evento {@link SimpleException}.
	 * Outras exceções são publicadas diretamente.
	 * 
	 * @param runnable O {@link FunctionalMethodListener} a ser executado.
	 */
	public void listening(FunctionalMethodListener runnable) {
		try {
			runnable.execution();
		} catch (Exception ex) {
			if ((ex.getClass() == Exception.class) || (ex.getClass() == RuntimeException.class)) {
				appEventPublisher.publishEvent(new SimpleException(ex));
				return;
			}
			appEventPublisher.publishEvent(ex);
		}
	}
}
