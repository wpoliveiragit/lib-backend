package br.com.pegasus.tutorial.project.exception.module.infra;

public class TutorialException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TutorialException(String message) {
		super(message);
	}
}
