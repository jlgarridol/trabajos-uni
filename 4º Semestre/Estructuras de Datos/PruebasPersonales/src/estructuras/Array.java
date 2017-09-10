package estructuras;

public class Array<E> {

	public long size;
	private ArrayElement<E> elementoInicial;
	
	public Array(long size){
		this.size = size;
		elementoInicial = new ArrayElement<>(null,size);
	}
	
	public void poner(E elemento,int posicion){
		if(posicion >= this.size || posicion < 0) throw new IndexOutOfBoundsException("Te has salido con la posicion " + posicion );
		ArrayElement<E> lugar = elementoInicial;
		for(int i = 1; i<=posicion;i++){
			lugar = lugar.siguiente();
		}
		lugar.cambio(elemento);
	}
	
	public E obtener(int posicion){
		if(posicion >= this.size || posicion < 0) throw new IndexOutOfBoundsException("Te has salido con la posicion " + posicion );
		ArrayElement<E> lugar = elementoInicial;
		for(int i = 1; i<=posicion;i++){
			lugar = lugar.siguiente();
		}
		return lugar.elemento;
	}
}
