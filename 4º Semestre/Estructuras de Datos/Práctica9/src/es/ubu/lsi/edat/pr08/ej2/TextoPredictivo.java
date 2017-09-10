package es.ubu.lsi.edat.pr08.ej2;

import java.util.*;

/**
 * Clase que permite almacenar un tesauro de palabras en un deteminado idioma y 
 * realizar sugerencias en torno a palabras incompletas o incorrectas a modo de corrector
 * ortográfico automático.
 * 
 * @author bbaruque
 *
 */
public class TextoPredictivo {

	/**
	 * Se necesitará un atributo que permita 
	 * Se empleará una clase que implemente el interfaz Set 
	 */
	private NavigableSet<String> set = new TreeSet<>();
	private int palabras;

	/**
	 * Constructor de la clase. Se inicializa con el conjunto de palabras incluidas
	 * en el diccionario (tesauro) que se podrán emplear como sugerencias.
	 * 
	 * @param tesauro array de palabras que se van a emplear para corregir
	 */
	public TextoPredictivo (String[] tesauro){
		set.addAll(Arrays.asList(tesauro));
		palabras = set.size();
	}

	/**
	 * Método que permite facilitar un listado de sugerencias dada una cadena de consulta.
	 * Se especifica como parámetro cuantas sugerencias se necesitan obtener.
	 * 
	 * Suponiendo que se ha establecido un orden alfabético en el tesauro, se devolverán como sugerencias
	 * en el array en el siguiente orden:
	 *
	 * 1. Al inicio del vector las (n/2) palabras anteriores a la cadena buscada
	 * Estas aparecerán en el orden ALFABÉTICO
	 * 2. A partir de la posición (n/2)  las palabras posteriores a la de busqueda.
	 * Estas aparecerán en el orden ALFABÉTICO
	 * 
	 * En caso de que se pida un numero IMPAR de palabras, se devolverá 1 menos del grupo de las ANTERIORES
	 * Si no existen suficeintes palabras para completar una de las dos mitades, se rellenará con las
	 * palabras que se pueda.
	 *  
	 * Por último en caso de que se soliciten sugerencias para una palabra que ya se encuentra en el 
	 * tesauro, solo se devolverá la propia palabra en el array de búsqueda.
	 * 
	 * @param incompleta cadena de texto sobre la que se quieren obtener sugerencias
	 * @param numSugerencias cantidad de palabras total que se quiere incluir en las sugerencias
	 * @return array de palabras sugeridas 
	 */
	public String[] sugiere (String incompleta, int numSugerencias){
		if(set.contains(incompleta)){
			String[] a = new String[1];
			a[0] = incompleta;
			return a;
		}
		String[] aDevolver = new String[numSugerencias];
		TreeSet<String> parte = new TreeSet<>();
		NavigableSet<String> auxiliar = set.headSet(incompleta, false);
		Iterator<String> it = auxiliar.descendingIterator();
		int salida = numSugerencias/2;
		if(auxiliar.size()<numSugerencias/2)
			salida = auxiliar.size();
		for(int i = 0; i<salida && it.hasNext();i++){
			parte.add(it.next());
		}
		it = parte.iterator();
		for(int i=0; i<numSugerencias/2 && it.hasNext();i++){
			aDevolver[i]=it.next();
		}
		auxiliar = set.tailSet(incompleta, false);
		it = auxiliar.iterator();
		for(int i = salida; i<numSugerencias && it.hasNext();i++){
			aDevolver[i]=it.next();
		}
		
		return aDevolver;
	}

	/**
	 * Metodo que permite conocer el tamaño del tesauro almacenado
	 * 
	 * @return numero de palabras DIFERENTES almacenadas en el tesauro
	 */
	public int tamañoDiccionario(){
		
		return palabras;
		
	}
	
//	public String corrige(String valor){
//		return set.floor(valor);		
//	}

}
