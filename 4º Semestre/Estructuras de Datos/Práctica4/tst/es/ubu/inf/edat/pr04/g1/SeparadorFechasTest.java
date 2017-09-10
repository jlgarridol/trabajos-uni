package es.ubu.inf.edat.pr04.g1;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import org.junit.Test;

import es.ubu.inf.edat.datos.GeneradorFechas_8;
import es.ubu.inf.edat.pr04.g1.SeparadorFechas;

public class SeparadorFechasTest {

	List<LocalDateTime> logFechas;
	List<LocalDateTime> evto1, evto2, evto3, evto4;

	@Test
	public void test4Eventos() {

		evto1 = GeneradorFechas_8.getFechasIncrementales(5, Duration.ofMinutes(5));
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(10));
		evto2 = GeneradorFechas_8.getFechasIncrementales(3, Duration.ofMinutes(5));
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(6));
		evto3 = GeneradorFechas_8.getFechasIncrementales(4, Duration.ofMinutes(5));
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(20));
		evto4 = GeneradorFechas_8.getFechasIncrementales(8, Duration.ofMinutes(5));

		logFechas = new ArrayList<LocalDateTime>();
		logFechas.addAll(evto1);
		logFechas.addAll(evto2);
		logFechas.addAll(evto3);
		logFechas.addAll(evto4);

		System.out.println(logFechas);

		Deque<List<LocalDateTime>> separadas = SeparadorFechas.separaFechas(logFechas, Duration.ofMinutes(5));

		System.out.println(separadas);

		List<LocalDateTime> ev;
		assertEquals(4,separadas.size());

		ev = separadas.removeFirst();
		assertEquals(evto4.size(),ev.size());
		assertTrue(ev.containsAll(evto4));

		ev = separadas.removeFirst();
		assertEquals(evto3.size(),ev.size());
		assertTrue(ev.containsAll(evto3));

		ev = separadas.removeFirst();
		assertEquals(evto2.size(),ev.size());
		assertTrue(ev.containsAll(evto2));

		ev = separadas.removeFirst();
		assertEquals(evto1.size(),ev.size());
		assertTrue(ev.containsAll(evto1));

	}

	@Test
	public void testParadasAlPrincipio() {

		evto1 = GeneradorFechas_8.getFechasIncrementales(3, Duration.ofMinutes(5));
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(6));
		evto2 = GeneradorFechas_8.getFechasIncrementales(4, Duration.ofMinutes(5));
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(20));
		evto3 = GeneradorFechas_8.getFechasIncrementales(8, Duration.ofMinutes(5));
		evto4 = GeneradorFechas_8.getFechasIncrementales(8, Duration.ofMinutes(25));

		logFechas = new ArrayList<LocalDateTime>();
		logFechas.addAll(evto1);
		logFechas.addAll(evto2);
		logFechas.addAll(evto3);
		logFechas.addAll(evto4);

		System.out.println(logFechas);

		Deque<List<LocalDateTime>> separadas = SeparadorFechas.separaFechas(logFechas, Duration.ofMinutes(5));

		System.out.println(separadas);

		List<LocalDateTime> ev;
		assertEquals(3,separadas.size());

		ev = separadas.removeFirst();
		assertEquals(evto3.size(),ev.size());
		assertTrue(ev.containsAll(evto3));

		ev = separadas.removeFirst();
		assertEquals(evto2.size(),ev.size());
		assertTrue(ev.containsAll(evto2));

		ev = separadas.removeFirst();
		assertEquals(evto1.size(),ev.size());
		assertTrue(ev.containsAll(evto1));

	}

	@Test	
	public void testParadasAlFinal() {

		evto1 = GeneradorFechas_8.getFechasIncrementales(3, Duration.ofMinutes(25));
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(10));
		evto2 = GeneradorFechas_8.getFechasIncrementales(3, Duration.ofMinutes(5));
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(6));
		evto3 = GeneradorFechas_8.getFechasIncrementales(4, Duration.ofMinutes(5));
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(20));
		evto4 = GeneradorFechas_8.getFechasIncrementales(8, Duration.ofMinutes(5));

		logFechas = new ArrayList<LocalDateTime>();
		logFechas.addAll(evto1);
		logFechas.addAll(evto2);
		logFechas.addAll(evto3);
		logFechas.addAll(evto4);

		System.out.println(logFechas);

		Deque<List<LocalDateTime>> separadas = SeparadorFechas.separaFechas(logFechas, Duration.ofMinutes(5));

		System.out.println(separadas);
		
		
		assertEquals(3,separadas.size());

		List<LocalDateTime> ev;
		ev = separadas.removeFirst();
		assertEquals(evto4.size(),ev.size());
		assertTrue(ev.containsAll(evto4));

		ev = separadas.removeFirst();
		assertEquals(evto3.size(),ev.size());
		assertTrue(ev.containsAll(evto3));

		ev = separadas.removeFirst();
		assertEquals(evto2.size(),ev.size());
		assertTrue(ev.containsAll(evto2));

	}


	@Test
	public void test1Parada() {

		evto1 = GeneradorFechas_8.getFechasIncrementales(5, Duration.ofMinutes(3));
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(2));
		evto2 = GeneradorFechas_8.getFechasIncrementales(3, Duration.ofMinutes(3));
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(2));
		evto3 = GeneradorFechas_8.getFechasIncrementales(4, Duration.ofMinutes(3));

		logFechas = new ArrayList<LocalDateTime>();
		logFechas.addAll(evto1);
		logFechas.addAll(evto2);
		logFechas.addAll(evto3);

		Deque<List<LocalDateTime>> separadas = SeparadorFechas.separaFechas(logFechas, Duration.ofMinutes(5));

		assertEquals(1,separadas.size());
		evto1.addAll(evto2);
		evto1.addAll(evto3);
		
		List<LocalDateTime> ev = separadas.removeFirst();
		assertEquals(evto1.size(),ev.size());
		assertTrue(ev.containsAll(evto1));

	}

	@Test
	public void testVacio() {

		logFechas = new ArrayList<LocalDateTime>();
		Deque<List<LocalDateTime>> separadas = SeparadorFechas.separaFechas(logFechas, Duration.ofMinutes(5));		
		assertTrue(separadas.isEmpty());

	}
	
	@Test
	public void testVacio2() {

		evto1 = GeneradorFechas_8.getFechasIncrementales(5, Duration.ofMinutes(10));
		
		logFechas = new ArrayList<LocalDateTime>();
		logFechas.addAll(evto1);
		
		Deque<List<LocalDateTime>> separadas = SeparadorFechas.separaFechas(logFechas, Duration.ofMinutes(5));		
		assertTrue(separadas.isEmpty());

	}

}
