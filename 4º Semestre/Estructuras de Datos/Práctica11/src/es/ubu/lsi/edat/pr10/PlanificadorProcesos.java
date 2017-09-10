package es.ubu.lsi.edat.pr10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import es.ubu.lsi.edat.datos.Proceso;

/**
 * Clase para la soluciónd el ejercicio planteado en la sesión 11 de prácticas
 * de EDAT. Se pretende simular un sistema sencillo de planificación de procesos
 * empleando el algoritmo de SRPT
 * 
 * @author bbaruque
 * @author José Miguel Ramírez Sanz
 * @author José Luis Garrido Labrador
 */
public class PlanificadorProcesos {

	private List<PriorityQueue<Proceso>> lista;

	/**
	 * Comparador que compara dos procesos según el tiempo de ejecución (SRPT)
	 * 
	 * @author José Miguel Ramírez Sanz
	 * @author José Luis Garrido Labrador
	 */
	private class Comparador implements Comparator<Proceso> {

		@Override
		public int compare(Proceso arg0, Proceso arg1) {
			if (arg0.getTiempoProceso() > arg1.getTiempoProceso()) {
				return 1;
			} else if (arg0.getTiempoProceso() < arg1.getTiempoProceso()) {
				return -1;
			}
			return 0;
		}
	}


	/**
	 * Constructor de la clase. Es importante asegurarse de que las colas están
	 * correctamente iniciadas.
	 */
	public PlanificadorProcesos() {
		lista = new ArrayList<>(5);
		lista.add(new PriorityQueue<>(11, new Comparador()));
		lista.add(new PriorityQueue<>(11, new Comparador()));
		lista.add(new PriorityQueue<>(11, new Comparador()));
		lista.add(new PriorityQueue<>(11, new Comparador()));
		lista.add(new PriorityQueue<>(11, new Comparador()));
	}

	/**
	 * Metodo que permite insertar nuevos procesos en el planificador. Se debe
	 * asegurar que los procesos se distribuyen en cada cola en funcion de su
	 * prioridad.
	 * 
	 * @param proc
	 *            Proceso a introducir en el planificador
	 * @return numero de procesos incluidos en la misma cola en que se ha
	 *         insertado el proceso actual (el actual incluido)
	 */
	public int insertaEnCola(Proceso proc) {
		switch (proc.getPrioridad()) {
		case MUYALTA:
			lista.get(0).add(proc);
			return lista.get(0).size();
		case ALTA:
			lista.get(1).add(proc);
			return lista.get(1).size();
		case MEDIA:
			lista.get(2).add(proc);
			return lista.get(2).size();
		case BAJA:
			lista.get(3).add(proc);
			return lista.get(3).size();
		case MUYBAJA:
			lista.get(4).add(proc);
			return lista.get(4).size();
		}
		return 0;
	}

	/**
	 * Metodo que permite simular el paso del tiempo en el sistema completo. Se
	 * facilita un numero de instantes de tiempo a avanzar y se reproducirá ese
	 * avance en cada una de las colas. Se deberán eliminar aquellos procesos
	 * que hayan terminado su trabajo (en orden de menor a mayor tiempo) y
	 * restar el tiempo correspondiente a aquellos que no vayan a terminar en el
	 * periodo asignado.
	 * 
	 * @param tiempo
	 *            numero de instantes de tiempo que transcurren en la simulación
	 * @return true en caso de que algún proceso finalice en cualquiera de las
	 *         colas y false en caso contrario
	 */
	public boolean adelantaTiempo(int tiempo) {
		boolean retorno = false;
		for (int i = 0; i < lista.size(); i++) {
			PriorityQueue<Proceso> supp = lista.get(i);
			int temp = tiempo;
			while (temp > 0 && !supp.isEmpty()) {
				int tp = supp.peek().getTiempoProceso();
				if (tp - temp <= 0) {
					temp -= tp;
					supp.poll();
					retorno = true;
				} else {
					supp.peek().consumeTiempoProceso(temp);
					temp = 0;
				}
			}
		}
		return retorno;
	}

	/**
	 * Metodo que permite consultar el contenido de cada una de las colas del
	 * sistema.
	 * 
	 * Se deberá devolver el contenido en el mismo orden en el que se haya
	 * planificado procesar los elementos en el momento de la llamada.
	 * 
	 * @param prioridad
	 *            correspondiente a la cola que se quiere acceder
	 * @return lista con los procesos incluidos en esa cola en el orden en que
	 *         se van a procesar
	 */
	public List<Proceso> recuperaProcesosPrioridad(Proceso.Prioridad prioridad) {
		int supp = 0;
		switch (prioridad) {
		case MUYALTA:
			supp = 0;
			break;
		case ALTA:
			supp = 1;
			break;
		case MEDIA:
			supp = 2;
			break;
		case BAJA:
			supp = 3;
			break;
		case MUYBAJA:
			supp = 4;
			break;
		}
		List<Proceso> retorno = new LinkedList<>();
		PriorityQueue<Proceso> swap = new PriorityQueue<>(lista.get(supp));
		while (!swap.isEmpty()) {
			retorno.add(swap.poll());
		}
		return retorno;
	}
}
