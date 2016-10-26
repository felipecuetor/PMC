package Lectura;

import java.util.Collection;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.simple.*;
import edu.stanford.nlp.util.CoreMap;

public class IntegracionNLP {
	static ArchivosTxt clase;

	public IntegracionNLP() {

	}

	public List<String> reconocerGramatica(String frase) {
		try {
			//System.out.println(frase);
			Sentence sen = new Sentence(frase);

			List<String> firstPOSTag = sen.posTags();
			//System.out.println(firstPOSTag);

//			Document doc = new Document(frase);
			// for (Sentence sent : doc.sentences()) { // Will iterate over two
			// // sentences
			//
			// List<String> nerTags = sent.nerTags(); // [PERSON, O, Esteban, O,
			// O,
			// // O, O, O]
			// List<String> firstPOSTag = sent.posTags(); // NNP
			//
			// System.out.println(nerTags);
			// System.out.println(firstPOSTag);
			// }
			
		    Properties props = new Properties();
		    props.setProperty("annotators", "tokenize,ssplit,pos,lemma,depparse,natlog,openie");
		    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		    // Annotate an example document.
		    Annotation doc = new Annotation(frase);
		    pipeline.annotate(doc);
			for (CoreMap sentence : doc
					.get(CoreAnnotations.SentencesAnnotation.class)) {
				// Get the OpenIE triples for the sentence
				Collection<RelationTriple> triples = sentence
						.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class);
				// Print the triples
				for (RelationTriple triple : triples) {
					System.out.println(triple.confidence + "\t"
							+ triple.subjectLemmaGloss() + "\t"
							+ triple.relationLemmaGloss() + "\t"
							+ triple.objectLemmaGloss());
				}
			}

			return firstPOSTag;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
