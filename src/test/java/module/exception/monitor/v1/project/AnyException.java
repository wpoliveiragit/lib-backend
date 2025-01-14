package module.exception.monitor.v1.project;

public class AnyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AnyException(String message) {
		super(message);
	}
}
