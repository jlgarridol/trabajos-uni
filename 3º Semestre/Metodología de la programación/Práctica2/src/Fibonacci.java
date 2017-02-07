/**
 * Fibonacci por recursividad
 * 
 * @author José Luis Garrido Labrador <JoseluCross>
 * @author José Miguel Ramírez Sanz
 * @version 1.0
 */

public class Fibonacci {
	public static void main(String[] args) {
		int value = 0;
		long fibo = fibonacciRecursive(value);
		long fibo2 = fibonacciIterative(value);
		System.out.println("El valor " + value + " en la sucesión es " + fibo);
		System.out.println("El valor " + value + " en la sucesión es " + fibo2);
	}

	/**
	 * @title fibonacciRecursive
	 * @description Saca el valor de fibonacci por recursividad
	 * @author <JoseluCross>
	 * @author José Miguel Ramírez Sanz
	 * @version 1.0
	 * @param1 valor
	 */
	public static long fibonacciRecursive(int valor) {
		if (valor == 0) // Si es 0 retornamos 0
			return 0;
		else if (valor == 1)
			return 1; // Si es 1 retornamos 1
		else
			return fibonacciRecursive(valor - 1) + fibonacciRecursive(valor - 2);
		// Bajamos el problema a los dos valores anteriores
	}

	/**
	 * @title fibonacciIterative
	 * @description Saca el valor de fibonacci por iterativo
	 * @author <JoseluCross>
	 * @author José Miguel Ramírez Sanz
	 * @version 1.0
	 * @param1 valor
	 */
	public static long fibonacciIterative(int valor) {

		long resultado = 1, resultadoAnterior = 0, swap;
		if (valor != 0) { // Si el valor es 0 no calculamos fibonacci
			for (int i = 1; i < valor; i++) { 
				// Calculamos el valor de fibonacci desde abajo
				swap = resultado;
				resultado = resultado + resultadoAnterior;
				resultadoAnterior = swap;
			}
			return resultado;
		} else {
			return 0;
		}
	}
}
