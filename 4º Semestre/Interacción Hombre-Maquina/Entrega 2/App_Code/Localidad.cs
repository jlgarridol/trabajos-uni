using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de Localidad
/// </summary>
public class Localidad
{
    private String nombre;
    private String cultura;
    private List<Parquimetro> parquimetros;

    public Localidad(String nombre, String cultura)
    {
        this.nombre = nombre;
        this.cultura = cultura;
        this.setParquimetros();
    }

    private void setParquimetros()
    {
        parquimetros = new List<Parquimetro>();
        parquimetros.Add(new Parquimetro("", "A"));
        parquimetros.Add(new Parquimetro("", "B"));
        parquimetros.Add(new Parquimetro("", "C"));
        for (int i = 0; i < parquimetros.Count; i++)
        {
            parquimetros[i].setLocalidad(this);
        }
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public String getCultura()
    {
        return this.cultura;
    }

    public String getCulture()
    {
        return this.cultura.Split(new char[] { '-' })[0];
    }

    public Parquimetro getParquimetro(int indice)
    {
        return this.parquimetros[indice];
    }

}