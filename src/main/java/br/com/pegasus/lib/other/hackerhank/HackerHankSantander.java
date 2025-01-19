package br.com.pegasus.lib.other.hackerhank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HackerHankSantander {

	public static void main(String[] args) throws Exception {
		List<Ar> arList = new ArrayList<>();

		arList.add(new Ar(2, Stream.of("1 2 1 2 1 3 2".split(" "))//
				.map(Integer::parseInt)//
				.collect(Collectors.toList())));

		arList.add(new Ar(3, Stream.of("10 20 20 10 10 30 50 10 20".split(" "))//
				.map(Integer::parseInt)//
				.collect(Collectors.toList())));

		arList.add(new Ar(6, Stream.of("1 2 1 2 1 3 2 3 6 6 7 7 7 7".split(" "))//
				.map(Integer::parseInt)//
				.collect(Collectors.toList())));

		arList.forEach(ar -> {
			int result = sockMerchant(ar.list.size(), ar.list);
			System.out.println("result: " + result);
			System.out.println("expec: " + ar.expec);
		});
	}

	public static int sockMerchant(int n, List<Integer> ar) {
		List<Integer> arClone = new ArrayList<Integer>(ar);
		int ret = 0;
		while (!arClone.isEmpty()) {
			int i = arClone.remove(0);
			arClone.removeAll(Collections.singleton(i));
			ret += Collections.frequency(ar, i) / 2;
		}
		return ret;
	}

	static class Ar {
		List<Integer> list;
		int expec;

		Ar(int expec, List<Integer> list) {
			this.list = list;
			this.expec = expec;
		}
	}

}
