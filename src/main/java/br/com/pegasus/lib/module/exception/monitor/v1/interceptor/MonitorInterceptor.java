package br.com.pegasus.lib.module.exception.monitor.v1.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import br.com.pegasus.lib.module.exception.monitor.v1.annotation.bean.MonitorMethodBean;
import br.com.pegasus.lib.module.exception.monitor.v1.util.MonitorUtil;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MonitorInterceptor implements MethodInterceptor {

	private final Object monitoredBeans;
	private final Object adviceBean;
	private final Map<Class<?>, Method> methodAdviceMap;

	/** CRIA O INTERCEPTADOR DE EXCEÇÕES DA CLASSE DE MONITORAMENTO */
	public MonitorInterceptor(Object monitoredBeans, DefaultListableBeanFactory defaultListableBeanFactory) {
		this.monitoredBeans = monitoredBeans;
		this.adviceBean = MonitorUtil.createBean(defaultListableBeanFactory, monitoredBeans);
		this.methodAdviceMap = MonitorUtil.createMap(adviceBean);
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		try { // EXECUTA TODOS OS METODOS DAS CLASSES MONITORADAS
			return proxy.invoke(monitoredBeans, args);
		} catch (Throwable ex) {
			//FILTRO DE MÉTODOS MONITORADOS
			if (method.isAnnotationPresent(MonitorMethodBean.class)) {
				MonitorUtil.invokeMethod(ex, adviceBean, methodAdviceMap.get(ex.getClass()));
				return null;
			}
			throw ex;//CASO CONTRÁRIO, REPASSA A EXCEÇÃO
		}
	}

}
