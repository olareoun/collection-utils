package org.olareoun.utils.collection;



public class NonAString implements IFilter<String>{

	public boolean check(String t) {
		if (t.equals("a")){
			return false;
		}
		return true;
	}

}
