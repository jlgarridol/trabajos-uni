package pruebas;

import estructuras.Array;

public class Prueba {
	
	static Array<Integer> elementos;
	
	public static void main(String[] args) {
		elementos = new Array<>(5);
		for(int i = 0; i<elementos.size;i++){
			elementos.poner(i, i);
			System.out.println(elementos.obtener(i));
		}
		System.out.print(elementos.obtener(5));
	}
	
}