package br.com.pegasus.lib.app;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.pegasus.lib.handler.exception.annotation.monitor.ExceptionMonitor;

@Component
public class AppMonitor {

	private static int i = 0;

	private @Autowired AppService appService;

	@Scheduled(fixedRate = 500)
	public void monitoring() throws Exception {
		try {
//			appService.task();
			method();
		} catch (Exception ex) {
			System.out.println("bla");
			method2();
			System.out.println("bla");
		}
	}

	@ExceptionMonitor
	private void method() throws Exception {
		System.out.printf("method: %d  \n", ++i);
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

	@ExceptionMonitor
	private void method2() throws Exception {
		System.out.printf("method2: %d  \n", ++i);
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