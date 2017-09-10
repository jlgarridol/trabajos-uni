using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de Infraccion
/// </summary>
public class Infraccion
{
    private Ticket ticket;
    private int vehiculo;
    private float multa;
    private Localidad localidad;
    private int causa;
    private bool pagada;
    private bool impugnada;

	public Infraccion( int vehiculo, float multa, Localidad localidad, int causa ) : this(null,vehiculo,multa,localidad,causa)
	{      
	}

    public Infraccion(Ticket ticket, int vehiculo, float multa, Localidad localidad, int causa)
    {
        this.vehiculo = vehiculo;
        this.multa = multa;
        this.localidad = localidad;
        this.causa = causa;
        this.ticket = ticket;
    }

    public Ticket getTicket()
    {
        return this.ticket;
    }

    public int getVehiculo()
    {
        return this.vehiculo;
    }

    public float getMulta()
    {
        return this.multa;
    }

    public Localidad getLocalidad()
    {
        return this.localidad;
    }

    public int getCausa()
    {
        return this.causa;
    }

    public bool getPagada()
    {
        return this.pagada;
    }

    public bool getImpugnada()
    {
        return this.impugnada;
    }

    public void pagar()
    {
        this.pagada = true;
    }

    public void impugnar()
    {
        this.impugnada = true;
    }
}