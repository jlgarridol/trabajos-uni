package es.ubu.inf.edat.pr01_d;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import es.ubu.inf.edat.pr01_d.datos.*;

public class GestorMatriculados_G1_Test {

	private GestorMatriculados_G1 gestor = new GestorMatriculados_G1();

	/**
	 *  DEBEN DE COMENTAR
	 */
	@Test
	public void testInsertar() {

		Vehiculo[] nuevos = GeneradorVehiculos.todos();
		assertEquals("El numero de vehiculos de mas de cuatro ruedas debe ser 12", 12, gestor.cargaListado(nuevos));
		
	}

	/**
	 * 
	 */
	@Test
	public void testIterador() {

		Vehiculo[] nuevos = GeneradorVehiculos.todos();
		gestor.cargaListado(nuevos);
		
		Iterator<Vehiculo> it = gestor.iterator();
		int recuperadas = 0;
		
		while(it.hasNext()){
			Vehiculo v = it.next();
			System.out.println(v);
			assertTrue((v instanceof Camion || v instanceof Coche) && v.getCilindrada()>=1000);
			recuperadas++;
		}
		
		assertEquals("El numero de vehículos recuperados debe ser 6, recuperados: ", 6, recuperadas);
		
		gestor.vaciaListado();
		Vehiculo[] vehiculosCargados = GeneradorVehiculos.soloCamiones();

		int cuantosHay = gestor.cargaListado(vehiculosCargados);
		
		assertEquals("El numero de vehiculos cargado debe ser 3: ", vehiculosCargados.length, cuantosHay);
		
		int posicion = 0;
		
		it = gestor.iterator();
		while(it.hasNext()){
			Vehiculo m = it.next();
			assertTrue(m==vehiculosCargados[posicion]);
			posicion++;
		}
		
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testMetodoEquals() {

		Vehiculo[] nuevos = GeneradorVehiculos.soloCamiones();
	
		Camion camion1 = (Camion) nuevos[0];
		Camion camion2 = (Camion) nuevos[1];
		
		assertTrue ("Comprobamos que se trata del mismo objeto ",camion1==nuevos[0]);
		assertFalse ("Comprobamos que no son el mismo objeto ", camion1==camion2);

		assertFalse ("Comprobamos que las matrículas no son iguales ",camion1.equals(camion2));
		camion2.setMatricula(camion1.getMatricula());
		assertTrue ("Comprobamos que las matriculas son iguales: ", camion1.equals(camion2));
		assertFalse ("Comprobamos que no son el mismo objeto: ", camion1==camion2);
		
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
		assertEquals("No se debería recuperar ningun vehiculo de más decuatro ruedas",0,recuperadas);
	}
	
}
