package juego.control;

import juego.modelo.Celda;
import juego.modelo.Jugador;
import juego.modelo.Tablero;

/**
 * Interface árbitro.
 * 
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena Sánchez</a>
 * @version 1.0 20161012
 */
public interface Arbitro {

	/**
	 * Consulta el turno actual.
	 * 
	 * @return consulta el jugador con turno actualmente
	 */
	Turno obtenerTurno();

	/**
	 * Cambia el turno actual.
	 */
	void cambiarTurno();

	/**
	 * Realiza el movimiento indicado por la jugada y cambia el turno si
	 * procede.
	 * 
	 * @param destino
	 *            destino donde colocar atomo 
	 */
	void jugar(Celda destino);

	/**
	 * Consulta el tablero actual.
	 * 
	 * @return tablero actual
	 */
	Tablero obtenerTablero();

	/**
	 * Consulta si el juego está acabado.
	 * 
	 * @return true si está acabado, false en caso contrario
	 * 
	 */
	boolean estaAcabado();

	/**
	 * Consulta el ganador actual de la partida.
	 * 
	 * @return ganador de la partida o null si no hay ganador actualmente
	 */
	Jugador obtenerGanador();

	/**
	 * Comprueba si la jugada es legal según las reglas del juego. 
	 * 
	 * @param celda
	 *            celda
	 * @return true si es legal, false en caso contrario
	 */
	boolean esMovimientoLegal(Celda celda);

}