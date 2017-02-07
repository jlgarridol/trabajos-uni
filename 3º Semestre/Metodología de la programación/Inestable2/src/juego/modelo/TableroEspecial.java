package juego.modelo;

/**
 * Clase con un tableros sin esquinas ocupables.
 * 
 * @author José Miguel Ramírez Sanz
 * @author José Luis Garrido Labrador
 * @since 1.0
 * @version 2.0
 */
public class TableroEspecial extends Tablero {

	/**
	 * Genera un tablero con esquinas no ocupables.
	 * 
	 * @param filas
	 *            dimension de las filas
	 * @param columnas
	 *            dimension de las columnas
	 */
	public TableroEspecial(int filas, int columnas) {
		super(filas, columnas);
		
		this.obtenerMatriz().get(0).set(0, new Celda(0, 0, false));
		this.obtenerMatriz().get(0).set(columnas - 1, new Celda(0, columnas - 1, false));
		this.obtenerMatriz().get(filas - 1).set(0, new Celda(filas - 1, 0, false));
		this.obtenerMatriz().get(filas - 1).set(columnas - 1, new Celda(filas - 1, columnas - 1, false));
	}

}
