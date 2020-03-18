package it.polito.tdp.indovinanumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private  final int NMAX=100; 
	private  final int TMAX=8; 
	private int segreto; 
	private int tentativiFatti; 
	private boolean inGioco=false;
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea textRisultato;

    @FXML
    private Button buttonNuova;

    @FXML
    private TextField textTentativi;

    @FXML
    private HBox layoutProva;

    @FXML
    private TextField textProva;

    @FXML
    private Button buttonProva;

    @FXML
    void doNuovaPartita(ActionEvent event) {
      //gestione dell'inizio di una nuova partita
    	this.segreto= (int)(Math.random()*NMAX+1);    //ritona un numero compreso tra 0 e il max(escluso)
    	this.tentativiFatti=0; 
    	this.inGioco=true; 
    	
    	
    	//gestione dell'interfaccia 
    	layoutProva.setDisable(false);
    	textRisultato.clear(); //elimino i commenti precedenti
    	textTentativi.setText(Integer.toString(TMAX));
    	
    	
    	
    }

    @FXML
    void doProva(ActionEvent event) {

    	//leggere l'input dell'utente
    	String ts= textProva.getText(); 
    	//CONTROLLARE SEMPRE L'INPUT
    	int tentativo;
    	try {
    	 tentativo = Integer.parseInt(ts); 
    	} catch (NumberFormatException e) {
    		textRisultato.setText("Inserire un numero");
    		return;
    	}
    	
    	tentativiFatti++;
    	
    	if(tentativo==segreto) {
    		//HO INDOVINATO
    		textRisultato.setText("HAI VINTO!!! \n Hai utilizzato "+this.tentativiFatti+ " tentativi");
    		layoutProva.setDisable(true);
    		this.inGioco=false;
    		return;
    	}
    	if(tentativiFatti==TMAX) {
    		//TENTATIVI ESAURITI
    		textRisultato.setText("HAI PERSO!!! \n Hai utilizzato tutti i tentativi\n Il numero da indovinare era: "+segreto);
    		layoutProva.setDisable(true);
    		this.inGioco=false;
    		return;
    	}
    	//devo informare l'utente se il tentativo è troppo alto o basso
    	if(tentativo<this.segreto) {
    		//textRisultato.clear();
    		textRisultato.appendText("Tentativo troppo basso! RIPROVA\n");
    		
    	} else {
    		//textRisultato.clear();
    		textRisultato.appendText("Tentativo troppo alto! RIPROVA\n");
    		
    	}
    	
    	textTentativi.setText(Integer.toString(TMAX-tentativiFatti));
    }

    @FXML
    void initialize() {
        assert textRisultato != null : "fx:id=\"textRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert buttonNuova != null : "fx:id=\"buttonNuova\" was not injected: check your FXML file 'Scene.fxml'.";
        assert textTentativi != null : "fx:id=\"textTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert layoutProva != null : "fx:id=\"layoutProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert textProva != null : "fx:id=\"textProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert buttonProva != null : "fx:id=\"buttonProva\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
