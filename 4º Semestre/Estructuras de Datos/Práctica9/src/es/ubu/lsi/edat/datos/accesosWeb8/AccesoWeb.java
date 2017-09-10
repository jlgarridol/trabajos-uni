package es.ubu.lsi.edat.datos.accesosWeb8;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;

public class AccesoWeb implements Comparable<AccesoWeb>, Comparator<AccesoWeb> {

	public enum SeccionWeb {
		Inicio, Servicios, Organizacion, Productos, Prensa, Blog, Contacto
	};

	private String IP;
	private LocalDateTime fechaYHora;
	private SeccionWeb destino;

	public AccesoWeb(SeccionWeb seccion, LocalDateTime time, String IP) {
		this.destino = seccion;
		this.fechaYHora = time;
		this.IP = IP;
	}

	public String getIP() {
		return IP;
	}

	public LocalDateTime getFechaYHora() {
		return fechaYHora;
	}

	public SeccionWeb getDestino() {
		return destino;
	}

	public void setProcedencia(SeccionWeb procedencia) {
		this.destino = procedencia;
	}

	@Override
	public String toString() {
		String s = "";
		s = s + destino.toString() + " - ";
		s = s + fechaYHora.toString() + " - ";
		s = s + IP.toString();
		return s;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof AccesoWeb) {
			AccesoWeb acceso = (AccesoWeb) o;
			if (IP.equals(acceso.getIP()) && fechaYHora.equals(acceso.getFechaYHora()))
				return true;
		}
		return false;
	}

	@Override
	public int compareTo(AccesoWeb acceso) {
		if (acceso.equals(this))
			return 0;
		else if (acceso.getFechaYHora().compareTo(this.getFechaYHora()) == 0)
			return acceso.getIP().compareTo(this.getIP());
		else
			return acceso.getFechaYHora().compareTo(this.getFechaYHora());

	}

	@Override
	public int compare(AccesoWeb arg0, AccesoWeb arg1) {
		return arg0.compareTo(arg1);
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(fechaYHora,IP);
	}

}
