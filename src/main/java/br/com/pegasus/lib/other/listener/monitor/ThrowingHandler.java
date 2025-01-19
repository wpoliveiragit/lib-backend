package br.com.pegasus.lib.other.listener.monitor;

@FunctionalInterface
public interface ThrowingHandler {
	void run() throws Exception;
}