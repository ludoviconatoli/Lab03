package it.polito.tdp.spellchecker.model;

import java.util.TreeMap;

public class RichWord {

	String parola;
	boolean corretto;
	
	public RichWord(String parola) {
		this.parola = parola;
		this.corretto=false;
	}
	
	public void isCorrect(TreeMap<String, String> tm) {
		for(String s: tm.values())
			if(this.parola.equals(s))
				this.corretto = true;
	}

	public String getParola() {
		return this.parola;
	}
}
