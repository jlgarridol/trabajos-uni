package es.ubu.lsi.edat.sesion07.g1;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TraduccionAvanzadaTest {

	TraduccionAvanzada traduccionAvanzada;

	@Before
	public void setUp(){
		traduccionAvanzada = new TraduccionAvanzada();
	}

	
	@After
	public void teardown(){
		traduccionAvanzada.clear();
	}
	
	@Test
	public void testCargaIdiomas() {

		// Idioma de consulta
		String[] idiomaConsulta = {"free", "free", 
				"dog", "cat", "keyboard", 
				"cool", "cool" , "cool", 
				"available", "available"};
		
		// Idioma de respuesta
		String[] idiomaRespuesta = {"libre", "gratis", 
				"perro", "gato", "teclado", 
				"fresco", "indiferente", "chévere", 
				"libre", "disponible" };
		
		assertEquals(6, traduccionAvanzada.cargaDiccionario(idiomaConsulta, idiomaRespuesta));
		
	}

	@Test
	public void testBuscaTraduccion() {
		
		testCargaIdiomas();

		assertEquals( Arrays.asList("perro"), traduccionAvanzada.buscaTraduccion("dog"));
		assertTrue( traduccionAvanzada.buscaTraduccion("free").containsAll( Arrays.asList("libre", "gratis")) );
		assertTrue( traduccionAvanzada.buscaTraduccion("cool").containsAll( Arrays.asList("fresco", "indiferente", "chévere")) );
		
	}

	// TODO - Si te sobra tiempo en esta sesión, puedes intentar completar el último
	// ejercicio, cuyo test está debajo
	
//	@Test
//	public void testBuscaSinonimos() {
//
//		testCargaIdiomas();
//
//		List<String> sinonims = traduccionAvanzada.buscaSinonimos("libre");
//		List<String> expected = Arrays.asList("gratis", "disponible");
//		
//		assertTrue( sinonims.containsAll(expected)  );
//		assertEquals( expected.size(), sinonims.size() );
//		
//	}

}
