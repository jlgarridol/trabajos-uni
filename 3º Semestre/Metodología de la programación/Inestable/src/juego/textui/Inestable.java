package juego.textui;

import juego.util.ConversorJugada;
import java.util.Scanner;
import juego.control.ArbitroInestable;
import juego.modelo.*;

/**
 * Ejecución del juego Inestable desde CLI.
 * 
 * @author José Luis Garrido Labrador
 * @author José Miguel Ramírez Sánz
 */
public class Inestable {
	/**
	 * Nombre de los jugadores.
	 */
	private static String player1, player2;
	/**
	 * dimensiones del tablero.
	 */
	private static int primero, segundo;
	/**
	 * Arbitro del juego.
	 */
	private static ArbitroInestable arbitro;
	/**
	 * Captura de datos.
	 */
	private static Scanner scan = new Scanner(System.in);

	/**
	 * Programa principal por linea de comandos.
	 * 
	 * @param args
	 *            argumentos pasados por teclado
	 */
	public static void main(String[] args) {
		comprobacion(args);
		if (args.length < 4) {
			primero = 5;
			segundo = 5;
			if (args.length < 2) {
				player1 = "ROJAS";
				player2 = "AZULES";
			}
		}
		jugar();
	}

	/**
	 * Procedimiento para jugar.
	 */
	private static void jugar() {
		// Creamos el tablero, los jugadores y el arbitro
		Tablero tablero = new Tablero(primero, segundo);
		Jugador rojo = new Jugador(player1, Color.ROJO);
		Jugador azul = new Jugador(player2, Color.AZUL);
		arbitro = new ArbitroInestable(tablero, rojo, azul);
		// Ganador y Celda de la jugada
		Jugador ganador;
		Celda celda;
		mensajeBienvenida(); // Recibimos a los jugadores
		do {
			System.out.println(tablero()); // Imprimimos el tablero
			do {
				// Anunciamos el turno
				System.out.print("El el turno de " + arbitro.obtenerTurno().obtenerJugadorConTurno().obtenerNombre()
						+ "\nIntroduzca su jugada: ");
				// Capturamos la jugada realizada
				celda = capturarJugada();
			} while (celda == null); // Mientras la celda no este en el tablero
										// o sea incorrecta se sigue pidiendo
										// los datos
			if (arbitro.esMovimientoLegal(celda)) { // Comprobamos si es legal
				arbitro.jugar(celda); // Jugamos
				ganador = arbitro.obtenerGanador(); // Comprobamos si hay
													// ganador
			} else {
				System.err.println("Movimiento ilegal"); // Se dice que es
															// ilegal
				ganador = null; // Se anula el ganador
			}
		} while (ganador == null); // Mientras no haya ganador
		// Se anuncia el ganador
		System.out.println(tablero() + "\nEl ganador es: " + ganador.obtenerNombre() + ". Felicidades");
		scan.close();// Cerramos el scanner
	}

	/**
	 * Lee desde el teclado la celda seleccionada.
	 * 
	 * @return La celda metida por teclado
	 */
	private static Celda capturarJugada() {
		Celda cell = null;
		String value = scan.nextLine();
		cell = ConversorJugada.convertir(value, arbitro.obtenerTablero());
		if (cell == null) {
			System.err.println("Coordenadas incorrectas");
		}
		return cell;
	}

	/**
	 * Recibimos a los jugadores.
	 */
	private static void mensajeBienvenida() {
		System.out.println("Bienvenidos " + arbitro.obtenerTurno().obtenerJugadorConTurno().obtenerNombre() + " y "
				+ arbitro.obtenerTurno().obtenerJugadorSinTurno().obtenerNombre());
		System.out.println("Poned vuestro temple al límite y que gane el mejor");
	}

	/**
	 * Recorremos el tablero y creamos una cadena con la información del juego.
	 * 
	 * @return cadena con el tablero
	 */
	private static String tablero() {
		Tablero tablero = arbitro.obtenerTablero();
		String texto = "";
		for (int i = 0; i < tablero.obtenerNumeroFilas(); i++) {
			for (int j = 0; j < tablero.obtenerNumeroColumnas(); j++) {
				if (j == 0)
					texto += i + "\t";
				if (tablero.obtenerCelda(i, j).estaVacia()) {
					texto += " -- ";
				} else {
					texto += " " + tablero.obtenerCelda(i, j).obtenerAtomo().obtenerColor().toChar()
							+ tablero.obtenerCelda(i, j).obtenerAtomo().obtenerCarga() + " ";
				}
			}
			texto += "\n";
		}
		texto += "  \t";
		char caracter = '\u0061';
		for (int i = 0; i < tablero.obtenerNumeroColumnas(); i++) {
			texto += " " + caracter + "  ";
			caracter++;
		}
		return texto;

	}

	/**
	 * Comprobamos que los argumentos pasados por parametros son 0, 2 ó >= 4.
	 * 
	 * @param argumentos
	 *            argumentos pasador por teclado
	 */
	private static void comprobacion(String[] argumentos) {
		// Si solo se ha pasado 1 o 3 valores es incorrecto
		if (argumentos.length == 1) {
			error();
		} else if (argumentos.length == 3) {
			error();
		} else if (argumentos.length >= 2) { // Si se cumple esta condicion o es
												// 2 o mayor que 4
			player1 = argumentos[0];
			player2 = argumentos[1];
			if (argumentos.length >= 4) {
				// Si no son numeros da excepción
				primero = Integer.parseInt(argumentos[2]);
				segundo = Integer.parseInt(argumentos[3]);
				// Se comprueba que los valores son correcto
				if (primero < 5 || primero > 9 || segundo < 5 || segundo > 9) {
					error();
				}
			}
		}
	}

	/**
	 * Imprime mensaje de error y salta la ejecución del juego.
	 */
	private static void error() {
		System.err.println("Argumentos incorrectos o incompletos");
		System.exit(1);
	}

}
