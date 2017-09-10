using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de Parquimetro
/// </summary>
public class Parquimetro
{
    private String descripcion;
    private String zona;
    private Localidad localidad;

	public Parquimetro(String descripcion, String zona)
	{
        this.descripcion = descripcion;
        this.zona = zona;
	}

    public void setLocalidad(Localidad localidad)
    {
        this.localidad = localidad;
    }

    public String getDescripcion()
    {
        return this.descripcion;
    }

    public String getZona()
    {
        return this.zona;
    }

    public Localidad getLocalidad()
    {
        return this.localidad;
    }
}