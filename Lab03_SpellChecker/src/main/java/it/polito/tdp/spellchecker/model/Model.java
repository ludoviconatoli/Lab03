package it.polito.tdp.spellchecker.model;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Model {

	LinkedList<String> dizionario = new LinkedList<>();
	
	public void loadDictionary(String language) {
		try {
			FileReader fr = new FileReader("src/main/resources/styles/"+language+".txt");
			BufferedReader br = new BufferedReader(fr);
			
			String riga;
			while((riga=br.readLine()) != null) {
				this.dizionario.add(riga);
			}
			
			br.close();
			fr.close();
				
		}catch(IOException ioe)
		{
			System.out.println("Errore in lettura file");
		}
	}
	
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
		
		LinkedList<RichWord> rw = new LinkedList<>();
		int count = 0;
		for(String s: inputTextList)
		{
			for(String st: this.dizionario)
			{
				if(st.equals(s))
			    {
			    	count++;
			    	break;
			    }
			}
			if(count == 0)
			{
				rw.add(new RichWord(s));
			}
			count = 0;
		}

		return rw;
	}
	
	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList){
		
		LinkedList<RichWord> rw = new LinkedList<>();
		int tetto = this.dizionario.size();
		int meta = 0;
		int pavimento = 0;
		int count = 0;
		/*for(String s: inputTextList)
		{
			for(int i=meta; i<tetto; i++) {
				if(s.compareTo(this.dizionario.get(i)) == 0)
				{
					count++;
					break;
				}else if(s.compareTo(this.dizionario.get(i)) < 0 ) {
					tetto = meta;
					meta = (meta + pavimento)/2;
					i = meta; 
				}else {
					pavimento = i;
					meta = meta + ((tetto - meta)/2) ;
					i = meta;
				}
			}
			
			if(count == 0)
				rw.add(new RichWord(s));
			
			count = 0;
		}*/
		for(String s: inputTextList)
		{
			while(pavimento <= tetto) {
				meta = (pavimento+tetto)/2;
				if(s.compareTo(this.dizionario.get(meta)) == 0)
				{
					count ++;
					break;
				}else if(s.compareTo(this.dizionario.get(meta)) < 0) {
					tetto = meta-1;
				}else {
					pavimento = meta+1;
				}
					
			}
			if(count == 0)
				rw.add(new RichWord(s));
			
			count = 0;
		}
		return rw;
	}
}
