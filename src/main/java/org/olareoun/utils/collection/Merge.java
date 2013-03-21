package org.olareoun.utils.collection;

import java.util.ArrayList;
import java.util.List;

class Merge {

	public static <T> List<T> mergeLists(List<T>... lists) {
		List<T> mergedList = new ArrayList<T>();
		for (int i = 0; i < lists.length; i++){
			mergedList = merge(lists[i], mergedList);
		}
		return mergedList;
	}

	public static <T> List<T> merge(
			List<T> list1,
			List<T> list2) {
		if (list1 == null){
			return merge(new ArrayList<T>(), list2);
		}
		if (list2 == null){
			return merge(list1, new ArrayList<T>());
		}
		return mergeNotNullLists(list1, list2);
	}

	private static <T> List<T> mergeNotNullLists(List<T> list1, List<T> list2) {
		List<T> mergedList = new ArrayList<T>();
		mergedList.addAll(list2);
		for (T o: list1){
			if (!mergedList.contains(o)){
				mergedList.add(o);
			}
		}
		return mergedList;
	}

	public static <T> List<T> merge(List<T> list1, final List<T> list2, final Merger<T> merger) {
		List<T> merged = new ArrayList<T>(list1);
		for (T t: list2){
			merged = merger.merge(t, merged);
		}
		return merged;
	}

}
