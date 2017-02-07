package juego.control;

import juego.modelo.Jugador;

/**
 * Turno del juego.
 * 
 * 
 * @author José Luis Garrido Labrador
 * @author Jose Miguel Ramírez Sanz
 * @since 1.0
 */
public class Turno {
	/**
	 * Jugador rojo que empieza.
	 */
	private Jugador rojo;
	/**
	 * Jugador azul que no empieza.
	 */
	private Jugador azul;
	/**
	 * Valor booleano, false cuando es rojo true cuando es azul.
	 */
	private boolean turno;

	/**
	 * Construye el turno.
	 * 
	 * @param rojas
	 *            Jugador que empieza
	 * @param azules
	 *            Jugador que no empieza
	 */
	public Turno(Jugador rojas, Jugador azules) {
		rojo = rojas;
		azul = azules;
	}

	/**
	 * Cambia el turno.
	 */
	public void cambiarTurno() {
		turno = !turno;
	}

	/**
	 * Devuelve el jugador que tiene el turno actualmente.
	 * 
	 * @return el jugador que juega
	 */
	public Jugador obtenerJugadorConTurno() {
		if (turno)
			return azul;
		else
			return rojo;
	}

	/**
	 * Devuelve el jugador que no mueve.
	 * 
	 * @return el jugador que no juega
	 */
	public Jugador obtenerJugadorSinTurno() {
		if (turno)
			return rojo;
		else
			return azul;
	}

	/**
	 * Retorna los jugadores que juegan en orden por el que tiene el turno y el
	 * que no.
	 */
	public String toString() {
		return "" + obtenerJugadorConTurno() + " - " + obtenerJugadorSinTurno();
	}
}
