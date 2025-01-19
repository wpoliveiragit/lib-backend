package br.com.pegasus.lib.method.convert;

import java.util.stream.Stream;

public class SuportConvert {

	public static final int[] toArrayInt(String... values) {
		return Stream.of(values).mapToInt(Integer::parseInt).toArray();
	}

	
}
