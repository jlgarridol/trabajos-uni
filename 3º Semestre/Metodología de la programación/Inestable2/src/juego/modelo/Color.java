package juego.modelo;

/**
 * Enumeración del color.
 * 
 * @author José Miguel Ramírez Sanz
 * @author José Luis Garrido Labrador
 * @since 1.0
 * @version 1.0
 */
public enum Color {
	/**
	 * Jugador primero.
	 */
	ROJO('O'),
	/**
	 * Jugador segundo.
	 */
	AZUL('X');
	// Atributos
	/**
	 * Caracter asociada al color.
	 */
	private char caracter; // Caracter asociado al color

	// Constructores

	/**
	 * Le da al color un caracter.
	 * 
	 * @param caracter
	 *            caracter asociado al color
	 */
	private Color(char caracter) {
		this.caracter = caracter;
	}

	// Funciones

	/**
	 * {@inheritDoc}.
	 * 
	 * @return {@inheritDoc}
	 */
	public char toChar() {
		return caracter;
	}
}
