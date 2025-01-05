package br.com.pegasus.lib.handler.exception.listener.suport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListenerApplication.class, args);
	}

}
