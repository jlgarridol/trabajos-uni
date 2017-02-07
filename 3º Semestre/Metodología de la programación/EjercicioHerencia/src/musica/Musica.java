package musica;

public class Musica {
	public static void afinar(Instrumento i) {
		// ...
		i.tocar(Nota.DO);
	}

	public static void main(String[] args) {
		Viento flauta = new Viento();
		Metal metalofono = new Metal();
		Cuerda guitarra = new Cuerda();
		afinar(flauta); // Upcasting
		afinar(metalofono);
		afinar(guitarra);
	}
}