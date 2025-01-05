package br.com.pegasus.lib.handler.exception.listener.suport;

import java.io.IOException;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.pegasus.lib.handler.exception.SimpleException;
import br.com.pegasus.lib.handler.exception.listener.advice.GlobalExceptionAdvice;
import lombok.Getter;

@Component
public class ListenerGlobalExceptionAdvice extends GlobalExceptionAdvice {

	@Getter
	private boolean[] check = { false, false, false };

	@Override
	public void handleException(SimpleException ex) {
		check[0] = true;
	}

	@EventListener
	public void handleException(ListenerException ex) {
		check[1] = true;
	}

	@EventListener
	public void handleException(IOException ex) {
		check[2] = true;
	}
}
