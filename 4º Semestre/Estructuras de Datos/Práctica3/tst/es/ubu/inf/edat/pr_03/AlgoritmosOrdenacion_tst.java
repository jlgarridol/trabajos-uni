package es.ubu.inf.edat.pr_03;

import static org.junit.Assert.*;
import es.ubu.inf.edat.pr_03.util.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author prenedo
 * @author José Luis Garrido Labrador
 * @author José Miguel Ramírez Sanz
 */
public class AlgoritmosOrdenacion_tst {

	private AlgoritmosOrdenacion<Integer> gestor;

	@Before
	public void antes() {
	}

	@Test
	public void comprobar() {

		long inicio, fin, all;

		System.out.println("Ordenado\nElementos\t|\tInterno\t|\tMerge Sort\t|\tRangos\t");
		for (int n = 0; n < 100; n++) {
			System.out.print((10000 + n * 5000)+"\t\t|");
			List<Integer> arrayAOrdenar = Arrays.asList(IntegerSortUtils.genOrder(10000 + n * 5000));

			gestor = new AlgoritmosOrdenacion<>(arrayAOrdenar);

			inicio = System.currentTimeMillis();
			assertTrue(IntegerSortUtils.isSort(gestor.resultadoMetodoInterno()));
			fin = System.currentTimeMillis();
			all = fin - inicio;
			System.out.print("\t" + all + "\t|");

			inicio = System.currentTimeMillis();
			assertTrue(IntegerSortUtils.isSort(gestor.resultadoMetodoA()));
			fin = System.currentTimeMillis();
			all = fin - inicio;
			System.out.print("\t\t" + all + "\t|");

			inicio = System.currentTimeMillis();
			assertTrue(IntegerSortUtils.isSort(gestor.resultadoMetodoB()));
			fin = System.currentTimeMillis();
			all = fin - inicio;
			System.out.println("\t" + all + "\t");
		}
		System.out.println();
		
		System.out.println("Aleatorio\nElementos\t|\tInterno\t|\tMerge Sort\t|\tRangos\t");
		for (int n = 0; n < 100; n++) {
			System.out.print((10000 + n * 5000)+"\t\t|");
			List<Integer> arrayAOrdenar = Arrays.asList(IntegerSortUtils.gen(10000 + n * 5000));

			gestor = new AlgoritmosOrdenacion<>(arrayAOrdenar);

			inicio = System.currentTimeMillis();
			assertTrue(IntegerSortUtils.isSort(gestor.resultadoMetodoInterno()));
			fin = System.currentTimeMillis();
			all = fin - inicio;
			System.out.print("\t" + all + "\t|");

			inicio = System.currentTimeMillis();
			assertTrue(IntegerSortUtils.isSort(gestor.resultadoMetodoA()));
			fin = System.currentTimeMillis();
			all = fin - inicio;
			System.out.print("\t\t" + all + "\t|");

			inicio = System.currentTimeMillis();
			assertTrue(IntegerSortUtils.isSort(gestor.resultadoMetodoB()));
			fin = System.currentTimeMillis();
			all = fin - inicio;
			System.out.println("\t" + all + "\t");
		}
		
		System.out.println("Inversamente Ordenado\nElementos\t|\tInterno\t|\tMerge Sort\t|\tRangos\t");
		for (int n = 0; n < 100; n++) {
			System.out.print((10000 + n * 5000)+"\t\t|");

			List<Integer> arrayAOrdenar = Arrays.asList(IntegerSortUtils.genInvertOrder(10000 + n * 5000));

			gestor = new AlgoritmosOrdenacion<>(arrayAOrdenar);

			inicio = System.currentTimeMillis();
			assertTrue(IntegerSortUtils.isSort(gestor.resultadoMetodoInterno()));
			fin = System.currentTimeMillis();
			all = fin - inicio;
			System.out.print("\t" + all + "\t|");

			inicio = System.currentTimeMillis();
			assertTrue(IntegerSortUtils.isSort(gestor.resultadoMetodoA()));
			fin = System.currentTimeMillis();
			all = fin - inicio;
			System.out.print("\t\t" + all + "\t|");

			inicio = System.currentTimeMillis();
			assertTrue(IntegerSortUtils.isSort(gestor.resultadoMetodoB()));
			fin = System.currentTimeMillis();
			all = fin - inicio;
			System.out.println("\t" + all + "\t");
		}

	}

}
