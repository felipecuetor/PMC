package Lectura;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArchivosTxt {

	private File archivo;
	private File parafos;
	private IntegracionNLP nlp;

	private ArrayList<String> sujetos;
	private ArrayList<String> verbos;
	private ArrayList<String> predicados;

	public ArchivosTxt() {
		sujetos = new ArrayList<String>();
		verbos = new ArrayList<String>();
		predicados = new ArrayList<String>();

		archivo = new File("./data/The origin of species.txt");
		parafos = new File("./data/output.txt");
		nlp = new IntegracionNLP();
		try {
			leer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void leer() throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(parafos)));

		System.out.println(".");

		FileInputStream fi = new FileInputStream(archivo);

		BufferedReader br = new BufferedReader(new InputStreamReader(fi));

		String linea = br.readLine();
		String parafo = "";

		System.out.println("Iniciando iteracion de lineas");
		while (linea != null) {

			if (!linea.equals("")) {
				parafo += linea;
				linea = br.readLine();
			} else if (parafo.endsWith(". ")) {
				parafo = parafo.replaceAll("- ", "");
				parafo = parafo.replaceAll("'", " ");
				bw.write(parafo + "\n");

				procesarParafo(parafo);

				parafo = "";
				linea = br.readLine();

			} else {
				int cont = 0;
				while (linea.equals("")) {
					cont++;
					linea = br.readLine();
				}

				if (cont > 1) {
					linea = br.readLine();
				}
			}
		}

		System.out.println("Cerrando");
		br.close();
	}

	private void procesarParafo(String parafo) {
		System.out.println("Procesando parafo");
		String[] frases = parafo.split("\\. ");
		System.out.println(frases.length);

		for (String frase : frases) {

			List<String> recon = nlp.reconocerGramatica(frase + ".");

			String[] palabras = frase.split(" ");

			String anterior = "";
			String posterior = "";
			String verbo = "";
			int cont = 0;
			boolean vb = false;

			String palabraActual = palabras[cont];

			for (String actual : recon) {
				System.out.println(actual + palabraActual);
				try {
					if (actual.contains(",") | actual.contains("`")
							| actual.isEmpty() | actual.equals(" ")
							| actual.contains(".")) {

					} else if (actual.contains("VB")) {
						verbo = palabraActual;
						vb = true;

						cont++;
						palabraActual = palabras[cont];
					} else if (!vb) {
						anterior += " "+palabraActual;

						cont++;
						palabraActual = palabras[cont];
					} else {
						posterior += " "+palabraActual;

						cont++;
						palabraActual = " "+palabras[cont];
					}
				} catch (Exception e) {

				}

			}

			posterior+=". ";
			sujetos.add(anterior);
			verbos.add(verbo);
			predicados.add(posterior);

			System.out.println(anterior + verbo + posterior);
		}
	}

	public String responder(String pregunta) {
		pregunta = pregunta.replace("?", "");
		System.out.println();
		pregunta = pregunta.toLowerCase();
		String[] split = pregunta.split(" is");

		// Sujeto y predicado
		boolean sp = false;
		Iterator iter = sujetos.iterator();
		ArrayList indices = new ArrayList();
		int cont = -1;
		while (iter.hasNext()) {
			String actual = (String) iter.next();
			cont++;

			if (actual.contains(split[1])) {
				indices.add(cont);
				sp = true;
			}
		}

		iter = indices.iterator();
		String resp = split[1] + " is";
		boolean and = false;
		System.out.println(indices.size());
		while (iter.hasNext()) {
			int actual = (Integer) iter.next();
			resp += sujetos.get(actual)+verbos.get(actual)+predicados.get(actual);
		}

		// Predicado y sujeto
		if (!sp) {
			resp = "";
			iter = predicados.iterator();
			indices = new ArrayList();
			cont = -1;
			while (iter.hasNext()) {
				String actual = (String) iter.next();
				cont++;

				if (actual.contains(split[1])) {
					indices.add(cont);
				}
			}

			iter = indices.iterator();
			and = false;
			System.out.println(indices.size());
			while (iter.hasNext()) {
				int actual = (Integer) iter.next();
				resp += sujetos.get(actual)+verbos.get(actual)+predicados.get(actual);
			}
		}

		System.out.println();
		return resp;
	}
}
