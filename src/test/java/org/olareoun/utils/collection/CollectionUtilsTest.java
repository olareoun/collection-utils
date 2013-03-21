package org.olareoun.utils.collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.olareoun.utils.collection.CollectionUtils;
import org.olareoun.utils.collection.EmptyListException;
import org.olareoun.utils.collection.Function;
import org.olareoun.utils.collection.Max;
import org.olareoun.utils.collection.MonoFunction;

public class CollectionUtilsTest {

	@Test
	public void test_map_lista_vacia_returns_lista_vacia(){
		assertEquals(0, CollectionUtils.map(new ArrayList<String>(), new MonoFunction<String, String>() {
			public String apply(String arg) {
				return arg;
			}
		}).size());
	}
	
	@Test
	public void test_map_lista_returns_una_lista_del_mismo_size(){
		List<String> lista = new ArrayList<String>();
		lista.add("a");
		lista.add("b");
		assertEquals(lista.size(), CollectionUtils.map(lista, new MonoFunction<String, String>() {
			public String apply(String arg) {
				return arg;
			}
		}).size());
	}
	
	@Test(expected=EmptyListException.class)
	public void test_reduce_lista_vacia() throws EmptyListException{
		CollectionUtils.reduce(new ArrayList<Integer>(), new Max());
	}
	
	@Test
	public void test_reduce_one_element_list() throws EmptyListException{
		Object element = null;
		List<Object> lista = new ArrayList<Object>();
		lista.add(element);
		assertEquals(element, CollectionUtils.reduce(lista, new Function<Object>(){
			public Object apply(Object arg0, Object arg1) {
				return null;
			}
			
		}));
	}
	
	@Test
	public void test_reduce_two_elements_list_returns_the_result_of_applying_function_to_both() throws EmptyListException{
		List<Integer> lista = Arrays.asList(new Integer[]{0, 1});
		assertEquals(Integer.valueOf(1), CollectionUtils.reduce(lista, new Max()));
	}

	@Test
	public void test_reduce_iterative() throws EmptyListException{
		List<Integer> lista = Arrays.asList(new Integer[]{0, 5, 2});
		assertEquals(Integer.valueOf(5), CollectionUtils.reduce(lista, new Max()));
	}
	
	@Test
	public void test_plain_null_list(){
		assertNotNull(CollectionUtils.plain(null));
		assertEquals(0, CollectionUtils.plain(null).size());
	}
	
	@Test
	public void test_plain_list_of_lists_returns_a_plain_list_with_all_the_elements(){
		List<Integer> list1 = Arrays.asList(new Integer[]{1, 2, 3});
		List<Integer> list2 = Arrays.asList(new Integer[]{4, 5});
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		list.add(list2);
		list.add(list1);
		assertTrue(CollectionUtils.plain(list).containsAll(Arrays.asList(new Integer[]{1, 2, 3, 4, 5})));
	}

}
