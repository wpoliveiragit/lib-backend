package module.exception.monitor.v1.project;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.springframework.stereotype.Service;

import br.com.pegasus.lib.module.exception.monitor.v1.annotation.bean.MonitorClassBean;
import br.com.pegasus.lib.module.exception.monitor.v1.annotation.bean.MonitorMethodBean;
import lombok.Getter;

@Service
@MonitorClassBean(AnyAdvice.class)
public class AnyService {

	private static final String MSG_EXCEPTION = "<monitor> - case %d> ERRO: %s";
	private @Getter CountDownLatch countDownLatch;
	private static int i = 0;

	public AnyService() {
		countDownLatch = new CountDownLatch(1);
	}

	@MonitorMethodBean
	public void monitorMethod() throws Exception {
		switch (++i) {
		case 1 -> throw new Exception(sFormat(MSG_EXCEPTION, i, Exception.class));
		case 2 -> throw new RuntimeException(sFormat(MSG_EXCEPTION, i, RuntimeException.class));
		case 3 -> throw new IOException(sFormat(MSG_EXCEPTION, i, IOException.class));
		case 4 -> throw new AnyException(sFormat(MSG_EXCEPTION, i, AnyException.class));
		case 5 -> throw new ArithmeticException(sFormat(MSG_EXCEPTION, i, ArithmeticException.class));
		default -> countDownLatch.countDown();
		}
		i = 0;
	}

	public void otherMethod() {
		throw new RuntimeException("otherMethod");
	}

	private static final String sFormat(String msg, int index, Class<?> clazz) {
		return String.format(msg, index, clazz.getSimpleName());
	}

}
