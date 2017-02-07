package juego.modelo;

/**
 * Enumeración color.
 *
 * @author José Luis Garrido Labrador
 * @author José Miguel Ramírez Sanz
 */

public enum Color {
	BLANCO('O'), NEGRO('X');

	// Atributos
	/**
	 * Letra asociada al color.
	 */
	private char letra; // Caracter asociado al color

	// Constructores

	/**
	 * Le da al color un caracter.
	 * 
	 * @param letra
	 *            caracter asociado al color
	 */
	private Color(char letra) {
		this.letra = letra;
	}

	// Funciones

	/**
	 * {@inheritDoc}.
	 * 
	 * @return {@inheritDoc}
	 */
	public char toChar() {
		return letra;
	}
}
