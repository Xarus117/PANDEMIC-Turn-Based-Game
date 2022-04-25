package panel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Enfermedades {
	static String NomFit = "Ficheros//CCP.bin"; // EL FICHERO BIN
	static String NomFit1 = "Ficheros//ciudades.txt";
	static String NomFit2 = "ficheros//ciudades-enfermedad.bin";

	static int[] n0 = new int[48];
	static String[] ciudades = new String[48];
	static int ronda = 10;
	static String Ciudad_Enfermedad[] = new String[48];
	static int infectadaRonda;

	public static void funcionamiento() throws IOException {
		// TODO Auto-generated method stub

		// for (int i = 0; i < ronda; i++) {
		// Ciudad_Enfermedad = ronda(NomFit1, NomFit, n0, ciudades, NomFit2,
		// Ciudad_Enfermedad);
		// System.out.println("\n");
		// for (int j = 0; j < Ciudad_Enfermedad.length; j++) {
		// System.out.print(" posicion " + j + Ciudad_Enfermedad[j]);
		// }
		// }

			ronda(NomFit1, NomFit, n0, ciudades, NomFit2, Ciudad_Enfermedad);
			for (int j = 0; j < Ciudad_Enfermedad.length; j++) {
				System.out.println(Ciudad_Enfermedad[j]);
			}
		

		

		System.out.println("\n\n");
		cura(Ciudad_Enfermedad);
	}

	public static String[] ronda(String NomFit1, String Fit, int n0[], String ciudades[], String NomFit2,
			String[] Ciudad_Enfermedad) throws IOException {

		FileReader fr = new FileReader(NomFit1);
		BufferedReader br = new BufferedReader(fr);
		// PARA LEER EL BIN
		String[] nombreEnfermedad = new String[4];
		int[] codigoEnfermedad = new int[4];
		String[] colorEnfermeda = new String[4];
		int cordenadasx = 0;
		int cordenadasy = 0;
		String la_frase = "";
		String separador = Pattern.quote(";");// un separador de ;
		int cantCiudadesInfectadasPorRonda = 0;

		int modo_facil = 5;

		String[] comproba_ciudad = new String[48];
		String[] comproba_enfe = new String[48];

		try {
			DataInputStream LeeFichero = new DataInputStream(new FileInputStream(Fit));

			LeeFichero.readUTF();
			for (int i = 0; i < 4; i++) {

				codigoEnfermedad[i] = LeeFichero.readInt();
				nombreEnfermedad[i] = LeeFichero.readUTF();
				colorEnfermeda[i] = LeeFichero.readUTF();

				// System.out.println(nombreEnfermedad[i]);
				// System.out.println(codigoEnfermedad[i]);
				// System.out.println(colorEnfermeda[i]);

			}

			for (int i = 0; i < 48; i++) {

				la_frase = br.readLine();

				String[] parts = la_frase.split(separador);// separamos la frase que nos introduce el usuario

				ciudades[i] = parts[0];
				n0[i] = Integer.parseInt(parts[1]);

				DataOutputStream EscribeFichero = new DataOutputStream(new FileOutputStream(NomFit2));

				if (n0[i] == 0) {
					EscribeFichero.writeUTF(ciudades[i] + " " + "Alfa\n");
				} else if (n0[i] == 1) {
					EscribeFichero.writeUTF(ciudades[i] + " " + "Beta\n");
				} else if (n0[i] == 2) {
					EscribeFichero.writeUTF(ciudades[i] + " " + "Gama\n");

				} else {
					EscribeFichero.writeUTF(ciudades[i] + " " + "Delta\n");
				}

			}

			while (cantCiudadesInfectadasPorRonda < modo_facil) {
				int contador = 1;
				int a = (int) Math.floor(Math.random() * (47 - 1 + 1) + 1);// el numero de la ciudad que se va a
				infectadaRonda = a;															// infectar(en el array).
				int guardar_ciudades[] = new int[modo_facil];
				guardar_ciudades[contador] = a;

				while (guardar_ciudades[contador--] == guardar_ciudades[contador]) {
					a = (int) Math.floor(Math.random() * (47 - 1 + 1) + 1);
				}

				if (Ciudad_Enfermedad[a] == null) {
					Ciudad_Enfermedad[a] = codigoEnfermedad[(int) Math.floor(Math.random() * (3 - 0 + 1) + 0)] + ";";
				} else {
					Ciudad_Enfermedad[a] += codigoEnfermedad[(int) Math.floor(Math.random() * (3 - 0 + 1) + 0)] + ";";
				}

				contador++;
				cantCiudadesInfectadasPorRonda++;
			}
		} catch (IOException e) {
			System.out.println("Error E/S");
		}
		brote(Ciudad_Enfermedad, NomFit1, NomFit2);
		
		return Ciudad_Enfermedad;
		
	}

	public static String[] brote(String[] Ciudad_Enfermedad, String NomFit, String NomFit1) {

		String[] colindantes = new String[48];
		String[] ciudades = new String[48];

		// Creamos un String que va a contener todo el texto del archivo
		String separador = Pattern.quote(";");// un separador de ;
		String separador1 = Pattern.quote(",");// un separador de ,
		String la_frase = "";
		int cont_enfer_0 = 0;
		int cont_enfer_1 = 0;
		int cont_enfer_2 = 0;
		int cont_enfer_3 = 0;

		try {
			// Abrir el fichero indicado en la variable nombreFichero
			FileReader fr = new FileReader(NomFit);
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw = new FileWriter(NomFit1, false);
			// Crear un objeto BufferedWriter
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i < 48; i++) {
				la_frase = br.readLine();

				String[] parts = la_frase.split(separador);// separamos la frase que nos introduce el usuario

				colindantes[i] = parts[3];
				ciudades[i] = parts[0];

				colindantes[i].split(separador1);
				bw.newLine();
			}

			fr.close();
			br.close();
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("Error E/S: " + e);
		}

		for (int i = 0; i < Ciudad_Enfermedad.length; i++) {

			if (Ciudad_Enfermedad[i] != null) {

				char temp;
				for (int a = 0; a < Ciudad_Enfermedad[i].length(); a++) {

					temp = Ciudad_Enfermedad[i].charAt(a);
					if (temp == '0') {
						cont_enfer_0++;
					} else if (temp == '1') {
						cont_enfer_1++;
					} else if (temp == '2') {
						cont_enfer_2++;
					} else if (temp == '3') {
						cont_enfer_3++;
					}

				}

				if (cont_enfer_0 >= 3) {
					System.out
							.println("la ciudad numero " + i + " tiene nivel de enfermedad 3 BROTE (codigo enfer: 0)");
					for (int j = 0; j < ciudades.length; j++) {// lo que quiero hacer aqui es que comprube el nombre de
																// la ciudad en las colindantes para saber en que
																// posicion esta y asi poder sumarle la enfermedad
						if (colindantes[i].contains(ciudades[j])) {
							Ciudad_Enfermedad[j] += "0;";
						}
					}
				} else if (cont_enfer_1 >= 3) {
					System.out
							.println("la ciudad numero " + i + " tiene nivel de enfermedad 3 BROTE (codigo enfer: 1)");
					for (int j = 0; j < ciudades.length; j++) {// lo que quiero hacer aqui es que comprube el nombre de
						// la ciudad en las colindantes para saber en que
						// posicion esta y asi poder sumarle la enfermedad
						if (colindantes[i].contains(ciudades[j])) {
							Ciudad_Enfermedad[j] += "1;";
						}
					}
				} else if (cont_enfer_2 >= 3) {
					System.out
							.println("la ciudad numero " + i + " tiene nivel de enfermedad 3 BROTE (codigo enfer: 2)");
					for (int j = 0; j < ciudades.length; j++) {// lo que quiero hacer aqui es que comprube el nombre de
						// la ciudad en las colindantes para saber en que
						// posicion esta y asi poder sumarle la enfermedad
						if (colindantes[i].contains(ciudades[j])) {
							Ciudad_Enfermedad[j] += "2;";
						}
					}
				} else if (cont_enfer_3 >= 3) {
					System.out
							.println("la ciudad numero " + i + " tiene nivel de enfermedad 3 BROTE (codigo enfer: 3)");
					for (int j = 0; j < ciudades.length; j++) {// lo que quiero hacer aqui es que comprube el nombre de
						// la ciudad en las colindantes para saber en que
						// posicion esta y asi poder sumarle la enfermedad
						if (colindantes[i].contains(ciudades[j])) {
							Ciudad_Enfermedad[j] += "3;";
						}
					}
				}
				cont_enfer_0 = 0;
				cont_enfer_1 = 0;
				cont_enfer_2 = 0;
				cont_enfer_3 = 0;
			}

		}
		return Ciudad_Enfermedad;
	}

	public static void cura(String Ciudad_Enfermedad[]) {
		Scanner sc = new Scanner(System.in);
		int ciudad_curar = 0;
		int codi_enferme_curar = 0;
		int position_ned = 0;

		System.out.println("numero de la ciudad que quieres curar:");
		ciudad_curar = sc.nextInt();

		System.out.println(
				"la ciudad numero " + ciudad_curar + " esta infectada por " + " " + Ciudad_Enfermedad[ciudad_curar]);
		System.out.println("que enfermedad quieres curar: ");
		codi_enferme_curar = sc.nextInt();

		if (codi_enferme_curar == 0) {
			for (int i = 0; i < Ciudad_Enfermedad[ciudad_curar].length(); i++) {
				if (Ciudad_Enfermedad[ciudad_curar].charAt(i) == '0') {
					position_ned = i;
				}
			}
		} else if (codi_enferme_curar == 1) {
			for (int i = 0; i < Ciudad_Enfermedad[ciudad_curar].length(); i++) {
				if (Ciudad_Enfermedad[ciudad_curar].charAt(i) == '1') {
					position_ned = i;
				}
			}
		} else if (codi_enferme_curar == 2) {
			for (int i = 0; i < Ciudad_Enfermedad[ciudad_curar].length(); i++) {
				if (Ciudad_Enfermedad[ciudad_curar].charAt(i) == '2') {
					position_ned = i;
				}
			}
		} else if (codi_enferme_curar == 3) {
			for (int i = 0; i < Ciudad_Enfermedad[ciudad_curar].length(); i++) {
				if (Ciudad_Enfermedad[ciudad_curar].charAt(i) == '3') {
					position_ned = i;
				}
			}
		}

		System.out.println(Ciudad_Enfermedad[ciudad_curar].length());
		if (Ciudad_Enfermedad[ciudad_curar].length() >= 3) {
			System.out.println(Ciudad_Enfermedad[ciudad_curar]);
			Ciudad_Enfermedad[ciudad_curar] = Ciudad_Enfermedad[ciudad_curar].substring(0, position_ned)
					+ Ciudad_Enfermedad[ciudad_curar].substring(position_ned + 2);
			System.out.println(Ciudad_Enfermedad[ciudad_curar]);
		} else {
			System.out.println(Ciudad_Enfermedad[ciudad_curar]);
			Ciudad_Enfermedad[ciudad_curar] = Ciudad_Enfermedad[ciudad_curar].substring(position_ned + 2);
			System.out.println(Ciudad_Enfermedad[ciudad_curar]);
		}

		position_ned = 0;
	}
}
