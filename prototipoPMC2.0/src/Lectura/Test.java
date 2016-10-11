import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.simple.*;



public class Test 
{
	static lecturaArchivosTest clase;
	
	public Test()
	{
		 Document doc = new Document((String) clase.darParrafos().get(1));
	        for (Sentence sent : doc.sentences()) {  // Will iterate over two sentences
	        	
	        	List<String> nerTags = sent.nerTags();  // [PERSON, O, Esteban, O, O, O, O, O]
	    		List<String> firstPOSTag = sent.posTags(); // NNP
	    		
	    		System.out.println(nerTags);
	    		System.out.println(firstPOSTag);
	        }
	
	        //Sentence sent = new Sentence("Lucy is Esteban the sky with diamonds.");
	
		
	}
	
	public static void main (String[] args)
	{
		clase = new lecturaArchivosTest();
		new Test();
	}
	
	

	
}
