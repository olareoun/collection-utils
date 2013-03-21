package org.olareoun.utils.collection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CollectionUtils {
	
	public static class Numbers{
		public static List<Integer> convert(List<String> strList){
			return map(strList, new MonoFunction<Integer, String>() {
				public Integer apply(String arg) {
					return Integer.valueOf(arg);
				}
			});
		}
		
		public static Integer add(List<Integer> lista){
			try {
				return reduce(lista, new Function<Integer>() {
					public Integer apply(Integer arg0, Integer arg1) {
						return arg0 + arg1;
					}
				});
			} catch (EmptyListException e) {
				return 0;
			}
		}
	}
	
	public static class BigDecimals{
		public static BigDecimal add(List<BigDecimal> lista){
			try {
				return reduce(lista, new Function<BigDecimal>(){
					public BigDecimal apply(BigDecimal arg0, BigDecimal arg1) {
						return arg0.add(arg1);
					}
					
				});
			} catch (EmptyListException e) {
				return BigDecimal.ZERO;
			}
		}
	}
	
	public static <T> List<T> evenPositions(List<T> lista){
		return positions(lista, 1);
	}

	public static <T> List<T> oddPositions(List<T> lista){
		return positions(lista, 0);
	}

	private static <T> List<T> positions(List<T> lista, int initIndex) {
		List<T> newList = new ArrayList<T>();
		while (initIndex < lista.size()) {
			newList.add(lista.get(initIndex));
			initIndex += 2;
		}
		return newList;
	}
	
	public static <T> List<T> filter(List<T> lista, Class<? extends IFilter<T>>... filterClasses) {
		return CollectionFilter.filter(lista, filterClasses);
	}
	
	public static <T> List<T> filter(List<T> lista, IFilter<T>... filters) {
		return CollectionFilter.filter(lista, filters);
	}
	
	public static <T> void each(List<T> lista, Procedure<T> procedure){
		for (T t : lista) {
			procedure.apply(t);
		}
	}
	
	public static <K, T> List<K> map(List<T> lista, MonoFunction<K, T> function){
		List<K> resultList = new ArrayList<K>();
		for (T t : lista) {
			resultList.add(function.apply(t));
		}
		return resultList;
	}
	
	public static <T> T reduce(List<T> lista, Function<T> function) throws EmptyListException {
		if (lista.isEmpty()){
			throw new EmptyListException();
		}
		if (lista.size() == 1){
			return lista.get(0);
		}
		if (lista.size() == 2){
			return function.apply(lista.get(0), lista.get(1));
		}
		else return function.apply(lista.get(0), reduce(lista.subList(1, lista.size()), function));
	}

	public static <T> List<T> mergeLists(List<T>... lists) {
		return Merge.mergeLists(lists);
	}

	public static <T> List<T> merge(List<T> list1, List<T> list2) {
		return Merge.merge(list1, list2);
	}
	
	public static <T> List<T> merge(List<T> list1, List<T> list2, Merger<T> merger){
		return Merge.merge(list1, list2, merger);
	}

	@SuppressWarnings("unchecked")
	public static <T> boolean contains(final List<T> mapped, final T obj1, final MergeCriteria<T> criteria) {
		return CollectionUtils.filter(mapped, new IFilter<T>() {
			public boolean check(T t) {
				return criteria.check(t, obj1);
			}
		}).size() > 0;
	}

	public static <T> List<T> plain(List<List<T>> list) {
		final ArrayList<T> result = new ArrayList<T>();
		if (list == null){
			return result;
		}
		if (list.size() == 0){
			return result;
		}
		for (List<T> sublist: list){
			for (T t: sublist){
				result.add(t);
			}
		}
		return result;
	}

}
