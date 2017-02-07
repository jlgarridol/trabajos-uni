package juego.util;

/**
 * Sentido del movimiento.
 * 
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @author <a href="mailto:rredondo@ubu.es">Raquel Redondo</a>
 * @version 1.0 20161012
 */
public enum Sentido {
	
	/** Izquierda. */
	IZQUIERDA(0, -1),
	
	/** Vertical. */
	ARRIBA(-1, 0), 
		
	/** Horizontal.
	 */
	DERECHA(0, 1), 
		
	/** Abajo. */
	ABAJO(1, 0);

	/**
	 * Constructor.
	 * 
	 * @param desplFila
	 *            desplazamiento en las filas
	 * @param desplColumna
	 *            desplazamiento en columnas
	 */
	private Sentido(int desplFila, int desplColumna) {
		this.desplazamientoFila = desplFila;
		this.desplazamientoColumna = desplColumna;
	}

	/**
	 * Desplazamiento en filas.
	 */
	private int desplazamientoFila;

	/**
	 * Desplazamiento en columnas.
	 */
	private int desplazamientoColumna;

	/**
	 * Obtiene el desplazamiento en filas.
	 * 
	 * @return desplazamiento en filas
	 */
	public int obtenerDesplazamientoFila() {
		return this.desplazamientoFila;
	}

	/**
	 * Obtiene el desplazamiento en columnas.
	 * 
	 * @return desplazamiento en columnas
	 */
	public int obtenerDesplazamientoColumna() {
		return this.desplazamientoColumna;
	}
}
