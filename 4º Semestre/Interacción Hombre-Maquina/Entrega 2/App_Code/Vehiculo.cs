using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de Vehiculo
/// </summary>
public class Vehiculo
{
    private String matricula;
    private List<Ticket> tickets;

	public Vehiculo(String matricula, List<Ticket> tickets)
	{
        this.matricula = matricula;
        this.tickets = tickets;
	}

    public String getMatricula()
    {
        return this.matricula;
    }

    public List<Ticket> getTickets()
    {
        return this.tickets;
    }

    public Ticket getTicket(int indice)
    {
        return this.tickets[indice];
    }
}