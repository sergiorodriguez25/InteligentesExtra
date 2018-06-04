/********************************
 * PRACTICA EXTRAORDINARIA
 * 
 * SERGIO RODRIGUEZ SALGUERO
 * 
 * SISTEMAS INTELIGENTES
 ********************************/

package practica;

import java.util.*;

public class Frontera {
	ArrayList<Nodo> frontera;
	ArrayList<NodoVisitado> nodosvisitados;

	public Frontera(ArrayList<Nodo> frontera, ArrayList<NodoVisitado> nodosvisitados) {
		super();
		this.frontera = frontera;
		this.nodosvisitados = nodosvisitados;
	}

	public Frontera() {
		frontera = new ArrayList<Nodo>();
		nodosvisitados = new ArrayList<NodoVisitado>();
	}
	public void anadir(Nodo na) {
		frontera.add(na);
	}

	public Nodo elimina() {
		if (frontera.isEmpty()) {
			return null;
		} else {
			return frontera.remove(0);
		}
	}

	public boolean esVacia() {
		return frontera.isEmpty();
	}

	public ArrayList<NodoVisitado> getNodosVisitados() {
		return nodosvisitados;
	}

	public void setNodosVisitados(ArrayList<NodoVisitado> nodosvisitados) {
		this.nodosvisitados = nodosvisitados;
	}

	public boolean comprobar_estado(String id, int f) {
		boolean comprobar = true;
		Iterator<NodoVisitado> it = nodosvisitados.iterator();
		while (it.hasNext()) {
			NodoVisitado v = it.next();
			if (v.getId().equals(id)) {
				if (v.getValor() <= f) {
					comprobar = false;
				}
			}
		}
		if (comprobar) {
			NodoVisitado v = new NodoVisitado(id, f);
			nodosvisitados.add(v);
		}
		return comprobar;
	}


}
