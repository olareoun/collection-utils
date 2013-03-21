package org.olareoun.utils.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class CollectionFilter {

	public static <T> List<T> filter(Collection<T> lista, IFilter<T>... filters) {
		List<T> filtered = new ArrayList<T>();
		for (T string : lista) {
			if (fulfillsFilters(string, Arrays.asList(filters))) filtered.add(string);
		}
		return filtered;
	}
	
	public static <T> List<T> filter(Collection<T> lista, Class<? extends IFilter<T>>... filterClasses) {
		List<T> filtered = new ArrayList<T>();
		List<IFilter<T>> filters = getFiltersInstances(filterClasses);
		for (T string : lista) {
			if (fulfillsFilters(string, filters)) filtered.add(string);
		}
		return filtered;
	}
	
	private static <T> List<IFilter<T>> getFiltersInstances(Class<? extends IFilter<T>>... filterClasses){
		List<IFilter<T>> filters = new ArrayList<IFilter<T>>();
		for (Class<? extends IFilter<T>> class1 : filterClasses) {
			filters.add(getFilterInstance(class1));
		}
		return filters;
	}
	
	private static <T> boolean fulfillsFilters(T string,
			List<IFilter<T>> filters) {
		if (filters.size() == 0){
			return true;
		}
		if (filters.size() == 1){
			return filters.get(0).check(string);
		}
		else return filters.get(0).check(string) && fulfillsFilters(string, filters.subList(1, filters.size()));
	}

	private static <T> IFilter<T> getFilterInstance(
			Class<? extends IFilter<T>> filterClass) {
		try {
			return filterClass.newInstance();
		} catch (Exception e) {
			return returnVoidFilter();
		}
	}

	private static <T> IFilter<T> returnVoidFilter() {
		return new IFilter<T>() {
			public boolean check(T t) {
				return true;
			}
		};
	}

}
