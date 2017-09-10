package es.ubu.lsi.edat.sesion12;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.ubu.lsi.edat.datos.POI.PointOfInterest;

public class EjerciciosPipelinesTest {

	List<PointOfInterest> places = new ArrayList<PointOfInterest>(7);
	
	@Before
	public void setUp() throws Exception {
		
		float[] pos = {(float) 3.5,(float) 5.2};
		places.add(new PointOfInterest("Catedral", pos, PointOfInterest.AmenityType.MUSEUM, (float)4.8, true));
		
		float[] pos2 = {(float) -3.5,(float) 5.2};
		places.add(new PointOfInterest("SuperCinexin", pos2, PointOfInterest.AmenityType.CINEMA, (float)3.8, false));

		float[] pos3 = {(float) 2.8,(float) -3.2};
		places.add(new PointOfInterest("Musicon", pos3, PointOfInterest.AmenityType.MUSIC, (float)4.2, false));

		float[] pos4 = {(float) -3.5,(float) -4.6};
		places.add(new PointOfInterest("CentralPark", pos4, PointOfInterest.AmenityType.PARK, (float)3.8, false));

		float[] pos5 = {(float) -4.1,(float) -5.2};
		places.add(new PointOfInterest("TwoPinesMall", pos5, PointOfInterest.AmenityType.SHOPPING, (float)2.1, false));

		float[] pos6 = {(float) 2.0,(float) 7};
		places.add(new PointOfInterest("ElRetiro", pos6, PointOfInterest.AmenityType.PARK, (float)3.9, false));

		float[] pos7 = {(float) 4.5,(float) -5.2};
		places.add(new PointOfInterest("KebabDeApu", pos7, PointOfInterest.AmenityType.RESTAURANT, (float)2, false));

	}

	
    @Test
    public void elementsToUpperCase() {
        List<String> collection = asList("My", "name", "is", "John", "Doe");
        List<String> expected = asList("MY", "NAME", "IS", "JOHN", "DOE");
        
        List<String> lI = EjerciciosPipelines.toUpper_Iter(collection);
        List<String> lS = EjerciciosPipelines.toUpper_Stream(collection);
        
        System.out.println(lI);
        System.out.println(lS); 
        
        assertEquals(expected, lI);
        assertEquals(expected, lS);
    }
    
	/*
	Filter collection so that only elements with less than 4 characters are returned.
	 */
    @Test
    public void filterCollectionByLength() {
        List<String> collection = asList("My", "name", "is", "John", "Doe");
        List<String> expected = asList("My", "is", "Doe");
        
        List<String> lI = EjerciciosPipelines.minimumLenght_Iter(collection,4);
        List<String> lS = EjerciciosPipelines.minimumLenght_Stream(collection,4);
        
        System.out.println(lI);
        System.out.println(lS); 
        
        assertEquals(expected, lI);
        assertEquals(expected, lS);
        
    }
	
	@Test
	public void averageParksGrade() {

		double expected = 3.85;
		
		double avI = EjerciciosPipelines.getParksAvg_Iter(places);
		double avS = EjerciciosPipelines.getParksAvg_Stream(places);
		
        System.out.println(avI);
        System.out.println(avS); 
        
        assertEquals(expected, avI, 0.05);
        assertEquals(expected, avS, 0.05);
	}
	
	@Test
	public void bestGraded() {

		PointOfInterest expected = places.get(0);
		
		PointOfInterest bI = EjerciciosPipelines.getBestGraded_Iter(places);
		PointOfInterest bS = EjerciciosPipelines.getBestGraded_Stream(places);
		
        System.out.println(bI);
        System.out.println(bS); 
        
        assertEquals(expected, bI);
        assertEquals(expected, bS);
	}

	@Test
	public void testGetPointsCuadrant() {

		List<String> expected = Arrays.asList("Catedral","KebabDeApu","Musicon");
		
		List<String> cI = EjerciciosPipelines.getPointsCuadrant_Iter(places, 1, 6);
		List<String> cS = EjerciciosPipelines.getPointsCuadrant_Stream(places, 1, 6); //Arreglado, estaba dos veces Iter
		
        System.out.println(cI);
        System.out.println(cS); 
        
        assertEquals(expected, cI);
        assertEquals(expected, cS);
	}
	
}
