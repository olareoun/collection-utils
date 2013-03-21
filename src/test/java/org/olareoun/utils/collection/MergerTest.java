package org.olareoun.utils.collection;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.olareoun.utils.collection.Merger;


public class MergerTest {

	@Test
	public void test_non_passing_merge_criteria() {
		Merger<MyObject> merger = new Merger<MyObject>(MyObjectMergeCriteria.class, MyObjectMergeProcessor.class);
		MyObject obj1 = new MyObject("1", BigDecimal.valueOf(33));
		MyObject merged = merger.merge(obj1, new MyObject("2", BigDecimal.valueOf(44)));
		assertEquals(obj1, merged);
	}
	
	@Test
	public void test_passing_merge_criteria() {
		Merger<MyObject> merger = new Merger<MyObject>(MyObjectMergeCriteria.class, MyObjectMergeProcessor.class);
		MyObject merged = merger.merge(new MyObject("1", BigDecimal.valueOf(33)), new MyObject("1", BigDecimal.valueOf(44)));
		assertEquals("1", merged.id);
		assertEquals(BigDecimal.valueOf(77), merged.property);
	}
	
	@Test
	public void test_merge_non_passing_on_list() throws InstantiationException, IllegalAccessException{
		Merger<MyObject> merger = new Merger<MyObject>(MyObjectMergeCriteria.class, MyObjectMergeProcessor.class);
		List<MyObject> list = new ArrayList<MyObject>();
		list.add(new MyObject("1", BigDecimal.valueOf(1)));
		list.add(new MyObject("2", BigDecimal.valueOf(2)));
		MyObject obj = new MyObject("3", BigDecimal.valueOf(3));

		List<MyObject> merged = merger.merge(obj, list);

		assertEquals(3, merged.size());
	}
	
	@Test
	public void test_merge_passing_on_list() throws InstantiationException, IllegalAccessException{
		Merger<MyObject> merger = new Merger<MyObject>(MyObjectMergeCriteria.class, MyObjectMergeProcessor.class);
		List<MyObject> list = new ArrayList<MyObject>();
		list.add(new MyObject("1", BigDecimal.valueOf(1)));
		list.add(new MyObject("2", BigDecimal.valueOf(2)));
		MyObject obj = new MyObject("1", BigDecimal.valueOf(3));

		List<MyObject> merged = merger.merge(obj, list);

		assertEquals(2, merged.size());
		for (MyObject myobj: merged){
			if (myobj.id.equals("1")){
				assertEquals(BigDecimal.valueOf(4), myobj.property);
			}
		}
	}
	
}
