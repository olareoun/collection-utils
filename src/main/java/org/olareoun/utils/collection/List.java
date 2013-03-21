package org.olareoun.utils.collection;

public class List<T> {
	
	private final java.util.List<T> list;

	public List(java.util.List<T> list){
		this.list = list;
	}
	
	public void each(Procedure<T> procedure){
		CollectionUtils.each(list, procedure);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List map(MonoFunction function){
		return new List(CollectionUtils.map(list, function));
	}
	
	public List<T> filter(Class<? extends IFilter<T>>... filterClasses){
		return new List<T>(CollectionUtils.filter(list, filterClasses));
	}
	
	public List<T> filter(IFilter<T>... filters){
		return new List<T>(CollectionUtils.filter(list, filters));
	}
	
	public T reduce(Function<T> function) throws EmptyListException{
		return CollectionUtils.reduce(list, function);
	}

}
