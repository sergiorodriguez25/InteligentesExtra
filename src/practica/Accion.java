/********************************
 * PRACTICA EXTRAORDINARIA
 * 
 * SERGIO RODRIGUEZ SALGUERO
 * 
 * SISTEMAS INTELIGENTES
 ********************************/

package practica;

import java.util.ArrayList;


public class Accion {

	private int x;
	private int y;
	private ArrayList<Distribucion> distribuciones;
	private int costo;

	public Accion(int x, int y, ArrayList<Distribucion> distribuciones) {
		super();
		this.x = x;
		this.y = y;
		this.distribuciones = distribuciones;
	}

	// Getters y setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ArrayList<Distribucion> getDistribuciones() {
		return distribuciones;
	}

	public void setDistribuciones(ArrayList<Distribucion> dist) {
		this.distribuciones = dist;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public String toString() {
		return "( (" + x + ", " + y + "), "
				+ distribuciones + ", " + costo + ")";
	}
}
