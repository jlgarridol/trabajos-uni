package es.ubu.inf.edat.pr01_d.datos;


/**
 * @author bbaruque
 * @author prenedo
 *
 * Clase para pruebas de codigo de Estructuras de Datos.
 */
public class Camion extends Vehiculo {    

	/**
	 * Matricula del camión.
	 */
	protected String Matricula;
	/**
	 * Ejes del camión.
	 */
    private int Ejes;
    
    /**
     * Constructor de la clase coche.
     */
    public Camion(String marca,String modelo,int cilindrada){
        this.Marca = marca;
        this.Modelo = modelo;
        this.Cilindrada = cilindrada;
    }

    /**
     * Se obtiene la matricula del camión.
     * 
     * @return cadena con la matricula
     */
	public String getMatricula() {
		return this.Matricula;
	}

	/**
	 * Se determina la matricula del camión.
	 * 
	 * @param matricula cadena con la matricula del camión
	 * @return devuelve la matricula nueva
	 */
	public String setMatricula(String matricula) {
		return this.Matricula = matricula;
	}

	/**
	 * Devuelve los ejes del camión.
	 * 
	 * @return entero con los ejes
	 */
	public int getEjes() {
		return this.Ejes;
	}
	
	/**
	 * Establece los ejes del camión.
	 * 
	 * @param ejes entero con el número de ejes
	 */
	public void setEjes(int ejes) {
		this.Ejes = ejes;
	}
	
	/**
	 * Compara dos camiones y los considera iguales si tienen la misma.
	 * 
	 * @param o objeto polimorfico camión
	 * @return verdadero si tienen la misma matricula, falso si no son iguales o si no es un camión
	 */
	@Override
	public boolean equals(Object o){
		return o instanceof Camion ? ((Camion)o).getMatricula()==this.Matricula : false;		
	}
        
}
