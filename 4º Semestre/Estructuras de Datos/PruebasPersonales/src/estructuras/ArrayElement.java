package estructuras;

public class ArrayElement<E> {

	E elemento;
	ArrayElement<E> siguiente;
	
	ArrayElement(E elemento, long cantidad){
		if(cantidad>0){
			this.elemento = elemento;
			this.siguiente = new ArrayElement<E>(null,--cantidad);
		}
	}
	
	void cambio(E elemento){
		this.elemento = elemento;
	}
	
	ArrayElement<E> siguiente(){
		return this.siguiente;
	}
	
}
