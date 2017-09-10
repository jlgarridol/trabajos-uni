using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de Ticket
/// </summary>
public class Ticket
{
    private DateTime inicio;
    private DateTime final;
    private Vehiculo vehiculo;
    private double abonado;
    private Parquimetro parquimetro;

	public Ticket(DateTime inicio, DateTime final, double abonado, Parquimetro parquimetro)
	{
        this.inicio = inicio;
        this.final = final;
        this.abonado = abonado;
        this.parquimetro = parquimetro;
	}

    public void setVehiculo(Vehiculo vehiculo)
    {
        this.vehiculo = vehiculo;
    }

    public DateTime getInicio()
    {
        return this.inicio;
    }

    public DateTime getFinal()
    {
        return this.final;
    }

    public Vehiculo getVehiculo()
    {
        return this.vehiculo;
    }

    public double getAbonado()
    {
        return this.abonado;
    }

    public Parquimetro getParquimetro()
    {
        return this.parquimetro;
    }

    public void incrementarAbonado(double abonado)
    {
        this.abonado += abonado;
    }

    public void incrementarTiempo(int final)
    {
        this.final = this.final.AddMinutes(final);
    }
}