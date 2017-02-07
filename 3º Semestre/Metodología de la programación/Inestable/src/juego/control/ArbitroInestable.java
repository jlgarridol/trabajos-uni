package juego.control;

import juego.modelo.*;
import java.util.ArrayList;

/**
 * Clase que es el árbitro del juego.
 * 
 * @author José Miguel Ramírez Sanz
 * @author José Luis Garrido Labrador
 * @since 1.0
 *
 */
public class ArbitroInestable {
	// Atributos
	/**
	 * Tablero.
	 */
	private Tablero tablero;

	/**
	 * Jugador azul.
	 */
	private Jugador azul;

	/**
	 * Jugador rojo.
	 */
	private Jugador rojo;

	/**
	 * Turno.
	 */
	private Turno turno;
	// static int llamadas; //Debug

	/**
	 * Constructor de la clase ArbitroInestable.
	 * 
	 * @param tablero
	 *            tablero del jueg
	 * @param rojo
	 *            jugador primero
	 * @param azul
	 *            jugador segundo
	 */
	public ArbitroInestable(Tablero tablero, Jugador rojo, Jugador azul) {
		this.tablero = tablero;
		this.rojo = rojo;
		this.azul = azul;
		turno = new Turno(rojo, azul);
	}

	/**
	 * Nos devuelve el tablero.
	 * 
	 * @return tablero
	 */
	public Tablero obtenerTablero() {
		return tablero;
	}

	/**
	 * Nos devuelve el turno.
	 * 
	 * @return turno
	 */
	public Turno obtenerTurno() {
		return turno;
	}

	/**
	 * Devuelve true o false según si el movimiento es legal o no.
	 * 
	 * @param celda
	 *            donde se desea colocar
	 * @return boolean que es true si el movimiento realizado es legal
	 */
	public boolean esMovimientoLegal(Celda celda) {
		boolean legal = true;
		if (!celda.estaVacia()) {
			if (!celda.obtenerAtomo().obtenerColor().equals(obtenerTurno().obtenerJugadorConTurno().obtenerColor())) {
				legal = false;
			}
		}
		return legal;
	}

	/**
	 * Método que nos dice si ha acabado el juego. El juego se acaba si no hay
	 * átomos de un jugador en el tablero
	 * 
	 * @return true si se ha acabado el juego o false si no se ha acabado
	 */
	public boolean estaAcabado() {
		boolean respuesta = false;
		if (rojo.estaIniciado() && azul.estaIniciado()) {
			int n_a = 0, n_r = 0;
			n_a = tablero.contarAtomosDeColor(azul.obtenerColor());
			n_r = tablero.contarAtomosDeColor(rojo.obtenerColor());
			if (n_a == 0 || n_r == 0) {
				respuesta = true;
			}
		}
		return respuesta;
	}

	/**
	 * A partir de una celda genera una jugada.
	 * 
	 * @param destino
	 *            celda de la jugada
	 */
	public void jugar(Celda destino) {
		ArrayList celda = new ArrayList();
		celda.add(destino);
		hacerJugada(celda);
		turno.cambiarTurno();
		// llamadas = 0;/Debug
	}

	/**
	 * Realiza las jugadas en las celdas pasadas por parámetro. Las celdas
	 * pasadas por parámetro se pasan por ponerPieza y si tienen que explotar se
	 * llama a explotar. Mientras no haya ganador o haya habido explosiones se
	 * seguirá repitiendo la recursividad.
	 * 
	 * @param celdas
	 *            ArrayList con las celdas para jugar
	 */
	private void hacerJugada(ArrayList celdas) {
		// System.out.println(llamadas++); //Debug
		ArrayList celdasNuevas = new ArrayList();
		if (celdas.size() != 0) {
			for (Object o : celdas) {
				Celda c = (Celda) o;
				ponerPieza(c);
				ArrayList celdasTemp = new ArrayList();
				if (c.obtenerAtomo().obtenerCarga() == tablero.obtenerCeldasContiguas(c).size()) {
					celdasTemp = explotar(c);
					moverDatos(celdasNuevas, celdasTemp);
				} // Aquí se explota
			} // Ponemos o explotamos todas las celdas
			if (obtenerGanador() == null || obtenerGanador() == turno.obtenerJugadorSinTurno())
				hacerJugada(celdasNuevas);
		}
	}

	/**
	 * Pone un átomo en una celda o incrementa su carga.
	 * 
	 * @param destino
	 *            celda donde interactuar
	 */
	private void ponerPieza(Celda destino) {
		if (destino.estaVacia()) {
			Atomo atomo = turno.obtenerJugadorConTurno().generarAtomo();
			tablero.colocar(atomo, destino);
		} else {
			destino.obtenerAtomo().incrementarCarga();
		}
	}

	/**
	 * Vacia la celda y convierte las de su alrededor a su color (si existen).
	 * 
	 * @param destino
	 *            celda a explotar
	 * @return celdas contiguas a la celda explotada
	 */
	private ArrayList explotar(Celda destino) {
		destino.vaciar();
		ArrayList celdas = tablero.obtenerCeldasContiguas(destino);
		for (Object o : celdas) {
			Celda c = (Celda) o;
			if (!c.estaVacia()) {
				if (c.obtenerAtomo().obtenerColor().equals(turno.obtenerJugadorSinTurno().obtenerColor()))
					c.obtenerAtomo().cambiarColor();
			}
		} // Cambiamos el color si es necesario
		return celdas;
	}

	private void moverDatos(ArrayList destino, ArrayList origen) {
		for (Object o : origen) {
			destino.add(o);
		}
	}

	/**
	 * Comprueba si el tablero está acabado y de quien son los atomos del
	 * tablero.
	 * 
	 * @return jugador ganador
	 */
	public Jugador obtenerGanador() {
		if (estaAcabado()) {
			if (tablero.contarAtomosDeColor(azul.obtenerColor()) > 0) {
				return azul;
			} else {
				return rojo;
			}
		}

		return null;
	}

	/**
	 * Imprime estado de tablero, y sus jugadores.
	 */
	public String toString() {
		return "" + tablero + '/' + turno;
	}
}
