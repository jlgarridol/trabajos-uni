package es.ubu.inf.edat.pr01_d.datos;


/**
 * @author bbaruque
 * @author prenedo
 *
 * Clase para pruebas de codigo de Estructuras de Datos
 */
public class Coche extends Vehiculo {    

    private int Puertas;
    
    /**
     * Constructor de la clase coche
     */
    public Coche(String marca,String modelo,int cilindrada){
        this.Marca = marca;
        this.Modelo = modelo;        
        this.Cilindrada = cilindrada;
    }

	public int getPuertas() {
		return Puertas;
	}

	public void setPuertas(int puertas) {
		Puertas = puertas;
	}


}
