package es.ubu.inf.edat.pr01_d.datos;


/**
 * @author bbaruque
 * @author prenedo
 *
 * Clase para pruebas de codigo de Estructuras de Datos
 */
public class Camion extends Vehiculo {    

    private int Ejes;
    
    /**
     * Constructor de la clase coche
     */
    public Camion(String marca,String modelo,int cilindrada){
        this.Marca = marca;
        this.Modelo = modelo;
        this.Cilindrada = cilindrada;
    }

	public int getEjes() {
		return this.Ejes;
	}

	public void setEjes(int ejes) {
		this.Ejes = ejes;
	}

}
