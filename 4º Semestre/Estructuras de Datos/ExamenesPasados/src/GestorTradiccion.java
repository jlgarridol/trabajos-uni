import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GestorTradiccion {

	Map<String,String> simple;
	Map<String,List<String>> ambigua;
	
	public GestorTradiccion(){
		simple = new HashMap<>();
		ambigua = new HashMap<>();
	}
	
	public int cargaDiccionario(String [] idioma1, String [] idioma2){
		
		for(int i =0 ; i<idioma1.length; i++){
			String uno = idioma1[i];
			String dos = idioma2[i];
			if(ambigua.containsKey(uno)){
				ambigua.get(uno).add(dos);
			}else if(simple.containsKey(uno)){
				List<String> ambiguas = new LinkedList<>();
				ambiguas.add(simple.remove(uno));
				ambigua.put(uno, ambiguas);
			}else{
				simple.put(uno, dos);
			}
		}
		
		return simple.size()+ambigua.size();
	}
	
	public String buscaTraduccon(String buscada){
		return this.simple.get(buscada);
	}
	
	public List<String> buscaAmbiguas(String buscada){
		return this.ambigua.get(buscada);
	}
	
}
