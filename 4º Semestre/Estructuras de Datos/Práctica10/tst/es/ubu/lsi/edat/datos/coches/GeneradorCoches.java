package es.ubu.lsi.edat.datos.coches;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import es.ubu.lsi.edat.datos.coches.Coche;

public class GeneradorCoches {

	/**
	 * 
	 * @param tamano
	 * @return
	 */
	public static List<Coche> listaSecuencial(int tamano){

		HashSet<Coche> conjunto = new HashSet<Coche>(tamano);

		for(int i=0; i<tamano; i++){
			Coche nuevo = new Coche("Marca"+i,"Modelo"+i,i*100);
			conjunto.add(nuevo);
		}

		ArrayList<Coche> lista = new ArrayList<Coche>(conjunto);
		return lista;
	}


	/**
	 * 
	 * @param tamano
	 * @return
	 */
	public static List<Coche> listaMezclada(int tamano){

		List<Coche> lista = listaSecuencial(tamano);
		Collections.shuffle(lista);
		return lista;

	}

	/**
	 * 
	 * @param tamano
	 * @return
	 */
	public static List<Coche> listaAleatoria(int tamano){
		
		HashSet<Coche> conjunto = new HashSet<Coche>(tamano);

		while(conjunto.size()<tamano){
			int aleatorio = (int) (Math.random()*tamano*10);
			Coche nuevo = new Coche("Marca"+aleatorio,"Modelo"+aleatorio,aleatorio);
			conjunto.add(nuevo);
		}

		ArrayList<Coche> lista = new ArrayList<Coche>(conjunto);
		return lista;
		
	}

	/**
	 * 
	 * @return
	 */
	public static Coche generaCoche(){

		double rand = (Math.random()*1000);
		Coche nuevo = new Coche("Marca"+(int)rand,"Modelo"+(int)rand,(int)rand);
		return nuevo;

	}

}
