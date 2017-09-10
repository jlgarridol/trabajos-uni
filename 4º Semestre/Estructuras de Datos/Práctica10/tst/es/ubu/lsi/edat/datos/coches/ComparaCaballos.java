package es.ubu.lsi.edat.datos.coches;

import java.util.Comparator;

public class ComparaCaballos implements Comparator<Coche>{

	/*****************  Metodos de la Interfaz  ************************/
  public int compare(Coche c1, Coche c2) throws ClassCastException{
//public int compare(<? extends Coche> o1, <? extends Coche> o2) throws ClassCastException{
//	 public int compare(Object o1, Object o2) throws ClassCastException{
	  
  	// Comparamos segun el No. de caballos
    return ( c1.getCaballos()- c2.getCaballos() );
  }

//  public boolean equals(Object o){
//    return this.equals(o);
//  }

} // Fin clase 