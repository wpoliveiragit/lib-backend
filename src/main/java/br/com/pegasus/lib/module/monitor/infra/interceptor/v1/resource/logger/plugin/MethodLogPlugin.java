package br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.plugin;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Logger;

import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.method.LogMethod;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class MethodLogPlugin {

	protected final Map<Method, String> methodMap;
	protected final LogMethod methodLog;
	protected final Logger log;

}
