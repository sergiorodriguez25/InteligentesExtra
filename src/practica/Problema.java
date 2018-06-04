/********************************
 * PRACTICA EXTRAORDINARIA
 * 
 * SERGIO RODRIGUEZ SALGUERO
 * 
 * SISTEMAS INTELIGENTES
 ********************************/

package practica;

import java.util.*;

public class Problema {

	private Estado nestado;

	public Problema(Estado nestado) {
		super();
		this.nestado = nestado;
	}


	public Nodo anchura() {
		Frontera f = new Frontera();
		int id_nodo = 1;
		Nodo ninicial = new Nodo(id_nodo, nestado, null, 0, null, 1);
		f.anadir(ninicial);
		boolean solucion = false;
		Nodo nactual = null;
		while (!solucion && !f.esVacia()) {
			nactual = f.elimina();

			try {
				System.out.println(
						"Id nodo: " + nactual.getId() + "((" + nactual.getAccion().getX() + ", " + nactual.getAccion().getY()
								+ "), " + nactual.getAccion().getDistribuciones() + ", " + nactual.getAccion().getCosto() + ")");
			} catch (NullPointerException npe) {

			}

			if (nactual.getEstado().TerrenoObjetivo(nactual.getEstado().get_terreno())) {
				solucion = true;
			} else {
				ArrayList<NodoSucesor> sucesores = nactual.getEstado().sucesores();
				for (NodoSucesor s : sucesores) {
					id_nodo++;
					Nodo na = new Nodo(id_nodo, s.getEstado(), nactual, nactual.getCoste() + s.getAccion().getCosto(),
							s.getAccion(), nactual.getProfundidad() + 1);
					if (f.comprobar_estado(s.getEstado().get_id(), na.getValor())) {
						f.anadir(na);
					}
				}
			}
		}
		recorreranchura(nactual);

		return nactual;
	}
	public void recorreranchura(Nodo na) {
		Stack<Nodo> nodos = new Stack<Nodo>();

		while (!(na.getPadre() == null)) {
			nodos.push(na);
			na = na.getPadre();
		}

		System.out.println("\n\n----------------------------------------------------------------------------------");
		System.out.println("Camino solucion: Accion realizada y estado actual\n\n");

		System.out.println("Estado Inicial:\n" + nestado);
		int solucion_size = nodos.size();
		for (int i = 0; i < solucion_size; i++) {
			System.out.println("Accion: " + nodos.peek().getAccion() + " Valor: " + nodos.peek().getCoste()
					+ " Prof: " + nodos.peek().getProfundidad() + " Coste: " + nodos.peek().getCoste());
			System.out.println("Estado:\n" + nodos.peek().getEstado());
			nodos.pop();
		}

		System.out.println("Fin de la solucion\n");
	}


	public Nodo profundidad_simple() {
		Frontera f = new Frontera();
		int id_nodo = 1;
		Nodo ninicial = new Nodo(id_nodo, nestado, null, 0, 0, null, 1);
		f.anadir(ninicial);
		boolean solucion = false;
		Nodo nactual = null;
		while (!solucion && !f.esVacia()) {
			nactual = f.elimina();
			try {
				System.out.println("((" + nactual.getAccion().getX() + ", " + nactual.getAccion().getY() + "), "
						+ nactual.getAccion().getDistribuciones() + ", " + nactual.getAccion().getCosto() + ")");
			} catch (NullPointerException npe) {

			}

			if (nactual.getEstado().TerrenoObjetivo(nactual.getEstado().get_terreno())) {
				solucion = true;
			} else {
				ArrayList<NodoSucesor> sucesores = nactual.getEstado().sucesores();
				for (NodoSucesor s : sucesores) {
					id_nodo++;
					Nodo na = new Nodo(id_nodo, s.getEstado(), nactual,
							(int) (nactual.getCoste() + s.getAccion().getCosto()), nactual.getProfundidad() + 1, s.getAccion(),
							nactual.getProfundidad() + 1);
					if (f.comprobar_estado(na.getEstado().get_id(), na.getValor())) {
						f.anadir(na);
					}
				}
			}
		}
		recorrerprofundidad(nactual);

		return nactual;
	}
	public void recorrerprofundidad(Nodo na) {
		Stack<Nodo> nodos = new Stack<Nodo>();

		while (!(na.getPadre() == null)) {
			nodos.push(na);
			na = na.getPadre();
		}

		System.out.println("\n\n----------------------------------------------------------------------------------");
		System.out.println("Solucion: Accion y estado en el que nos encontramos\n\n");

		System.out.println("Estado Inicial:\n" + nestado);
		int numeroNodos = nodos.size();
		int valor = 9999;
		for (int i = 0; i < numeroNodos; i++) {
			System.out.println("Accion: " + nodos.peek().getAccion() + " Valor: " + valor-- + " Profundidad: "
					+ nodos.peek().getProfundidad() + " Coste: " + nodos.peek().getCoste());
			System.out.println("Estado:\n" + nodos.peek().getEstado());
			nodos.pop();
		}

		System.out.println("Fin de la solucion\n");
	}

	public Nodo profundidad_acotada(int base) {
		Frontera f = new Frontera();
		int id_nodo = 1;
		Nodo ninicial = new Nodo(id_nodo, nestado, null, 0, base, null, 1);
		f.anadir(ninicial);
		boolean solucion = false;
		Nodo nactual = null;
		while (!solucion && !f.esVacia()) {
			nactual = f.elimina();
			
			try {
				System.out.println("((" + nactual.getAccion().getX() + ", " + nactual.getAccion().getY() + "), "
						+ nactual.getAccion().getDistribuciones() + ", " + nactual.getAccion().getCosto() + ")");
			} catch (NullPointerException npe) {

			}

			if (nactual.getEstado().TerrenoObjetivo(nactual.getEstado().get_terreno())) {
				solucion = true;
			} else {
				ArrayList<NodoSucesor> sucesores = nactual.getEstado().sucesores();
				for (NodoSucesor s : sucesores) {
					id_nodo++;
					Nodo na = new Nodo(id_nodo, s.getEstado(), nactual,
							(int) (nactual.getCoste() + s.getAccion().getCosto()), nactual.getValor() - 1, s.getAccion(),
							nactual.getProfundidad() + 1);
					if (f.comprobar_estado(s.getEstado().get_id(), na.getValor())) {
						f.anadir(na);
					}
				}
			}
		}
		recorrerSolucion(nactual);

		return nactual;
	}

	public Nodo profundidad_iterativa(int suelo, int incremento) {
		Frontera f = new Frontera();
		int id_nodo = 1;
		Nodo ninicial = new Nodo(id_nodo, nestado, null, 0, suelo, null, 1);
		f.anadir(ninicial);
		boolean solucion = false;
		Nodo nactual = null;
		while (!solucion && !f.esVacia()) {
			nactual = f.elimina();
			
			try {
				System.out.println("((" + nactual.getAccion().getX() + ", " + nactual.getAccion().getY() + "), "
						+ nactual.getAccion().getDistribuciones() + ", " + nactual.getAccion().getCosto() + ")");
			} catch (NullPointerException npe) {

			}
			if (nactual.getEstado().TerrenoObjetivo(nactual.getEstado().get_terreno())) {
				solucion = true;
			} else {
				ArrayList<NodoSucesor> sucesores = nactual.getEstado().sucesores();
				for (NodoSucesor s : sucesores) {
					id_nodo++;
					Nodo na = new Nodo(id_nodo, s.getEstado(), nactual,
							(int) (nactual.getCoste() + s.getAccion().getCosto()), nactual.getValor() - 1, s.getAccion(),
							nactual.getProfundidad() + 1);
					if (f.comprobar_estado(s.getEstado().get_id(), na.getValor())) {
						f.anadir(na);
					}
				}
			}
		}
		recorrerSolucion(nactual);

		return nactual;
	}

	public Nodo costo_uniforme() {
		Frontera f = new Frontera();
		int id_nodo = 1;
		Nodo ninicial = new Nodo(id_nodo, nestado, null, 0, 0, null, 1);
		f.anadir(ninicial);
		boolean solucion = false;
		Nodo nactual = null;
		while (!solucion && !f.esVacia()) {
			nactual = f.elimina();
			try {
				System.out.println("((" + nactual.getAccion().getX() + ", " + nactual.getAccion().getY() + "), "
						+ nactual.getAccion().getDistribuciones() + ", " + nactual.getAccion().getCosto() + ")");
			} catch (NullPointerException npe) {

			}

			if (nactual.getEstado().TerrenoObjetivo(nactual.getEstado().get_terreno())) {
				solucion = true;
			} else {
				ArrayList<NodoSucesor> sucesores = nactual.getEstado().sucesores();
				for (NodoSucesor s : sucesores) {
					id_nodo++;
					Nodo na = new Nodo(id_nodo, s.getEstado(), nactual,
							(int) (nactual.getCoste() + s.getAccion().getCosto()), nactual.getCoste() + s.getAccion().getCosto(),
							s.getAccion(), nactual.getProfundidad() + 1);
					if (f.comprobar_estado(s.getEstado().get_id(), na.getValor())) {
						f.anadir(na);
					}
				}
			}
		}
		recorrerSolucion(nactual);

		return nactual;

	}

	public Nodo estrategia_A_estrella() {
		Frontera f = new Frontera();
		int id_nodo = 1;
		Nodo ninicial = new Nodo(id_nodo, nestado, null, 0, null, 1,
				nestado.CasillasImperfectas(nestado.get_terreno())); 
		f.anadir(ninicial);
		boolean solucion = false;
		Nodo nactual = null;
		while (!solucion && !f.esVacia()) {
			nactual = f.elimina();
			try {
				System.out.println("((" + nactual.getAccion().getX() + ", " + nactual.getAccion().getY() + "), "
						+ nactual.getAccion().getDistribuciones() + ", " + nactual.getAccion().getCosto() + ")");
			} catch (NullPointerException npe) {

			}
			if (nactual.getEstado().TerrenoObjetivo(nactual.getEstado().get_terreno())) {
				solucion = true;
			} else {
				ArrayList<NodoSucesor> sucesores = nactual.getEstado().sucesores();
				for (NodoSucesor s : sucesores) {
					id_nodo++;
					Nodo na = new Nodo(id_nodo, s.getEstado(), nactual, nactual.getCoste() + s.getAccion().getCosto(),
							s.getAccion(), nactual.getProfundidad() + 1, s.getEstado().CasillasImperfectas(s.getEstado().get_terreno()));
					if (f.comprobar_estado(s.getEstado().get_id(), na.getValor())) {
						f.anadir(na);
					}
				}
			}
		}

		recorrerSolucion(nactual);
		return nactual;
	}

	public void recorrerSolucion(Nodo na) {
		Stack<Nodo> nodos = new Stack<Nodo>();

		while (!(na.getPadre() == null)) {
			nodos.push(na);
			na = na.getPadre();
		}

		System.out.println("\n\n----------------------------------------------------------------------------------");
		System.out.println("Solucion: Accion y estado en el que nos encontramos\n\n");

		System.out.println("Estado Inicial:\n" + nestado);
		int solucion_size = nodos.size();
		for (int i = 0; i < solucion_size; i++) {
			System.out.println("Accion: " + nodos.peek().getAccion() + " Valor: " + nodos.peek().getValor()
					+ " Profundidad: " + nodos.peek().getProfundidad() + " Coste: " + nodos.peek().getCoste());
			System.out.println("Estado:\n" + nodos.peek().getEstado());
			nodos.pop();
		}

		System.out.println("Fin de la solucion\n");
	}

}
