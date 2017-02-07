package juego.modelo;

/**
 * Tablero del juego.
 * 
 * @author Jose Luis Garrido Labrador
 * @author Jose Miguel Ramirez Sanz 
 */

//Importaciones de paquetes
import juego.util.Direccion;

public class Tablero {

	// Atributos
	private Celda[][] matriz;

	// Constructores

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

	// Procedimientos

	/**
	 * Pone en el tablero una pieza y asocia la celda con la pieza.
	 * 
	 * @param pieza
	 *            Pieza a la que se le va asignar a la Celda
	 * @param celda
	 *            Celda a la que se le va a asigna la Pieza
	 */
	public void colocar(Pieza pieza, Celda celda) {
		celda.establecerPieza(pieza);
		pieza.establecerCelda(celda);
	}

	// Funciones

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
	 * Devuelve el número del mismo color en el tablero.
	 * 
	 * @param color
	 *            Color del que quieres saber la cantidad en el tablero
	 * @return numero de piezas con el mismo color
	 */
	public int obtenerNumeroPiezas(Color color) {
		int contador = 0;
		for (int i = 0; i < matriz.length; ++i) {
			for (int j = 0; j < matriz[i].length; ++j) {
				if (!matriz[i][j].estaVacia()) {
					Color colorActual = matriz[i][j].obtenerPieza().obtenerColor();
					if (colorActual == color) {
						++contador;
					}
				}
			}
		}
		return contador;
	}

	/**
	 * Devuelve cierto solo si estan todas las casilla asociadas a una cleda con
	 * pieza.
	 * 
	 * @return true si todas las celdas tienen una pieza, false si alguna de las
	 *         celdas tiene referencia nula
	 */
	public boolean estaCompleto() {
		for (int i = 0; i < matriz.length; ++i) {
			for (int j = 0; j < matriz[i].length; ++i) {
				if (matriz[i][j].estaVacia()) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Cuenta la cantidad de piezas consecutivas del mismo color segun la
	 * direccion.
	 * 
	 * @param celda
	 *            Celda a comprobar las iguales consecutivas
	 * @param direccion
	 *            Direccion a comprobar
	 * @return cantidad de celdas consecutivas con la misma celda
	 */
	public int contarPiezas(Celda celda, Direccion direccion) {
		int coordX, coordY;// Coordendas de celda
		int contador = 0;// Veces que aparece la misma pieza
		Pieza pieza = celda.obtenerPieza(); // Pieza asociada la celda dada
		boolean salida = false; // Variable para salida de bucles excepcional
		coordX = celda.obtenerColumna();
		coordY = celda.obtenerFila();
		if (pieza != null) {
			switch (direccion) {
			case HORIZONTAL:
				contador = this.movimiento(0, 1, celda);
				break;
			case VERTICAL:
				contador = this.movimiento(1, 0, celda);
				break;
			case DIAGONAL_SO_NE:
				contador = this.movimiento(-1, 1, celda);
				break;
			case DIAGONAL_NO_SE:
				contador = this.movimiento(1, 1, celda);
				break;
			}
		}
		return contador;
	}

	private int movimiento(int i, int j, Celda celda) {

		boolean movimiento = true;
		Color colorCelda = celda.obtenerPieza().obtenerColor();
		int positionFila, positionColumna;
		int contador = 0;
		// Avanzamos positivamente
		while (movimiento) {
			positionFila = celda.obtenerFila();
			positionColumna = celda.obtenerColumna();
			if (this.estaEnTablero(positionFila + i, positionColumna + j)) {
				positionFila += i;
				positionColumna += j;
				if (colorCelda.equals(matriz[positionFila][positionColumna].obtenerPieza().obtenerColor())) {
					contador++;
				} else {
					movimiento = false;
				}
			} else {
				movimiento = false;
			}
		}
		// Avanzamos negativamente
		movimiento = true;
		while (movimiento) {
			positionFila = celda.obtenerFila();
			positionColumna = celda.obtenerColumna();
			if (this.estaEnTablero(positionFila - i, positionColumna - j)) {
				positionFila -= i;
				positionColumna -= j;
				if (colorCelda.equals(matriz[positionFila][positionColumna].obtenerPieza().obtenerColor())) {
					contador++;
				} else {
					movimiento = false;
				}
			} else {
				movimiento = false;
			}
		}
		return contador;
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
					texto += obtenerCelda(i, j).obtenerPieza().obtenerColor().toChar() + "\t";
				}
			}
			texto += "\n";
		}
		return texto;
	}

}
