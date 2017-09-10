package es.ubu.lsi.edat.datos;

/**
 * Clase que permite el encapsulamiento de la informaci�n asociada
 * a un determinado proceso.
 * 
 * @author bbaruque (bbaruque@ubu.es)
 * @version 1.0
 */
public class Proceso {

	/** Enumeracion que determina el rango de prioridades que pueden ser asignadas a un proceso. */
	public enum Prioridad { 
		MUYALTA, ALTA, MEDIA, BAJA, MUYBAJA 
	};
	
	/** Identificador de proceso. Deber�a ser unico para cada proceso.*/
	private String id;
	/** Tiempo que precisa el proceso para completar la tarea que tiene asignada*/
	private int tiempoProceso;
	/** Prioridad del proceso. Permite definir que procesos se necesitan finalizar antes que otros*/
	private Prioridad prioridad;
	
	/**
	 * Constructor de la clase proceso. 
	 * El tiempo de proceso y la prioridad se asignan de forma arbitraria.
	 * Permite comprobar si existe un Proceso determinado en alguna de las estructuras que trabajan con ellos.
	 */
	public Proceso(String id){
		this.id = id;
		this.tiempoProceso = 1;
		this.prioridad = Prioridad.MEDIA;
	}
	
	/**
	 * Constructor de la clase proceso que permite incluir todos los parametros del mismo.
	 * 
	 * @param id identificador del proceso
	 * @param tpIni tiempo total que va a necesitar el proceso para finalizar su trabajo
	 * @param pr prioridad que se asigna al proceso
	 */
	
	public Proceso(String id, int tpIni, Prioridad pr){
		this.id = id;
		this.tiempoProceso = tpIni;
		this.prioridad = pr;
	}

	/**
	 * Permite consultar el identificador de un proceso.
	 * 
	 * @return identificador del proceso
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * Permite consultar el tiempo que queda PENDIENTE para completar el proceso. Ser� siempre igual o menor que
	 * el tiempo que se ha asignado al instanciar el proceso.
	 * 
	 * @return tiempo de proceso que se necesita hasta finalizar el trabajo 
	 */
	public int getTiempoProceso() {
		return tiempoProceso;
	}

	/**
	 * Metodo que permite DECREMENTAR el tiempo de proceso que se haya realizado hasta el momento
	 * del tiempo que resta hasta su finalizacion. No se deber� permitir ninguna otra operaci�n distinta
	 * del DECREMENTO sobre este tiempo de proceso.  
	 * 
	 * @param tiempoConsumido unidades de tiempo a decrementar
	 */
	public void consumeTiempoProceso(int tiempoConsumido) {
		this.tiempoProceso -= tiempoConsumido;
	}

	/**
	 * Permite consultar la prioridad asignada a un proceso.
	 * 
	 * @return prioridad del proceso
	 */
	public Prioridad getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Prioridad prioridad) {
		this.prioridad = prioridad;
	}

	/**
	 * Permite obtener una representaci�n alfanum�rica del proceso. Se recomienda mostrar todos sus atributos en
	 * esta representaci�n, con el objetivo de facilitar la comprobaci�n de las pruebas.
	 */
	@Override
	public String toString(){
		String s = new String();
		s = s + this.id + "-" + this.tiempoProceso + "-" + this.prioridad;
		return s;
	}
	
	// TODO - Es posible que se necesite completar esta clase

}
