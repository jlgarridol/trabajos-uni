package es.ubu.lsi.edat.sesion07.g1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TraduccionTest {

	Traduccion traduccion;

	@Before
	public void setUp(){
		traduccion = new Traduccion();
	}
	
	@Test
	public void testCargaIdiomas() {

		String[] idiomaConsulta = {"free", "dog", "cat", "keyboard", "data structures", "A", "hopefully"};
		String[] idiomaRespuesta = {"libre", "perro", "gato", "teclado", "estructuras de datos", "sobresaliente", "con optimismo"};
		
		assertEquals(7, traduccion.cargaDiccionario(idiomaConsulta, idiomaRespuesta));
		
	}

	@Test
	public void testBuscaTraduccionDirecta() {
		
		testCargaIdiomas();

		assertEquals("perro", traduccion.buscaTraduccion("dog"));
		assertEquals("gato", traduccion.buscaTraduccion("cat"));
		assertEquals("estructuras de datos", traduccion.buscaTraduccion("data structures"));
		
	}

	@Test
	public void testBuscaTraduccionInversa() {

		testCargaIdiomas();
		
		assertEquals("dog", traduccion.buscaTraduccion("perro"));
		assertEquals("cat", traduccion.buscaTraduccion("gato"));
		assertEquals("data structures", traduccion.buscaTraduccion("estructuras de datos"));
		
	}

	
}
