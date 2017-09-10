package es.ubu.lsi.edat.pract08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementación de una tabla utilizando mapas hash.
 * 
 * @author José Miguel Ramírez Sanz
 * @author José Luis Garrido Labrador
 *
 * @param <R> Row (fila de la tabla)
 * @param <C> Column (columna de la tabla)
 * @param <V> Value (valor almacenado en esa fila y columna)
 */
public class HashMapTable <R,C,V> implements Table<R,C,V> {
	
	/**
	 * Mapa de mapas que es un mapa hash donde se almacenará la tabla.
	 */
	private Map<R,Map<C,V>> mapa = new HashMap<>();
	/**
	 * Cantidad de elementos de la tabla.
	 */
	private int elementos;
	
	/**
	 * {@inheritDoc}.
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override //O(log nm)
	public V put(R row, C column, V value) {
		if(mapa.get(row)==null){
			mapa.put(row, new HashMap<C,V>());
		}
		elementos++;
		V supp = mapa.get(row).get(column);
		if(supp != null){
			elementos--;
		}
		return mapa.get(row).put(column, value);
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override //O(1)
	public V remove(R row, C column) {
		V element = mapa.get(row).get(column);
		if(element!=null){
			elementos--;
			mapa.get(row).remove(column);
		}
		return element;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override //O(1)
	public V get(Object row, Object column) {
		Map<C,V> mapaInterno = mapa.get(row);
		if(mapaInterno==null)
			return null;
		else
			return mapaInterno.get(column);
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override //O(1)
	public boolean containsKeys(Object row, Object column) {
		if(mapa.containsKey(row))
			return mapa.get(row).containsKey(column);
		else
			return false;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @param {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override //O(n*m) //m es la dimensión mayor de entre todas las filas (peor caso)
	public boolean containsValue(V value) {
		for(Map.Entry<R, Map<C,V>> e: mapa.entrySet()){
			for(Map.Entry<C, V> v: e.getValue().entrySet()){
				if(v.getValue().equals(value)){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @param {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override //Complejidad de O(1)
	public Map<C, V> row(R rowKey) {
		if(mapa.containsKey(rowKey))
			return mapa.get(rowKey);
		else
			return new HashMap<>();
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @param {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override //Complejidad de O(nm log nm) en el peor caso 
	public Map<R, V> column(C columnKey) {
		Map<R,V> supp = new HashMap<>();
		for(Map.Entry<R, Map<C,V>> e : mapa.entrySet()){
			for(Map.Entry<C, V> v: e.getValue().entrySet()){
				if(v.getKey().equals(columnKey)){
					supp.put(e.getKey(), v.getValue());
				}
			}
		}
		return supp;
	}
	
	/**
	 * Clase agrupación que implementa una celda de una tabla.
	 * 
	 * @author José Miguel Ramírez Sanz
	 * @author José Luis Garrido Labrador
	 */
	private class Agrupacion implements Table.Cell<R, C, V>{
		
		/**
		 * Fila de la agrupación.
		 */
		private R rowa; 
		/**
		 * Columna de la agrupación.
		 */
		private C columna;
		/**
		 * Valor de la agrupación.
		 */
		private V valuea;
		
		/**
		 * Almacena en una agrupación los elemenetos de una celda de la tabla.
		 * 
		 * @param row fila de la celda
		 * @param column columna de la celda
		 * @param value valor de la celda
		 */
		public Agrupacion(R row, C column, V value){
			rowa = row;
			columna = column;
			valuea = value;
		}
		
		/**
		 * {@inheritDoc}.
		 * 
		 * @return {@inheritDoc}
		 */
		@Override
		public R getRowKey() {
			return rowa;
		}

		/**
		 * {@inheritDoc}.
		 * 
		 * @return {@inheritDoc}
		 */
		@Override
		public C getColumnKey() {
			return columna;
		}

		/**
		 * {@inheritDoc}.
		 *
		 * @return {@inheritDoc}
		 */
		@Override
		public V getValue() {
			return valuea;
		}

		/**
		 * {@inheritDoc}.
		 * 
		 * @param {@inheritDoc}
		 * @return {@inheritDoc}
		 */
		@Override
		public V setValue(V value) {
			V anterior = put(rowa, columna, value);
			valuea=value;
			return anterior;
		}
		
	}
	

	/**
	 * {@inheritDoc}.
	 * 
	 * @return {@inheritDoc}
	 */
	@Override //O(nm)
	public Collection<es.ubu.lsi.edat.pract08.Table.Cell<R, C, V>> cellSet() {
		ArrayList<es.ubu.lsi.edat.pract08.Table.Cell<R, C, V>> salida = new ArrayList<>(size());
		Agrupacion supp = null; //Variable de intercambio
		for(Map.Entry<R, Map<C,V>> e : mapa.entrySet()){
			for(Map.Entry<C, V> v: e.getValue().entrySet()){
				supp = new Agrupacion(e.getKey(),v.getKey(),v.getValue());
				salida.add(supp);
			}
		}
		return Collections.unmodifiableCollection(salida);
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @return {@inheritDoc}
	 */
	@Override //O(1)
	public int size() {
		return elementos;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @return {@inheritDoc}
	 */
	@Override //O(1)
	public boolean isEmpty() {
		return elementos==0;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @return {@inheritDoc}
	 */
	@Override //O(1)
	public void clear() {
		mapa = new HashMap<>();
		elementos=0;
	}

}
