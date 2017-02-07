package excepcion;

/**
 * Realiza conversiones entre cadenas excepto cuando
 * la cadena empieza por x.
 * 
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @author <a href="mailto:cpardo@ubu.es">Carlos Pardo</a> *
 * @version 1.0
 */
public class Conversor {

	/**
	 * Convierte la cadena a minúsculas.
	 * 
	 * @param s texto entrada
	 * @return texto en minúsculas
	 * @throws CException cuando la cadena comienza por x
	 */
	public String aMinúsculas(String s) throws CException {
		if (s.charAt(0)=='x') {
			throw new CException("Error con cadena comenzando con x");
		}
		return s.toLowerCase();
	}
	
	/**
	 * Convierte la cadena a mayúsculas.
	 * 
	 * @param s texto entrada
	 * @return texto en mayúsculas
	 * @throws BException cuando la cadena comienza por y
	 */
	public String aMayúsculas(String s) throws BException {
		if (s.charAt(0)=='y') {
			throw new BException("Error con cadena comenzando con y");
		}
		return s.toUpperCase();
	}
}
