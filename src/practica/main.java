/********************************
 * PRACTICA EXTRAORDINARIA
 * 
 * SERGIO RODRIGUEZ SALGUERO
 * 
 * SISTEMAS INTELIGENTES
 ********************************/

package practica;

import java.util.Scanner;

public class main {
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Estado t = new Estado();
		Problema p = new Problema(t);
		boolean seguir = true;
		int opcion;
		Nodo nodo;
		int profundidad;
		int costo;
		System.out.println("--------------INICIO DEL PROGRAMA--------------");
		while(seguir==true) {
			System.out.println("¿Que estrategia quiere aplicar?\n\n1. Anchura \n2. Profundidad\n3. Costo Uniforme\n4. Búsqueda A*\n0. Salir");
			opcion = scanner.nextInt();
			switch (opcion) {
			case 1:
				
				nodo = p.anchura();
				profundidad = nodo.getProfundidad();
				costo = nodo.getCoste();
				System.out.println("Estrategia Anchura:\n Costo: " + costo + "\n Profundidad: " + profundidad);
				break;
			case 2:
				nodo = p.profundidad_simple();
				profundidad = nodo.getProfundidad();
				costo = nodo.getCoste();
				System.out.println(
						"Estrategia Profundidad:\n Costo: " + costo + "\n Profundidad: " + profundidad);
				break;
			case 3:
				nodo = p.costo_uniforme();
				profundidad = nodo.getProfundidad();
				costo = nodo.getCoste();
				System.out.println(
						"Estrategia Costo uniforme:\n Costo: " +  costo + "\n Profundidad: " + profundidad);
				break;
			case 4:
				nodo = p.estrategia_A_estrella();
				System.out.println(
						"Estrategia A*\nCoste: " + nodo.getCoste() + " Profundidad: " + nodo.getProfundidad() + "\n\n");
				break;
			default:
				seguir = false;
				System.out.println("--------------FIN DEL PROGRAMA--------------");
				break;
			
			}
		};

	}
}
