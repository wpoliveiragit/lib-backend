package br.com.pegasus.lib.handler.exception.listener;

@FunctionalInterface
public interface FunctionalMethodListener {
	void execution() throws Exception;
}