package juego.util;

/**
 * Excepción para cuando las coordenadas provistas son incorrectas.
 * 
 * @author José Luis Garrido Labrador
 * @author José Miguel Ramírez Sanz
 * @since 1.0
 * @version 1.0
 */
public class CoordenadasIncorrectasException extends Exception {

	/**
	 * Nos obliga ponerlo.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor vacio.
	 */
	public CoordenadasIncorrectasException(){
		super();
	}
	/**
	 * Constructor con mensaje.
	 * 
	 * @param s mensaje
	 */
	public CoordenadasIncorrectasException(String s){
		super(s);
	}
	
	/**
	 * Constructor con mensaje y objeto lanzable.
	 * 
	 * @param s Mensaje
	 * @param e objeto lanzable
	 */
	public CoordenadasIncorrectasException(String s, Throwable e){
		super(s,e);
	}
	/**
	 * Constructor con unicamente un objeto lanzable.
	 * 
	 * @param e objeto lanzable
	 */
	public CoordenadasIncorrectasException(Throwable e){
		super(e);
	}
	/**
	 * Constructor para try-with-resources que se hace automáticamente.
	 * 
	 * @param s cadena con mensaje
	 * @param e Objeto lanzable
	 * @param b1 boolean 1
	 * @param b2 boolean 2
	 */
	public CoordenadasIncorrectasException(String s, Throwable e, boolean b1, boolean b2){
		super(s,e,b1,b2);
	}
}
