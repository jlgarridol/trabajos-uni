package es.ubu.inf.edat.pr01_d.datos;


/**
 * @author bbaruque
 * @author prenedo
 *
 * Clase para pruebas de codigo de Estructuras de Datos
 */
public class Moto extends Vehiculo {    

    private int Ruedas;
    
    /**
     * Constructor de la clase coche
     */
    public Moto(String marca,String modelo,int cilindrada){
        this.Marca = marca;
        this.Modelo = modelo;        
        this.Cilindrada = cilindrada;
    }

	public int getRuedas() {
		return Ruedas;
	}

	public void setRuedas(int ruedas) {
		Ruedas = ruedas;
	}

}
