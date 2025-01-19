package br.com.pegasus.lib.module.exception.monitor.v1.annotation.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import br.com.pegasus.lib.module.exception.monitor.v1.annotation.advice.MonitorAdvice;
import br.com.pegasus.lib.module.exception.monitor.v1.processor.MonitorAddInterceptorProcessor;

/** Adicionado na classe que deseja monitorar. */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MonitorAddInterceptorProcessor.class)
public @interface MonitorClassBean {
	/** Tipo da classe que foi implementado a anotação {@link MonitorAdvice}. */
	Class<?> value();
}
