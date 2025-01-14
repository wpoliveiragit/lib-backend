package br.com.pegasus.lib.module.exception.monitor.v1.util;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import br.com.pegasus.lib.module.exception.monitor.v1.annotation.advice.MonitorMethodAdvice;
import br.com.pegasus.lib.module.exception.monitor.v1.annotation.bean.MonitorClassBean;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MonitorUtil {

	private static final String MSG_LOG_WARN = "Nenhum método encontrado para a exceção: {}";
	private static final String MSG_LOG_ERROR = "Erro ao invocar handler da exceção: {}";

	private static final String MSG_EXCEPTION_ERROR = "Erro ao recuperar o Advice do contexto do Spring Boot: ";

	/** Recupera o advice definido na anotação {@link MonitorClassBean}. */
	public static Object createBean(DefaultListableBeanFactory defaultListableBeanFactory, Object monitoredBeans) {
		Class<?> clazz = monitoredBeans.getClass().getAnnotation(MonitorClassBean.class).value();
		try { // RECUPERA O BEAN DO ADVICE
			return defaultListableBeanFactory.getBean(clazz);
		} catch (Exception e) {
			throw new RuntimeException(MSG_EXCEPTION_ERROR + clazz.getCanonicalName(), e);
		}
	}

	/** Retorna um map contendo todos os métodos advice usando o atributo da anotação como chave*/
	public static Map<Class<?>, Method> createMap(Object adviceBean) {
		return Stream.of(adviceBean.getClass().getDeclaredMethods())
				.filter(method -> method.isAnnotationPresent(MonitorMethodAdvice.class))
				.filter(method -> method.getParameterCount() == 1)//
				.collect(Collectors.toMap(//
						method -> method.getAnnotation(MonitorMethodAdvice.class).value(), //
						method -> method));
	}

	/** Invoca o metodo do advice correspondente a exceção */
	public static void invokeMethod(Throwable ex, Object adviceBean, Method method) {
		if (method == null) {// AVISO: SEM MÉTODO ADVICE CORRESPONDENTE AO TIPO DA EXCEÇÃO
			log.warn(MSG_LOG_WARN, ex.getClass().getName());
		} else {
			try {//INVOCA O MÉTODO ADVICE CORRESPONDENTE
				method.invoke(adviceBean, ex);
			} catch (Throwable thr) {
				log.error(MSG_LOG_ERROR, ex.getMessage());
			}
		}
	}
}
