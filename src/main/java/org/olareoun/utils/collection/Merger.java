package org.olareoun.utils.collection;

import java.util.List;

public class Merger<T> {

	private final MergeCriteria<T> criteria;
	private final MergeProcess<T> processor;

	public Merger(Class<? extends MergeCriteria<T>> criteria, Class<? extends MergeProcess<T>> processor){
		try {
			this.criteria = criteria.newInstance();
			this.processor = processor.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public T merge(T obj1, T obj2) {
		try {
			if (criteria.check(obj1, obj2)){
				return processor.process(obj1, obj2);
			}else{
				return obj1;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<T> merge(final T obj1, List<T> list){
		List<T> mapped = CollectionUtils.map(list, new MonoFunction<T, T>() {
			public T apply(T arg) {
				return merge(arg, obj1);
			}
		});
		if (!CollectionUtils.contains(mapped, obj1, criteria)) mapped.add(obj1);
		return mapped;
	}

}
