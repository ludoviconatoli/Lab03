package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Model;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

	private Model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label txtChoose;

    @FXML
    private ComboBox<String> tendina;

    @FXML
    private TextArea txtTesto;

    @FXML
    private Button btSpellCheck;

    @FXML
    private Label txtWrong;

    @FXML
    private TextArea txtRisultatoControllo;

    @FXML
    private Label txtResult;

    @FXML
    private Button btClearText;

    @FXML
    private TextArea txtTime;

    @FXML
    void handleClear(ActionEvent event) {
    	this.txtResult.setText("");
    	this.txtRisultatoControllo.clear();
    	this.txtTesto.clear();
    	this.txtTime.clear();
    }

    @FXML
    void handleControlla(ActionEvent event) {
    	
    	this.txtRisultatoControllo.setText("");
    	this.model.loadDictionary(this.tendina.getValue());
    	Long l = System.currentTimeMillis();
    	
    	LinkedList<String> testo = new LinkedList<>();
    	String[] s = this.txtTesto.getText().split(" ");
        for(int i=0; i<s.length; i++)
        {
        	s[i] = s[i].toLowerCase();
        	s[i] = s[i].replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
        }
        
        for(String str: s)
        {
        	testo.add(str);
        }
        
        LinkedList<RichWord> rw = new LinkedList<>();
        rw.addAll(this.model.spellCheckTextDichotomic(testo));
        
        if(rw.isEmpty())
        {
        	this.txtTime.setText("L'operazione è stata completata in "+Long.toString(System.currentTimeMillis() - l) + " millisecondi");
        	this.txtResult.setText("Non hai inserito parole sbagliate");
        	return;
        }
        
        for(RichWord p: rw)
        	this.txtRisultatoControllo.appendText(p.getParola()+"\n");
        
        this.txtResult.setText("Il testo contiene "+ rw.size() + " errori");
    	this.txtTime.setText("L'operazione è stata completata in "+Long.toString(System.currentTimeMillis() - l) + " millisecondi");
    	return;
    }

    @FXML
    void initialize() {
        assert txtChoose != null : "fx:id=\"txtChoose\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tendina != null : "fx:id=\"tendina\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btSpellCheck != null : "fx:id=\"btSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtWrong != null : "fx:id=\"txtWrong\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultatoControllo != null : "fx:id=\"txtRisultatoControllo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btClearText != null : "fx:id=\"btClearText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model m) {
    	this.model= m;
    	this.tendina.getItems().addAll("English", "Italian");
    }
}

