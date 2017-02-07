/**
 * Comprobar si una palabra es palíndromo
 * 
 * @author José Luis Garrido Labrador <JoseluCross>
 * @version 1.0
 */
public class Palindromo {
	public static void main(String[] args) {
		// Variable a comprobar si es palíndroma
		char[] v = { 'a', 'n', 'i', 't', 'a', 'l', 'a', 'v', 'a', 'l', 'a', 't', 'i', 'n', 'a' };

		// Comprobamos si la cadena es palíndroma
		boolean esPalindromo = true; // Variable para saber si es palíndroma o
										// no
		for (int i = 0, j = v.length - 1; i < (int) v.length / 2
				&& esPalindromo/* Solo se sigue si es palíndromo */; i++, j--) {
			if (v[i] != v[j]) {
				esPalindromo = false; // No es palíndromo
			}
		}
		// Imprimimos el resultado
		System.out.print("La cadena ");
		System.out.print(v);
		if (esPalindromo) {
			System.out.println(" es palíndroma");
		} else {
			System.out.println(" no es palíndroma");
		}
	}
}
