package es.ubu.lsi.edat.pr10;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestArbolAVL {

	ArbolAVL<Integer> arbol;
	List<Integer> datos;
	
	@Before
	public void setUp() throws Exception {
		arbol = new ArbolAVL<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		arbol.clear();
	}


	@Test
	public void rotacionDerecha(){
		
		datos = Arrays.asList(20,10,40,8);
		arbol.addAll(datos);

		assertEquals(2,arbol.altura(20));
		assertEquals(1,arbol.altura(10));
		assertEquals(0,arbol.altura(40));
		assertEquals(0,arbol.altura(8));
		assertEquals(4, arbol.size());

		arbol.add(5); // Se fuerza el reequilibrado
		
		assertEquals(2,arbol.altura(20));
		assertEquals(1,arbol.altura(8));
		assertEquals(0,arbol.altura(10));
		assertEquals(0,arbol.altura(40));
		assertEquals(0,arbol.altura(5));
		assertEquals(5, arbol.size());
		
	}

	
	@Test
	public void rotacionIzquierdaDerecha (){
		
		datos = Arrays.asList(20,10,40,12);
		arbol.addAll(datos);
		
		assertEquals(2,arbol.altura(20));
		assertEquals(1,arbol.altura(10));
		assertEquals(0,arbol.altura(40));
		assertEquals(0,arbol.altura(12));
		assertEquals(4, arbol.size());
		
		arbol.add(11); // Se fuerza el reequilibrado
		
		assertEquals(2,arbol.altura(20));
		assertEquals(1,arbol.altura(11));
		assertEquals(0,arbol.altura(10));
		assertEquals(0,arbol.altura(40));
		assertEquals(0,arbol.altura(12));
		assertEquals(5, arbol.size());
		
	}

	
	@Test
	public void rotacionIzquierda(){
		
		datos = Arrays.asList(8,20,5,40);
		arbol.addAll(datos);

		assertEquals(2,arbol.altura(8));
		assertEquals(1,arbol.altura(20));
		assertEquals(0,arbol.altura(5));
		assertEquals(0,arbol.altura(40));
		assertEquals(4, arbol.size());

		arbol.add(45); // Se fuerza el reequilibrado
		
		assertEquals(2,arbol.altura(8));
		assertEquals(1,arbol.altura(40));
		assertEquals(0,arbol.altura(45));
		assertEquals(0,arbol.altura(5));
		assertEquals(0,arbol.altura(20));
		assertEquals(5, arbol.size());
		
	}

	
	@Test
	public void rotacionDerechaIzquierda(){
		
		datos = Arrays.asList(8,20,5,40);
		arbol.addAll(datos);

		assertEquals(2,arbol.altura(8));
		assertEquals(1,arbol.altura(20));
		assertEquals(0,arbol.altura(5));
		assertEquals(0,arbol.altura(40));
		assertEquals(4, arbol.size());

		arbol.add(30); // Se fuerza el reequilibrado
		
		assertEquals(2,arbol.altura(8));
		assertEquals(1,arbol.altura(30));
		assertEquals(0,arbol.altura(40));
		assertEquals(0,arbol.altura(5));
		assertEquals(0,arbol.altura(20));
		assertEquals(5, arbol.size());
		
	}

	// TODO - Comprobacion de equilibrio en borrados
	
	@Test
	public void testProfundidad() {
		
		datos = Arrays.asList(8,20,5,40,30);
		arbol.addAll(datos);
		
		assertEquals(0, arbol.profundidad(8));
		assertEquals(1, arbol.profundidad(5));
		assertEquals(1, arbol.profundidad(30));
		assertEquals(2, arbol.profundidad(20));
		assertEquals(2, arbol.profundidad(40));
		
	}

	@Test
	public void testInOrden() {
		
		datos = Arrays.asList(8,20,5,40,30);
		arbol.addAll(datos);
		
		Collections.sort(datos);
		List<Integer> inO = arbol.inOrden(false);
		
		Iterator<Integer> dIt = datos.iterator();
		Iterator<Integer> inIt = inO.iterator();
		
		while(dIt.hasNext()){
			assertEquals(dIt.next(),inIt.next());
		}
		
	}
	
	@Test
	public void testPreOrden() {
		
		datos = Arrays.asList(8,20,5,40,30);
		arbol.addAll(datos);
		
		datos = Arrays.asList(8,5,30,20,40);
		List<Integer> preO = arbol.preOrden();
		
		Iterator<Integer> dIt = datos.iterator();
		Iterator<Integer> preIt = preO.iterator();
		
		while(dIt.hasNext()){
			assertEquals(dIt.next(), preIt.next());
		}
		
	}
	
	@Test
	public void testPosOrden() {
		
		datos = Arrays.asList(8,20,5,40,30);
		arbol.addAll(datos);
		
		datos = Arrays.asList(5,20,40,30,8);
		List<Integer> posO = arbol.posOrden();
		
		Iterator<Integer> dIt = datos.iterator();
		Iterator<Integer> preIt = posO.iterator();
		
		while(dIt.hasNext()){
			assertEquals(dIt.next(), preIt.next());
		}
		
	}
	
	@Test
	public void testRemove() {
		datos = Arrays.asList(25,21,63,2,22,51,94,40,55,87);
		arbol.addAll(datos);
		
		assertEquals(3,arbol.altura(25));
		assertEquals(1,arbol.altura(21));
		assertEquals(0,arbol.altura(22));
		assertEquals(2,arbol.altura(63));
		assertEquals(0,arbol.altura(2));
		assertEquals(1,arbol.altura(51));
		assertEquals(1,arbol.altura(94));
		assertEquals(0,arbol.altura(40));
		assertEquals(0,arbol.altura(55));
		assertEquals(0,arbol.altura(87));
		
		arbol.remove(21);
		
		assertEquals(3,arbol.altura(25));
		assertEquals(-1,arbol.altura(21));
		assertTrue(1==arbol.altura(22) || 0==arbol.altura(22));
		assertEquals(2,arbol.altura(63));
		assertTrue(0==arbol.altura(2) || 1==arbol.altura(2));
		assertEquals(1,arbol.altura(51));
		assertEquals(1,arbol.altura(94));
		assertEquals(0,arbol.altura(40));
		assertEquals(0,arbol.altura(55));
		assertEquals(0,arbol.altura(87));
		
		arbol.remove(22);
		
		assertEquals(2,arbol.altura(25));
		assertEquals(-1,arbol.altura(21));
		assertEquals(-1,arbol.altura(22));
		assertEquals(3,arbol.altura(63));
		assertTrue(0==arbol.altura(2) || 1==arbol.altura(2));
		assertEquals(1,arbol.altura(51));
		assertEquals(1,arbol.altura(94));
		assertEquals(0,arbol.altura(40));
		assertEquals(0,arbol.altura(55));
		assertEquals(0,arbol.altura(87));
	}
}
