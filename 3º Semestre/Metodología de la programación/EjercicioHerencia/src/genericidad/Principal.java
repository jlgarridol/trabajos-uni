package genericidad;

import musica.*;

public class Principal {

	public static void main(String[] args) {
		Lista<Instrumento> instrumentos = new Lista<Instrumento>(10);
		Lista<Nota> notas = new Lista<Nota>(10);
		cargarInstrumentos(instrumentos);
		cargarNotas(notas);
		mostrar(instrumentos);
		//mostrar(notas);
	}
	
	private static <T extends Instrumento> void mostrar(Lista<T> lista){
		for(int i = 0; i< lista.size();i++){
			lista.get(i).tocar(Nota.SI);
		}
	}
	

	private static void cargarInstrumentos(Lista<Instrumento> lista) {
		for (int i = 0; i < lista.size(); i++) {
			int aleatorio = (int) (Math.random() * 3);
			switch (aleatorio) {
			case 0:
				lista.set(i, new Viento());
				break;
			case 1:
				lista.set(i, new Metal());
				break;
			case 2:
				lista.set(i, new Cuerda());
			}
		}
	}

	private static void cargarNotas(Lista<Nota> lista) {
		for (int i = 0; i < lista.size(); i++) {
			int aleatorio = (int) (Math.random() * Nota.values().length);
			lista.set(i, Nota.values()[aleatorio]);
		}
	}

}
