package br.com.pegasus.lib.module.exception.monitor.v1.processor;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.cglib.proxy.Enhancer;

import br.com.pegasus.lib.module.exception.monitor.v1.annotation.bean.MonitorClassBean;
import br.com.pegasus.lib.module.exception.monitor.v1.interceptor.MonitorInterceptor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MonitorAddInterceptorProcessor implements BeanPostProcessor {

	private final DefaultListableBeanFactory defaultListableBeanFactory;

	@Override // ADD CLASSE INTERCEPTOR NO BEAN COM {@link ExceptionClassMonitor.}
	public Object postProcessAfterInitialization(Object bean, String beanName) {
		if (bean.getClass().isAnnotationPresent(MonitorClassBean.class)) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(bean.getClass());
			enhancer.setCallback(new MonitorInterceptor(bean, defaultListableBeanFactory));
			return enhancer.create();
		}
		return bean;
	}
}