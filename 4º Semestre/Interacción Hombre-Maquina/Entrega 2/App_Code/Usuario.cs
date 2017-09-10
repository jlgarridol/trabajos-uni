using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de Usuario
/// </summary>
public class Usuario
{
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String nif;
    private String email;
    private String IBAN;
    private String PAN;
    private String contrasena;

    private List<Vehiculo> vehiculos;
    private List<Ticket> tickets;
    private String lang;
    private List<Infraccion> infracciones;

	public Usuario(String nombre, String apellido1, String apellido2, String nif, String email, String IBAN, String PAN, String contrasena,List<Vehiculo> vehiculos, List<Ticket> tickets, List<Infraccion> infracciones, String lang)
	{
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nif = nif;
        this.email = email;
        this.IBAN = IBAN;
        this.PAN = PAN;
        this.contrasena = contrasena;
        this.vehiculos = vehiculos;
        this.tickets = tickets;
        this.infracciones = infracciones;
        this.lang = lang;
	}

    public String getNombre()
    {
        return this.nombre; 
    }

    public String getApellido1()
    {
        return this.apellido1;
    }

    public String getApellido2()
    {
        return this.apellido2;
    }

    public String getNIF()
    {
        return this.nif;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getContrasena()
    {
        return this.contrasena;
    }

    public String getIBAN()
    {
        return this.IBAN;
    }

    public String getPAN()
    {
        return this.PAN;
    }

    public Vehiculo getVehiculo(int index)
    {
        return vehiculos[index];
    }

    public List<Vehiculo> getVehiculos()
    {
        return vehiculos;
    }

    public Ticket getTicket(int index)
    {
        return this.tickets[index];
    }

    public List<Ticket> getTickets()
    {
        return this.tickets;
    }

    public Infraccion getInfraccion(int index)
    {
        return this.infracciones[index];
    }

    public List<Infraccion> getInfracciones()
    {
        return this.infracciones;
    }

    public String getLang()
    {
        return this.lang;
    }

    public void setValues(Dictionary<String, String> valores)
    {
        foreach (String nombre in valores.Keys)
        {
            switch(nombre){
                case "nombre":
                    this.nombre = valores[nombre];
                    break;
                case "apellido1":
                    this.apellido1 = valores[nombre];
                    break;
                case "apellido2":
                    this.apellido2 = valores[nombre];
                    break;
                case "nif":
                    this.nif = valores[nombre];
                    break;
                case "IBAN":
                    this.IBAN = valores[nombre];
                    break;
                case "PAN":
                    this.PAN = valores[nombre];
                    break;
                case "contrasena":
                    this.contrasena = valores[nombre];
                    break;
                case "email":
                    this.email = valores[nombre];
                    break;
            }
        }

    }

    public void setLang(String lang)
    {
        this.lang = lang;
    }

    public String getCulture()
    {
        return this.lang.Split(new Char[] { '-' })[0];
    }

}