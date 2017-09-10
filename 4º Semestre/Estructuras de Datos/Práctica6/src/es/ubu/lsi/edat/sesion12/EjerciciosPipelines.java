package es.ubu.lsi.edat.sesion12;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import es.ubu.lsi.edat.datos.POI.PointOfInterest;
import es.ubu.lsi.edat.datos.POI.PointOfInterest.AmenityType;

/**
 * Contiene ejercicios a resolver en la sesión 06 de prácticas.
 * Asignatura: Estructuras de Datos
 * Curso: 16-17 
 * 
 * @author bbaruque
 */

public class EjerciciosPipelines {

	/**
	 * El metodo recibe una colección que contiene cadenas de caracteres sin comprobar.
	 * Debe devolver otra colección igual, pero cuyo contenido sean las mismas cadenas
	 * pero con todo el texto en mayúsculas.
	 *
	 * Este metodo se resolverá con un iterador.
	 * 
	 * @param collection la coleccion que contiene las cadenas
	 * @return el contenido de la colección transformado a mayúsculas
	 */
	public static List<String> toUpper_Iter(List<String> collection) {
		List<String> list = new ArrayList<>(collection.size());
		Iterator<String> it = collection.iterator();
		while(it.hasNext()){
			list.add(it.next().toUpperCase());
		}
		return list;
		
	}

	/**
	 * El metodo recibe una colección que contiene cadenas de caracteres sin comprobar.
	 * Debe devolver otra colección igual, pero cuyo contenido sean las mismas cadenas
	 * pero con todo el texto en mayúsculas.
	 *
	 * Este metodo se resolverá empleando un stream
	 * 
	 * @param collection la coleccion que contiene las cadenas
	 * @return el contenido de la colección transformado a mayúsculas 
	 */
	public static List<String> toUpper_Stream(List<String> collection) {

		return collection.stream().map(p -> p.toUpperCase()).collect(Collectors.toList());
		
	}

	// TODO - Pensar cómo se puede hacer en ambos casos para devolverlo en
	// el orden INVERSO a como aparecen en la colección que se nos pasa.

	/**
	 * El metodo recibe una colección que contiene cadenas de caracteres sin comprobar.
	 * Debe devolver otra colección igual, pero cuyo contenido sea SOLO aquellas cadenas
	 * que tengan una longitud MENOR a la que se pasa por parámetro.
	 *
	 * Este metodo se resolverá con un iterador.
	 * 
	 * @param collection la coleccion que contiene las cadenas
	 * @param longitud maxima de las cadenas contenidas en la coleccion
	 * @return el contenido de la colección incluyendo solo las cadenas con longitud menor 
	 */
    public static List<String> minimumLenght_Iter(List<String> collection, int longitud) {

		List<String> list = new ArrayList<>(collection.size());
		Iterator<String> it = collection.iterator();
		while(it.hasNext()){
			String next = it.next();
			if(next.length()<longitud)
				list.add(next);
		}
		return list;
    	
    }

	/**
	 * El metodo recibe una colección que contiene cadenas de caracteres sin comprobar.
	 * Debe devolver otra colección igual, pero cuyo contenido sea SOLO aquellas cadenas
	 * que tengan una longitud MENOR a la que se pasa por parámetro.
	 *
	 * Este metodo se resolverá empleando streams.
	 * 
	 * @param collection la coleccion que contiene las cadenas
	 * @param longitud maxima de las cadenas contenidas en la coleccion
	 * @return el contenido de la colección incluyendo solo las cadenas con longitud menor 
	 */
    public static List<String> minimumLenght_Stream(List<String> collection, int longitud) {

		return collection.stream().filter(p -> p.length()<longitud)//Filtramos por aquello que tengan una longitud menor que la pasada
				.collect(Collectors.toList());//Recogemos la lista con el resultado
		
    }

	
	/**
	 * Permite calcular la media de la nota de los puntos de tipo "PARK".
	 * 
	 * Este metodo se resolverá con un iterador.
	 * 
	 * @param places Coleccion que contiene todos los puntos de interes.
	 * @return media de la puntuacion de los puntos de tipo PARK 
	 */
	public static double getParksAvg_Iter(List<PointOfInterest> places) {
		
		double suma = 0;
		double elementos = 0;
		Iterator<PointOfInterest> it = places.iterator();
		while(it.hasNext()){
			PointOfInterest next = it.next();
			if(next.getType() == AmenityType.PARK){
				elementos++;
				suma += next.getGrade();
			}
		}
		return suma/elementos;

	}
	

	/**
	 * Permite calcular la media de la nota de los puntos de tipo "PARK".
	 * 
	 * Este metodo se resolverá empleando un stream.
	 * 
	 * @param places Coleccion que contiene todos los puntos de interes.
	 * @return media de la puntuacion de los puntos de tipo PARK 
	 */
	public static double getParksAvg_Stream(List<PointOfInterest> places) {
		return places.stream().filter(p -> p.getType()==AmenityType.PARK)//Nos quedamos con los tipos Park
				.mapToDouble(p -> p.getGrade())// Seleccionamos las notas como Doubles
				.average() //Hacemos la media
				.getAsDouble();	//Obtenemos la media.	
	}
	
	/**
	 * El método permite obtener el punto con la mayor puntuación de la colección.
	 *  
	 * Se debe calcular empleando un iterador sobre la coleccion.
	 * 
	 * @param places Colección que contiene todos los POIs del sistema
	 * @return POI que tiene la puntuación más alta en toda la colección
	 */

	public static PointOfInterest getBestGraded_Iter(List<PointOfInterest> places) {

		PointOfInterest max=null;
		float grade = 0;
		Iterator<PointOfInterest> it = places.iterator();
		while(it.hasNext()){
			PointOfInterest next = it.next();
			if(next.getGrade()>grade){
				grade=next.getGrade();
				max=next;
			}
		}
		return max;	
		
	}

	/**
	 * El método permite obtener el punto con la mayor puntuación de la colección.
	 *  
	 * Se debe calcular empleando un stream sobre la coleccion.
	 * 
	 * @param places Colección que contiene todos los POIs del sistema
	 * @return POI que tiene la puntuación más alta en toda la colección
	 */

	public static PointOfInterest getBestGraded_Stream(List<PointOfInterest> places) {

		/**
		 * Creamos un comparador para hacer la comparación porque no viene implementada.
		 */
		Comparator<PointOfInterest> com = new Comparator<PointOfInterest>(){
			@Override
			public int compare(PointOfInterest p1, PointOfInterest p2){
				if(p1.getGrade()>p2.getGrade()){
					return 1;
				}else if(p1.getGrade()<p2.getGrade()){
					return -1;
				}
				return 0;
			}
		};
		
		return places.stream().max(com)//Obtenemos el máximo a partir del comparador
				.get(); //Recogemos el valor
		
	}

	/**
	 * Metodo que permite obtener todos los POI que se encuentre en el cuadrante
	 * inferior derecho, tomando el punto que se facilita como parámetro como la
	 * esquina superior izquierda del cuadrante.
	 * 
	 * Se devolverán todos los puntos cuya latitud sea mayor a la consultada y 
	 * a la vez su longitud sea menor. Además, la lista estará ordenada de forma
	 * alfabética por el nombre del punto.
	 * 
	 * Este método se debe completar utilizando un iterador.
	 * 
	 * @param places colección que contiene los puntos a explorar 
	 * @param lat latitud del punto que sirve como referencia (esquina superior izquierda)
	 * @param lon longitud del punto que sirve como referencia (esquina superior izquierda)
	 * @return lista con los puntos que están contenidos en ese cuadrante, ordenados por su nombre
	 */
	public static List<String> getPointsCuadrant_Iter(List<PointOfInterest> places, float lat, float lon){
		
		Iterator<PointOfInterest> it = places.iterator();
		List<String> list = new ArrayList(places.size());
		while(it.hasNext()){
			PointOfInterest next = it.next();
			float latitud = next.getPosition()[0];
			float longitud = next.getPosition()[1];
			if(latitud>lat && longitud < lon){
				list.add(next.getName());
			}
			
		}
		Collections.sort(list);
		return list;
		
	}
	
	/**
	 * Metodo que permite obtener todos los POI que se encuentre en el cuadrante
	 * inferior derecho, tomando el punto que se facilita como parámetro como la
	 * esquina superior izquierda del cuadrante.
	 * 
	 * Se devolverán todos los puntos cuya latitud sea mayor a la consultada y 
	 * a la vez su longitud sea menor. Además, la lista estará ordenada de forma
	 * alfabética por el nombre del punto.
	 * 
	 * Este método se debe completar utilizando un stream sobre la coleccion.
	 * 
	 * @param places colección que contiene los puntos a explorar 
	 * @param lat latitud del punto que sirve como referencia (esquina superior izquierda)
	 * @param lon longitud del punto que sirve como referencia (esquina superior izquierda)
	 * @return lista con los puntos que están contenidos en ese cuadrante, ordenados por su nombre
	 */
	public static List<String> getPointsCuadrant_Stream(List<PointOfInterest> places, float lat, float lon){
		
		return places.stream().filter(p -> p.getPosition()[0]>lat && p.getPosition()[1]<lon)//Filtramos por geolocalización
				.map(p -> p.getName())//Mapeamos por el nombre
				.sorted()//Los ordenamos
				.collect(Collectors.toList());//Los recogemos
		
	}

    
}
