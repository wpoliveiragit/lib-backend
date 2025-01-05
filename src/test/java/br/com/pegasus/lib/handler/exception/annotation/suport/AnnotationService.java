package br.com.pegasus.lib.handler.exception.annotation.suport;

import org.springframework.stereotype.Service;

import br.com.pegasus.lib.handler.exception.annotation.monitor.ExceptionMonitor;

@Service
public class AnnotationService {

	@ExceptionMonitor
	public void task() {
		System.out.println("SERVICE :: Executando tarefa...");
		throw new RuntimeException("Erro durante a execução!");
	}
}