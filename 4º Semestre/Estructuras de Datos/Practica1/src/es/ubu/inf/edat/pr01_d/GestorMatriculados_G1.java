package es.ubu.inf.edat.pr01_d;

import java.util.Iterator;
import java.util.NoSuchElementException;

import es.ubu.inf.edat.pr01_d.datos.*;

/**
 * Gestor de coches matriculados para la primera práctica de Estructura de datos.
 * 
 * @author bbaruque
 * @author prenedo
 * @author José Luis Garrido Labrador a.k.a JoseluCross 
 */
public class GestorMatriculados_G1 implements Iterable<Vehiculo> {

	/**
	 * Listado de vehiculos guardados.
	 */
	private Vehiculo[] listado = new Vehiculo[25];

	/**
	 * Carga una lista de vehículos.
	 * 
	 * @param nuevos
	 *            array de vehiculos
	 * @return entero con la cantidad de vehiculos.
	 */
	public int cargaListado(Vehiculo[] nuevos) {

		int tam = 0;

		for (int i = 0; i < nuevos.length; i++) {
			listado[i] = nuevos[i];
			tam++;
		}

		return tam;

	}

	/**
	 * Vacia el listado de vehiculos.
	 * 
	 * @return tamaño del listado
	 */
	public int vaciaListado() {

		int tam = 0;

		for (int i = 0; i < listado.length; i++) {
			listado[i] = null;
		}

		return tam;

	}

	/**
	 * Obtienes un array con todos los vehículos.
	 * 
	 * @return array de vehiculos
	 */
	public Vehiculo[] getMatriculados() {
		return listado;
	}

	/**
	 * Devuelve un array con los vehículos de la marca pasada.
	 * 
	 * @param marca
	 *            cadena con la marca de la que se quiere obtener los vehículos
	 * @return lista de vehículos de la misma marca
	 */
	public Vehiculo[] listadoMarca(String marca) {

		Vehiculo[] listadoMarca = new Vehiculo[listado.length];

		for (int i = 0; i < listado.length; i++) {
			Vehiculo actual = listado[i];
			if (actual.getMarca().equalsIgnoreCase(marca))
				listadoMarca[i] = actual;
		}
		return listadoMarca;
	}

	/**
	 * Lista de motos del mismo modelo.
	 * 
	 * @param modelo
	 *            modelo de moto que se quiere
	 * @return array de motos con el mismo modelo
	 */
	public Moto[] listadoMotoMarca(String modelo) {

		Moto[] listadoMarca = new Moto[listado.length];

		for (int i = 0; i < listado.length; i++) {
			Vehiculo actual = listado[i];
			if (actual instanceof Moto && actual.getMarca().equalsIgnoreCase(modelo))
				listadoMarca[i] = (Moto) actual;
		}
		return listadoMarca;
	}

	/**
	 * Iterador de Vehiculos con una cilindrada de 1000cc o más y que sean camiones o coches.
	 * 
	 * @return iterador de vehiculos
	 */
	@Override
	public Iterator<Vehiculo> iterator() {
		// TODO Auto-generated method stub
		return new IteradorVehiculos();
	}

	/**
	 * Iterador de vehículos coches o camiones con 1000cc o mas de cilindrada. 
	 * 
	 * @author JoseluCross
	 */
	protected class IteradorVehiculos implements Iterator<Vehiculo> {

		private int cantidad;
		private int devueltos;
		private int posicion;

		/**
		 * Crea un iterador de vehiculos.
		 */
		public IteradorVehiculos() {
			posicion = -1;
			for (int i = 0; i < listado.length; i++) {
				if (listado[i] != null && !(listado[i] instanceof Moto) && listado[i].getCilindrada() >= 1000)
					cantidad++;
			}
		}

		/**
		 * Comprueba si existen más vehículos que cumplan con el enunciado.
		 * 
		 * @return true si hay más y false si no
		 */
		@Override
		public boolean hasNext() {
			if (cantidad == devueltos)
				return false;
			else
				return true;
		}

		/**
		 * Devuelve el siguiente vehículo que cumpla con el enunciado.
		 * 
		 * @return vehiculo en cuestión.
		 */
		@Override
		public Vehiculo next() {
			if (!hasNext())
				throw new NoSuchElementException();
			else {
				while (true) {
					posicion++;
					if (listado[posicion] != null && !(listado[posicion] instanceof Moto)
							&& listado[posicion].getCilindrada() >= 1000) {
						devueltos++;
						return listado[posicion];
					}
				}
			}
		}

		/**
		 * Borra el último vehículo devuelto.++++++++++++++++++++++++++
		 */
		@Override
		public void remove() {
			listado[posicion] = null;
			cantidad--;
			devueltos--;
		}
	}

}
