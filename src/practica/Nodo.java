/********************************
 * PRACTICA EXTRAORDINARIA
 * 
 * SERGIO RODRIGUEZ SALGUERO
 * 
 * SISTEMAS INTELIGENTES
 ********************************/

package practica;

public class Nodo {
	private Estado estado;
	private Nodo padre;
	private int coste;
	private int valor;
	private Accion accion;
	private int id;
	private int profundidad;
	private int heuristica;

	public Nodo(int id_nodo, Estado estado, Nodo padre, int costo, Accion acc, int prof,
			int heuristica) {
		this.estado = estado;
		this.padre = padre;
		this.coste = costo;
		this.valor = costo + heuristica;
		this.accion = acc;
		this.profundidad = prof;
		this.id = id_nodo;
		this.heuristica = heuristica;
	}
	public Nodo(int id_nodo, Estado estado, Nodo padre, int costo, int f, Accion accion, int profundidad) {
		this.estado = estado;
		this.padre = padre;
		this.coste = costo;
		this.valor = f;
		this.accion = accion;
		this.profundidad = profundidad;
		this.id = id_nodo;
	}

	public Nodo(int id_nodo, Estado estado, Nodo padre, int costo, Accion acc, int prof) {
		this.estado = estado;
		this.padre = padre;
		this.coste = costo;
		this.accion = acc;
		this.profundidad = prof;
		this.id = id_nodo;
		this.valor = id_nodo;
	}

	public Estado getEstado() {
		return estado;
	}

	public int getId() {
		return id;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Nodo getPadre() {
		return padre;
	}
	public int getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}
	public Accion getAccion() {
		return accion;
	}

	public void setAccion(Accion accion) {
		this.accion = accion;
	}

	public void setPadre(Nodo padre) {
		this.padre = padre;
	}

	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	
	
	
}
