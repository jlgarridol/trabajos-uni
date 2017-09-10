package es.ubu.lsi.edat.pr10;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Arbol de búsqueda AVL.
 * 
 * @author José Miguel Ramírez Sanz
 * @author José Luis Garrido Labrador
 *
 * @param <E> tipo de datos que estarán en el árbol
 */
public class ArbolAVL<E> extends ArbolBB<E> {
	
	/**
	 * Calcula la altura de un elemento en el árbol.
	 * 
	 * @param elemento elemento del que se quiere conocer la altura
	 * @return la altura o -1 si el elemento no está
	 */
	public int altura(E elemento) { 
		List<Nodo> lista = buscar(super.raiz, elemento);
		Nodo supp = lista.get(0);
		if (supp == null) {
			return -1;
		}
		return alturaR(supp);
	}

	/**
	 * Metodo interno para calcular la altura recursivamente.
	 * 
	 * @param nodo nodo cuya altura queremos saber
	 * @return altura 
	 */
	private int alturaR(Nodo nodo) {
		int alturaD = 0;
		int alturaI = 0;
		if (nodo.getDer() == null && nodo.getIzq() == null) {
			return 0;
		}
		if (nodo.getDer() == null) {
			alturaI = 1 + alturaR(nodo.getIzq());
		} else if (nodo.getIzq() == null) {
			alturaD = 1 + alturaR(nodo.getDer());
		} else {
			alturaI = 1 + alturaR(nodo.getIzq());
			alturaD = 1 + alturaR(nodo.getDer());
		}
		if (alturaD > alturaI) { //Se tiene que devolver el camino más largo.
			return alturaD;
		}
		return alturaI;
	}

	/**
	 * Calcula la profundidad de un elemento en el árbol.
	 * 
	 * @param elemento elemento del que se quiere conocer la profundidad
	 * @return profundidad o -1 si el elemento no está en el árbol
	 */
	public int profundidad(E elemento) {
		List<Nodo> lista = buscar(super.raiz, elemento);
		Nodo supp = lista.get(0);
		if (supp == null) {
			return -1;
		}
		if (lista.get(1) == null) { //Si no tiene padre es que es la raiz, es decir, profundidad 0
			return 0;
		}
		return 1 + profundidad(lista.get(1).getDato());
	}

	/**
	 * Añade un elemento y reequilibra el árbol.
	 * 
	 * @param elemento elemento a añadir
	 * @return true si se ha añadido false si no
	 */
	@Override
	public boolean add(E elemento) { 
		if (!super.add(elemento)) {
			return false;
		}
		Nodo padre = super.buscar(raiz, elemento).get(1);
		if(padre!=null)
			reequilibrioAVL(encontrarDes(padre));
		return true;
	}

	/**
	 * Método interno que devuelve el nodo más profundo donde hay desequilibrio.
	 * 
	 * @param nodo nodo desde el que empezamos a buscar
	 * @return nodo, ancestros del nodo pasado que está desequilibrado o null si no hay nodo desequilibrado
	 */
	private Nodo encontrarDes(Nodo nodo) {
		int factor = comprobarAVL(nodo);
		if(factor == 2 || factor == -2) //Si el factor de 2 o -2 es que hay desequilibrio
			return nodo;
		else if(nodo==raiz) //Si estamos en la raiz y no ha habido desequilibrio no hay nodo desequilibrado en todo el árbol
			return null;
		else
			return encontrarDes(buscar(raiz,nodo.getDato()).get(1)); //Si no se cumple nada de lo anterior se sigue hacia arriba
	}

	
	/**
	 * Calcula el factor de desequilibrio del nodo pasado.
	 * 
	 * @param nodo nodo del que se quiere conocer el desequilibrio
	 * @return diferencia de las alturas de su hijo derecho - hijo izquierdo
	 */
	private int comprobarAVL(Nodo nodo) { 
		int alturaD;
		int alturaI;
		if (nodo == null) {
			return 0;
		}

		if (nodo.getDer() == null) {
			alturaD = 0;
		} else {
			alturaD = 1 + altura(nodo.getDer().getDato());
		}
		if (nodo.getIzq() == null) {
			alturaI = 0;
		} else {
			alturaI = 1 + altura(nodo.getIzq().getDato());
		}
		return alturaD - alturaI;
	}

	/**
	 * Función que determina el tipo de rotación ha ejecutar sobre el nodo pasado.
	 * 
	 * @param nodo null si no hay que reequilibrar o el nodo desequilibrado
	 */
	private void reequilibrioAVL(Nodo nodo) { 
		if (nodo != null) {
			int factor, derecho, izquierdo;
			factor = this.comprobarAVL(nodo);
			derecho = this.comprobarAVL(nodo.getDer());
			izquierdo = this.comprobarAVL(nodo.getIzq());
			switch (factor) {
			case 2:
				switch (derecho) {
				case 1:
				case 0: //Cuando hay una eliminación y el subarbol está completamente equilibrado
					rotacionIzquierda(nodo);
					break;
				case -1:
					rotacionDerechaIzquierda(nodo);
					break;
				}
				break;
			case -2:
				switch (izquierdo) {
				case -1:
				case 0: //Cuando hay una eliminación y el subarbol está completamente equilibrado
					rotacionDerecha(nodo);
					break;
				case 1:
					rotacionIzquierdaDerecha(nodo);
					break;
				}
				break;
			}
		}
	}

	/**
	 * Hace una rotación izquierda sobre el nodo.
	 * 
	 * @param nodo nodo sobre el que se va a reequilibrar el árbol
	 */
	private void rotacionIzquierda(Nodo nodo) { 
		Nodo raizLocal;
		raizLocal = nodo.getDer();
		nodo.setDer(raizLocal.getIzq());
		raizLocal.setIzq(nodo);
		Nodo padre = buscar(super.raiz, nodo.getDato()).get(1);
		if (padre == null)
			super.raiz = raizLocal; //El nodo hijo derecho se convierte en raiz
		else if (padre.getDer() == nodo)
			padre.setDer(raizLocal);
		else
			padre.setIzq(raizLocal);
	}

	/**
	 * Hace una rotación derecha sobre el nodo.
	 * 
	 * @param nodo nodo sobre el que se va a reequilibrar el árbol
	 */
	private void rotacionDerecha(Nodo nodo) { 
		Nodo raizLocal;
		raizLocal = nodo.getIzq();
		nodo.setIzq(raizLocal.getDer());
		raizLocal.setDer(nodo);
		Nodo padre = buscar(super.raiz, nodo.getDato()).get(1);
		if (padre == null)
			super.raiz = raizLocal; //El nodo hijo izquierdo se convierte en raiz
		else if (padre.getDer() == nodo)
			padre.setDer(raizLocal);
		else
			padre.setIzq(raizLocal);
	}

	/**
	 * Hace una rotación izquierda derecha desde el nodo.
	 * 
	 * @param nodo nodo que será el centro de la rotación
	 */
	private void rotacionIzquierdaDerecha(Nodo nodo) { 
		rotacionIzquierda(nodo.getIzq()); //Primero rotamos a la izquierda el hijo izquierdo
		rotacionDerecha(nodo); //Luego rotamos todo a la derecha
	}

	/**
	 * Hace una rotación derecha izquierda desde el nodo.
	 * 
	 * @param nodo nodo que será el centro de la rotación
	 */
	private void rotacionDerechaIzquierda(Nodo nodo) { 
		rotacionDerecha(nodo.getDer()); //Primero rotamos a la derecha el hijo derecho
		rotacionIzquierda(nodo); //Luego rotamos todo a la izquierda
	}

	/**
	 * Aumenta en uno las veces que se ha pasado por un nodo.
	 * 
	 * @param veces pila con la cantidad de veces pasadas
	 */
	private void tratar(Stack<Integer> veces) {
		Integer elemento = veces.pop();
		elemento++;
		veces.push(elemento);
	}

	/**
	 * Hace un recorrido pre orden sobre el árbol. La primera vez que se pasa se saca.
	 * 
	 * @return Lista de elementos ordenados por la primera vez en la que se pasó por su nodo
	 */
	public List<E> preOrden() {
		List<E> retorno = new ArrayList<>(this.size());
		Stack<Integer> pila = new Stack<>();
		Stack<Nodo> pilaNodos = new Stack<>();
		pilaNodos.push(super.raiz);
		pila.push(1);
		while (pilaNodos.peek() != super.raiz || pila.peek() != 3) {
			Nodo cabeza = pilaNodos.peek();
			Integer veces = pila.peek();
			if (veces == 3) {//A la tercera vez sacamos el elemento de la pila para siempre
				pila.pop();
				pilaNodos.pop();
			} else if (veces == 1) {
				tratar(pila);
				retorno.add(cabeza.getDato()); //A la primera vez que se pasa se guarda el elemento el la lista
				cabeza = cabeza.getIzq();
				if (cabeza != null) {
					pila.push(1); 
					pilaNodos.push(cabeza);
				}
			} else {
				tratar(pila);
				cabeza = cabeza.getDer();
				if (cabeza != null) {
					pila.push(1);
					pilaNodos.push(cabeza);
				}
			}
		}

		return retorno;
	}

	/**
	 * Hace un recorrido in orden sobre el árbol. La segunda vez que se pasa se saca.
	 * 
	 * @param bool booleano que aparecía en el test y no sabemos para que sirve
	 * @return Lista de elementos ordenados por la segunda vez en la que se pasó por su nodo
	 */
	public List<E> inOrden(boolean bool) {
		List<E> retorno = new ArrayList<>(this.size());
		Stack<Integer> pila = new Stack<>();
		Stack<Nodo> pilaNodos = new Stack<>();
		pilaNodos.push(super.raiz);
		pila.push(1);
		while (pilaNodos.peek() != super.raiz || pila.peek() != 3) {
			Nodo cabeza = pilaNodos.peek();
			Integer veces = pila.peek();
			if (veces == 3) { //A la tercera vez sacamos el elemento de la pila para siempre
				pila.pop();
				pilaNodos.pop();
			} else if (veces == 1) {
				tratar(pila);
				cabeza = cabeza.getIzq();
				if (cabeza != null) {
					pila.push(1);
					pilaNodos.push(cabeza);
				}
			} else {
				tratar(pila); 
				retorno.add(cabeza.getDato()); //A la segunda vez que se pasa se guarda el elemento el la lista
				cabeza = cabeza.getDer();
				if (cabeza != null) {
					pila.push(1);
					pilaNodos.push(cabeza);
				}
			}
		}

		return retorno;
	}

	/**
	 * Hace un recorrido in orden sobre el árbol. La tercera vez que se pasa se saca.
	 * 
	 * @return Lista de elementos ordenados por la tercera vez en la que se pasó por su nodo
	 */
	public List<E> posOrden() {
		List<E> retorno = new ArrayList<>(this.size());
		Stack<Integer> pila = new Stack<>();
		Stack<Nodo> pilaNodos = new Stack<>();
		pilaNodos.push(super.raiz);
		pila.push(1);
		while (pilaNodos.peek() != super.raiz || pila.peek() != 3) {
			Nodo cabeza = pilaNodos.peek();
			Integer veces = pila.peek();
			if (veces == 3) { //A la tercera vez sacamos el elemento de la pila para siempre
				retorno.add(cabeza.getDato()); //A la tercera vez que se pasa se guarda el elemento el la lista
				pila.pop();
				pilaNodos.pop();
			} else if (veces == 1) {
				tratar(pila);
				cabeza = cabeza.getIzq();
				if (cabeza != null) {
					pila.push(1);
					pilaNodos.push(cabeza);
				}
			} else {
				tratar(pila);
				cabeza = cabeza.getDer();
				if (cabeza != null) {
					pila.push(1);
					pilaNodos.push(cabeza);
				}
			}
		}
		retorno.add(super.raiz.getDato());
		return retorno;
	}
	
	/**
	 * Elimina un objecto del árbol.
	 * 
	 * @param o objeto a borrar
	 * @return false si no se ha borrado, true si si se ha borrado
	 */
	@Override
	public boolean remove(Object o){
		Nodo padre;
		try{
			padre = super.buscar(raiz, (E) o).get(1);
		}catch(ClassCastException ex){
			return false;
		}
		boolean retorno = super.remove(o);
		if(retorno){ //Si si que se ha borrado comprobamos el reequilibrio.
			if(padre!=null)
				reequilibrioAVL(encontrarDes(padre));
			else{
				reequilibrioAVL(encontrarDes(raiz));
			}
		}
		return retorno;
	}

}
