package juego.control;

import juego.modelo.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que es el árbitro del juego que no para hasta que terminen las explosiones.
 * 
 * @author José Miguel Ramírez Sanz
 * @author José Luis Garrido Labrador
 * @since 1.0
 * @version 2.0
 */
public class ArbitroInestable extends ArbitroAbstracto {


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
		super(tablero,rojo,azul);
	}


	/**
	 * A partir de una celda genera una jugada.
	 * 
	 * @param destino
	 *            celda de la jugada
	 */
	@Override
	public void jugar(Celda destino) {
		ArrayList<Celda> celda = new ArrayList<>();
		celda.add(destino);
		hacerJugada(celda);
		obtenerTurno().cambiarTurno();
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
	private void hacerJugada(List<Celda> celdas) {
		List<Celda> celdasNuevas = new ArrayList<>(); //Celdas afectadas por las explosiones
		if (celdas.size() != 0) {
			for (Celda c : celdas) {
				ponerPieza(c);
				if (c.obtenerAtomo().obtenerCarga() == obtenerTablero().obtenerCeldasContiguas(c).size()) {
					moverDatos(celdasNuevas, explotar(c)); //Exploto la celda y guardo las afectadas
				} // Aquí se explota
			} // Ponemos o explotamos todas las celdas
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
			Atomo atomo = obtenerTurno().obtenerJugadorConTurno().generarAtomo();
			obtenerTablero().colocar(atomo, destino);
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
	private List<Celda> explotar(Celda destino) {
		destino.vaciar();
		List<Celda> celdas = obtenerTablero().obtenerCeldasContiguas(destino);
		for (Celda c : celdas) {
			if (!c.estaVacia()) {
				if (c.obtenerAtomo().obtenerColor().equals(obtenerTurno().obtenerJugadorSinTurno().obtenerColor()))
					c.obtenerAtomo().cambiarColor();
			}
		} // Cambiamos el color si es necesario
		return celdas;
	}

	/**
	 * Mueve los datos de un arraylist a otro arraylist.
	 * 
	 * @param destino arraylist de destino con los datos a mover
	 * @param origen arraylist de origen con los datos a mover
	 */
	private void moverDatos(List<Celda> destino, List<Celda> origen) {
		for (Celda o : origen) {
			destino.add(o);
		}
	}
}

	
