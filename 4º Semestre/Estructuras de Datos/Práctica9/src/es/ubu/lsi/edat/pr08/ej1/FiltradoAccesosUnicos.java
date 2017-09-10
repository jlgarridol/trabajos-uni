package es.ubu.lsi.edat.pr08.ej1;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import es.ubu.lsi.edat.datos.accesosWeb8.AccesoWeb;

/**
 * Clase que permite comprobar el funcionamiento de los diferentes tipos de Set a la hora de
 * establecer la pertenencia de un dato concreto al conjunto. Este funcionamiento depende
 * principalmente de cómo se hayan definido los datos. 
 * 
 * @author bbaruque
 *
 */

public class FiltradoAccesosUnicos {

	/**
	 * Metodo que permitirá filtrar los datos de tipo AccesoWeb obtenidos de varias fuentes
	 * (almacenado cada uno como una lista de accesos). Como resultado del método, se obtendrá
	 * un conjunto de accesos UNICOS. Se deberá emplear como estructura de apoyo una HashSet.
	 * 
	 * @param logs Lista de listas de AccesoWeb
	 * @return 
	 */
	
	public static HashSet<AccesoWeb> accesosUnicosDesordenados (Collection<? extends Collection<AccesoWeb>> logs){
		HashSet<AccesoWeb> aDevolver = new HashSet<>();
		for(Collection<AccesoWeb> col : logs){
			aDevolver.addAll(col);
		}
		return aDevolver;
	}

	
	public static TreeSet<AccesoWeb> accesosUnicosOrdenados (Collection<? extends Collection<AccesoWeb>> logs){
		TreeSet<AccesoWeb> aDevolver = new TreeSet<>();
		for(Collection<AccesoWeb> col : logs){
			aDevolver.addAll(col);
		}
		return aDevolver;		
		
	}

	/* TODO - Contestar a las siguientes cuestiones:
	 * 
	 * ¿Qué ventajas observas en el empleo de cada tipo de Set?
	 * Utlizando TreeSet tendríamos todos los datos ordenados y nos permitiría volcar los datos ordenadamente. Por otro lado el HashSet es mucho más rápido
	 * ¿Cual será la complejidad algorítmica con la que será capaz de deshechar los duplicados?
	 * de O(n)
	 * ¿Qué características deberán observar los datos según se pretendan emplear en uno u otro tipo de Set?
	 * La ordenación entre estos o el uso de tablas hash
	 */

	
}
