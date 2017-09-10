package es.ubu.inf.edat.pr01_d.datos;


/**
 * @author bbaruque
 * @author prenedo
 *
 * Clase para pruebas de codigo de Estructuras de Datos.
 */
public class Moto extends Vehiculo {    

	/**
	 * Matricula de la moto.
	 */
	protected String Matricula;
	/**
	 * Ruedas de la moto.
	 */
    private int Ruedas;
    
    /**
     * Constructor de la clase moto.
     */
    public Moto(String marca,String modelo,int cilindrada){
        this.Marca = marca;
        this.Modelo = modelo;        
        this.Cilindrada = cilindrada;
    }

    /**
     * Obtiene la matricula de la moto.
     * 
     * @return cadena con la matricula
     */
	public String getMatricula() {
		return this.Matricula;
	}

	/**
	 * Establece la matricula de la moto.
	 * 
	 * @param matricula cadena con la matricula para la moto
	 * @return la nueva matricula
	 */
	public String setMatricula(String matricula) {
		return this.Matricula = matricula;
	}

	/**
	 * Obtiene las ruedas de las motos.
	 * 
	 * @return entero con la cantidad de ruedas
	 */
	public int getRuedas() {
		return Ruedas;
	}

	/**
	 * Establece las ruedas de la moto.
	 * 
	 * @param ruedas entero con la cantidad de ruedas
	 */
	public void setRuedas(int ruedas) {
		Ruedas = ruedas;
	}
}
