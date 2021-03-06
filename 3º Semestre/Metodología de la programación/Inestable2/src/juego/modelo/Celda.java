package juego.modelo;

/**
 * Clase con las casillas de los tableros.
 * 
 * @author José Luis Garrido Labrador
 * @author José Miguel Ramírez Sanz
 * @since 1.0
 * @version 2.0
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

	/**
	 * Valor que determina si la celda puede contener atomos.
	 */
	private boolean ocupable;

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
		this.ocupable = true; //Es ocupable hasta que se demuestre lo contrario
	}

	/**
	 * Generar celdas para tablero especial (con esquinas no ocupables).
	 * 
	 * @param fila Posicion fila que ocupa
	 * @param columna Posicion columna que ocupa
	 * @param ocupable Si la celda puede contener atomos
	 * @see #Celda(int fila, int columna)
	 */
	public Celda(int fila, int columna, boolean ocupable) {
		this(fila,columna);
		this.ocupable = ocupable;
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
	 * No puede llamarse si está vacia
	 */
	public void vaciar() {
		//Precondición
		assert !this.estaVacia() : "Intentando vaciar celda sin átomo";
		
		atomo.establecerCelda(null);
		atomo = null;
		
		//Postcondición
		assert this.estaVacia() : "Error vaciando la celda";
	}

	// Funciones

	/**
	 * Dice si una celda puede contener átomos.
	 * 
	 * @return numero booleano con la variable ocupable
	 */
	public boolean esOcupable(){
		return this.ocupable;
	}
	
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
		return fila + " - " + columna + " - " + ocupable + "-" + atomo;
	}
}
