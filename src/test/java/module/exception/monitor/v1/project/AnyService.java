package module.exception.monitor.v1.project;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.springframework.stereotype.Service;

import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.exception.advice.MonitorClassAdvice;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.exception.advice.MonitorMethodAdvice;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.AllMethodLog;
import lombok.Getter;

@Service
@MonitorClassAdvice(AnyAdvice.class)
public class AnyService {

	private static final String MSG_EXCEPTION = "<monitor> - case %d> ERRO: %s";
	private @Getter CountDownLatch countDownLatch;
	private static int i = 0;

	public AnyService() {
		countDownLatch = new CountDownLatch(1);
	}

	@AllMethodLog(msgBegine = "INICIO DO MÉTODO", msgAfter = "MÉTHODO FINALIZADO COM SUCESSO", msgException = "OCORREU UMA FALHA NO PROCESSO")
	@MonitorMethodAdvice
	public void monitorMethod() throws Exception {
		switch (++i) {
		case 1 -> throw new Exception(sFormat(MSG_EXCEPTION, i, Exception.class));
		case 2 -> throw new RuntimeException(sFormat(MSG_EXCEPTION, i, RuntimeException.class));
		case 3 -> throw new IOException(sFormat(MSG_EXCEPTION, i, IOException.class));
		case 4 -> throw new AnyException(sFormat(MSG_EXCEPTION, i, AnyException.class));
		case 5 -> throw new AnyException(sFormat(MSG_EXCEPTION, i, AnyException.class), "");
		case 6 -> throw new ArithmeticException(sFormat(MSG_EXCEPTION, i, ArithmeticException.class));
		default -> countDownLatch.countDown();
		}
		i = 0;
	}

	private static final String sFormat(String msg, int index, Class<?> clazz) {
		return String.format(msg, index, clazz.getSimpleName());
	}

}
