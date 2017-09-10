package es.ubu.inf.edat.pr01_d;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.ubu.inf.edat.pr01_d.datos.*;

public class GestorMatriculados_Test {

	private GestorMatriculados_Coleccion gestor = new GestorMatriculados_Coleccion();

	@Before
	public void antes(){
		 gestor = new GestorMatriculados_Coleccion(); 
	}
	

	/**
	 *  DEBEN DE COMENTAR
	 */
	@Test
	public void testInsertar() {

		Vehiculo[] nuevos = GeneradorVehiculos.todos();
		assertEquals("El numero de vehiculos debe ser 12", 12, gestor.cargaListado(nuevos));
		
	}

	/**
	 * 
	 */
	@Test
	public void testIterador() {

		Vehiculo[] nuevos = GeneradorVehiculos.todos();
		gestor.cargaListado(nuevos);
		
		Iterator<Vehiculo> it = gestor.iterator();
		int recuperados = 0;
		int contador = nuevos.length-1;
		
		while(it.hasNext()){
			Vehiculo v = it.next();
			System.out.println(v);
			assertEquals(v,nuevos[contador]);
			recuperados++;
			contador--;
		}
		
		assertEquals("El numero de Vehiculos recuperados es correcto", nuevos.length, recuperados);
		
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testVacio() {
		
		Iterator<Vehiculo> it = gestor.iterator();
		int recuperadas = 0;
		
		while(it.hasNext()){
			recuperadas++;
		}
		assertEquals("No se deber�a recuperar ningun vehiculo (excluyendo camiones)",0,recuperadas);
	}
	
	@Test
	public void testAdd() {
		
		System.out.println("-- testAdd --");
		
		Vehiculo[] nuevos = GeneradorVehiculos.cochesYMotos();
		
		assertTrue(gestor.isEmpty());
		
		System.out.println("Se espera insertar un objeto sin errores.");
		
		for(int i=0; i<3; i++){
			assertTrue( "La coleccion deber�a haberse modificado.", gestor.add(nuevos[i]) );
		}
		
		System.out.println("El tama�o de la coleccion deber�a ser igual al numero de los elmentos insertados");
		assertEquals(3, gestor.size());
		assertFalse("La coleccion no deber�a estar vac�a", gestor.isEmpty());
		
	}

	/**
	 * Test method for {@link es.ubu.inf.edat.pr02_1415.GestorMatriculados_Coleccion#addAll(java.util.Collection)}.
	 */
	@Test
	public void testAddAll() {
		
		System.out.println("-- testAddAll --");
		
		Vehiculo[] nuevos = GeneradorVehiculos.cochesYMotos();
		
		assertTrue("La coleccion se deber�a iniciar como vac�a", gestor.isEmpty());
		
		System.out.println("Se espera insertar la nueva coleccion sobre la coleccion de prueba sin errores.");
		
		assertTrue("La coleccion deber�a haberse modificado.", gestor.addAll(Arrays.asList(nuevos)));
		
		System.out.println("El tama�o de la coleccion bajo prueba deber�a ser el mismo de la coleccion insertada");
		assertEquals(nuevos.length, gestor.size());
		assertFalse("La coleccion no deber�a estar vac�a", gestor.isEmpty());		
		
	}

	/**
	 * Test method for {@link es.ubu.inf.edat.pr02_1415.GestorMatriculados_Coleccion#clear()}.
	 */
	@Test
	public void testClear() {
		
		System.out.println("-- testClear --");
		
		Vehiculo[] nuevos = GeneradorVehiculos.todos();
		assertEquals("El numero de vehiculos debe ser 12", 12, gestor.cargaListado(nuevos));
	
		System.out.println("Se una vez llena, se espera limpiar la coleccion sin errores.");
		gestor.clear();
		
		System.out.println("Tras ejecutar el clear(), el tama�o deber�a ser 0");
		assertEquals(0, gestor.size());
		
		assertTrue("Tras ejecutar el clear(), la coleccion deber�a estar vac�a", gestor.isEmpty());
		
	}

	@Test
	public void testContains() {

		System.out.println("-- testContains --");
		
		Vehiculo[] nuevos = GeneradorVehiculos.cochesYMotos();
		
		assertFalse("La coleccion esta vac�a y no existe", gestor.contains(nuevos[nuevos.length-1]));
		
		System.out.println("Se espera insertar la nueva coleccion sobre la coleccion de prueba sin errores.");
		
		assertEquals("El numero de vehiculos debe ser 9", 9, gestor.cargaListado(nuevos));
		
		assertFalse("La coleccion no deber�a estar vac�a", gestor.isEmpty());		

		assertTrue("Busco el �ltimo:", gestor.contains(nuevos[nuevos.length-1]));
		assertTrue("Busco el primero:", gestor.contains(nuevos[0]));

		// Vehiculo No contenido
		Moto m = new Moto("Marca01","Modelo01",500); m.setMatricula("123456MA");
		assertFalse("Busco uno que no existe: ", gestor.contains(m));
	}

	@Test
	public void testContainsAll() {
		System.out.println("-- testContainsAll --");
		
		Vehiculo[] nuevos = GeneradorVehiculos.cochesYMotos();
		
		assertFalse("La coleccion esta vac�a y no existe", gestor.contains(nuevos[nuevos.length-1]));
		
		System.out.println("Se espera insertar la nueva coleccion sobre la coleccion de prueba sin errores.");
		
		assertEquals("El numero de vehiculos debe ser 9", 9, gestor.cargaListado(nuevos));
		
		assertFalse("La coleccion no deber�a estar vac�a", gestor.isEmpty());		

		assertTrue("Busco el �ltimo:", gestor.containsAll (Arrays.asList(nuevos).subList(1, nuevos.length-1)));
		assertTrue("Busco el primero:", gestor.contains(nuevos[0]));

		// Vehiculo No contenido
		Moto m = new Moto("Marca01","Modelo01",500); m.setMatricula("123456MA");
		assertFalse("Busco uno que no existe: ", gestor.contains(m));

	}


	@Test
	public void testIsEmpty() {
		System.out.println("-- testAdd --");
		
		Vehiculo[] nuevos = GeneradorVehiculos.cochesYMotos();
		
		assertTrue(gestor.isEmpty());
		
		System.out.println("Se espera insertar un objeto sin errores.");
		
		assertEquals("El numero de vehiculos debe ser 9", 9, gestor.cargaListado(nuevos));
		
		System.out.println("El tama�o de la coleccion deber�a ser igual al numero de los elmentos insertados");

		assertFalse("La coleccion no deber�a estar vac�a", gestor.isEmpty());
	}

	@Test
	public void testRemove() {
		System.out.println("-- testRemove --");
		
		Vehiculo[] nuevos = GeneradorVehiculos.cochesYMotos();

		assertEquals("El numero de vehiculos debe ser 9", 9, gestor.cargaListado(nuevos));

		// Vehiculo contenido
		System.out.println("Se prueba a borrar un objeto sin errores.");
		
		assertTrue("La coleccion deber�a haberse modificado.", gestor.remove(nuevos[3]));
		
		System.out.println("El tama�o de la coleccion deber�a ser menor en un elemento.");
		
		assertEquals(nuevos.length-1, gestor.size());
		
		// Vehiculo No contenido
		Moto m = new Moto("Marca01","Modelo01",500); m.setMatricula("123456MA");
		
		System.out.println("Se prueba a borrar un objeto no contenido.");
		assertFalse("La coleccion NO deber�a haberse modificado.", gestor.remove(m));
		
		System.out.println("El tama�o de la coleccion deber�a ser igual.");
		assertEquals(nuevos.length-1, gestor.size());

	}

	@Test
	public void testRemoveAll() {
		System.out.println("-- testRemoveAll --");
		
		Vehiculo[] nuevos = GeneradorVehiculos.cochesYMotos();
		
		assertEquals("El numero de vehiculos debe ser 9", 9, gestor.cargaListado(nuevos));

		List<Vehiculo> contenidos =  Arrays.asList(nuevos).subList(1,4);
		
		System.out.println("Se prueba a borrar un conjunto de objetos sin errores.");
		assertTrue( "La coleccion deber�a haberse modificado.",	gestor.removeAll(contenidos) );
		
		System.out.println("El tama�o de la coleccion deber�a ser menor en el num. de elementos eliminados.");
		assertEquals(nuevos.length-3, gestor.size());
		
		// Los insertados en 'contenidos' ya estaban eliminados
		// El unico elemento nuevo no estaba contenido en la coleccion
		
		System.out.println("Se prueba a borrar un conjunto de objetos entre los que hay un "
				+ "objeto no contenido y los otras ya han sido eliminados.");
		
		Moto m = new Moto("Marca01","Modelo01",500); m.setMatricula("123456MA");
		contenidos.set(0,m);
		
		assertFalse("La coleccion NO deber�a haberse modificado.",	gestor.removeAll(contenidos) );
		System.out.println("El tama�o de la coleccion deber�a ser igual que antes.");
		assertEquals(nuevos.length-3, gestor.size());
	}

	@Test
	public void testRetainAll() {
		System.out.println("-- testRetainAll --");
		
		Vehiculo[] nuevos = GeneradorVehiculos.cochesYMotos();
	
		assertEquals("El numero de vehiculos debe ser 9", 9, gestor.cargaListado(nuevos));

		List<Vehiculo> contenidos =  Arrays.asList(nuevos).subList(1,4);
		
		System.out.println("Se prueba a conservar solo un subconjunto de objetos contenido en el original (sin errores).");
		
		assertTrue( "La coleccion deber�a haberse modificado.", gestor.retainAll(contenidos) );
		System.out.println("El tama�o de la coleccion deber�a ser de solo de 3 elementos (los que se han pedido conservar).");
		assertEquals(3, gestor.size());
		
		// Los insertados en 'contenidos' ya estaban eliminados
		// El unico elemento nuevo no estaba contenido en la coleccion
		Moto m = new Moto("Marca01","Modelo01",500); m.setMatricula("123456MA");
		contenidos.set(0,m);
		
		System.out.println("Se prueba a conservar solo un subconjunto de objetos contenido en el original."
				+ "En este caso se introduce un elemento que NO est� contenido."
				+ "Solo se conservan 2.");
		
		assertTrue( "La coleccion deber�a haberse modificado.",	gestor.retainAll(contenidos) );
		System.out.println("El tama�o de la coleccion deber�a ser de 2 elementos.");
		assertEquals(2, gestor.size());
	}

	@Test
	public void testSize() {
		System.out.println("-- testAdd --");
		
		Vehiculo[] nuevos = GeneradorVehiculos.cochesYMotos();
		
		assertTrue(gestor.isEmpty());

		assertEquals(0, gestor.size());
		
		System.out.println("Se espera insertar un objeto sin errores.");
		
		assertEquals("El numero de vehiculos debe ser 9", 9, gestor.cargaListado(nuevos));

		System.out.println("El tama�o de la coleccion deber�a ser igual al numero de los elmentos insertados");
		
		assertFalse("La coleccion no deber�a estar vac�a", gestor.isEmpty());
	}

	@Test
	public void testToArray() {
		fail("No implementado.");
	}

	@Test
	public void testToArrayTArray() {
		fail("No implementado.");
	}
	
	@Test
	public void testEnsanchar(){
		System.out.println("-- testEnsanchar --");
		
		Vehiculo[] nuevos = GeneradorVehiculos.cochesYMotos();
		Vehiculo[] copiaNuevos = nuevos.clone();
		gestor.cargaListado(nuevos);
		gestor.ensanchar();
		GestorMatriculados_Coleccion gest = new GestorMatriculados_Coleccion();
		gest.cargaListado(copiaNuevos);
		
		assertTrue("Los elementos del array ahora no son los mismos que antes", gestor.containsAll(gest));
		assertEquals("El tamaño no es el doble",gestor.getMatriculados().length,gest.getMatriculados().length*2);
	}
	
	@Test
	public void testEncoger(){
		System.out.println("-- testEncoger --");
		
		Vehiculo[] nuevos = GeneradorVehiculos.cochesYMotos();
		Vehiculo[] copiaNuevos = nuevos.clone();
		gestor.cargaListado(nuevos);
		gestor.encoger();
		GestorMatriculados_Coleccion gest = new GestorMatriculados_Coleccion();
		gest.cargaListado(copiaNuevos);
		
		assertTrue("Los elementos del array ahora no son los mismos que antes", gestor.containsAll(gest));
		assertEquals("El tamaño no es el del array original (que no tiene nulos)",gestor.getMatriculados().length, nuevos.length);
		assertEquals("El tamaño no es el de size()",gestor.getMatriculados().length,gestor.size());
	}
	
}
