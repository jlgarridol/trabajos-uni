package juego.util;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import juego.modelo.Celda;
import juego.modelo.Tablero;

/**
 * Conversor de texto a celda. 
 * 
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @author <a href="mailto:rredondo@ubu.es">Raquel Redondo</a>
 * @version 1.0 20161008
 */
public class ConversorJugada {

	/** Logger. */
	private static final Logger logger = LoggerFactory.getLogger(ConversorJugada.class);

	/**
	 * Valida la corrección de la jugada.
	 * 
	 * @param textoJugada
	 *            texto con la jugada a validar
	 * @param tablero
	 *            tablero
	 * @return celda seleccionada o null si la entrada no puede ser convertida a
	 *         una celda válida
	 * @throws CoordenadasIncorrectasException si el texto es incorrecto
	 */
	public static Celda convertir(String textoJugada, Tablero tablero) throws CoordenadasIncorrectasException {
		Celda celda = null;
		if (validar(textoJugada, tablero)) {
			celda = traducirACelda(textoJugada, tablero);
			assert tablero.estaEnTablero(celda.obtenerFila(), celda.obtenerColumna()) : "La celda obtenida "
					+ celda.toString() + " no pertenece al tablero.";
			return celda;
		}
		else {
			throw new CoordenadasIncorrectasException("No se puede convertir " + textoJugada + " en coordenadas válidas dentro del tablero");
		}
		
	}

	/**
	 * Valida la sintaxis y semántica de la jugada en función del tamaño del
	 * tablero.
	 * 
	 * @param textoJugada
	 *            texto con la jugada a validar
	 * @param tablero
	 *            tablero 
	 * @return true si es válida y false en caso contrario
	 */
	public static boolean validar(String textoJugada, Tablero tablero) {

		final char LIM_NUM_SUP = calcularSuperior('0', tablero.obtenerNumeroFilas());
		final char LIM_CAR_SUP = calcularSuperior('a', tablero.obtenerNumeroColumnas());

		/** Expresión regular con patrón de texto a utilizar. */
		final Pattern PATRON = Pattern.compile("^[0-" + LIM_NUM_SUP + "][a-" + LIM_CAR_SUP + "]$");

		return PATRON.matcher(textoJugada).matches();
	}

	/**
	 * Calcula el valor límite superior que se puede introducir por teclado.
	 * 
	 * @param letra
	 *            letra de inicio
	 * @param tamaño
	 *            tamaño o número de elementos
	 * @return límite superior
	 */
	private static char calcularSuperior(char letra, int tamaño) {
		return (char) (letra + tamaño - 1); // límite superior
	}

	/**
	 * Convierte una coordenadas en texto a su correspondiente celda.
	 * 
	 * @param coordenadas
	 *            coordenadas en texto Ej: 0a
	 * @param tablero
	 *            tablero
	 * @return celda correspondiente a las coordenadas
	 * @throws CoordenadasIncorrectasException si el texto es incorrecto
	 */
	private static Celda traducirACelda(String coordenadas, Tablero tablero) throws CoordenadasIncorrectasException {
		int fila = coordenadas.charAt(0) - '0';
		int columna = coordenadas.charAt(1) - 'a';
		logger.debug("Coordenadas obtenidas: [{}/{}]", fila, columna);
		return tablero.obtenerCelda(fila, columna);
	}
} // ConversorJugada
