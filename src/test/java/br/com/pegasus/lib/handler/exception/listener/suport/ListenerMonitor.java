package br.com.pegasus.lib.handler.exception.listener.suport;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.pegasus.lib.handler.exception.listener.ExceptionListener;
import lombok.Getter;
import suport.CheckSuport;

@Component
public class ListenerMonitor {

	private @Autowired ExceptionListener sniffer;

	private static int i = 0;
	private @Getter CheckSuport latch = new CheckSuport();

	@Scheduled(fixedRate = 25)
	public void monitoring() {
		sniffer.listening(() -> {
			switch (++i) {
			case 1 -> throw new Exception("<Simple>");
			case 2 -> throw new RuntimeException("<Runtime>");
			case 3 -> throw new IOException("<IO>");
			case 4 -> throw new ListenerException("<Custom>");
			default -> latch.finishing();
			}
		});
	}
}