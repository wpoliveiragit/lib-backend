package br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.method;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.springframework.cglib.proxy.MethodProxy;

public interface LogMethod {

	default void before(Object customBean, Method method, Object[] args, MethodProxy proxy, Logger log, String logMsg) {
	}

	default void after(Object customBean, Method method, Object[] args, MethodProxy proxy, Object returnObj, Logger log,
			String logMsg) {
	}

	default void onException(Object customBean, Method method, Object[] args, MethodProxy proxy, Throwable ex,
			Logger log, String logMsg) {
	}

}
