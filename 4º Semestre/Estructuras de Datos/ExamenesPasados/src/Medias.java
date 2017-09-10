import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Medias<E> {
	
	public static String[] hazUnaMedia(String[] notas){
		Map<String, Double> valores = new HashMap<>();
		Map<String,Integer> veces = new HashMap<>();
		for(int i=0;i<notas.length;i+=2){
			if(valores.containsKey(notas[i])){
				Double valor = valores.get(notas[i]);
				valor += Double.parseDouble(notas[i+1]);
				valores.put(notas[i], valor);
				Integer value = veces.get(notas[i]);
				value++;
				veces.put(notas[i], value);
			}
			else{
				valores.put(notas[i], Double.parseDouble(notas[i+1]));
				veces.put(notas[i], 1);
			}
		}
		String[] ret = new String[valores.size()*2];
		Iterator<String> it = valores.keySet().iterator();
		int j=0;
		while(it.hasNext()){
			String next = it.next();
			ret[j++]=next;
			ret[j++]=""+(valores.get(next)/veces.get(next));
		}
		return ret;
	}
	
	Nodo inicio;
	
	private class Nodo{
		E dato;
		Nodo siguiente;
	}
	
	public void quitaDuplicados(){
		Nodo actual=inicio;
		Nodo anterior=null;
		Nodo siguiente=inicio.siguiente;
		while(siguiente!=null){
			Nodo otroSiguiente=siguiente;
			while(otroSiguiente!=null){
				if(actual.dato==otroSiguiente.dato){
					if(anterior==null){
						actual.siguiente=otroSiguiente.siguiente;
					}else{
						anterior.siguiente=otroSiguiente.siguiente;
					}
				}//End if
				anterior=otroSiguiente;
				otroSiguiente=otroSiguiente.siguiente;
			}//End while
			siguiente=siguiente.siguiente;
		}
	}

}
