/********************************
 * PRACTICA EXTRAORDINARIA
 * 
 * SERGIO RODRIGUEZ SALGUERO
 * 
 * SISTEMAS INTELIGENTES
 ********************************/

package practica;

public class NodoSucesor {
	
	private Estado estado;
	private Accion accion;
	
	public NodoSucesor(Estado estado, Accion accion) {
		super();
		this.estado = estado;
		this.accion = accion;
	}
	
	public Accion getAccion() {
		return accion;
	}
	public void setAccion(Accion accion) {
		this.accion = accion;
	}
	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
