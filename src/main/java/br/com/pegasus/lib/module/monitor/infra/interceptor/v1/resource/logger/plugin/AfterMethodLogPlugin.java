package br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.plugin;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.cglib.proxy.MethodProxy;

import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.invoke.AfterBlockInvoke;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.method.LogMethod;

public class AfterMethodLogPlugin extends MethodLogPlugin implements AfterBlockInvoke {

	public AfterMethodLogPlugin(Object bean, Map<Method, String> methodMap, LogMethod methodLog) {
		super(methodMap, methodLog, Logger.getLogger(bean.getClass().getSimpleName()));
	}

	@Override
	public void intercept(Object customBean, Method method, Object[] args, MethodProxy proxy, Object returnObj) {
		methodLog.after(customBean, method, args, proxy, returnObj, log, methodMap.get(method));
	}

}
