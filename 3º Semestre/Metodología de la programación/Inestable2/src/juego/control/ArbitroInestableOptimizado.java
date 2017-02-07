package juego.control;

import juego.modelo.*;
import java.util.List;

/**
 * Arbitro con un jugar que termina antes si ya hay un ganador.
 * 
 * @author José Miguel Ramírez Sanz
 * @author José Luis Garrido Labrador
 * @since 1.0
 * @version 1.0
 */
public class ArbitroInestableOptimizado extends ArbitroAbstracto {
	/**
	 * Constructor del árbitro optimizado.
	 * 
	 * @param tablero referencia al tablero con el que se jugará
	 * @param rojo jugador rojo
	 * @param azul jugador azul
	 */
	public ArbitroInestableOptimizado(Tablero tablero, Jugador rojo, Jugador azul) {
		super(tablero, rojo, azul);
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @param destino
	 *            {@inheritDoc}
	 */
	@Override
	public void jugar(Celda destino) {
		explotar(destino);
		cambiarTurno();
	}

	/**
	 * General las explosiones.
	 * 
	 * @param destino celda a explotar
	 */
	private void explotar(Celda destino) {
		if (destino.estaVacia()) {
			Atomo atomo = obtenerTurno().obtenerJugadorConTurno().generarAtomo();
			obtenerTablero().colocar(atomo, destino);
		} else if (destino.obtenerAtomo().obtenerColor()
				.equals(obtenerTurno().obtenerJugadorConTurno().obtenerColor())) {
			destino.obtenerAtomo().incrementarCarga();
		}
		List<Celda> contiguas = obtenerTablero().obtenerCeldasContiguas(destino);
		if (destino.obtenerAtomo().obtenerCarga() == contiguas.size()) {
			calcularExplosiones(destino);
		}
	}

	/**
	 * Calcula las explosiones ocasionadas por una jugada.
	 * 
	 * @param destino
	 *            celda de la jugada
	 */
	private void calcularExplosiones(Celda destino) {
		destino.vaciar();
		for (Celda celda : obtenerTablero().obtenerCeldasContiguas(destino)) {
			if (!celda.estaVacia())
				if (celda.obtenerAtomo().obtenerColor().equals(obtenerTurno().obtenerJugadorSinTurno().obtenerColor()))
					celda.obtenerAtomo().cambiarColor();

			if (obtenerGanador() == null || obtenerGanador() == obtenerTurno().obtenerJugadorSinTurno())
				if (celda.esOcupable())
					explotar(celda);

		}
	}
}
