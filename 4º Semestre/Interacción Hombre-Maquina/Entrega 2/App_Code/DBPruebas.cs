using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;



/// <summary>
/// DBPruebas es la clase que contendrá el set de ejemplos para la realización de la práctica
/// </summary>
public class DBPruebas
{
    private static int NUMLOC = 2;
    public static int MAXTIME = 120;
    public static bool init = false;
    public static String CIA;
    
    private static Dictionary<String, Usuario> usuarios;
    private static Localidad[] localidades;

    /// <summary>
    /// Establece el CIA del usuario logueado
    /// </summary>
    /// <param name="C">CIA del usuario</param>
    public static void setCIA(String C)
    {
        CIA = C;
    }

    /// <summary>
    /// Comprueba que los datos introducidos son correctos
    /// </summary>
    /// <param name="cia">Identificación del usuario</param>
    /// <param name="pass">Contraseña del usuario</param>
    /// <param name="email">Email del usuario</param>
    /// <returns>0 Si es correcto, 1 si el cia no existe, 2 si la combinación es incorrecta</returns>
    public static int checkUser(String cia, String pass, String email)
    {
        Usuario user;
        try {
        user = usuarios[cia];
        }catch(KeyNotFoundException ex){
            return 1;
        }
        if (user.getContrasena() == pass && user.getEmail() == email)
        {
            return 0;
        }
        return 2;
    }

    /// <summary>
    /// Inicializa la base de datos
    /// </summary>
    public static void Init(){
        init = true;
        localidades = new Localidad[NUMLOC];
        usuarios = new Dictionary<string, Usuario>();
        localidades[0] = new Localidad("Burgos", "es-ES");
        localidades[1] = new Localidad("Liverpool", "en-GB");
        usuarios.Add("USUARIO1", usuario1());
        usuarios.Add("USUARIO2", usuario2());
        usuarios.Add("USUARIO3", usuario3());
        usuarios.Add("USUARIO4", usuario4());
    }

    /// <summary>
    /// Crea el usuario 1
    /// </summary>
    /// <returns>Usuario José Miguel</returns>
    private static Usuario usuario1()
    {
        List<Vehiculo> vehiculos = new List<Vehiculo>();
        List<Ticket> tickets = new List<Ticket>();
        List<Ticket> actuales = new List<Ticket>();
        tickets.Add(new Ticket(new DateTime(2017, 5, 9, 15, 25, 6), new DateTime(2017, 5, 9, 15, 55, 6), 0.3, localidades[1].getParquimetro(1)));
        vehiculos.Add(new Vehiculo("4526 ABC", tickets));
        tickets[0].setVehiculo(vehiculos[0]);
        vehiculos.Add(new Vehiculo("1234 RTF", new List<Ticket>()));
        actuales.Add(new Ticket(DateTime.Now, DateTime.Now.AddHours(1), 0.6, localidades[1].getParquimetro(2)));
        actuales[0].setVehiculo(vehiculos[1]);
        return new Usuario("José Miguel", "Ramírez", "Sanz", "71303106R", "jrs0070@alu.ubu.es", "ES00 0000 0000 0000 0000 0000", "1111 1111 1111 1111", "mlg420", vehiculos, actuales , new List<Infraccion>(), "es-ES");
    }

    /// <summary>
    /// Crea el usuario2
    /// </summary>
    /// <returns>Usuario José Luis</returns>
    private static Usuario usuario2()
    {
        List<Vehiculo> vehiculos = new List<Vehiculo>();
        List<Ticket> tickets = new List<Ticket>();
        List<Infraccion> infracciones = new List<Infraccion>();
        tickets.Add(new Ticket(new DateTime(2017, 5, 13, 17, 0, 0), new DateTime(2017, 5, 13, 19, 0, 0), 1.2, localidades[1].getParquimetro(2)));
        tickets.Add(new Ticket(new DateTime(2017, 5, 20, 14, 15, 0), new DateTime(2017, 5, 20, 15, 15, 0), 0.6, localidades[1].getParquimetro(1)));
        vehiculos.Add(new Vehiculo("1245 VGH", tickets));
        tickets[0].setVehiculo(vehiculos[0]);
        infracciones.Add(new Infraccion(tickets[0],0, 55, localidades[1], 0));
        infracciones[0].pagar();
        infracciones[0].impugnar();
        infracciones.Add(new Infraccion(tickets[1], 0, 100, localidades[1], 2));
        infracciones[1].pagar();
        infracciones.Add(new Infraccion(0, 70, localidades[0], 1));
        return new Usuario("José Luis", "Garrido", "Labrador", "71707244Y", "jgl0062@alu.ubu.es", "GB00 0000 0000 00000 0000 0001", "0001 0001 0001 0001", "mainhanzo", vehiculos, new List<Ticket>(), infracciones, "en-GB");
    }

    /// <summary>
    /// Crea el usuario3
    /// </summary>
    /// <returns>Usuario Eduardo</returns>
    private static Usuario usuario3()
    {
        List<Vehiculo> vehiculos = new List<Vehiculo>();
        List<Ticket> actuales = new List<Ticket>();
        actuales.Add(new Ticket(DateTime.Now, DateTime.Now.AddMinutes(30), 0.55, localidades[0].getParquimetro(0)));
        actuales.Add(new Ticket(DateTime.Now, DateTime.Now.AddMinutes(20), 0.88, localidades[0].getParquimetro(1)));
        vehiculos.Add(new Vehiculo("0003 EZL",new List<Ticket>()));
        vehiculos.Add(new Vehiculo("2550 CGH", new List<Ticket>()));
        actuales[0].setVehiculo(vehiculos[0]);
        actuales[1].setVehiculo(vehiculos[0]);
        return new Usuario("Eduardo", "Zambrano", "León", "71707320J","ezl0003@alu.ubu.es","ES00 0000 0000 0000 0000 0001","0011 0011 0011 0011", "mainRein", vehiculos,actuales,new List<Infraccion>(),"es-ES");
    }

    /// <summary>
    /// Usuario 4
    /// </summary>
    /// <returns>Usuario Sun Wu</returns>
    private static Usuario usuario4()
    {
        List<Vehiculo> vehiculos = new List<Vehiculo>();
        List<Ticket> tickets = new List<Ticket>();
        tickets.Add(new Ticket(new DateTime(2017, 4, 20, 11, 00, 00), new DateTime(2017, 4, 20, 12, 00, 00), 0.66, localidades[0].getParquimetro(0)));
        tickets.Add(new Ticket(new DateTime(2017, 5, 2, 19, 00, 00), new DateTime(2017, 5, 2, 20, 00, 00), 0.66, localidades[0].getParquimetro(1)));
        tickets.Add(new Ticket(new DateTime(2017, 5, 5, 12, 00, 00), new DateTime(2017, 5, 5, 14, 00, 00), 1.32, localidades[0].getParquimetro(2)));
        tickets.Add(new Ticket(new DateTime(2017, 5, 16, 18, 00, 00), new DateTime(2017, 5, 16, 19, 30, 00), 2.09, localidades[1].getParquimetro(0)));
        vehiculos.Add(new Vehiculo("6666 TZU", tickets));
        for (int i = 0; i < tickets.Count; i++)
        {
            tickets[i].setVehiculo(vehiculos[0]);
        }
        return new Usuario("Sun", "Wu", "", "12345678P", "suntzu@suntzu.cn", "CN11 1111 1111 1111 1111 1111", "0111 0111 0111 0111", "lol", vehiculos, new List<Ticket>(), new List<Infraccion>(), "en-GB");        
    }

    /// <summary>
    /// Cierra sesión
    /// </summary>
    public void End()
    {
        CIA = null;
    }

    /// <summary>
    /// Modifica los datos del usuario
    /// </summary>
    /// <param name="valores">Diccionario con los datos nuevos</param>
    public static void ModifyUser(Dictionary<String, String> valores)
    {
        usuarios[CIA].setValues(valores);
    }

    /// <summary>
    /// Incrementa en base a los mintuos el tiempo de aparcamiento del usuario logueado
    /// </summary>
    /// <param name="idTicket">Ticket a modificar</param>
    /// <param name="minutos">Minutos a añadir</param>
    /// <returns>abonado</returns>
    public static double AlterParkingTime(int idTicket, int minutos)
    {
        usuarios[CIA].getTicket(idTicket).incrementarTiempo(minutos);
        if (usuarios[CIA].getTicket(idTicket).getParquimetro().getLocalidad().getCultura() == "en-GB")
        {
            usuarios[CIA].getTicket(idTicket).incrementarAbonado(minutos * 0.01);
            return minutos * 0.01;
        }
        else
        {
            usuarios[CIA].getTicket(idTicket).incrementarAbonado(minutos * 0.011);
            return minutos * 0.011;
        }
    }

    /// <summary>
    /// Incrementa en base a lo abonado el tiempo de aparcamiento del usuario logueado
    /// </summary>
    /// <param name="idTicket">Ticket a modificar</param>
    /// <param name="abonado">Dinero a añadir</param>
    /// <returns>Minutos</returns>
    public static int AlterParkingTime(int idTicket, double abonado)
    {
        usuarios[CIA].getTicket(idTicket).incrementarAbonado(abonado);
        if (usuarios[CIA].getTicket(idTicket).getParquimetro().getLocalidad().getCultura() == "en-GB")
        {
            usuarios[CIA].getTicket(idTicket).incrementarTiempo((int)Math.Floor(abonado / 0.01));
            return(int) Math.Floor(abonado / 0.01);
        }
        else
        {
            usuarios[CIA].getTicket(idTicket).incrementarTiempo((int)Math.Floor(abonado / 0.011));
            return (int) Math.Floor(abonado / 0.011);
        }
    }

    /// <summary>
    /// Muestra las infracciones del usuario
    /// </summary>
    /// <returns>Lista con las infracciones</returns>
    public static List<Infraccion> ShowTresspassings()
    {
        return usuarios[CIA].getInfracciones();
    }

    /// <summary>
    /// Paga una infracción
    /// </summary>
    /// <param name="idInfraccion">infracción a pagar</param>
    public static void PayTresspassing(int idInfraccion) {
        usuarios[CIA].getInfraccion(idInfraccion).pagar();
    }

    /// <summary>
    /// Impugna una infracción
    /// </summary>
    /// <param name="idInfraccion">Infracción a pagar</param>
    /// <returns>true si se puede impugnar, false si no</returns>
    public static bool RequestTresspassing(int idInfraccion)
    {
        if (usuarios[CIA].getInfraccion(idInfraccion).getPagada())
        {
            usuarios[CIA].getInfraccion(idInfraccion).impugnar();
            return true;
        }
        return false;
    }
    /// <summary>
    /// Obtiene el usuario logueado
    /// </summary>
    /// <returns>Usuario logueado</returns>
    public static Usuario getUsuario()
    {
        return usuarios[CIA];
    }
}