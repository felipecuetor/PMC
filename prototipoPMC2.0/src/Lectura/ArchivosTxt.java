package Lectura;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import estructuras.GrafoDirigido;
import estructuras.Vertice;

public class ArchivosTxt {

	private File archivo;
	private File parafos;
	private IntegracionNLP nlp;

	private ArrayList<String> sujetos;
	private ArrayList<String> verbos;
	private ArrayList<String> predicados;

	private GrafoDirigido<String, ContenedorVertice, ContenedorArco> mapa;
	private List<CoreMap> sentences;

	public ArchivosTxt() {
		sujetos = new ArrayList<String>();
		verbos = new ArrayList<String>();
		predicados = new ArrayList<String>();

		mapa = new GrafoDirigido();

		archivo = new File("./data/d.txt");
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

		System.out.println("Iniciando lectura");
		FileInputStream fi = new FileInputStream(archivo);
		BufferedReader br = new BufferedReader(new InputStreamReader(fi));
		String linea = br.readLine();
		String parafo = "";
		while (linea != null) {

			if (!linea.equals("")) {
				parafo += linea;
				linea = br.readLine();
			} else if (parafo.endsWith(". ")) {
				parafo = parafo.replaceAll("- ", "");
				// parafo = parafo.replaceAll("— ", "");
				// parafo = parafo.replaceAll("'", " ");
				// parafo = parafo.replaceAll(";", "");
				// parafo = parafo.replaceAll(" :", "");
				// parafo = parafo.replaceAll("\"", "");
				bw.write(parafo + "\n");

				// procesarParafo(parafo);

				System.out.print("-");
				reconocerParafo(parafo);
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

		for (String frase : frases) {

			List<String> recon = null;
			recon = nlp.reconocerGramatica(frase + ".");

			String[] palabras = frase.split(" ");

			String anterior = "";
			String posterior = "";
			String verbo = "";
			int cont = 0;
			boolean vb = false;

			String palabraActual = palabras[cont];

			for (String actual : recon) {
				try {
					if (actual.contains(",") | actual.contains("`")
							| actual.isEmpty() | actual.equals(" ")
							| actual.contains(".")) {

					} else if (!vb && actual.contains("VB")) {
						verbo = palabraActual;
						vb = true;

						cont++;
						palabraActual = palabras[cont];
					}

				} catch (Exception e) {
				}
			}

			try {
				String[] partes = frase.split(verbo);
				anterior = partes[0];
				posterior = partes[1];
			} catch (Exception e) {

			}

			posterior += ". ";
			sujetos.add(anterior);
			verbos.add(verbo);
			predicados.add(posterior);
		}
	}

	private void reconocerParafo(String text) {

		Properties props = new Properties();
		props.put("annotators", "tokenize, ssplit, pos, lemma, ner");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		Annotation document = new Annotation(text);

		// run all Annotators on this text
		pipeline.annotate(document);

		// these are all the sentences in this document
		// a CoreMap is essentially a Map that uses class objects as keys
		// and has values with custom types
		sentences = document.get(SentencesAnnotation.class);

		for (CoreMap sentence : sentences) {
			// traversing the words in the current sentence
			// a CoreLabel is a CoreMap with additional token-specific
			// methods
			procesarFrase(sentence);

		}

	}

	private void procesarFrase(CoreMap sentence) {
		// traversing the words in the current sentence
		// a CoreLabel is a CoreMap with additional token-specific
		// methods
		String anterior = "";
		String posterior = "";
		String verbo = "";

		for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
			// this is the text of the token
			String word = token.get(TextAnnotation.class);
			// this is the POS tag of the token
			String pos = token.get(PartOfSpeechAnnotation.class);

			if (pos.contains("VB")) {
				verbo = word;
			} else if (verbo.equals("")) {
				anterior += word + " ";
			} else {
				posterior += word + " ";
			}

			if (pos.equals(".") | pos.equals(",") | pos.equals(":")
					| pos.equals(";")) {
				if (!verbo.equals("")) {
					System.out.println(anterior + verbo + posterior);
					ContenedorArco contVerbo = new ContenedorArco(verbo);
					ContenedorVertice contAnterior = new ContenedorVertice(
							anterior);
					ContenedorVertice contPosterior = new ContenedorVertice(
							posterior);
					try {
						mapa.agregarVertice(contAnterior);
						mapa.agregarVertice(contPosterior);
						mapa.agregarArco(contAnterior.darId(),
								contPosterior.darId(), contVerbo);
					} catch (Exception e) {
//						e.printStackTrace();
					}

				}
				anterior = "";
				posterior = "";
				verbo = "";
			}

		}
	}

	public String[] responder(String pregunta) {
		String[] resp = new String[3];

		Properties props = new Properties();
		props.put("annotators", "tokenize, ssplit, pos, lemma, ner");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		Annotation document = new Annotation(pregunta);

		// run all Annotators on this text
		pipeline.annotate(document);

		// these are all the sentences in this document
		// a CoreMap is essentially a Map that uses class objects as keys
		// and has values with custom types
		sentences = document.get(SentencesAnnotation.class);

		for (CoreMap sentence : sentences) {
			// traversing the words in the current sentence
			// a CoreLabel is a CoreMap with additional token-specific
			// methods
			String verbo = "";
			String sujeto = "";

			for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
				// this is the text of the token
				String word = token.get(TextAnnotation.class);
				// this is the POS tag of the token
				String pos = token.get(PartOfSpeechAnnotation.class);

				if (pos.contains("VB")) {
					verbo = word;
				} else if (pos.contains("NN")) {
					sujeto = word;
				}
			}
			
			resp[1]=sujeto;
			resp[2]=verbo;
			
			System.out.println(verbo+sujeto  );

			ArrayList<Vertice> vertices = mapa.bucarTextoEnVertices(sujeto);
			Iterator iter = vertices.iterator();

			while (iter.hasNext()) {
				Vertice actual = (Vertice) iter.next();
				String resFrase = "";
				try {
					resFrase = actual.darSucesorPorArco(verbo).toString();
				} catch (Exception e) {
					//e.printStackTrace();
				}
				if(!resFrase.equals(""))
				{
					resp[0] += actual.toString() + verbo + resFrase+"\n"+"\n";
				}
				
				System.out.println(actual.toString() + verbo + resFrase);
			}
		}

		
		return resp;

	}

	public String responder1(String pregunta) {
		pregunta = pregunta.replace("?", "");
		pregunta = pregunta.toLowerCase();
		String[] split = pregunta.split(" about");

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
		String resp = "";
		boolean and = false;
		// while (iter.hasNext()) {
		// int actual = (Integer) iter.next();
		// resp += sujetos.get(actual) + verbos.get(actual)
		// + predicados.get(actual);
		// }

		// Predicado y sujeto
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
		while (iter.hasNext()) {
			int actual = (Integer) iter.next();
			resp += sujetos.get(actual) + verbos.get(actual)
					+ predicados.get(actual) + "\n";
		}

		return resp;
	}
}
