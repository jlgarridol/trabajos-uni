package es.ubu.inf.edat.pr01_d.datos;


/**
 * @author bbaruque
 * @author prenedo
 *
 * Clase para pruebas de codigo de Estructuras de Datos.
 */
public class Coche extends Vehiculo {    

	/**
	 * Matricula del coche.
	 */
	protected String Matricula;
    /**
     * Cantidad de puertas del coche.
     */
	private int Puertas;
    
    /**
     * Constructor de la clase coche.
     */
    public Coche(String marca,String modelo,int cilindrada){
        this.Marca = marca;
        this.Modelo = modelo;        
        this.Cilindrada = cilindrada;
    }

    /**
     * Obtienes la matricula del coche.
     * 
     * @return cadena con la matricula
     */
	public String getMatricula() {
		return this.Matricula;
	}

	/**
	 * Introduce la matricula al coche.
	 * 
	 * @param matricula
	 * @return
	 */
	public String setMatricula(String matricula) {
		return this.Matricula = matricula;
	}

	/**
	 * Obtiene las puertas.
	 * 
	 * @return entero con las puertas del coche
	 */
	public int getPuertas() {
		return Puertas;
	}

	/**
	 * Establece las puertas del coche.
	 * 
	 * @param puertas entero con las puertas
	 */
	public void setPuertas(int puertas) {
		Puertas = puertas;
	}
        
}
