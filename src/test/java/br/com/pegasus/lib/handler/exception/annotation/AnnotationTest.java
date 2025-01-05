//package br.com.pegasus.lib.handler.exception.annotation;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import br.com.pegasus.lib.handler.exception.annotation.aspect.ExceptionMonitorAspect;
//import br.com.pegasus.lib.handler.exception.annotation.suport.AnnotationApplication;
//import br.com.pegasus.lib.handler.exception.annotation.suport.AnnotationService;
//
//@SpringBootTest(classes = AnnotationApplication.class)
//public class AnnotationTest {
//
//	private @Autowired ExceptionMonitorAspect exceptionMonitorAspect;
//
//	@Test
//	public void meyhod() {
//
//		// mock
//		ExceptionMonitorAspect handleExceptionAspect = Mockito.mock(ExceptionMonitorAspect.class);
//
//		// Criação do proxy para a classe DemoService
//		AspectJProxyFactory factory = new AspectJProxyFactory(new AnnotationService());
//		factory.addAspect(handleExceptionAspect);
//
//		AnnotationService demoService = factory.getProxy();
//
//		// test
//		// Configuração para simular o comportamento do aspecto
//		doNothing().when(handleExceptionAspect).handleException(any(), any());
//
//		// Executa o método que lançará a exceção
//		assertThrows(RuntimeException.class, () -> demoService.task());
//
//		// Verifica se o aspecto foi acionado
//		verify(handleExceptionAspect, times(1)).handleException(any(), any());
//
//	}
//}
