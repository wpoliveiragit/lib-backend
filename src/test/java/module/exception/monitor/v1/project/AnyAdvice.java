package module.exception.monitor.v1.project;

import java.io.IOException;

import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.exception.advice.ThrowableClassAdvice;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.exception.advice.ThrowableMethodAdvice;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ThrowableClassAdvice
public class AnyAdvice {

	private @Getter boolean[] check = { false, false, false, false };

	@ThrowableMethodAdvice(Exception.class)
	public void handleException(Exception ex) {
		log.info("{}: {{}}", ex.getClass().getSimpleName(), ex.getMessage());
		check[0] = true;
	}

	@ThrowableMethodAdvice(RuntimeException.class)
	public void handleRuntimeException(RuntimeException ex) {
		log.info("{}: {{}}", ex.getClass().getSimpleName(), ex.getMessage());
		check[1] = true;
	}

	@ThrowableMethodAdvice(IOException.class)
	public void handleIOException(IOException ex) {
		log.info("{}: {{}}", ex.getClass().getSimpleName(), ex.getMessage());
		check[2] = true;
	}

	@ThrowableMethodAdvice(AnyException.class)
	public void handleException(AnyException ex) {
		log.info("{}: {{}}", ex.getClass().getSimpleName(), ex.getMessage());
		check[3] = true;
	}

}
