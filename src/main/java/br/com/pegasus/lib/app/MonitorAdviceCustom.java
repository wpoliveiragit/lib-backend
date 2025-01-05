package br.com.pegasus.lib.app;

import java.io.IOException;

import org.springframework.stereotype.Component;

import br.com.pegasus.lib.handler.exception.annotation.advice.ExceptionHandlerMethod;
import br.com.pegasus.lib.handler.exception.annotation.advice.MonitorAdvice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MonitorAdviceCustom implements MonitorAdvice {

	public void test() {

	}

	@ExceptionHandlerMethod
	public void handleRuntimeException(RuntimeException ex) {
		log.info("{}: {{}}", ex.getClass().getSimpleName(), ex.getMessage());
	}

	@ExceptionHandlerMethod
	public void handleIOException(IOException ex) {
		log.info("{}: {{}}", ex.getClass().getSimpleName(), ex.getMessage());
	}

	@ExceptionHandlerMethod
	public void handleException(Exception ex) {
		log.info("{}: {{}}", ex.getClass().getSimpleName(), ex.getMessage());
	}

	@ExceptionHandlerMethod
	public void handleException(AppException ex) {
		log.info("{}: {{}}", ex.getClass().getSimpleName(), ex.getMessage());
	}

}
