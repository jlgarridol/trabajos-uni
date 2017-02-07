/**
 * Triángulos posibles y clase de triángulo
 * 
 * @author José Luis Garrido Labrador <JoseluCross>
 * @author José Miguel Ramírez Sanz
 * @version 1.0
 */

public class Triangulo {
	public static void main(String[] args) {

		// Variables
		float lado1 = 1.2f, lado2 = 1.2f, lado3 = 1.2f; // Lados
		String t; // Texto con mensaje

		if (lado1 <= lado2 + lado3 && lado2 <= lado1 + lado3 && lado3 <= lado1 + lado2) {
			if (lado1 == lado2 && lado2 == lado3) {
				t = "equilatero";
			} else if (lado1 == lado2 || lado2 == lado3 || lado3 == lado1) {
				t = "isósceles";
			} else {
				t = "escaleno";
			}
			System.out.println("EL tríangulo es " + t);
		} else {
			System.out.println("El tríangulo no existe");
		}

	}
}
