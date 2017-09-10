package es.ubu.lsi.edat.pr08.ej2;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.ubu.lsi.edat.pr08.ej2.TextoPredictivo;

public class TextoPredictivoTest {

	TextoPredictivo predictivo;
	
	String [] diccionario = {"a", "ante", "bajo", "cabe", "con", "contra", "de", "desde",
			"durante", "en", "entre", "hacia", "hasta", "mediante", "para", "por", "segun",
			"sin", "so", "sobre", "tras", "versus", "via"};
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCargaTextoPredictivo() {
		predictivo = new TextoPredictivo(diccionario);
		assertEquals(23,predictivo.tamañoDiccionario());
	}

	@Test
	public void testSugiereTodas() {
		
		String [] sugerencias;
		
		 testCargaTextoPredictivo();
		
		sugerencias = predictivo.sugiere("ca", 6);
		assertEquals("a",sugerencias[0]);
		assertEquals("ante",sugerencias[1]);
		assertEquals("bajo",sugerencias[2]);
		assertEquals("cabe",sugerencias[3]);
		assertEquals("con",sugerencias[4]);
		assertEquals("contra",sugerencias[5]);
		
		sugerencias = predictivo.sugiere("tr", 3);
		assertEquals("sobre",sugerencias[0]);
		assertEquals("tras",sugerencias[1]);
		assertEquals("versus",sugerencias[2]);

	}

	@Test
	public void testSugiereIncompletas() {
		
		String [] sugerencias;
		
		testCargaTextoPredictivo();
		
		// Probamos con un elemento que no tiene anteriores
		sugerencias = predictivo.sugiere("1", 3); 
		assertEquals("a",sugerencias[0]); // Solo debe devolver las posteriores
		assertEquals("ante",sugerencias[1]);
		
		// Probamos con un elemento que no tiene posteriores
		sugerencias = predictivo.sugiere("vu", 5);
		assertEquals("versus",sugerencias[0]); 
		assertEquals("via",sugerencias[1]);

	}

	@Test
	public void testSugiereExacta(){
	String [] sugerencias;
		
		testCargaTextoPredictivo();
		
		// Probamos con una coincidencia perfecta
		sugerencias = predictivo.sugiere("sobre", 3);
		assertEquals(1,sugerencias.length);
		assertEquals("sobre",sugerencias[0]);
		
	}
	
//	// TODO - Pensar cómo se podría hacer para devolver
//	// la mejor corrección posible a una palabra mal escrita.
//	
//	@Test
//	public void testCorrige() {
//		
//		String corregida;
//		
//		corregida = predictivo.corrige("baje");
//		assertEquals(corregida,"bajo");
//		
//		corregida = predictivo.corrige("cave");
//		assertEquals(corregida,"cabe");
//		
//	}

}
