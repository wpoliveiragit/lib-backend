package br.com.pegasus.lib.module.monitor.infra.interceptor.v1.factory;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.cglib.proxy.MethodProxy;

import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.MonitorInterceptor;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.AfterMethodLog;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.AllMethodLog;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.BeforeAfterMethodLog;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.BeforeMethodLog;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.OnExceptionMethodLog;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.method.LogMethod;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.plugin.AfterMethodLogPlugin;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.plugin.BeforeMethodLogPlugin;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.plugin.OnExceptionMethodLogPlugin;

public final class LogPluginFactory {

	private static final String MSG_PATTERN_LOG = "{local-Log} -> {method-Name} :: {logMsg}";

	public void createBeforeMethodLogPlugin(MonitorInterceptor monitorInterceptor, Object bean,
			List<Method> methodList) {

		LogMethod methodLog = new LogMethod() {

			@Override
			public void before(Object customBean, Method method, Object[] args, MethodProxy proxy, Logger log,
					String logMsg) {
				if (method.isAnnotationPresent(BeforeMethodLog.class)) {
					log.info(logMsg);
				}
			}
		};

		Map<Method, String> methodBegineMap = methodList.stream().collect(Collectors.toMap(//
				m -> m, m -> MSG_PATTERN_LOG//
						.replace("{local-Log}", BeforeMethodLog.class.getSimpleName())//
						.replace("{method-Name}", m.getName())///
						.replace("{logMsg}", m.getAnnotation(BeforeMethodLog.class).value())//
		));

		monitorInterceptor.add(new BeforeMethodLogPlugin(bean, methodBegineMap, methodLog));
	}

	public void createAfterMethodLogPlugin(MonitorInterceptor monitorInterceptor, //
			Object bean, List<Method> methodList) {
		LogMethod methodLog = new LogMethod() {

			@Override
			public void before(Object customBean, Method method, Object[] args, MethodProxy proxy, Logger log,
					String logMsg) {
				if (method.isAnnotationPresent(AfterMethodLog.class)) {
					log.info(logMsg);
				}
			}
		};

		Map<Method, String> methodAfterMap = methodList.stream().collect(Collectors.toMap(//
				m -> m, m -> MSG_PATTERN_LOG//
						.replace("{local-Log}", AfterMethodLog.class.getSimpleName())//
						.replace("{method-Name}", m.getName())///
						.replace("{logMsg}", m.getAnnotation(AfterMethodLog.class).value())//
		));

		monitorInterceptor.add(new AfterMethodLogPlugin(bean, methodAfterMap, methodLog));
	}

	public void createOnExceptionMethodLogPlugin(MonitorInterceptor monitorInterceptor, //
			Object bean, List<Method> methodList) {
		LogMethod methodLog = new LogMethod() {

			@Override
			public void onException(Object customBean, Method method, Object[] args, MethodProxy proxy, Throwable ex,
					Logger log, String logMsg) {
				if (method.isAnnotationPresent(OnExceptionMethodLog.class)) {
					log.info(logMsg);
				}
			}
		};

		Map<Method, String> methodExceptionMap = methodList.stream().collect(Collectors.toMap(//
				m -> m, m -> MSG_PATTERN_LOG//
						.replace("{local-Log}", OnExceptionMethodLog.class.getSimpleName())//
						.replace("{method-Name}", m.getName())///
						.replace("{logMsg}", m.getAnnotation(OnExceptionMethodLog.class).value())//
		));

		monitorInterceptor.add(new OnExceptionMethodLogPlugin(bean, methodExceptionMap, methodLog));
	}

	public void createBeforeAfterMethodLogPlugin(MonitorInterceptor monitorInterceptor, //
			Object bean, List<Method> methodList) {
		LogMethod methodLog = new LogMethod() {

			@Override
			public void before(Object customBean, Method method, Object[] args, MethodProxy proxy, Logger log,
					String logMsg) {
				if (method.isAnnotationPresent(AllMethodLog.class)) {
					log.info(logMsg);
				}
			}

			@Override
			public void after(Object customBean, Method method, Object[] args, MethodProxy proxy, Object returnObj,
					Logger log, String logMsg) {
				if (method.isAnnotationPresent(AllMethodLog.class)) {
					log.info(logMsg);
				}
			}
		};

		Map<Method, String> methodBegineMap = methodList.stream().collect(Collectors.toMap(//
				m -> m, m -> MSG_PATTERN_LOG//
						.replace("{local-Log}", BeforeMethodLog.class.getSimpleName())//
						.replace("{method-Name}", m.getName())///
						.replace("{logMsg}", m.getAnnotation(BeforeAfterMethodLog.class).msgBegine())//
		));

		Map<Method, String> methodAfterMap = methodList.stream().collect(Collectors.toMap(//
				m -> m, m -> MSG_PATTERN_LOG//
						.replace("{local-Log}", AfterMethodLog.class.getSimpleName())//
						.replace("{method-Name}", m.getName())///
						.replace("{logMsg}", m.getAnnotation(BeforeAfterMethodLog.class).msgAfter())//
		));

		monitorInterceptor.add(new BeforeMethodLogPlugin(bean, methodBegineMap, methodLog));
		monitorInterceptor.add(new AfterMethodLogPlugin(bean, methodAfterMap, methodLog));
	}

	public void createAllMethodLogPlugin(MonitorInterceptor monitorInterceptor, //
			Object bean, List<Method> methodList) {

		LogMethod methodLog = new LogMethod() {

			@Override
			public void before(Object customBean, Method method, Object[] args, MethodProxy proxy, Logger log,
					String logMsg) {
				if (method.isAnnotationPresent(AllMethodLog.class)) {
					log.info(logMsg);
				}
			}

			@Override
			public void after(Object customBean, Method method, Object[] args, MethodProxy proxy, Object returnObj,
					Logger log, String logMsg) {
				if (method.isAnnotationPresent(AllMethodLog.class)) {
					log.info(logMsg);
				}
			}

			@Override
			public void onException(Object customBean, Method method, Object[] args, MethodProxy proxy, Throwable ex,
					Logger log, String logMsg) {
				if (method.isAnnotationPresent(AllMethodLog.class)) {
					log.info(logMsg);
				}
			}
		};

		Map<Method, String> methodBegineMap = methodList.stream().collect(Collectors.toMap(//
				m -> m, m -> MSG_PATTERN_LOG//
						.replace("{local-Log}", BeforeMethodLog.class.getSimpleName())//
						.replace("{method-Name}", m.getName())///
						.replace("{logMsg}", m.getAnnotation(AllMethodLog.class).msgBegine())//
		));

		Map<Method, String> methodAfterMap = methodList.stream().collect(Collectors.toMap(//
				m -> m, m -> MSG_PATTERN_LOG//
						.replace("{local-Log}", AfterMethodLog.class.getSimpleName())//
						.replace("{method-Name}", m.getName())///
						.replace("{logMsg}", m.getAnnotation(AllMethodLog.class).msgAfter())//
		));

		Map<Method, String> methodExceptionMap = methodList.stream().collect(Collectors.toMap(//
				m -> m, m -> MSG_PATTERN_LOG//
						.replace("{local-Log}", OnExceptionMethodLog.class.getSimpleName())//
						.replace("{method-Name}", m.getName())///
						.replace("{logMsg}", m.getAnnotation(AllMethodLog.class).msgException())//
		));

		monitorInterceptor.add(new BeforeMethodLogPlugin(bean, methodBegineMap, methodLog));
		monitorInterceptor.add(new AfterMethodLogPlugin(bean, methodAfterMap, methodLog));
		monitorInterceptor.add(new OnExceptionMethodLogPlugin(bean, methodExceptionMap, methodLog));
	}

}
