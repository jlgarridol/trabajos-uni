package es.ubu.lsi.edat.pr08.ej1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.ubu.lsi.edat.datos.accesosWeb8.AccesoWeb;
import es.ubu.lsi.edat.pr08.ej1.FiltradoAccesosUnicos;
import es.ubu.lsi.edat.datos.accesosWeb8.GeneradorAccesos;

public class FiltradoAccesosUnicosTest {

	List<List<AccesoWeb>> logs;
	List<AccesoWeb> todos;
	 
	
	@Before
	public void setUp() throws Exception {
		
		logs = new ArrayList<List<AccesoWeb>>(5);
		todos = new ArrayList<AccesoWeb>();
		
		List<AccesoWeb> aW = new ArrayList<AccesoWeb>();
		aW.addAll(GeneradorAccesos.accesosAleatorios(10));
		logs.add(aW);
		for (AccesoWeb acc : aW){
			if (!todos.contains(acc))
				todos.add(acc);
		}
		
		aW = new ArrayList<AccesoWeb>();
		aW.addAll(GeneradorAccesos.accesosAleatorios(15));
		logs.add(aW);
		for (AccesoWeb acc : aW){
			if (!todos.contains(acc))
				todos.add(acc);
		}

		
		aW = new ArrayList<AccesoWeb>();
		aW.addAll(GeneradorAccesos.accesosAleatorios(17));
		logs.add(aW);
		for (AccesoWeb acc : aW){
			if (!todos.contains(acc))
				todos.add(acc);
		}

		
		aW = new ArrayList<AccesoWeb>();
		aW.addAll(GeneradorAccesos.accesosAleatorios(5));
		logs.add(aW);
		for (AccesoWeb acc : aW){
			if (!todos.contains(acc))
				todos.add(acc);
		}

		
		aW = new ArrayList<AccesoWeb>();
		aW.addAll(GeneradorAccesos.accesosAleatorios(20));
		logs.add(aW);
		for (AccesoWeb acc : aW){
			if (!todos.contains(acc))
				todos.add(acc);
		}

		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAccesosUnicosDesordenados() {

		System.out.println(logs);
		
		HashSet<AccesoWeb> result = FiltradoAccesosUnicos.accesosUnicosDesordenados (logs);
		
		System.out.println(result);
		
		assertEquals(28,result.size());
		assertTrue(result.containsAll(todos));
		
	}

	@Test
	public void testAccesosUnicosOrdenados() {
		System.out.println(logs);
		
		TreeSet<AccesoWeb> result = FiltradoAccesosUnicos.accesosUnicosOrdenados(logs);
		
		System.out.println(result);
		
		assertEquals(28,result.size());
		assertTrue(result.containsAll(todos));

	}

}
