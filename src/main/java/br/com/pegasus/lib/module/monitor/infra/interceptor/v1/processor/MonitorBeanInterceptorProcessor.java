package br.com.pegasus.lib.module.monitor.infra.interceptor.v1.processor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.cglib.proxy.Enhancer;

import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.MonitorInterceptor;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.factory.LogPluginFactory;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.factory.MonitorAdvicePluginFactory;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.exception.advice.MonitorClassAdvice;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.AfterMethodLog;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.AllMethodLog;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.BeforeAfterMethodLog;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.BeforeMethodLog;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.OnExceptionMethodLog;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MonitorBeanInterceptorProcessor implements BeanPostProcessor {

	private final DefaultListableBeanFactory defaultListableBeanFactory;

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) {
		MonitorInterceptor monitorInterceptor = new MonitorInterceptor();
		boolean isBeanMonitoring = false;

		// monitoramentos
		isBeanMonitoring = checkMonitorAdvice(bean, monitorInterceptor);
		isBeanMonitoring = checkMonitorMethodLog(bean, monitorInterceptor);

		// create monitoring for bean
		if (isBeanMonitoring) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(bean.getClass());
			enhancer.setCallback(monitorInterceptor);
			return enhancer.create();
		}
		return bean;
	}

	private boolean checkMonitorAdvice(Object bean, MonitorInterceptor monitorInterceptor) {
		if (bean.getClass().isAnnotationPresent(MonitorClassAdvice.class)) {
			MonitorAdvicePluginFactory.createMonitorAdvicePlugin(bean, defaultListableBeanFactory, monitorInterceptor);
			return true;
		}
		return false;
	}

	private boolean checkMonitorMethodLog(Object bean, MonitorInterceptor monitorInterceptor) {
		boolean isBeanMonitoring = false;

		List<Method> begineLogs = new ArrayList<>();
		List<Method> afterLogs = new ArrayList<>();
		List<Method> beforeAfterLogs = new ArrayList<>();
		List<Method> onExLogs = new ArrayList<>();
		List<Method> AllLogs = new ArrayList<>();

		for (Method method : bean.getClass().getDeclaredMethods()) {
			if (method.isAnnotationPresent(BeforeMethodLog.class)) {
				begineLogs.add(method);
			}
			if (method.isAnnotationPresent(AfterMethodLog.class)) {
				afterLogs.add(method);
			}
			if (method.isAnnotationPresent(BeforeAfterMethodLog.class)) {
				beforeAfterLogs.add(method);
			}
			if (method.isAnnotationPresent(OnExceptionMethodLog.class)) {
				onExLogs.add(method);
			}
			if (method.isAnnotationPresent(AllMethodLog.class)) {
				AllLogs.add(method);
			}
		}

		LogPluginFactory logPluginFactory = new LogPluginFactory();
		if (begineLogs.size() > 0) {
			logPluginFactory.createBeforeMethodLogPlugin(monitorInterceptor, bean, begineLogs);
			isBeanMonitoring = true;
		}
		if (afterLogs.size() > 0) {
			logPluginFactory.createAfterMethodLogPlugin(monitorInterceptor, bean, afterLogs);
			isBeanMonitoring = true;
		}
		if (beforeAfterLogs.size() > 0) {
			logPluginFactory.createBeforeAfterMethodLogPlugin(monitorInterceptor, bean, beforeAfterLogs);
			isBeanMonitoring = true;
		}
		if (onExLogs.size() > 0) {
			logPluginFactory.createOnExceptionMethodLogPlugin(monitorInterceptor, bean, onExLogs);
			isBeanMonitoring = true;
		}
		if (AllLogs.size() > 0) {
			logPluginFactory.createAllMethodLogPlugin(monitorInterceptor, bean, AllLogs);
			isBeanMonitoring = true;
		}

		return isBeanMonitoring;
	}

}