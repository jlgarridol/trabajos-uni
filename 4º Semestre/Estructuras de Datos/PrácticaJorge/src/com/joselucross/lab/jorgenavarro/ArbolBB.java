package com.joselucross.lab.jorgenavarro;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Clase que implementa el interfaz java.util.Set empleando
 * para ello la estructura de un árbol binario de búsqueda 
 * 
 * @author bbaruque 
 *
 * @param <E> Parametro de genericiadad de la estructura
 */
public class ArbolBB<E> extends AbstractSet<E> {

	/**
	 * 
	 * @author bbaruque
	 *
	 */
	public class Nodo {      
		/**
		 * Dato incluido en el nodo
		 */
		private E dato;
		/**
		 * Subarbol izquierdo del nodo
		 */
		private Nodo izq;
		/**
		 * Subarbol derecho del nodo
		 */
		private Nodo der;

		/**
		 * Constructor
		 * 
		 * @param Object dato El dato que contendra el nodo   
		 */
		public Nodo (E dato) {
			this.dato = dato;
		}

		/**
		 * Devuelve el objeto que hay en el nodo
		 * 
		 * @return Object El objeto que hay en el nodo   
		 */
		public E getDato(){
			return dato;
		}

		/**
		 * Establece el elemento de un nodo.
		 * 
		 * @param dato El nuevo elemento del Nodo
		 * 	 
		 */
		public void setDato(E dato) {
			this.dato = dato;
		}
		/**
		 * Devuelve el subarbol izquierdo de un nodo
		 *
		 * @return Nodo La raiz del subarbol izquierdo del nodo
		 */
		public Nodo getIzq(){
			return izq;
		}

		/**
		 * Establece el subarbol izquierdo de un nodo
		 * 
		 * @param Nodo El nuevo subarbol izquierdo de ese nodo
		 */
		public void setIzq(Nodo valor) {
			izq = valor;
		}
		/**
		 * Devuelve el subarbol derecho de un nodo
		 * 
		 * @return Nodo La raiz del subarbol derecho del nodo
		 */
		public Nodo getDer(){
			return der;
		}

		/**
		 * Establece el subarbol derecho de un nodo
		 * 
		 * @param Nodo El nuevo subarbol derecho de ese nodo
		 * 	 
		 */
		public void setDer(Nodo valor){
			der = valor;
		}

		public String toString(){
			return this.getDato().toString();
		}

	} // Nodo

	/** 
	 * La raiz del arbol
	 */
	protected Nodo raiz = null; // Un arbol está definido por una referencia a un nodo
	
	/**
	 * Número de elementos incluidos en el Set
	 */
	protected int numElementos = 0;
	
	/**
	 * Referencia al comparador empleado para organizar los elementos en
	 * el arbol binario de busqueda
	 */
	protected Comparator<? super E> comparator;

	/**
	 * Constructor por defecto
	 */
	public ArbolBB(){ }

	/** 
	 * Constructor que incluye una coleccion para inicializar el Set
	 * 
	 * @param coleccion que se incluye en el set en el momento de su instanciacion
	 * No se incluiran los elementos duplicados
	 */
	public ArbolBB(Collection<? extends E> coleccion){
		this.addAll(coleccion);
	}

	/** 
	 * Constructor que incluye una coleccion para inicializar el Set
	 * 
	 * @param coleccion que se incluye en el set en el momento de su instanciacion
	 * No se incluiran los elementos duplicados
	 * @param c comparador empleado para determinar el orden de los elementos en el Set
	 */
	public ArbolBB(Collection<? extends E> coleccion, Comparator<? super E> c){
		this.comparator = c;
		this.addAll(coleccion);
	}

	@Override
	public boolean add(E dato) {

		List<Nodo> encontrado = this.buscar(this.raiz, dato);

		if(encontrado.get(0) != null) // Si el elemento ya esta presente
			return false;

		this.insertar(encontrado.get(1), dato);
		// Aumenta el numero de elementos
		numElementos++;

		return true;
	}

	@Override
	public boolean remove(Object o){

		E borrado;
		try{
			borrado = (E) o;
		}catch(ClassCastException cce){
			return false;
		}

		List<Nodo> encontrado = this.buscar(this.raiz, borrado);
		
		if(encontrado.get(0) == null) // Si el elemento no esta presente
			return false;
		
		eliminar(encontrado.get(0), encontrado.get(1), borrado);
		numElementos--;
		
		return true; 

	}

	@Override
	public int size() {
		return this.numElementos;
	}

	@Override
	public void clear(){
		this.raiz = null;
	}
	
	//////////////// MÉTODOS AUXILIARES ////////////////	
	/**
	 * Metodo que permite comparar dos elementos pasados por parámetro.
	 * @param o1
	 * @param o2
	 * @return
	 * @throws ClassCastException
	 */
	@SuppressWarnings("unchecked")
	protected int comparar(E o1, E o2) throws ClassCastException{            
		if (comparator != null)      // Si hay un comparador, se emplea                          
			return comparator.compare(o1,o2);
		else  // Si no, hay que suponer que sean Comparable        
			return ((Comparable<E>) o1).compareTo(o2);                        
	}

	/**
	 * Metodo que permite obtener la referencia del nodo que contiene un dato
	 * que se solicita localizar en el árbol. 
	 * Permite facilitar el punto de partida desde el que se va a proceder a buscar.
	 * No es obligatorio que se trate de la raiz de todo el arbol.
	 * 
	 * La lista que devuelve contiene dos referencias a nodos: 
	 * el primero es el nodo que contiene el dato buscado y (null si el dato no esta en el arbol)
	 * el segundo corresponde al nodo del que cuelga (null si es la raiz).
	 * 
	 * @param raiz del subarbol sobre el que se va a buscar 
	 * @param buscado dato que se ppretende localizar
	 * @return lista con referencias
	 */
	protected List<Nodo> buscar (Nodo raiz, E buscado){

		Nodo actual = null, padre = null;
		List<Nodo> encontrado = new ArrayList<Nodo>(2);

		actual = raiz;

		while (actual != null && comparar(buscado, actual.getDato()) != 0) {
			padre = actual;            
			if (comparar(buscado, actual.getDato()) < 0)
				actual = actual.getIzq();
			else
				actual = actual.getDer();
		}

		encontrado.add(actual);
		encontrado.add(padre);
		return encontrado;
	}

	/**
	 * Metodo para encontrar el elemento mayor en un subarbol
	 * 
	 * @param inicio punto desde el que se va a proceder a buscar
	 * @return referencia al nodo con el mayor valor del arbol
	 */
	private Nodo mayor(Nodo inicio){

		Nodo actual = inicio;

		while(actual.getDer() != null)
			actual = actual.getDer();

		return actual;
	}

	/**
	 * Metodo para encontrar el elemento menor en un subarbol
	 * 
	 * @param inicio punto desde el que se va a proceder a buscar
	 * @return referencia al nodo con el menor valor del arbol
	 */	private Nodo menor(Nodo inicio){

		Nodo actual = inicio;

		while(actual.getIzq() != null)
			actual = actual.getIzq();

		return actual;
	}

	/**
	 * Metodo auxiliar que permite enlazar un nuevo nodo en el arbol.
	 * El nuevo nodo colgará de aquel que se pasa como parametro 
	 *  y tendrá como contenido el dato facilitado
	 * 
	 * @param encontrado lista que contiene el nodo sobre el que colgará el nuevo
	 * @param datoInsertar dato que contendrá el nuevo nodo
	 * @return
	 */
	private void insertar(Nodo padre, E datoInsertar){

		if (padre == null) //Arbol vacio
			raiz = new Nodo(datoInsertar); // es la raiz
		else 
			if (comparar(datoInsertar, padre.getDato())>0) // Si el padre es menor
				padre.setDer(new Nodo(datoInsertar)); // se inserta a la derecha
			else
				padre.setIzq(new Nodo(datoInsertar)); // se inserta a la izquierda

	}

	/**
	 * Metodo auxiliar que permite desenlazar un nodo ya contenido en el arbol, respetando
	 * que el resto de nodos ya incluidos sigan conformando un árbol binario de búsqueda.
	 * 
	 * @param raiz Nodo que se va a eliminar del arbol.
	 * @param padre Nodo al que está enlazado el nodo a eliminar
	 * @param datoBorrar dato que se pretende eliminar del arbol
	 * @return true en caso de que se haya procedido a eliminarlo y false en caso contrario
	 */
	private boolean eliminar(Nodo raiz, Nodo padre, E datoBorrar){

		boolean tieneIzq=false, tieneDer=false, esHijoDer=false, esHijoIzq=false;
		List<Nodo> encontrado = buscar(raiz, datoBorrar);

		Nodo actual = encontrado.get(0);
		// Si el paso anterior fue el intercambio, no se encontrará el padre correctamente
		// esta comprobación sirve para evitarlo		
		if(encontrado.get(1)!=null)
			padre = encontrado.get(1);

		tieneIzq = actual.getIzq() != null;
		tieneDer = actual.getDer() != null;
		if(padre!=null){
			esHijoDer = padre.getDer() != null && padre.getDer().equals(actual);
			esHijoIzq = padre.getIzq() != null && padre.getIzq().equals(actual);
		}

		// Si es una hoja se elimina sin más
		if( !tieneIzq && !tieneDer){

			// Si es la raiz
			if(padre == null)
				raiz = null;
			
			// Si no es la raiz
			if(esHijoDer){
				padre.setDer(null);
			} else if(esHijoIzq) {
				padre.setIzq(null);
			} 

		// Si tiene 2 hijos, se sustituye por el menor de la derecha o por el mayor de la izquierda
		} else if (tieneIzq && tieneDer){

			Nodo sustituir;

			if(Math.random()>0.5){
				sustituir = menor(actual.getDer());
			} else {
				sustituir = mayor(actual.getIzq());
			}

			E aux = sustituir.getDato();
			sustituir.setDato(actual.getDato());
			actual.setDato(aux);

			// Una vez sustituido, se pasa a eliminar del arbol 
			// (al sustituirlo, pasa a ser una hoja)
			return eliminar(sustituir, actual, datoBorrar);

		// Si sólo tiene un hijo se sustituye por su hijo
		} else if (tieneDer){ // Sustitucion por el hijo derecho

			if(esHijoDer){
				padre.setDer(actual.getDer());
			}else{
				padre.setIzq(actual.getDer());
			}

		} else if (tieneIzq){ // Sustitucion por el hijo izquierdo

			if(esHijoDer){
				padre.setDer(actual.getIzq());
			}else{
				padre.setIzq(actual.getIzq());
			}

		}

		return true;
		
	}

	/**
	 * Metodo que permite obtener el listado ordenado de datos contenidos
	 * en el Set. Permite obtener un subconjunto del mismo, al admitir en la
	 * raiz el punto en el que se comienza a recuperar datos.
	 *   
	 * Se emplea como metodo auxiliar para realizar el iterador
	 * Resulta poco eficiente, pero se emplea como muestra.
	 * 
	 * @param actual nodo a partir del que se realiza el recorrido del subarbol
	 * @param listaRecorridos recorrido en forma de lista (ordenado)
	 */
	private void inOrdenRecursivo(Nodo actual, List<E> listaRecorridos) {
		if (actual != null) {            
			inOrdenRecursivo(actual.getIzq(), listaRecorridos);            
			listaRecorridos.add(actual.getDato());        
			inOrdenRecursivo(actual.getDer(), listaRecorridos);
		}
	}
	
	@Override
	public Iterator<E> iterator(){
		return new IteradorArbol<>();
	}
	
	private class IteradorArbol<E> implements Iterator<E>{
		private int total, devueltos;
		private List<E> lista;
		
		private void recorridoPostOrden(Nodo nodo){
			if(nodo!=null){
				recorridoPostOrden(nodo.getDer());
				lista.add((E) nodo.getDato());
			}
		}
		
		public IteradorArbol(){
			lista = new ArrayList<>(size());
			recorridoPostOrden(raiz);
			total=lista.size();
			devueltos=-1;
		}

		@Override
		public boolean hasNext() {
			return devueltos != total;
		}

		@Override
		public E next() {
			devueltos++;
			if(!hasNext())
				throw new NoSuchElementException();
			return lista.get(devueltos);			
		}
		
		
	}

}
