package juego.modelo;

/**
 * Pieza.
 *
 * @author Jos� Luis Garrido Labrador
 * @author Jos� Miguel Ram�rez Sanz
 */

public class Pieza {

	// Atributos

	/**
	 * Color asociado a la Pieza.
	 */
	private Color color;

	/**
	 * Celda que contiene esta Pieza.
	 */
	private Celda celda;

	// Constructores

	/**
	 * Construye Pieza con color.
	 * 
	 * @param color
	 *            valor a asignar a color
	 */
	public Pieza(Color color) {
		establecerColor(color);
	}

	// Procedimientos

	/**
	 * Asocia el color.
	 * 
	 * @param color
	 *            valor a asignar a color
	 */
	private void establecerColor(Color color) {
		this.color = color;
	}

	/**
	 * Asocia la celda.
	 * 
	 * @param celda
	 *            celda donde va a estar esta ficha
	 */
	public void establecerCelda(Celda celda) {
		this.celda = celda;
	}
	
	

	// Funciones

	/**
	 * Retorna el color asociado.
	 * 
	 * @return atributo color
	 */
	public Color obtenerColor() {
		return color;
	}

	/**
	 * Retorna la celda asociada.
	 * 
	 * @return atributo celda
	 */
	public Celda obtenerCelda() {
		return celda;
	}

	/**
	 * Retorna el estado de Pieza.
	 * 
	 * @return Estado del objeto
	 */
	public String toString() {
		return color + " - " + celda;
	}
}
