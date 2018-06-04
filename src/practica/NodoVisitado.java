/********************************
 * PRACTICA EXTRAORDINARIA
 * 
 * SERGIO RODRIGUEZ SALGUERO
 * 
 * SISTEMAS INTELIGENTES
 ********************************/

package practica;

public class NodoVisitado {
	private String id;
	private int valor;
	
	public NodoVisitado(String id, int f) {
		super();
		this.id = id;
		this.valor = f;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}
