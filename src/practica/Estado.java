
/********************************
 * PRACTICA EXTRAORDINARIA
 * 
 * SERGIO RODRIGUEZ SALGUERO
 * 
 * SISTEMAS INTELIGENTES
 ********************************/

package practica;

import java.io.*;
import java.security.*;
import java.util.*;

public class Estado {

	private int x;
	private int y;
	private int k;
	private int max;
	private int c;
	private int f;
	private int[][] Terreno = null;
	private String id;
	
	public Estado(int x, int y, int k, int max, int c, int f, int[][] Terreno) {
		super();
		this.x = x;
		this.y = y;
		this.k = k;
		this.max = max;
		this.c = c;
		this.f = f;
		this.Terreno = Terreno;
		this.id = getMD5(this.toString());
	}
	public Estado() {
		obtenerestadofichero();
	}
	public String get_id() {
		return id;
	}

	public void set_id() {
		id = getMD5(this.toString());
	}

	public int[][] get_terreno() {
		return Terreno;
	}

	private static String getMD5(final String s) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();
			StringBuilder hexString = new StringBuilder();
			for (int i = 0; i < messageDigest.length; i++) {
				String h = Integer.toHexString(0xFF & messageDigest[i]);
				while (h.length() < 2) {
					h = "0" + h;
				}
				hexString.append(h);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			
		}
		return "";
	}
	
	public void obtenerestadofichero() {
		Scanner entrada = null;
		try {
			entrada = new Scanner(new File("Problema.dat"));
		} catch (FileNotFoundException fnE) {
			System.out.println(fnE.getMessage());
		}
		x = entrada.nextInt();
		y = entrada.nextInt();
		k = entrada.nextInt();
		max = entrada.nextInt();
		c = entrada.nextInt();
		f = entrada.nextInt();
		Terreno = new int[f][c];
		for (int i = 0; i < Terreno.length; i++) {
			for (int j = 0; j < Terreno.length; j++) {
				Terreno[i][j] = entrada.nextInt();
			}
		}

	}

	public void sobreescribirfichero() {
		PrintWriter fichero = null;
		try {
			fichero = new PrintWriter(new FileWriter("Problema.dat")); 
		} catch (IOException fnE) {
			System.out.println(fnE.getMessage());
		}
		String cadena = "" + x + " " + y + " " + k + " " + max + " " + c + " " + f;
		fichero.println(cadena);
		for (int i = 0; i < Terreno.length; i++) {
			for (int j = 0; j < Terreno.length; j++) {
				fichero.print(" " + Terreno[i][j]);
			}
			fichero.println();
		}
		fichero.close();
	}

	public ArrayList<ArrayList<Distribucion>> distribuciones(ArrayList<int[]> lista_mov) {
		int sobrante = 0;
		ArrayList<ArrayList<Distribucion>> l = null;
		if (Terreno[x][y] > k) {
			sobrante = Terreno[x][y] - k;
			l = acciones(lista_mov, sobrante);
		}
		return l;
	}
	public ArrayList<int[]> movimientosposibles() {
		ArrayList<int[]> lista_mov = new ArrayList<int[]>();

		if (x - 1 >= 0) {
			if (Terreno[x - 1][y] < max) {
				int[] cor = new int[2];
				cor[0]=x - 1;
				cor[1]=y;
				lista_mov.add(cor);
			}
		}
		if (x + 1 < f) {
			if (Terreno[x + 1][y] < max) {
				int[] cor = new int[2];
				cor[0]=x + 1;
				cor[1]=y;
				lista_mov.add(cor);
			}
		}

		if (y - 1 >= 0) {
			if (Terreno[x][y - 1] < max) {
				int[] cor = new int[2];
				cor[0]=x;
				cor[1]=y - 1;
				lista_mov.add(cor);
			}
		}
		if (y + 1 < c) {
			if (Terreno[x][y + 1] < max) {
				int[] cor = new int[2];
				cor[0]=x;
				cor[1]=y+1;
				lista_mov.add(cor);
			}
		}

		return lista_mov;
	}

	public ArrayList<ArrayList<Distribucion>> acciones(ArrayList<int[]> lista_mov, int r) {
		ArrayList<ArrayList<Distribucion>> lista_distribuciones = new ArrayList<ArrayList<Distribucion>>();
		int[] distarena = new int[lista_mov.size()];
		repartoarena(lista_mov, r, distarena, lista_distribuciones);
		return lista_distribuciones;
	}

	public void repartoarena(ArrayList<int[]> lista_mov, int r, int[] distarena,
			ArrayList<ArrayList<Distribucion>> lista_distribuciones) {
		if (r == 0) {
			ArrayList<Distribucion> v_dist = new ArrayList<Distribucion>();
			for (int i = 0; i < distarena.length; i++) {
				int[] c = lista_mov.get(i);
				Distribucion d = new Distribucion(c[0], c[1], distarena[i]);
				v_dist.add(d);
			}
			if (comprobarmovimiento(v_dist)) {
				lista_distribuciones.add(v_dist);
				
			}

		} else {
			for (int j = 0; j < distarena.length; j++) {
				distarena[j]++;
				repartoarena(lista_mov, r - 1, distarena, lista_distribuciones);
				distarena[j]--;
			}
		}
	}

	public boolean comprobarmovimiento(ArrayList<Distribucion> v_dist) {
		boolean comprobar = true;
		for (Distribucion dis : v_dist) {
			if (Terreno[dis.getX()][dis.getY()] + dis.getArena() > max) {
				comprobar = false;
			}
		}

		return comprobar;
	}
	public ArrayList<int[]> movimientostractor() {
		ArrayList<int[]> lista_mov = new ArrayList<int[]>();

		if (x - 1 >= 0) {
			int[] cor = new int[2];
			cor[0]=x - 1;
			cor[1]=y;
			lista_mov.add(cor);
		}
		if (x + 1 < f) {
			int[] cor = new int[2];
			cor[0]=x + 1;
			cor[1]=y;
			lista_mov.add(cor);
		}

		if (y - 1 >= 0) {
			int[] cor = new int[2];
			cor[0]=x;
			cor[1]=y - 1;
			lista_mov.add(cor);
		}
		if (y + 1 < c) {
			int[] cor = new int[2];
			cor[0]=x;
			cor[1]=y + 1;
			lista_mov.add(cor);
		}

		return lista_mov;
	}
	public void imprimirterreno() {
		for (int i = 0; i < Terreno.length; i++) {
			for (int j = 0; j < Terreno.length; j++) {

				System.out.print(Terreno[i][j]);
			}
			System.out.println();
		}
	}


	public boolean esposible() {
		int suma = 0;
		int i;
		int j = 0;

		for (i = 0; i < Terreno.length; i++) {
			for (j = 0; j < Terreno.length; j++) {
				suma += Terreno[i][j];
			}
		}
		if (suma == (k * i * j)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean TerrenoObjetivo(int[][] terreno) {

		boolean objetivo = true;
		for (int i = 0; i < f; i++) {
			for (int j = 0; j < c; j++) {
				if (terreno[i][j] != k) {
					objetivo = false;
				}
			}
		}
		return objetivo;
	}

	public Estado generarterreno(Accion a) {
		ArrayList<Distribucion> lista_dist = a.getDistribuciones();
		Estado t = null;
		int[][] taux = new int[f][c];
		for (int i = 0; i < f; i++) {
			for (int j = 0; j < c; j++) {
				taux[i][j] = Terreno[i][j];
			}
		}
		if (lista_dist == null) {
			t = new Estado(a.getX(), a.getY(), k, max, c, f, taux);

		} else {
			for (Distribucion element : lista_dist) {
				taux[element.getX()][element.getY()] += element.getArena();
				taux[x][y] -= element.getArena();
			}
			t = new Estado(a.getX(), a.getY(), k, max, c, f, taux);
		}

		return t;
	}

	public int costo(Accion a) {
		int cost = 1;
		if (a.getDistribuciones() == null) {
			cost = 1;
		} else {
			ArrayList<Distribucion> l = a.getDistribuciones();
			Iterator<Distribucion> it = l.iterator();
			while (it.hasNext()) {
				Distribucion d = it.next();
				cost += d.getArena();
			}
		}
		return cost;
	}
	public ArrayList<Accion> accionesposibles(ArrayList<ArrayList<Distribucion>> distribuciones) {
		ArrayList<Accion> listaacciones = new ArrayList<Accion>();
		ArrayList<int[]> lista_mov = movimientostractor();
		if (distribuciones == null) {
			for (int[] c : lista_mov) {
				Accion a = new Accion(c[0], c[1], null);
				a.setCosto(costo(a));
				listaacciones.add(a);
			}
		} else {
			for (int[] c : lista_mov) {
				for (ArrayList<Distribucion> list_dist : distribuciones) {
					Accion a = new Accion(c[0], c[1], list_dist);
					a.setCosto(costo(a));
					listaacciones.add(a);
				}
			}
			
		}

		return listaacciones;
	}

	public ArrayList<NodoSucesor> sucesores() {
		ArrayList<NodoSucesor> sucesores = new ArrayList<NodoSucesor>();
		ArrayList<ArrayList<Distribucion>> distribuciones = distribuciones(movimientosposibles()); 
		ArrayList<Accion> lista_acciones = accionesposibles(distribuciones);
		Iterator<Accion> it = lista_acciones.iterator();
		while (it.hasNext()) {
			Accion a = it.next();
			Estado t = generarterreno(a);
			NodoSucesor s = new NodoSucesor(t, a);
			sucesores.add(s);
		}
		return sucesores;
	}

	public int CasillasImperfectas(int[][] terreno) {
		int casillas = 0;
		for (int i = 0; i < terreno.length; i++) {
			for (int j = 0; j < terreno.length; j++) {
				if (terreno[i][j] != k) {
					casillas++;
				}
			}
		}
		return casillas;
	}
	public String toString() {
		String cadena = "";
		for (int i = 0; i < Terreno.length; i++) {
			for (int j = 0; j < Terreno.length; j++) {
				cadena = cadena + " " + Terreno[i][j];
			}
			cadena = cadena + "\n";
		}
		return x + " " + y + " " + k + " " + max + " " + c + " " + f + "\n" + cadena;
	}


}