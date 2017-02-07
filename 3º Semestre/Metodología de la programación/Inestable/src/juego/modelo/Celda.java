package juego.modelo;

/**
 * Clase con las casillas de los tableros.
 * 
 * @author José Luis Garrido Labrador
 * @author José Miguel Ramírez Sanz
 * @since 1.0
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
	 * Atomo asociada a esta celda.
	 */
	private Atomo atomo;

	// Constructores

	/**
	 * Genera el objeto clase.
	 * 
	 * @param fila
	 *            Posicion fila que ocupa
	 * @param columna
	 *            Posicion columna que ocupa
	 */
	public Celda(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}

	// Procedimientos

	/**
	 * Establece el objeto Atomo al objeto Celda.
	 * 
	 * @param atomo
	 *            atomo que se colocará en esta celda
	 */
	public void establecerAtomo(Atomo atomo) {
		this.atomo = atomo;
	}

	/**
	 * Desvincula la celda del atomo y viceversa.
	 */
	public void vaciar() {
		atomo.establecerCelda(null);
		atomo = null;
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
	public Atomo obtenerAtomo() {
		return this.atomo;
	}

	/**
	 * Retorna true si esta vacia y false si está con alguna pieza.
	 * 
	 * @return atomo == null
	 */
	public boolean estaVacia() {
		return atomo == null;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public String toString() {
		return fila + " - " + columna + " - " + atomo;
	}
}
