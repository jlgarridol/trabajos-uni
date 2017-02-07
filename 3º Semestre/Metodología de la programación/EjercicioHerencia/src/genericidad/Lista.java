package genericidad;

import java.util.ArrayList;

public class Lista<E> {

		private ArrayList<E> datos;
		
		public Lista(int tamaño){
			datos = new ArrayList<E>();
			for(int i=0;i<tamaño;i++){
				datos.add(null);
			}
		}
		
		public void set(int index, E elemento){
			datos.set(index, elemento);
		}
		
		public E get(int index){
			return datos.get(index);
		}
		
		public int size(){
			return datos.size();
		}
}
