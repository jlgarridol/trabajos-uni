package juego.modelo;

/**
 * Clase con las casillas de los tableros.
 * 
 * @author José Luis Garrido Labrador
 * @author José Miguel Ramírez Sanz
 */

public class Celda {

	// Atributos

	/**
	 * Coordenadas Y de la celda.
	 */
	private int fila;

	/**
	 * Coordenada X de la celda.
	 */
	private int columna;

	/**
	 * Pieza asociada a esta celda.
	 */
	private Pieza pieza;

	// Constructores

	/**
	 * Genera el objeto clase.
	 * 
	 * @param fila
	 *            - Posicion fila que ocupa
	 * @param columna
	 *            - Posicion columna que ocupa
	 */
	public Celda(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}

	// Procedimientos

	/**
	 * Establece el objeto Pieza al objeto Celda.
	 * 
	 * @param pieza
	 *            - Pieza que se colocarÃ¡ en esta celda
	 */
	public void establecerPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	// Funciones

	/**
	 * Retorna la fila.
	 * 
	 * @return el atributo entero fila
	 */
	public int obtenerFila() {
		return fila;
	}

	/**
	 * Retorna la columna.
	 * 
	 * @return el atributo entero columna
	 */
	public int obtenerColumna() {
		return columna;
	}

	/**
	 * Retorna la Pieza.
	 * 
	 * @return el atributo objeto pieza
	 */
	public Pieza obtenerPieza() {
		return pieza;
	}

	/**
	 * Retorna true si esta vacia y false si estÃ¡ con alguna pieza.
	 * 
	 * @return pieza == null
	 */
	public boolean estaVacia() {
		return pieza == null;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public String toString() {
		return fila + " - " + columna + " - " + pieza;
	}

}
