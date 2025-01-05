package br.com.pegasus.lib.handler.exception.annotation.aspect;

import java.lang.reflect.Method;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.stereotype.Component;

import br.com.pegasus.lib.handler.exception.annotation.advice.MonitorAdvice;
import br.com.pegasus.lib.handler.exception.annotation.monitor.ExceptionMonitor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExceptionMonitorPostProcessor implements BeanPostProcessor {

	private final MonitorAdvice monitorAdvice;

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) {
		for (Method method : bean.getClass().getDeclaredMethods()) {
			if (method.isAnnotationPresent(ExceptionMonitor.class)) {
				Enhancer enhancer = new Enhancer();
				enhancer.setSuperclass(bean.getClass());
				enhancer.setCallback(new ExceptionMonitorInterceptor(bean, monitorAdvice));
				return enhancer.create();
			}
		}
		return bean;
	}

}