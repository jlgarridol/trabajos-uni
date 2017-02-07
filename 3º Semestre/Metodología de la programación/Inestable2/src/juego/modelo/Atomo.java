package juego.modelo;

/**
 * Atomo de cada celda.
 *
 * @author José Luis Garrido Labrador
 * @author José Miguel Ramírez Sanz
 * @since 1.0
 * @version 1.0
 */
public class Atomo {

	// Atributos

	/**
	 * Color asociado del atomo.
	 */
	private Color color;

	/**
	 * Celda que contiene esta atomo.
	 */
	private Celda celda;

	/**
	 * Carga del atomo, al comenzar vale 1.
	 */
	private int carga = 1;

	// Constructores

	/**
	 * Construye Atomo con color.
	 * 
	 * @param color
	 *            valor a asignar a color
	 */
	Atomo(Color color) {
		this.color = color;
	}

	/**
	 * Cambia el color del atomo.
	 */
	public void cambiarColor() {
		switch (this.color) {
		case ROJO:
			this.color = Color.AZUL;
			break;
		default:
			this.color = Color.ROJO;
			break;
		}
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

	/**
	 * Incrementa en 1 la carga del atomo.
	 */
	public void incrementarCarga() {
		this.carga++;
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
	 * Retorna la carga del atomo.
	 * 
	 * @return entero con la carga
	 */
	public int obtenerCarga() {
		return carga;
	}

	/**
	 * Retorna el estado del atomo.
	 * 
	 * @return Estado del objeto
	 */
	@Override
	public String toString() {
		return color + " - " + carga;
	}

}
