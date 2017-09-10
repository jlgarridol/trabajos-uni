package es.ubu.lsi.edat.datos.accesosWeb8;

import java.time.LocalDateTime;
import java.util.*;

import es.ubu.lsi.edat.datos.GeneradorFechas_8;
import es.ubu.lsi.edat.datos.accesosWeb8.AccesoWeb;
import es.ubu.lsi.edat.datos.accesosWeb8.AccesoWeb.SeccionWeb;

public class GeneradorAccesos implements Iterable<AccesoWeb>{

	static String[] secciones = {"Productos", "Contacto", "Productos", 
			"Organizacion", "Blog","Organizacion", 
			"Inicio", "Organizacion", "Prensa", 
			"Prensa", "Prensa", "Blog"};

	static String[] ips = {"53.184.200.233", "130.44.242.174", "56.44.225.243", 
			"121.15.13.62", "180.12.191.35", "121.15.13.62",
			"230.151.141.104", "214.236.244.28", "182.146.186.42", 
			"182.146.186.42", "180.12.191.35", "121.15.13.62"};
	
	
	static String[] fechas = {
			"2017-04-19T21:40:26.022","2017-04-19T14:01:26.022","2017-04-19T09:52:26.022",
			"2017-04-19T21:02:53.022","2017-04-20T20:53:56.022","2017-04-20T19:43:56.022",
			"2017-04-21T05:36:05.022","2017-04-20T13:59:05.022","2017-04-20T02:30:05.022",
			"2017-04-20T20:49:33.022","2017-04-20T23:16:40.022","2017-04-21T20:53:48.022" 
	};
	
	//static List<LocalDateTime> fechas = GeneradorFechas_8.getFechasAleatorias(12);
	
	/**
	 * Iterador que genera siempre un AccesoWeb nuevo.
	 * Los 12 primeros son siempre iguales: obtenidos de la lista.
	 * El resto seran aleatorios. 
	 * 
	 * @author bbaruque
	 *
	 */
	private class Iterador implements Iterator<AccesoWeb>{

		int indice = -1;
		
		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public AccesoWeb next() {
			
			indice++;
			
			if(indice < secciones.length){
				
				return new AccesoWeb(AccesoWeb.SeccionWeb.valueOf(secciones[indice]), 
						LocalDateTime.parse(fechas[indice]), 
						ips[indice]);
			}else{
				return accesoAleatorio();
			}
		
		}
	
	}

	static int bufferSize = 10;
	static ArrayList<String> IPbuffer = new ArrayList<String>(bufferSize);
	
	/**
	 * 
	 * @return
	 */
	private static String ipAleatoria(){

		int sel = (int) (Math.random() * bufferSize);
		
		if(IPbuffer.size()>= bufferSize){
		
			// El 50% de las veces, devuelve una ya empleada antes
			if(Math.random() > 0.5){
				return IPbuffer.get(sel);
			}
			
		}
		
		// El otro % de las veces, genera una ip nueva
		String newIP = "";

		for(int i=0; i<3; i++){
			int aleat = (int) (Math.random()*255);
			newIP = newIP + String.valueOf(aleat) + ".";
		}

		int aleat = (int) (Math.random()*255);
		newIP = newIP + String.valueOf(aleat);

		// La nueva sustituye a una de las anteriores
		if(IPbuffer.size()>= bufferSize)
			IPbuffer.set(sel, newIP);
		else
			IPbuffer.add(newIP);
		
		return newIP;

	}

	/**
	 * 
	 * @return
	 */
	private static SeccionWeb seccionAleatoria(){

		SeccionWeb[] posibilidades = SeccionWeb.values();

		int aleat = (int) (Math.random() * posibilidades.length);
		return posibilidades[aleat];

	}

	@Override
	public Iterator<AccesoWeb> iterator() {
		return new Iterador();
	}
	
	/**
	 * 
	 * @return
	 */
	public static AccesoWeb accesoAleatorio(){

		return new AccesoWeb(seccionAleatoria(),
				GeneradorFechas_8.getFechaAleatoria(),
				ipAleatoria());

	}

	public static List<AccesoWeb> accesosAleatorios(int numAccesos){
		
		List<AccesoWeb> lista = new ArrayList<AccesoWeb>(numAccesos);
		
		for(int indice=0; indice<12; indice++)
			lista.add(new AccesoWeb(AccesoWeb.SeccionWeb.valueOf(secciones[indice]), 
					LocalDateTime.parse(fechas[indice]), 
					ips[indice]));
		for(int indice = 12; indice<numAccesos; indice++){
			lista.add(accesoAleatorio());
		}
		
		return lista;
		
	}
	
}
