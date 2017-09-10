package es.ubu.inf.edat.pr01_d.datos;

public class Vehiculo implements Comparable<Vehiculo> {
	
	protected String Marca;
	protected String Modelo;    
	protected int Cilindrada;
	protected int PotenciaFiscal;
    protected double Precio;
	protected String Matricula;

	protected Vehiculo(){ };
	
    public String toString() {
        return Marca + "-" + Modelo + "-" + Cilindrada + "-PF:(" + PotenciaFiscal+") \t";
    }
    /**
     * @return devuelve la cilindrada del vehiculo.
     */
    public int getCilindrada() {
        return Cilindrada;
    }
    /**
     * @param cilindrada a almacenar.
     */
    public void setCilindrada(int cilindrada) {
        Cilindrada = cilindrada;
    }
    /**
     * @return devuelve la marca del vehiculo.
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
     * @return devuelve el modelo de vehiculo.
     */
    public String getModelo() {
        return Modelo;
    }
    
    /**
     * @param modelo que se desea establecer en el vehiculo.
     */
    public void setModelo(String modelo) {
        Modelo = modelo;
    }
 
    /**
     * @return devuelve la potencia fiscal del vehiculo.
     */
    public int getPotenciaFiscal() {
        return PotenciaFiscal;
    }
    
    /**
     * @param potencia fiscal del vehiculo.
     */
    public void setPotenciaFiscal(int potenciaFiscal) {
        PotenciaFiscal = potenciaFiscal;
    }

	public Double getPrecio() {
		return this.Precio;
	}

	public void setPrecio(double precio) {
		this.Precio = precio;
	}

	public String getMatricula() {
		return this.Matricula;
	}

	public String setMatricula(String matricula) {
		return this.Matricula = matricula;
	}

	@Override
	public boolean equals (Object obj){
		boolean retorno = false;
		if (obj instanceof Vehiculo){
			Vehiculo v = (Vehiculo) obj;
			retorno = (this.getMatricula().equals(v.getMatricula()));
		}
		return retorno;
	}
	/**
	 * Compara dos vehículos según su matricula
	 * 
	 * @param o vehículo con el que queremos comparar
	 * @return 0 si son iguales, -1 o +1 si son distintos (según el compareTo de String)
	 */
	@Override
	public int compareTo(Vehiculo o) {
		return this.Matricula.compareTo(o.Matricula);
	}
}
