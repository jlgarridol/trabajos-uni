package estructuras;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ArrayMap<K,V> implements Map<K,V> {

	private ArrayList<K> keyList;
	private ArrayList<V> valueList;
	
	public ArrayMap(){
		clear();
	}
	
	@Override
	public void clear() {
		keyList = new ArrayList<>();
		valueList = new ArrayList<>();
	}

	@Override
	public boolean containsKey(Object arg0) {
		return keyList.contains(arg0);
	}

	@Override
	public boolean containsValue(Object arg0) {
		return valueList.contains(arg0);
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public V get(Object arg0) {
		int index = keyList.indexOf(arg0);
		if(index!=-1)
			return valueList.get(index);
		else
			return null;
	}

	@Override
	public boolean isEmpty() {
		return keyList.size()==0;
	}

	@Override
	public Set<K> keySet() {
		return new HashSet<>(keyList);
	}

	@Override
	public V put(K arg0, V arg1) {
		int index = keyList.indexOf(arg0);
		if(index==-1){
			keyList.add(arg0);
			valueList.add(arg1);
			return null;
		}else{
			V retur = valueList.get(index);
			valueList.set(index, arg1);
			return retur;
		}
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> arg0) {
		Iterator<? extends K> iterators = arg0.keySet().iterator();
		while(iterators.hasNext()){
			K next = iterators.next();
			this.put(next, arg0.get(next));
		}
		
		
	}

	@Override
	public V remove(Object arg0) {
		int index = keyList.indexOf(arg0);
		if(index==-1)
			return null;
		else{
			keyList.remove(index);
			return valueList.remove(index);
		}
	}

	@Override
	public int size() {
		return valueList.size();
	}

	@Override
	public Collection<V> values() {
		return valueList;
	}

}
