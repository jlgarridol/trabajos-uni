package juego.control;

/**
 * ArbitroTresEnRaya
 * 
 * @author Jose Luis Garrido Labrador
 * @author Jose Miguel Ramirez Sanz
 */

//Importaciones de paquetes
import juego.modelo.*;
import juego.util.*;
import java.util.Scanner;

public class ArbitroTresEnRaya {

	// Atributos
	/**
	 * Numero necesario para ganar.
	 */
	private static final int NUM_GANADOR = 3;
	/**
	 * 
	 */
	private static int jugadoresRegistrados = 0;
	/**
	 * 
	 */
	private static boolean turno = false;
	/**
	 * Numero de jugadores.
	 */
	private int numeroJugadores;
	/**
	 * Boolean que nos dice si el juego ha acabado o no.
	 */
	private boolean juegoAcabado;
	/**
	 * Tablero del juego.
	 */
	private Tablero tablero;
	/**
	 * Array de Jugadores.
	 */
	private Jugador[] jugadores = new Jugador[2];

	/**
	 * Constructor.
	 * 
	 * @param numeroJugadores
	 */
	public ArbitroTresEnRaya(int numeroJugadores) {
		this.numeroJugadores = numeroJugadores;
		this.tablero = new Tablero(3, 3);
	}

	// Procedimientos
	/**
	 * Funcion para registrar el nombre de un jugador.
	 * 
	 * @param nombre
	 *            variable donde se alamcenara el nombre del jugador
	 */
	public void registrarJugador(String nombre) {
		Color color = null;
		if (jugadoresRegistrados == 0)
			color = Color.BLANCO;
		else
			color = Color.NEGRO;
		this.jugadores[jugadoresRegistrados++] = new Jugador(nombre, color);
	}

	/**
	 * Método que nos dice de que jugador es el turno.
	 * 
	 * @return variable jugadores[] según el turno
	 */
	public Jugador obtenerTurno() {
		if (turno)
			return jugadores[1];
		return jugadores[0];
	}

	/**
	 * Nos devuelve el ganador del Tres en raya si lo hay.
	 * 
	 * @return jugadores[]
	 */
	public Jugador obtenerGanador() {
		int contador;
		Color colorGanador = null;
		Celda celda;
		for (int i = 0; i < tablero.obtenerNumeroFilas(); ++i) {
			for (int j = 0; j < tablero.obtenerNumeroColumnas(); ++j) {
				celda = tablero.obtenerCelda(i, j);
				contador = tablero.contarPiezas(celda, Direccion.HORIZONTAL);
				colorGanador = colorGanador(contador, celda);
				if (colorGanador == null) {
					contador = tablero.contarPiezas(celda, Direccion.VERTICAL);
					colorGanador = colorGanador(contador, celda);
				}
				if (colorGanador == null) {
					contador = tablero.contarPiezas(celda, Direccion.DIAGONAL_NO_SE);
					colorGanador = colorGanador(contador, celda);
				}
				contador = tablero.contarPiezas(celda, Direccion.DIAGONAL_SO_NE);
				colorGanador = colorGanador(contador, celda);
				if (colorGanador == null)
					return null;
				else {
					if (jugadores[0].obtenerColor() == colorGanador)
						return jugadores[0];
					else
						return jugadores[1];
				}
			}
		}
		return null;
	}

	/**
	 * Nos devuelve el color del ganador si lo hay.
	 * 
	 * @param contador
	 *            con el número de piezas para Tres en raya
	 * @param celda
	 * @return
	 */
	private Color colorGanador(int contador, Celda celda) {
		if (contador == NUM_GANADOR)
			return celda.obtenerPieza().obtenerColor();
		return null;
	}

	/**
	 * Coloca piezas en el tablero y cambia el turno.
	 * 
	 * @param x
	 *            fila pasada por el jugador
	 * @param y
	 *            columna pasada por el jugador
	 */
	public void jugar(int x, int y) {
		Pieza pieza;
		if (turno)
			pieza = new Pieza(jugadores[1].obtenerColor());
		else
			pieza = new Pieza(jugadores[0].obtenerColor());

		if (tablero.estaEnTablero(x, y) && esMovimientoLegal(x, y)) {
			tablero.colocar(pieza, tablero.obtenerCelda(x, y));
		}
		turno = !turno;
	}

	/**
	 * Nos dice si la pieza colocada es legal o no.
	 * 
	 * @param x
	 *            fila
	 * @param y
	 *            columna
	 * @return true o false según si el movimiento es legal
	 */
	public boolean esMovimientoLegal(int x, int y) {
		if (tablero.obtenerCelda(x, y).estaVacia())
			return true;
		else
			return false;
	}

	/**
	 * Devuelve el tablero.
	 * 
	 * @return tablero
	 */
	public Tablero obtenerTablero() {
		return tablero;
	}

	/**
	 * Nos dice si el juego ha acabado o no.
	 * 
	 * @return true o false según si se ha acabado el juego o no
	 */
	public boolean estaAcabado() {
		if (tablero.estaCompleto() || this.obtenerGanador() != null)
			return true;
		else
			return false;
	}

	/**
	 * Devuelve el número de jugadores.
	 * 
	 * @return numeroJugadores
	 */
	public int obtenerNumeroJugadores() {
		return this.numeroJugadores;
	}

	/**
	 * Devuelve el jugador.
	 * 
	 * @param i número asociado al jugador
	 * @return jugadores
	 */
	public Jugador obtenerJugador(int i) {
		return jugadores[i];
	}

	/**
	 * Método main que realiza todo el juego del tres en raya.
	 * 
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArbitroTresEnRaya arbitro = new ArbitroTresEnRaya(2);
		int x, y;

		for (int i = 0; i < arbitro.obtenerNumeroJugadores(); ++i) {
			System.out.println("Jugador " + (i + 1) + "\n¿Cual es tu nombre?");
			arbitro.registrarJugador(scanner.nextLine());
			System.out.println("Tu figura es " + arbitro.obtenerJugador(i).obtenerColor().toChar());
		}
		do {
			// System.out.print(arbitro.obtenerTablero());
			System.out.print(arbitro.obtenerTablero().toString());
			System.out.println("El turno es de " + arbitro.obtenerTurno().obtenerNombre());
			System.out.print("Selecciona la fila: ");
			x = scanner.nextInt();
			System.out.print("\nSelecciona la columna: ");
			y = scanner.nextInt();
			if (!arbitro.obtenerTablero().estaEnTablero(x, y)) {
				System.err.println("Coordenadas incorrectas");
			} else if (arbitro.obtenerTablero().obtenerCelda(x, y).estaVacia()) {
				arbitro.jugar(x, y);
			}
			if (arbitro.obtenerGanador() != null) {
				System.out.println(arbitro.obtenerGanador().obtenerNombre());
			}
		} while (!arbitro.estaAcabado());
		scanner.close();
	}

}
