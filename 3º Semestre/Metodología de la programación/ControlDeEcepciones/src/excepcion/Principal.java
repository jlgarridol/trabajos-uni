package excepcion;

import java.util.Scanner;

/**
 * Principal.
 * 
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
public class Principal {

	/**
	 * Método raíz.
	 *
	 * @param args
	 *            argumentos
	 */
	public static void main(String[] args) throws RuntimeException {
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		int counter = 0;
		try {
			while (flag) {
				try {
					String s = scanner.next();
					Conversor conversor = new Conversor();
					System.out.println(conversor.aMinúsculas(s));
					System.out.println(conversor.aMayúsculas(s));
					flag = false;
				} catch (AException e) {
					System.out.println("El texto es incorrecto, no puede empezar por x o y\n");
					if (counter++ == 4)
						throw new RuntimeException("Excedido número de intentos", e);
				}
			}
		} finally {
			scanner.close();
		}
	}

}
