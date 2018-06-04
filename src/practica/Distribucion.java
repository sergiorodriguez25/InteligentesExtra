/********************************
 * PRACTICA EXTRAORDINARIA
 * 
 * SERGIO RODRIGUEZ SALGUERO
 * 
 * SISTEMAS INTELIGENTES
 ********************************/

package practica;

public class Distribucion {

	private int x;
	private int y;
	private int arena;
	
	public Distribucion(int x, int y, int arena) {
		super();
		this.x = x;
		this.y = y;
		this.arena = arena;
	}

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

	public int getArena() {
		return arena;
	}

	public void setArena(int arena) {
		this.arena = arena;
	}

	public String toString() {
		return "(" + arena + ", (" + x + ", " + y + "))";
	}
}
