package br.com.pegasus.lib.handler.exception.annotation.suport;

import java.util.concurrent.CountDownLatch;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.pegasus.lib.handler.exception.annotation.monitor.ExceptionMonitor;
import lombok.Setter;

@Component
public class AnnotationMonitor {

	private final AnnotationService annotationService;

	private @Setter CountDownLatch latch = new CountDownLatch(1);

	public AnnotationMonitor(AnnotationService annotationService) {
		this.annotationService = annotationService;
	}

	@ExceptionMonitor
	@Scheduled(fixedRate = 25)
	public void task() {
		try {
			System.out.println("Controller :: start");
			annotationService.task();
			System.out.println("Controller :: POS SERVICE");
		} catch (Exception e) {
			System.out.println("Controller :: EXCEPTION");
		}
		System.out.println("Controller :: FINISH");
		latch.countDown();
	}
}