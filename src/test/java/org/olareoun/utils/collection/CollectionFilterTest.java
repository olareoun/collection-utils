package org.olareoun.utils.collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.olareoun.utils.collection.CollectionFilter;



public class CollectionFilterTest {

	@Test
	public void test_filter_list_with_one_empty_string_returns_empty_list_when_filtering_empty_strings() {
		List<String> lista = new ArrayList<String>();
		lista.add("");

		assertTrue(CollectionFilter.filter(lista, NonEmptyString.class).isEmpty());
	}
	
	@Test
	public void test_filter_list_with_one_non_empty_string_returns_a_list_with_the_string_when_filtering_empty_strings() {
		List<String> lista = new ArrayList<String>();
		String nonEmptyStr = "a";
		lista.add(nonEmptyStr);

		List<String> filtered = CollectionFilter.filter(lista, NonEmptyString.class);
		
		assertEquals(1, filtered.size());
		assertEquals(nonEmptyStr, filtered.get(0));
	}
	
	@Test
	public void test_filter_list_with_one_non_empty_and_one_empty_string_returns_a_list_with_the_non_empty_string_when_filtering_empty_strings() {
		List<String> lista = new ArrayList<String>();
		String nonEmptyStr = "a";
		lista.add(nonEmptyStr);
		lista.add("");

		List<String> filtered = CollectionFilter.filter(lista, NonEmptyString.class);
		
		assertEquals(1, filtered.size());
		assertEquals(nonEmptyStr, filtered.get(0));
	}
	
	@Test
	public void test_filter_list_with_one_non_empty_and_one_a_string_returns_empty_list_when_filtering_empty_strings_and_non_a_strings() {
		List<String> lista = new ArrayList<String>();
		String nonEmptyStr = "a";
		lista.add(nonEmptyStr);
		lista.add("");
		
		List<String> filtered = CollectionFilter.filter(lista, NonEmptyString.class, NonAString.class);

		assertTrue(filtered.isEmpty());
	}
	
	@Test
	public void test_filter_numbers_smaller_than_10(){
		List<Integer> lista = new ArrayList<Integer>();
		lista.add(1);
		lista.add(11);
		lista.add(9);
		List<Integer> filtered = CollectionFilter.filter(lista, SmallerThan10Filter.class);

		assertEquals(1, filtered.size());
		assertEquals(Integer.valueOf(11), filtered.get(0));
	}
	
}
