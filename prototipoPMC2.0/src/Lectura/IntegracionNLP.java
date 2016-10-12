package Lectura;

import java.util.List;

import edu.stanford.nlp.simple.*;

public class IntegracionNLP {
	static ArchivosTxt clase;

	public IntegracionNLP() {

	}
	
	public List<String> reconocerGramatica(String frase)
	{
		
		
		Sentence sen = new Sentence(frase);
		
		List<String> firstPOSTag = sen.posTags();
		
//		Document doc = new Document(frase);
//		for (Sentence sent : doc.sentences()) { // Will iterate over two
//												// sentences
//
//			List<String> nerTags = sent.nerTags(); // [PERSON, O, Esteban, O, O,
//													// O, O, O]
//			List<String> firstPOSTag = sent.posTags(); // NNP
//
//			System.out.println(nerTags);
//			System.out.println(firstPOSTag);
//		}
		return firstPOSTag;
	}

}
