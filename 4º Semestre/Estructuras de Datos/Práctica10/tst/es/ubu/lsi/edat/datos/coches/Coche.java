package es.ubu.lsi.edat.datos.coches;


/**
 * @author bbaruque
 *
 * Clase No comparable para pruebas de codigo de Estructuras de Datos
 */
public class Coche {    
    
	private String Marca;
    private String Modelo;    
    private int Caballos;
    
    /**
     * Constructor de la clase coche
     */
    public Coche(String Marca,String Modelo,int Caballos){
        this.Marca = Marca;
        this.Modelo = Modelo;        
        this.Caballos = Caballos;
    }
    /**
     * @return devuelve una cadena de texto representando el contenido del objeto
     */
    public String toString() {
        //return Caballos + " " + Marca + " " + Modelo + " " + Matricula;
        return Caballos + "/" + Marca + "/" + Modelo + " \t ";
    }
    /**
     * @return devuelve el numero de caballos del coche.
     */
    public int getCaballos() {
        return Caballos;
    }
    /**
     * @param caballos numero de caballos a almacenar.
     */
    public void setCaballos(int caballos) {
        Caballos = caballos;
    }
    /**
     * @return devuelve la marca del coche.
     */
    public String getMarca() {
        return Marca;
    }
    /**
     * @param marca que se desea almacenar.
     */
    public void setMarca(String marca) {
        Marca = marca;
    }
    
    /**
     * @return devuelve el modelo de coche.
     */
    public String getModelo() {
        return Modelo;
    }
    
    /**
     * @param modelo que se desea establecer en el coche.
     */
    public void setModelo(String modelo) {
        Modelo = modelo;
    }
    
    /**
     * Para comprobar si dos coches son iguales, deberán ser iguales:
     * la marca, el modelo y el numero de caballos.
     */
    @Override
    public boolean equals(Object o){
        Coche a = (Coche) o;
        if((this.Marca.equals(a.Marca)) && (this.Modelo.equals(a.Modelo)) && (this.Caballos == a.Caballos) )
            return true;
       else
           return false;

    }
    
    /**
     * 
     */
    public int hashCode() {
        return Caballos;
    }
        
}
