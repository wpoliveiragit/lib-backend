package br.com.pegasus.lib.app;

import java.io.IOException;

import org.springframework.stereotype.Service;

import br.com.pegasus.lib.handler.exception.annotation.monitor.ExceptionMonitor;

@Service
public class AppService {

	private static int i = 0;

	@ExceptionMonitor
	public void task() throws Exception {
		System.out.printf("case %d  \n", ++i);
		switch (i) {
		case 1 -> throw new Exception("<Simple>");
		case 2 -> throw new RuntimeException("<Runtime>");
		case 3 -> throw new IOException("<IO>");
		case 4 -> throw new AppException("<Custom>");
		case 5 -> {
			i = 0;
			throw new ExceptionInInitializerError("<ExceptionInInitializerError>");
		}

		}
	}

}
