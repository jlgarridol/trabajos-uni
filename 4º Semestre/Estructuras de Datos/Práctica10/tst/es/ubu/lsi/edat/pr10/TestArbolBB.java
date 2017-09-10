package es.ubu.lsi.edat.pr10;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestArbolBB {

	ArbolBB<Integer> arbol;
	List<Integer> datos;

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
		arbol.clear();
	}

	@Test
	public void testAdd_NoRepetidos() {

		datos = Arrays.asList(375,450,400,350);

		arbol = new ArbolBB<Integer>(datos);
		assertEquals(4, arbol.size());

		datos = Arrays.asList(375,450,400,350,300,90,125);

		arbol = new ArbolBB<Integer>(datos);
		assertEquals(7, arbol.size());

	}

	@Test
	public void testAdd_Repetidos() {

		datos = Arrays.asList(375,450,400,350);

		arbol = new ArbolBB<Integer>(datos);
		assertEquals(4, arbol.size());

		datos = Arrays.asList(375,450,400,350,375,450,400,350);
		arbol = new ArbolBB<Integer>(datos);

		assertEquals(4, arbol.size());

	}

	@Test
	public void testIterator() {

		testAdd_NoRepetidos();

		datos = Arrays.asList(375,450,400,350,300,90,125);
		Collections.sort(datos);

		Iterator<Integer> it = arbol.iterator();

		int i = 0;
		while(it.hasNext()){
			assertEquals(it.next(), datos.get(i));
			i++;
		}

		assertEquals(datos.size(), i);

	}

	@Test
	public void testBorradoHoja() {

		testAdd_NoRepetidos();

		datos = new ArrayList<Integer>(Arrays.asList(375,450,400,350,300,90,125,360,500));
		arbol.add(360);
		arbol.add(500);

		assertTrue(arbol.containsAll(datos));

		arbol.remove(125);
		datos.remove(new Integer(125));
		assertEquals(8,arbol.size());
		assertTrue(arbol.containsAll(datos));

	}

	@Test
	public void testBorradoSolo1Hijo() {

		testAdd_NoRepetidos();

		datos = new ArrayList<Integer>(Arrays.asList(375,450,400,350,300,90,125,360,500));
		arbol.add(360);
		arbol.add(500);

		assertTrue(arbol.containsAll(datos));

		arbol.remove(90);	
		datos.remove(new Integer(90));
		assertEquals(8,arbol.size());
		assertTrue(arbol.containsAll(datos));

	}


	@Test
	public void testBorrado2Hijos() {

		testAdd_NoRepetidos();
		arbol.add(360);
		arbol.add(500);

		datos = new ArrayList<Integer>(Arrays.asList(375,450,400,350,300,90,125,360,500));

		assertTrue(arbol.containsAll(datos));

		arbol.remove(350);	
		datos.remove(new Integer(350));
		
		assertEquals(8,arbol.size());
		assertTrue(arbol.containsAll(datos));

		arbol.remove(450);	
		datos.remove(new Integer(450));
		
		assertEquals(7,arbol.size());
		assertTrue(arbol.containsAll(datos));

		
	}

}
