package br.com.pegasus.tutorial.project.exception.module.domain;

import java.io.IOException;

import org.springframework.stereotype.Component;

import br.com.pegasus.lib.module.exception.monitor.v1.annotation.bean.MonitorClassBean;
import br.com.pegasus.lib.module.exception.monitor.v1.annotation.bean.MonitorMethodBean;
import br.com.pegasus.tutorial.project.exception.module.infra.TutorialAdvice;
import br.com.pegasus.tutorial.project.exception.module.infra.TutorialException;

@MonitorClassBean(TutorialAdvice.class)
@Component
public class TutorialService {

	private static final String MSG_EXCEPTION = "<monitor> - case %d> ERRO: %s";
	private static int i = 0;

	@MonitorMethodBean
	public void monitoring() throws Exception {
		switch (++i) {
		case 1 -> throw new Exception(sFormat(MSG_EXCEPTION, i, Exception.class));
		case 2 -> throw new RuntimeException(sFormat(MSG_EXCEPTION, i, RuntimeException.class));
		case 3 -> throw new IOException(sFormat(MSG_EXCEPTION, i, IOException.class));
		case 4 -> throw new TutorialException(sFormat(MSG_EXCEPTION, i, TutorialException.class));
		case 5 -> throw new ArithmeticException(sFormat(MSG_EXCEPTION, i, ArithmeticException.class));
		}
		i = 0;
	}

	private static final String sFormat(String msg, int index, Class<?> clazz) {
		return String.format(msg, index, clazz.getSimpleName());
	}

}
