package br.com.pegasus.lib.module.monitor.infra.interceptor.v1.invoke;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodProxy;

public interface AfterBlockInvoke {

	void intercept(Object beanCustom, Method method, Object[] args, MethodProxy proxy, Object returnMethod);

}
