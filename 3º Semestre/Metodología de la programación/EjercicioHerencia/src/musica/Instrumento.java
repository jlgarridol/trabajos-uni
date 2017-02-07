package musica;

public class Instrumento implements Musical {
	private float precio;

	public float consultarPrecio() {
		return precio;
	}

	public void tocar(Nota n){
		System.out.println("Instrumento.tocar()");
	}
	
	
}
