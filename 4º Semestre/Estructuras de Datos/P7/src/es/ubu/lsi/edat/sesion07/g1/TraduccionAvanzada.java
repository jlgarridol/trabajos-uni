package es.ubu.lsi.edat.sesion07.g1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que permite implementar un diccionario bilingüe para obtener 
 * la traducción de palabras entre dos idiomas empleando  alguna de las 
 * clases que implementan HashMap.
 * 
 * En este caso se implementan funciones más avanzadas sobre la original.
 * 
 * @author bbaruque
 *
 */


public class TraduccionAvanzada {

	/**
	 * Mapa que contendrá las traducciones para realizar luego la consulta.
	 * En este caso el mapa deberá permitir emplear copias de una clave para almacenar 
	 * diferentes valores. Como almacenar la misma clave con dos valores no se permite 
	 * en un mapa, se añadirá una variable numerica a la palabra.  
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
	 * Cada palabra tiene en este caso una o varias traducciones. Esto implica que la misma
	 * palabra puede aparece más de una vez dentro del mismo vector, teniendo como traduccion
	 * palabras diferentes en el otro vector. Se deberá almacenar por lo tanto una palabra
	 * junto con todas las posibles traducciones de la misma para su posterior consulta.
	 * 
	 * Para ello: 1. Se almacenará la palabra original como clave, guardando como valor 
	 * el numero de veces que aparece repetida.
	 * 2. Cada vez que se quiera almacenar esa clave otra vez, se recuperará el numero de 
	 * repeticiones, se incrementará en 1 y se añadira al final de la clave a guardar.
	 * 
	 * Ejemplo: Si se necesita almacenar la palabra "key" dos veces como clave (porque tiene 
	 * dos acepciones: clave y llave), el mapa almacenará {"key"="2", "key_1"="clave", "key_2"="llave"}
	 * 
	 * @param idioma1 Array de cadenas con las palabras en el idioma de consulta
	 * @param idioma2 Array de cadenas correspondientes en el idioma de respuesta.
	 * @return numero de traducciones disponibles (sin contar con las entradas que indican las repeticiones)
	 */
	public int cargaDiccionario (String[] idioma1, String[] idioma2){
		for(int i=0; i < idioma1.length ; i++){
			if(m.containsKey(idioma1[i])){
				m.put(idioma1[i], m.get(idioma2[i]) + 1);
				palabras--;
			}else{
				m.put(idioma1[i], "1");
			}
			String temp = idioma1[i] + "_" + m.get(idioma1[i]);
			m.put(temp, idioma2[i]);
			palabras++;
		}
		return palabras;
	}

	/**
	 * Método que permite obtener la traducción en el idioma de respuesta 
	 * de una palabra facilitada en el idioma de consulta (o idioma nativo).
	 * 
	 * Para ello: 1. Se busca la palabra a traducir y se obtiene el valor. Será el
	 * numero de repeticiones que tiene esa clave en el mapa de traduccion.
	 * 2. Se busca en bucle la palabra cambiando su sufijo para ir recopilando
	 * los diferentes valores a los que se asocia.
	 * 3. Los valores encontrados en 2, se van almacenando en una lista.
	 * 
	 * @param buscada palabra a traducir, escrita en el idioma de consulta
	 * @return traduccion de la palabra en el idioma de respuesta
	 * Se debe tener en cuenta que en este caso una palabra puede tener
	 * más de una traducción (se devuelve una lista)
	 */
	public List<String> buscaTraduccion(String buscada){
		List<String> temp = new ArrayList<String>();
		if(m.get(buscada) != null){
			String t = m.get(buscada);
			while(t.compareTo("1")== 1){
				temp.add(m.get(buscada + "_" + t));
				
			}
		}
		for(Map.Entry<String, String> e: m.entrySet()){
			if(e.getValue().equals(buscada)){
				String t = e.getKey();
				while(t.compareTo("1")== 1){
					temp.add(e.getKey() + "_" + t);
				}
			}
		}
		temp.remove(0);
		return temp;
	}	

	/**
	 * Método que permite obtener los sinónimos a una palabra dada.
	 * En este caso, la palabra se consultará en el idioma de respuesta.
	 * Se devolverán todas aquellas otras acepciones que se han encontrado
	 * almacenadas junto con la palabra de consulta como traducciones a 
	 * otras palabras en el idioma de consulta original (nativo) 
	 * 
	 * @param buscada palabra de la que se quieren obtener sinónimos (idioma de respuesta)
	 * @return sinónimos a la palabra introducida (puede haber más de uno)
	 */
	//	public List<String> buscaSinonimos(String buscada){
	//		
	// TODO - A completar por el alumno
	//		
	//	}

	/**
	 * Método que permite eliminar por completo todas las traducciones almacenadas.
	 */
	public void clear (){
		m = new HashMap<String,String>();
		palabras = 0;
	}

}
