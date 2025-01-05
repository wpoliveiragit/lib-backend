package br.com.pegasus.lib.handler.exception.listener;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.pegasus.lib.handler.exception.listener.suport.ListenerApplication;
import br.com.pegasus.lib.handler.exception.listener.suport.ListenerGlobalExceptionAdvice;
import br.com.pegasus.lib.handler.exception.listener.suport.ListenerMonitor;

@SpringBootTest(classes = ListenerApplication.class)
public class ListenerTest {

	private @Autowired ListenerGlobalExceptionAdvice listenerGlobalExceptionAdvice;
	private @Autowired ListenerMonitor observer;

	@Test
	public void testScheduledTask() throws Exception {
		observer.getLatch().await();
		for (boolean b : listenerGlobalExceptionAdvice.getCheck()) {
			assertTrue(b);
		}
	}

}
