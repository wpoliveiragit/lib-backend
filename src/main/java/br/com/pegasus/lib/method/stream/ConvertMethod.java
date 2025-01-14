package br.com.pegasus.lib.method.stream;

import java.util.Map;
import java.util.function.Function;

public final class ConvertMethod {

	public static <K, V, U, T> Map<K, V> toMap(Function<? super T, ? extends K> keyMapper,
			Function<? super T, ? extends U> valueMapper) {
		return null;
	}

}
