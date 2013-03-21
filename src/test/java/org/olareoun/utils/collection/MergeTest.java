package org.olareoun.utils.collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.olareoun.utils.collection.Merge;
import org.olareoun.utils.collection.Merger;


public class MergeTest {

	@Test
	public void test_mergeNullListInToEmptyListNotNullResult() {
		List<?> result = Merge.merge(null, new ArrayList<Object>());
		assertNotNull(result);
	}

	@Test
	public void test_mergeEmptyListInToEmptyListNotNullResult() {
		List<?> result = Merge.merge(new ArrayList<Object>(), new ArrayList<Object>());
		assertNotNull(result);
	}

	@Test
	public void test_mergeEmptyListInToEmptyListReturnsEmptyList() {
		List<?> result = Merge.merge(new ArrayList<Object>(), new ArrayList<Object>());
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void test_mergeListsWithDifferentObjectsReturnListWithAll(){
		List<String> list1 = new ArrayList<String>();
		String myStr = "aaaaaaaaaaaa";
		list1.add(myStr);
		List<String> list2 = new ArrayList<String>();
		String myStr2 = "bbb";
		list2.add(myStr2);
		List<?> result = Merge.merge(list1, list2);
		assertTrue(result.contains(myStr) && result.contains(myStr2));
	}

	@Test
	public void test_mergeListsWithEqualObjectsReturnListWithOneOfThem(){
		List<String> list1 = new ArrayList<String>();
		String myStr = "aaaaaaaaaaaa";
		list1.add(myStr);
		List<String> list2 = new ArrayList<String>();
		list2.add(myStr);
		List<?> result = Merge.merge(list1, list2);
		assertTrue(result.size() == 1 && result.contains(myStr));
	}
	
	@Test
	public void test_merging_with_merger() throws InstantiationException, IllegalAccessException{
		List<MyObject> list1 = new ArrayList<MyObject>();
		list1.add(new MyObject("1", BigDecimal.valueOf(33.33)));
		MyObject nonMergedObj1 = new MyObject("2", BigDecimal.valueOf(44.44));
		list1.add(nonMergedObj1);

		List<MyObject> list2 = new ArrayList<MyObject>();
		list2.add(new MyObject("1", BigDecimal.valueOf(33.33)));
		list2.add(new MyObject("1", BigDecimal.valueOf(22.22)));
		MyObject nonMergedObj2 = new MyObject("3", BigDecimal.valueOf(55.55));
		list2.add(nonMergedObj2);

		List<MyObject> result = Merge.merge(list1, list2, new Merger<MyObject>(MyObjectMergeCriteria.class, MyObjectMergeProcessor.class));
		
		assertEquals(3, result.size());
		for(MyObject myobj: result){
			if (myobj.id.equals("1")){
				assertEquals(BigDecimal.valueOf(88.88), myobj.property);
			}
			else if (myobj.id.equals("2")){
				assertEquals(nonMergedObj1, myobj);
			}
			else if (myobj.id.equals("3")){
				assertEquals(nonMergedObj2, myobj);
			}
		}
	}
	
}
