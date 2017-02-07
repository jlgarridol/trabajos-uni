package excepcion;

/**
 * Excepción ejemplo.
 * 
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @author <a href="mailto:cpardo@ubu.es">Carlos Pardo</a> *
 * @version 1.0
 */
@SuppressWarnings("serial")
public class AException extends Exception {

	/**
	 * Constructor sin argumentos.
	 */
	public AException() {		
	}

	/**
	 * Constructor con texto descriptivo.
	 * 
	 * @param arg0 texto descriptivo
	 */
	public AException(String arg0) {
		super(arg0);
	}

	/**
	 * Constructor con excepción encadenada.
	 * 
	 * @param arg0 excepción encadenada
	 */
	public AException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * Constructor con texto descriptivo y excepción encadenada.
	 * 
	 * @param arg0 texto descriptivo
	 * @param arg1 excepción encadenada
	 */
	public AException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
