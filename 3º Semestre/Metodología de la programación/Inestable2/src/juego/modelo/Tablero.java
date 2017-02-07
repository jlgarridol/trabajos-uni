package juego.modelo;

import juego.util.CoordenadasIncorrectasException;
import juego.util.Sentido;
import java.util.*;

/**
 * Clase tablero.
 * 
 * @author José Miguel Ramírez Sanz
 * @author José Luis Garrido Labrador
 * @since 1.0
 * @version 2.0
 */
public class Tablero {

	// Atributos
	/**
	 * Matriz del tablero compuesto por celdas.
	 */
	private List<List<Celda>> matriz;

	/**
	 * Construye el tablero y crea una celda en cada una de las casillas.
	 * 
	 * @param filas
	 *            Dimensión filas
	 * @param columnas
	 *            Dimensión columnas
	 */
	public Tablero(int filas, int columnas) {
		matriz = new ArrayList<List<Celda>>();
		for (int i = 0; i < filas; ++i) {
			matriz.add(new ArrayList<Celda>());
			for (int j = 0; j < columnas; ++j) {
				matriz.get(i).add(new Celda(i, j));
			}
		}
	}

	/**
	 * Pone en el tablero un atomo y asocia la celda con el atomo.
	 * 
	 * @param atomo
	 *            Atomo a la que se le va asignar a la Celda
	 * @param celda
	 *            Celda a la que se le va a asigna la Pieza
	 */
	public void colocar(Atomo atomo, Celda celda) {
		//Precondición
		assert celda.estaVacia() : "No puedes colocar un átomo en una celda no vacía";
		
		celda.establecerAtomo(atomo);
		atomo.establecerCelda(celda);
		
		//Postcondición
		assert !celda.estaVacia() : "El atomo " + atomo + " no se colocó en la celda " + celda;
	}

	/**
	 * Devuelve la celda asociada a la fila y coluna si esta esta en el tablero.
	 * 
	 * @param fila
	 *            fila de la matriz a extraer celda
	 * @param columna
	 *            columna de la matriz a extraer celda
	 * @return la celda de la matriz si los datos son correctos, si no devuelve
	 *         null
	 * @throws CoordenadasIncorrectasException
	 *             si las cordenadas no está en tablero se lanza la excepción
	 */
	public Celda obtenerCelda(int fila, int columna) throws CoordenadasIncorrectasException {
		if (estaEnTablero(fila, columna))
			return matriz.get(fila).get(columna);
		else
			throw new CoordenadasIncorrectasException("Las coordenadas provistas son erroneas");
	}

	/**
	 * Devuelve true si las dimensiones son correctas.
	 * 
	 * @param fila
	 *            posicion fila del tablero
	 * @param columna
	 *            posicion columna del tablero
	 * @return true si está, false si no
	 */
	public boolean estaEnTablero(int fila, int columna) {
		return (fila < matriz.size() && columna < matriz.get(0).size() && fila >= 0 && columna >= 0);
	}

	/**
	 * Retorna la cantidad de filas del tablero.
	 * 
	 * @return las filas de la matriz
	 */
	public int obtenerNumeroFilas() {
		return matriz.size();
	}

	/**
	 * Retorna la cantidad de columnas del tablero.
	 * 
	 * @return las columnas de la matriz
	 */
	public int obtenerNumeroColumnas() {
		return matriz.get(0).size();
	}

	/**
	 * Nos devuelve una lista de celdas contiguas.
	 * 
	 * @param celda
	 *            celda a la que se quiere conocer sus contiguas
	 * @return ArrayList de las celdas contiguas
	 */
	public List<Celda> obtenerCeldasContiguas(Celda celda) {
		List<Celda> consecutivos = new ArrayList<Celda>();
		int fila, columna;
		fila = celda.obtenerFila();
		columna = celda.obtenerColumna();
		for (Sentido s : Sentido.values()) {
			if (estaEnTablero(fila + s.obtenerDesplazamientoFila(), columna + s.obtenerDesplazamientoColumna())) {
				try {
					if (this.obtenerCelda(fila + s.obtenerDesplazamientoFila(),
							columna + s.obtenerDesplazamientoColumna()).esOcupable()) {

						consecutivos.add(obtenerCelda(fila + s.obtenerDesplazamientoFila(),
								columna + s.obtenerDesplazamientoColumna()));
					}
				} catch (CoordenadasIncorrectasException e) {
					throw new RuntimeException("Error calculando las celdas contiguas para " + s, e);
				}
			}
		}
		return consecutivos;
	}

	/**
	 * Devuelve el tablero en cadena.
	 * 
	 * @return Estado de todas las celdas
	 */
	public String toString() {
		String texto = "";
		for (int i = 0; i < obtenerNumeroFilas(); ++i) {
			for (int j = 0; j < obtenerNumeroColumnas(); ++j) {
				try {
					if (obtenerCelda(i, j).estaVacia()) {
						texto += "-\t";
					} else {
						texto += obtenerCelda(i, j).obtenerAtomo().obtenerColor().toChar() + "\t";
					}
				} catch (CoordenadasIncorrectasException e) {
					throw new RuntimeException("Error capturando la coordenada en " + i + " " + j, e);
				}
			}
			texto += "\n";
		}
		return texto;
	}

	/**
	 * Nos devuelve el número de atomos de un color que hay en el tablero.
	 * 
	 * @param color
	 *            color del que queremos saber su cantidad
	 * @return Número de atomos del mismo color
	 */
	public int contarAtomosDeColor(Color color) {
		int numero = 0;
		for (int i = 0; i < matriz.size(); i++) {
			for (int j = 0; j < matriz.get(0).size(); j++) {
				try {
					if (!obtenerCelda(i, j).estaVacia()) {
						if (color.equals(obtenerCelda(i, j).obtenerAtomo().obtenerColor())) {
							numero++;
						}
					}
				} catch (CoordenadasIncorrectasException e) {
					throw new RuntimeException("Error capturando la coordenada en " + i + " " + j, e);
				}
			}
		}
		return numero;
	}

	/**
	 * Obtiene la matriz de celdas para utilizar en clases descendientes.
	 * 
	 * @return matriz de 2 dimensiones de referencias a Celda
	 */
	protected List<List<Celda>> obtenerMatriz() {
		return this.matriz;
	}
}
