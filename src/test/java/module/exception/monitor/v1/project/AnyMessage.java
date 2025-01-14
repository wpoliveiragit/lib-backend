package module.exception.monitor.v1.project;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AnyMessage {

	private final AnyService anyService;

	@Scheduled(fixedRate = 25)
	public void monitoring() throws Exception {
		anyService.monitorMethod();
	}

	@Scheduled(fixedRate = 25)
	public void monitoring2() throws Exception {
		try {
			anyService.otherMethod();
		} catch (Exception e) {
		}
	}

}