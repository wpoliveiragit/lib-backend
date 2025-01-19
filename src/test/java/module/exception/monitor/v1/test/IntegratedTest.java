package module.exception.monitor.v1.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.pegasus.lib.module.exception.monitor.v1.annotation.bean.MonitorClassBean;
import br.com.pegasus.lib.module.exception.monitor.v1.util.MonitorUtil;
import module.exception.monitor.v1.project.AnyAdvice;
import module.exception.monitor.v1.project.AnyService;
import module.exception.monitor.v1.project.MainAnyApplication;

@SpringBootTest(classes = MainAnyApplication.class)
public class IntegratedTest {

	@Test
	@DisplayName("TESTE DE INTEGRADO")
	public void test01(@Autowired AnyService anyService, @Autowired AnyAdvice anyAdvice) throws Exception {
		anyService.getCountDownLatch().await();
		for (boolean b : anyAdvice.getCheck()) {
			assertTrue(b);
		}
	}

	@Test
	@DisplayName("TESTE DE COBERTURA")
	public void test02(@Autowired DefaultListableBeanFactory defaultListableBeanFactory) throws Exception {
		//TESTE DE COBERTURA
		new MonitorUtil();
		assertDoesNotThrow(() -> MonitorUtil.invokeMethod(new Exception(), null, IntegratedTest.class.getMethods()[0]));
		assertThrows(Exception.class, () -> MonitorUtil.createBean(defaultListableBeanFactory, new TestClass()));
	}

	@MonitorClassBean(IntegratedTest.class)
	static class TestClass {
	}

}
