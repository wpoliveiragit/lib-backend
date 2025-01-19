package br.com.pegasus.tutorial.project.exception.module.infra;

import java.io.IOException;

import br.com.pegasus.lib.module.exception.monitor.v1.annotation.advice.MonitorAdvice;
import br.com.pegasus.lib.module.exception.monitor.v1.annotation.advice.MonitorMethodAdvice;
import lombok.extern.log4j.Log4j2;

@Log4j2
@MonitorAdvice
public class TutorialAdvice {

	@MonitorMethodAdvice(Exception.class)
	public void handleException(Exception ex) {
		log.info("{}: {{}}", ex.getClass().getSimpleName(), ex.getMessage());
	}

	@MonitorMethodAdvice(RuntimeException.class)
	public void handleRuntimeException(RuntimeException ex) {
		log.info("{}: {{}}", ex.getClass().getSimpleName(), ex.getMessage());
	}

	@MonitorMethodAdvice(IOException.class)
	public void handleIOException(IOException ex) {
		log.info("{}: {{}}", ex.getClass().getSimpleName(), ex.getMessage());
	}

	@MonitorMethodAdvice(TutorialException.class)
	public void handleException(TutorialException ex) {
		log.info("{}: {{}}", ex.getClass().getSimpleName(), ex.getMessage());
	}

	@MonitorMethodAdvice(TutorialException.class)
	public void handleException(TutorialException ex, String s) {
		log.info("{}: {{}}", ex.getClass().getSimpleName(), ex.getMessage());
	}

}
