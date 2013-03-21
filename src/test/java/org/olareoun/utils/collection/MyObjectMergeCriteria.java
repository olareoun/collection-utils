package org.olareoun.utils.collection;

import org.olareoun.utils.collection.MergeCriteria;


public class MyObjectMergeCriteria implements MergeCriteria<MyObject>{

	public boolean check(MyObject obj1, MyObject obj2) {
		return obj1.id.equals(obj2.id);
	}

}
