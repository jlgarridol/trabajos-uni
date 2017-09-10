package es.ubu.inf.edat.pr05;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación de una cola mixta.
 * 
 * @author José Luis Garrido Labrador
 * @author José Miguel Ramírez Sanz
 * @param <E>
 *            Tipo genérico para el uso de la cola
 */
public class ColaMixta<E> extends AbstractQueue<E> {

	/**
	 * Dimensión del nodo por defecto.
	 */
	private static final int DIM = 3;

	/**
	 * Dimensión del nodo.
	 */
	private int dim;
	/**
	 * Cantidad de elementos guardados en la cola.
	 */
	private int size;

	/**
	 * Clase con el nodo de la cola mixta.
	 * 
	 * @author José Luis Garrido Labrador
	 * @author José Miguel Ramírez Sanz
	 * @param <T>
	 *            Tipo genérico del nodo
	 */
	private class NodoMixto<T> {
		/**
		 * Siguiente nodo.
		 */
		private NodoMixto<T> sig;
		/**
		 * Array con los elementos del nodo.
		 */
		private ArrayList<T> cont;

		/**
		 * Creación del nodo.
		 * 
		 * @param dim
		 *            dimensión del array del nodo
		 */
		public NodoMixto(int dim) {
			cont = new ArrayList<T>(dim);
		}

		/**
		 * Obtener el siguiente nodo.
		 * 
		 * @return el nodo siguiente
		 */
		public NodoMixto<T> getSig() {
			return sig;
		}

		/**
		 * Determina cual es el siguiente nodo.
		 * 
		 * @param sig
		 *            establece el nodo
		 */
		public void setSig(NodoMixto<T> sig) {
			this.sig = sig;
		}

		/**
		 * Obtiene el array de elementos.
		 * 
		 * @return array con los valores de ese nodo de la cola
		 */
		public ArrayList<T> getCont() {
			return cont;
		}
	}

	/**
	 * Nodo primero.
	 */
	private NodoMixto<E> ini;
	/**
	 * Nodo último.
	 */
	private NodoMixto<E> fin;

	/**
	 * Crea una cola con la dimensión pasada para cada nodo.
	 * 
	 * @param dim
	 *            dimensión de cada nodo
	 */
	public ColaMixta(int dim) {
		this.dim = dim;
		ini = new NodoMixto<E>(dim);
		fin = ini;
	}

	/**
	 * Crea una cola cuyo nodo será de DIM.
	 */
	public ColaMixta() {
		this(DIM);
	}

	/**
	 * Introduce al final de la cola un elemento pasado por parámetro.
	 * 
	 * @param arg0
	 *            elemento a meter
	 * @throws NullPointerException
	 *             cuando el valor arg0 es nulo
	 * @return true si va bien, false nunca
	 */
	@Override
	public boolean offer(E arg0) throws NullPointerException {
		if (arg0 == null)
			throw new NullPointerException("No se pueden añadir nulos");
		if (fin.getCont().size() == dim) {
			NodoMixto<E> temp = new NodoMixto<>(dim);
			fin.setSig(temp);
			fin = temp;
		}
		fin.getCont().add(arg0);
		size++;
		if (peek(size - 1) == arg0)
			return true;
		else
			return false;
	}

	/**
	 * Muestra el elemento primero de la cola.
	 * 
	 * @return elemento primero o nulo si no hay ninguno
	 */
	@Override
	public E peek() {
		try {
			return ini.getCont().get(0);
		} catch (IndexOutOfBoundsException ex) {
			return null;
		}
	}

	/**
	 * Muestra un elemento con el índice de la cola.
	 * 
	 * @param num
	 *            índice del elemento que queremos obtener
	 * @return elemento cuyo índice sea el pasado
	 */
	public E peek(int num) {
		int i = 0;
		if (num >= 0 && num < size) {
			Iterator<E> it = this.iterator();
			while (it.hasNext()) {
				if (i == num) {
					return it.next();
				} else {
					i++;
					it.next();
				}
			}
		}
		return null;
	}

	/**
	 * Saca de la cola el primer elemento.
	 * 
	 * @return El elemento primero
	 */
	@Override
	public E poll() {
		Integer[] prueba = new Integer[5];
		boolean resultado = prueba[0] == 0;
		System.out.println(resultado+"\n");
		try {
			E temp;
			temp = ini.getCont().get(0);
			ini.getCont().remove(0);
			size--;
			if (ini.getCont().size() == 0) {
				ini = ini.getSig();
			}
			return temp;
		} catch (ArrayIndexOutOfBoundsException ex) {
			return null;
		}
	}

	/**
	 * Vacia la cola.
	 */
	@Override
	public void clear() {
		ini = new NodoMixto<E>(dim);
		fin = ini;
		size = 0;
	}

	/**
	 * Devuelve el tamaño de la cola.
	 * 
	 * @return entero con el tamaño
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Devuelve un iterador de la cola.
	 * 
	 * @return iterador
	 */
	@Override
	public Iterator<E> iterator() {
		return new IteratorCola<E>();
	}

	/**
	 * Clase del iterador de la cola mixta.
	 * 
	 * @author José Luis Garrido Labrador
	 * @author José Miguel Ramírez Sanz
	 * @param <T>
	 *            tipo genérico del iterador
	 */
	protected class IteratorCola<T> implements Iterator<T> {

		/**
		 * Índice de la cola.
		 */
		private int indice = -1;
		/**
		 * Número de devuelto.
		 */
		private int devueltos;
		/**
		 * Elementos del iterador.
		 */
		private int elementos = size();
		/**
		 * nodo actual del iterador.
		 */
		@SuppressWarnings("unchecked")
		private ColaMixta<T>.NodoMixto<T> nodo = (ColaMixta<T>.NodoMixto<T>) ini;

		/**
		 * Comprueba si hay más elementos.
		 * 
		 * @return true si hay más, false si no
		 */
		@Override
		public boolean hasNext() {
			if (devueltos == elementos) {
				return false;
			}
			return true;
		}

		/**
		 * Devuelve el siguiente elemento.
		 * 
		 * @throws NoSuchElementException
		 *             si no hay más y se ha llamado
		 * @return siguiente elemento
		 */
		@Override
		public T next() throws NoSuchElementException {
			T temp;
			indice++;
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			temp = nodo.getCont().get(indice);
			devueltos++;
			if (indice == dim - 1) {
				indice = -1;
				nodo = nodo.getSig();
			}
			return temp;
		}
	}
}
