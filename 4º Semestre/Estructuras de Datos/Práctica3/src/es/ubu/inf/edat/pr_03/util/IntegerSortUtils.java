package es.ubu.inf.edat.pr_03.util;

import java.util.Collection;
import java.util.Iterator;

/**
 * Clase con funciones útiles para la realización de la práctica 3 de Estructura de Datos.
 * 
 * @author José Luis Garrido Labrador
 * @author José Miguel Ramírez Sanz
 */
public class IntegerSortUtils {

	/**
	 * Crea un array de Integer con valores aleatorios.
	 * 
	 * @param dim Tamaño del array
	 * @return array de tamaño dim con números aleatorios
	 */
	public static Integer[] gen(int dim) {
		Integer[] lt = new Integer[dim];
		for (int i = 0; i < dim; i++) {
			lt[i] = new Integer((int) Math.random() * dim * 2);
		}
		return lt;
	}
	
	/**
	 * Crea un array de Integer ordenado de 0 a dim.
	 * 
	 * @param dim número de dims a introducir
	 * @return array ordenado
	 */
	public static Integer[] genOrder(int dim){
		Integer[] lt = new Integer[dim];
		for (int i = 0; i< dim; i++){
			lt[i]=i;
		}
		return lt;
	}
	
	/**
	 * Crea un array ordenado inversamente.
	 * 
	 * @param dim tamaño del array
	 * @return Array inversamente ordenado
	 */
	public static Integer[] genInvertOrder(int dim){
		Integer[] lt = new Integer[dim];
		for (int i = dim-1; i>=0; i--){
			lt[i]=i;
		}
		return lt;
	}

	/**
	 * Comprueba si una colección está ordenada
	 * 
	 * @param c
	 * @return
	 */
	public static <E extends Comparable<E>> boolean isSort(Collection<E> c) {
		E pointer = null, next;
		Iterator<E> it = c.iterator();
		if (it.hasNext())
			pointer = it.next();
		while (it.hasNext()) {
			next=it.next();
			if(pointer.compareTo(next)>0)
				return false;
			pointer=next;
		}
		return true;
	}

}
