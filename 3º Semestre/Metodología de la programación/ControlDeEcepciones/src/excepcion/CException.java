package excepcion;

/**
 * Excepción ejemplo.
 * 
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @author <a href="mailto:cpardo@ubu.es">Carlos Pardo</a> *
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CException extends BException {

	/**
	 * Constructor sin argumentos.
	 */
	public CException() {		
	}

	/**
	 * Constructor con texto descriptivo.
	 * 
	 * @param arg0 texto descriptivo
	 */
	public CException(String arg0) {
		super(arg0);
	}

	/**
	 * Constructor con excepción encadenada.
	 * 
	 * @param arg0 excepción encadenada
	 */
	public CException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * Constructor con texto descriptivo y excepción encadenada.
	 * 
	 * @param arg0 texto descriptivo
	 * @param arg1 excepción encadenada
	 */
	public CException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
