package org.olareoun.utils.collection;

import org.olareoun.utils.collection.IFilter;


public class NonEmptyString implements IFilter<String>{

	public boolean check(String t) {
		return t.length() != 0;
	}
	
}

