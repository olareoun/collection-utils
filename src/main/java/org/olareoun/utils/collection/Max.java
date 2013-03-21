package org.olareoun.utils.collection;

public class Max implements Function<Integer> {

	public Integer apply(Integer arg0, Integer arg1) {
		return arg0.compareTo(arg1) <= 0? arg1: arg0;
	}

}
