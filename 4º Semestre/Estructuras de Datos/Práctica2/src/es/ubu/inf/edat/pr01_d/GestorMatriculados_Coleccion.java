package es.ubu.inf.edat.pr01_d;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import es.ubu.inf.edat.pr01_d.datos.*;

public class GestorMatriculados_Coleccion implements Collection<Vehiculo> {

	private Vehiculo[] listado = new Vehiculo[25];
	private int elementos = 0;

	public int cargaListado(Vehiculo[] nuevos) {
		this.elementos = 0;
		for (int i = 0; i < nuevos.length; i++) {
			if (nuevos[i] instanceof Vehiculo) {
				listado[elementos] = nuevos[i];
				elementos++;
			}
		}
		return elementos;
	}

	public Vehiculo[] getMatriculados() {
		return listado;
	}

	public Vehiculo[] listadoMarca(String marca) {

		Vehiculo[] listadoMarca = new Vehiculo[listado.length];

		for (int i = 0; i < listado.length; i++) {
			Vehiculo actual = listado[i];
			if (actual.getMarca().equalsIgnoreCase(marca))
				listadoMarca[i] = actual;
		}
		return listadoMarca;
	}

	public Moto[] listadoMotoMarca(String modelo) {

		Moto[] listadoMarca = new Moto[listado.length];

		for (int i = 0; i < listado.length; i++) {
			Vehiculo actual = listado[i];
			if (actual instanceof Moto && actual.getMarca().equalsIgnoreCase(modelo))
				listadoMarca[i] = (Moto) actual;
		}
		return listadoMarca;
	}

	@Override
	public Iterator<Vehiculo> iterator() {
		return new IteradorDescendente();
	}

	protected class IteradorDescendente implements Iterator<Vehiculo> {

		private int cuantosHay;
		private int ultimoDevuelto;
		private int devueltos;

		public IteradorDescendente() {
			this.ultimoDevuelto = listado.length;
			this.devueltos = 0;
			this.cuantosHay = 0;
			for (Vehiculo v : listado) {
				if (v instanceof Vehiculo)
					this.cuantosHay += 1;
			}
		}

		@Override
		public boolean hasNext() {
			return (devueltos < cuantosHay);
		}

		@Override
		public Vehiculo next() {
			Vehiculo retorno = null;
			while (this.hasNext() && retorno == null && this.ultimoDevuelto >= 0) {
				this.ultimoDevuelto--;
				if (listado[this.ultimoDevuelto] instanceof Vehiculo) {
					retorno = (Vehiculo) listado[this.ultimoDevuelto];
					this.devueltos++;
				}
			}
			if (retorno == null)
				throw new NoSuchElementException();
			return retorno;
		}

	}

	/**
	 * Duplica el tamaño del ARRAY (length).
	 */
	public void ensanchar(){
		Vehiculo[] temp = new Vehiculo[this.listado.length*2];
		Iterator<Vehiculo> it = this.iterator();
		for(int i=0; it.hasNext(); i++){
			temp[i]=it.next();
		}
		this.listado = temp;
	}
	
	/**
	 * Convierte el array a un array exclusivo del tamaño (sin nulos).
	 */
	public void encoger(){
		Iterator<Vehiculo> it = this.iterator();
		Vehiculo[] temp = new Vehiculo[this.size()];
		for(int i=0; it.hasNext(); i++){
			temp[i]=it.next();
		}
		this.listado = temp;
	}
	
	@Override
	public Object[] toArray() {
		this.encoger();
		return (Object[]) listado;
	}

	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] arg0) {
		this.encoger();
		return (T[]) listado;
	}

	@Override
	public boolean add(Vehiculo e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends Vehiculo> c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Limpia el array y lo pone por defecto.
	 */
	@Override
	public void clear() {
		this.listado = new Vehiculo[25];
		this.elementos = 0;
	}

	/**
	 * Comprueba si el objeto pasado está contenido en el array.
	 * 
	 * @param o objeto a comprobar
	 * @return true si está false si no
	 */
	@Override
	public boolean contains(Object o) {
		Iterator<Vehiculo> it = this.iterator();
		while (it.hasNext()) {
			Vehiculo v = it.next();
			if (v.equals(o))
				return true;
		}
		return false;
	}

	/**
	 * Comprueba que la colección entera pasada por parámetro está dentro.
	 * 
	 * @param c Colección
	 * @return true si toda está, false si no
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		Iterator<?> itC = c.iterator();
		while (itC.hasNext()) {
			if (!this.contains(itC.next()))
				return false;
		}
		return true;
	}

	/**
	 * Comprueba que la colección está vacía.
	 * 
	 * @return true si está vacía, false si no
	 */
	@Override
	public boolean isEmpty() {
		return elementos == 0;
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Mantiene en la colección los elementos que coinciden de la colección pasada.
	 * 
	 * @param c colección cuyos elementos comparamos
	 * @return true si se modifica la colección (this) y false si no hay cambios
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		if (!this.containsAll(c) || c.size() != this.elementos) {
			Vehiculo[] temp = new Vehiculo[this.size()];
			int elementosTemp = 0;
			Iterator<?> itC = c.iterator();
			while (itC.hasNext()) {
				try {
					Vehiculo v = (Vehiculo) itC.next();
					if (this.contains(v)) {
						temp[elementosTemp] = v;
						elementosTemp++;
					}
				} catch (ClassCastException ex) {
					// No es que pasemos de ello, es que así no se salta la
					// ejecución y si no es un vehiculo tampoco estará dentro.
				}
			}
			this.listado = temp;
			this.elementos = elementosTemp;
			return true;
		}else
			return false;
	}

	/**
	 * Devuelve el número de elementos.
	 * 
	 * @return número de elementos
	 */
	@Override
	public int size() {
		return elementos;
	}
}
