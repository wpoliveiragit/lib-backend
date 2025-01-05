package br.com.pegasus.lib.handler.exception.annotation.aspect;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import br.com.pegasus.lib.handler.exception.annotation.advice.ExceptionHandlerMethod;
import br.com.pegasus.lib.handler.exception.annotation.advice.MonitorAdvice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ExceptionMonitorInterceptor implements MethodInterceptor {

	private final Object bean;
	private final MonitorAdvice monitorAdvice;

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		try {
			return proxy.invoke(bean, args);
		} catch (Throwable ex) {
			invokeExceptionHandler(ex);
			return null;
		}
	}

	private void invokeExceptionHandler(Throwable ex) {
		for (Method method : monitorAdvice.getClass().getDeclaredMethods()) {
			if (method.isAnnotationPresent(ExceptionHandlerMethod.class)) {
				Class<?>[] parameter = method.getParameterTypes();
				if (parameter[0].equals(ex.getClass())) {
					try {
						method.invoke(monitorAdvice, ex);
					} catch (Exception e) {
						log.error("Erro ao invocar handler de exceção: {}", e.getMessage());
					}
					return;
				}
			}
		}
		log.warn("Nenhum método encontrado para a exceção: {}", ex.getClass().getName());
	}
}