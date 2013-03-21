package org.olareoun.utils.collection;

import org.olareoun.utils.collection.IFilter;


public class SmallerThan10Filter implements IFilter<Integer>{

	public boolean check(Integer t) {
		return t.compareTo(10) >= 0;
	}

}
