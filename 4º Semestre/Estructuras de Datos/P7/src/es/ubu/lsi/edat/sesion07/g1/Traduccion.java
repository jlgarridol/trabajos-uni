package es.ubu.lsi.edat.sesion07.g1;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que permite implementar un diccionario bilingüe para obtener 
 * la traducción de palabras entre dos idiomas empleando  alguna de las 
 * clases que implementan HashMap.
 * 
 * @author bbaruque
 *
 */
public class Traduccion {

	/**
	 * Mapa que contendrá las traducciones para realizar luego la consulta.
	 */
	private Map<String, String> m = new HashMap<String,String>();
	private int palabras;
	// TODO - A completar por el alumno

	/**
	 * Método que permite almacenar las diferentes traducciones dentro del Mapa
	 * creado para ello. Se pasan dos arrays de cadenas (del mismo tamaño), cada
	 * uno contiene en la misma posición la traducción de una palabra en su correspondiente
	 * idioma. Se considera el idioma que se pasa como primer parámetro el idioma
	 * que se va a emplear para consultar (esto es el idioma nativo del usuario)
	 * y el segundo el que se obtendrá como respuesta.  
	 * 
	 * Cada palabra tiene en este caso una ÚNICA traducción.
	 * 
	 * @param idioma1 Array de cadenas con las palabras en el idioma de consulta
	 * @param idioma2 Array de cadenas correspondientes en el idioma de respuesta.
	 * @return numero de traducciones disponibles
	 */
	public int cargaDiccionario (String[] idioma1, String[] idioma2){
		for(int i=0; i < idioma1.length ; i++){
			m.put(idioma1[i],idioma2[i]);
			palabras++;
		}
		return palabras;
	}

	/**
	 * Método que permite obtener la traducción en el idioma de respuesta 
	 * de una palabra facilitada en el idioma de consulta (o idioma nativo).
	 * 
	 * @param buscada palabra a traducir, escrita en el idioma de consulta
	 * @return traduccion de la palabra en el idioma de respuesta
	 */
	public String buscaTraduccion(String buscada){
		if(m.get(buscada) != null){
			return m.get(buscada);
		}
		for(Map.Entry<String, String> e: m.entrySet()){
			if(e.getValue().equals(buscada)){
				return e.getKey();
			}
		}
		return null;
	}	

	/**
	 * Método que permite eliminar por completo todas las traducciones almacenadas.
	 */
	public void clear (){
		m = new HashMap<String,String>();
		palabras = 0;
	}

}
