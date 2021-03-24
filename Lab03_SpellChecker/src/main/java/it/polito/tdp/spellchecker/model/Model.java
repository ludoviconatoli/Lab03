package it.polito.tdp.spellchecker.model;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Model {

	TreeMap<String, String> dizionario = new TreeMap<>();
	
	public void loadDictionary(String language) {
		try {
			FileReader fr = new FileReader("src/main/resources/styles/"+language+".txt");
			BufferedReader br = new BufferedReader(fr);
			
			String riga;
			while((riga=br.readLine()) != null) {
				this.dizionario.put(riga, riga);
			}
			
			br.close();
			fr.close();
				
		}catch(IOException ioe)
		{
			System.out.println("Errore in lettura file");
		}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		
		LinkedList<RichWord> rw = new LinkedList<>();
		for(String s: inputTextList)
			if(this.dizionario.get(s) == null)
				rw.add(new RichWord(s));

		return rw;
	}
}
