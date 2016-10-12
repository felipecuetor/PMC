package Lectura;

import java.awt.List;
import java.io.*;
import java.util.List;

public class ArchivosTxt {

	private File archivo;
	private File parafos;
	private IntegracionNLP nlp;

	public ArchivosTxt() {
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
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(parafos)));
		
		System.out.println(".");
		
		FileInputStream fi = new FileInputStream(archivo);

		BufferedReader br = new BufferedReader(new InputStreamReader(fi));

		String linea = br.readLine();
		String parafo = "";

		while (linea != null) {
			

			if (!linea.equals("")) {
				parafo += linea;
				linea = br.readLine();
			}
			else if(parafo.endsWith(". "))
			{
				parafo = parafo.replaceAll("- ", "");
//				System.out.println(parafo);
				bw.write(parafo+"\n");
				
				procesarParafo(parafo);				
				
				parafo = "";
				linea = br.readLine();
				
			}
			else
			{
				int cont = 0;
				while(linea.equals(""))
				{
					cont++;
					linea = br.readLine();
				}
				
				if(cont>1)
				{
					linea =br.readLine();
				}
			}
		}
		
		System.out.println("Cerrando");
		br.close();
	}

	private void procesarParafo(String parafo) {
		String[] frases = parafo.split(".");
		
		for(String frase:frases)
		{
			System.out.println(frase);
			java.util.List<String> recon = nlp.reconocerGramatica(frase);			
		}
		
	}
}
