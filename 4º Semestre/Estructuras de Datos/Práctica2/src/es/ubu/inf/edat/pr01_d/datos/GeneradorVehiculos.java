package es.ubu.inf.edat.pr01_d.datos;

import java.util.ArrayList;
import java.util.List;

public class GeneradorVehiculos {

	final static String[] marcasC = {"Audi","Ford","Renault","Toyota","Nissan","Kia"};
	final static String[] modelosC = {"A3","Focus","Clio","Auris","Micra","Sportage"};
	final static int[] caballosC = {180,100,110,150,100,200};
	final static int[] potenciaFC = {30, 20, 28, 18, 34, 15};
	
	final static String[] marcasM = {"BMW","Honda","Vespa"};
	final static String[] modelosM = {"RM1200","CB650F","R1"};
	final static int[] caballosM = {1200,650,125};
	final static int[] potenciaFM = {32,8,1};

	final static String[] marcasCm = {"Pegaso","Iveco","JMC"};
	final static String[] modelosCm = {"Troner","Trakker","Convey"};
	final static int[] caballosCm = {3500,3300,2120};
	final static int[] potenciaFCm = {180,150,100};
	
	/**
	 * 
	 * @return
	 */
	public static Vehiculo[] soloCoches(){
		
		List<Vehiculo> lv = new ArrayList<Vehiculo>();
		
		for(int i=0;i<marcasC.length;i++){
			
			String matricula = generaMatricula();
			
			Coche c = new Coche(marcasC[i], modelosC[i], caballosC[i]);
			c.setPuertas(generaPuertas());
			c.setMatricula(matricula);
			c.setPotenciaFiscal(potenciaFC[i]);
			c.setPrecio(6000+generaAleatorioDoble(15)*1000);
			lv.add(c);
		}

		Vehiculo [] listadoC = new Vehiculo[6];
		return lv.toArray(listadoC);
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static Vehiculo[] soloMotos(){

		List<Vehiculo> lv = new ArrayList<Vehiculo>();
		
		for(int i=0;i<marcasM.length;i++){

			String matricula = generaMatricula();
			
			Moto m = new Moto(marcasM[i], modelosM[i], caballosM[i]);
			m.setRuedas(generaRuedas());
			m.setMatricula(matricula);
			m.setPotenciaFiscal(potenciaFM[i]);
			m.setPrecio(1000+generaAleatorioDoble(10)*1000);

			lv.add(m);
		}		

		Vehiculo [] listadoC = new Vehiculo[3];
		return lv.toArray(listadoC);
	}

	/**
	 * 
	 * @return
	 */
	public static Vehiculo[] soloCamiones(){

		List<Vehiculo> lv = new ArrayList<Vehiculo>();
		
		for(int i=0;i<marcasM.length;i++){

			String matricula = generaMatricula();
			
			Camion cm = new Camion(marcasCm[i], modelosCm[i], caballosCm[i]);
			cm.setEjes(generaEjes());
			cm.setMatricula(matricula);
			cm.setPotenciaFiscal(potenciaFCm[i]);
			cm.setPrecio(5000+generaAleatorioDoble(15)*1000);

			lv.add(cm);
		}		

		Vehiculo [] listadoCm = new Vehiculo[3];
		return lv.toArray(listadoCm);
	}

	/**
	 * 
	 * @return
	 */
	public static Vehiculo[] cochesYMotos(){

		Vehiculo[] coches = soloCoches();
		Vehiculo[] motos = soloMotos();

		List<Vehiculo> lv = new ArrayList<Vehiculo>();
		
		for(Vehiculo c : coches)
			lv.add(c);
		
		lv.add(1, motos[0]);
		lv.add(4, motos[1]);
		lv.add(motos[2]);

		Vehiculo[] listado = new Vehiculo[9];
		return lv.toArray(listado);
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static Vehiculo[] cochesYCamiones(){

		Vehiculo[] coches = soloCoches();
		Vehiculo[] camiones = soloCamiones();

		List<Vehiculo> lv = new ArrayList<Vehiculo>();
		
		for(Vehiculo c : coches)
			lv.add(c);
		
		lv.add(1, camiones[0]);
		lv.add(4, camiones[1]);
		lv.add(camiones[2]);

		Vehiculo[] listado = new Vehiculo[9];
		return lv.toArray(listado);
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static Vehiculo[] todos(){

		Vehiculo[] coches = soloCoches();
		Vehiculo[] camiones = soloCamiones();
		Vehiculo[] motos = soloMotos();

		List<Vehiculo> lv = new ArrayList<Vehiculo>();
		
		for(Vehiculo c : coches)
			lv.add(c);
		
		lv.add(1, camiones[0]);
		lv.add(3, camiones[1]);
		lv.add(camiones[2]);
		
		lv.add(5, motos[0]);
		lv.add(7, motos[1]);
		lv.add(motos[2]);
		

		Vehiculo[] listado = new Vehiculo[12];
		return lv.toArray(listado);
		
	}
	

	
	public static Vehiculo[] aleatorios (int tamano){
		
		Vehiculo [] listado = new Vehiculo[tamano];
		
		for (int i=0; i<tamano; i++){
			
			Vehiculo nuevo;
			String matricula = generaMatricula();
			
			if( (int) Math.round(Math.random()) == 0 ){
				nuevo = new Coche(marcasC[generaAleatorio(8)],modelosC[generaAleatorio(8)],caballosC[generaAleatorio(8)]);
				((Coche)nuevo).setPuertas(generaPuertas());
				((Coche)nuevo).setMatricula(matricula);
				((Coche)nuevo).setPotenciaFiscal(generaAleatorio(25));
				((Coche)nuevo).setPrecio(generaAleatorioDoble(25)*1000);
			}
			else{
				nuevo = new Moto(marcasM[generaAleatorio(8)],modelosM[generaAleatorio(8)],caballosM[generaAleatorio(8)]);
				((Moto)nuevo).setRuedas(generaRuedas());
				((Moto)nuevo).setMatricula(matricula);
				((Moto)nuevo).setPotenciaFiscal(generaAleatorio(5));
				((Moto)nuevo).setPrecio(generaAleatorioDoble(15)*1000);
			}
			
			listado[i] = nuevo;			
		}
		
		return listado;
		
	}
	
	private static String generaMatricula(){
		return new Double (Math.ceil(Math.random() * 10000)).toString();
	}

	private static int generaPuertas(){
		int [] posibles = {3,5};
		return (int) posibles[generaAleatorio(1)];
	}
	
	private static int generaRuedas(){
		int [] posibles = {2,3,4};
		return (int) posibles[generaAleatorio(2)];
	}
	
	private static int generaEjes(){
		int [] posibles = {2,3,4,5,6};
		return (int) posibles[generaAleatorio(4)];
	}
	
	private static int generaAleatorio(int max){
		return (int) Math.round(Math.random()) * max;
	}

	private static double generaAleatorioDoble(double max){
		return (double) Math.round(Math.random()) * max;
	}

}
