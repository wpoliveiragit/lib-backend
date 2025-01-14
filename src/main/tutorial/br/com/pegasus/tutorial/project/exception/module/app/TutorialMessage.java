package br.com.pegasus.tutorial.project.exception.module.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.pegasus.tutorial.project.exception.module.domain.TutorialService;

@Component
public class TutorialMessage {

	private @Autowired TutorialService anyService;

	@Scheduled(fixedRate = 25)
	public void monitoring() throws Exception {
		anyService.monitoring();
	}

}