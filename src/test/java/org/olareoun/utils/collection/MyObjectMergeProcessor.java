package org.olareoun.utils.collection;

import org.olareoun.utils.collection.MergeProcess;


public class MyObjectMergeProcessor implements MergeProcess<MyObject>{

	public MyObject process(MyObject obj1, MyObject obj2) {
		return new MyObject(obj1.id, obj1.property.add(obj2.property));
	}
	
}
