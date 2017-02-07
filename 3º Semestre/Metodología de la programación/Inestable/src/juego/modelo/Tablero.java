package juego.modelo;

import juego.util.Sentido;
import java.util.ArrayList;

/**
 * Clase tablero.
 * 
 * @author José Miguel Ramírez Sanz
 * @author José Luis Garrido Labrador
 * @since 1.0
 */
public class Tablero {

	// Atributos
	/**
	 * Matriz del tablero compuesto por celdas.
	 */
	private Celda[][] matriz;

	/**
	 * Construye el tablero y crea una celda en cada una de las casillas.
	 * 
	 * @param filas
	 *            Dimensión filas
	 * @param columnas
	 *            Dimensión columnas
	 */
	public Tablero(int filas, int columnas) {
		matriz = new Celda[filas][columnas];
		for (int i = 0; i < filas; ++i) {
			for (int j = 0; j < columnas; ++j) {
				this.matriz[i][j] = new Celda(i, j);
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
		celda.establecerAtomo(atomo);
		atomo.establecerCelda(celda);
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
	 */
	public Celda obtenerCelda(int fila, int columna) {
		if (estaEnTablero(fila, columna))
			return matriz[fila][columna];
		else
			return null;
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
		return (fila < matriz.length && columna < matriz[0].length && fila >= 0 && columna >= 0);
	}

	/**
	 * Retorna la cantidad de filas del tablero.
	 * 
	 * @return las filas de la matriz
	 */
	public int obtenerNumeroFilas() {
		return matriz.length;
	}

	/**
	 * Retorna la cantidad de columnas del tablero.
	 * 
	 * @return las columnas de la matriz
	 */
	public int obtenerNumeroColumnas() {
		return matriz[0].length;
	}

	/**
	 * Nos devuelve una lista de celdas contiguas.
	 * 
	 * @param celda celda a la que se quiere conocer sus contiguas
	 * @return ArrayList de las celdas contiguas
	 */
	public ArrayList<Celda> obtenerCeldasContiguas(Celda celda) {
		ArrayList<Celda> consecutivos = new ArrayList<>();
		int fila, columna;
		fila = celda.obtenerFila();
		columna = celda.obtenerColumna();
		for (Sentido s : Sentido.values()) {
			if (estaEnTablero(fila + s.obtenerDesplazamientoFila(), columna + s.obtenerDesplazamientoColumna())) {
				consecutivos.add(
						obtenerCelda(fila + s.obtenerDesplazamientoFila(), columna + s.obtenerDesplazamientoColumna()));
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
				if (obtenerCelda(i, j).estaVacia()) {
					texto += "-\t";
				} else {
					texto += obtenerCelda(i, j).obtenerAtomo().obtenerColor().toChar() + "\t";
				}
			}
			texto += "\n";
		}
		return texto;
	}

	/**
	 * Nos devuelve el número de atomos de un color que hay en el tablero.
	 * 
	 * @param color color del que queremos saber su cantidad
	 * @return Número de atomos del mismo color
	 */
	public int contarAtomosDeColor(Color color) {
		int numero = 0;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				if (!obtenerCelda(i, j).estaVacia()) {
					if (color.equals(obtenerCelda(i, j).obtenerAtomo().obtenerColor())) {
						numero++;
					}
				}
			}
		}
		return numero;
	}

}
