package module.exception.monitor.v1.project;

import java.io.IOException;

import br.com.pegasus.lib.module.exception.monitor.v1.annotation.advice.MonitorAdvice;
import br.com.pegasus.lib.module.exception.monitor.v1.annotation.advice.MonitorMethodAdvice;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@MonitorAdvice
public class AnyAdvice {

	private @Getter boolean[] check = { false, false, false, false };

	@MonitorMethodAdvice(Exception.class)
	public void handleException(Exception ex) {
		log.info("{}: {{}}", ex.getClass().getSimpleName(), ex.getMessage());
		check[0] = true;
	}

	@MonitorMethodAdvice(RuntimeException.class)
	public void handleRuntimeException(RuntimeException ex) {
		log.info("{}: {{}}", ex.getClass().getSimpleName(), ex.getMessage());
		check[1] = true;
	}

	@MonitorMethodAdvice(IOException.class)
	public void handleIOException(IOException ex) {
		log.info("{}: {{}}", ex.getClass().getSimpleName(), ex.getMessage());
		check[2] = true;
	}

	@MonitorMethodAdvice(AnyException.class)
	public void handleException(AnyException ex) {
		log.info("{}: {{}}", ex.getClass().getSimpleName(), ex.getMessage());
		check[3] = true;
	}
	@MonitorMethodAdvice(AnyException.class)
	public void handleException(AnyException ex, String s) {
		log.info("{}: {{}}", ex.getClass().getSimpleName(), ex.getMessage());
		check[3] = true;
	}

}
