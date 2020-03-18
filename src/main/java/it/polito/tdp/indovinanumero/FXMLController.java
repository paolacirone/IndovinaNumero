package it.polito.tdp.indovinanumero;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.indovinanumero.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {

	// nel controller avrò sicuramente un riferimento al mio modello

	private Model model;

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

		this.model.NuovaPartita();
		
		// gestione dell'interfaccia
		layoutProva.setDisable(false);
		textRisultato.clear(); // elimino i commenti precedenti
		textTentativi.setText(Integer.toString(this.model.getTMAX()));

	}

	@FXML
	void doProva(ActionEvent event) {

		// leggere l'input dell'utente
		String ts = textProva.getText();
		// CONTROLLARE SEMPRE L'INPUT
		int tentativo;
		try {
			tentativo = Integer.parseInt(ts);
		} catch (NumberFormatException e) {
			textRisultato.setText("Inserire un numero");
			return;
		}

		int risultato; 
		try{
        	risultato= this.model.tentativo(tentativo);
        } catch(IllegalStateException se){
        	textRisultato.appendText(se.getMessage());
        	return;
        }catch (InvalidParameterException pe) {
        	textRisultato.appendText(pe.getMessage());
        	return;
        }
		
		if (risultato==0) {
			// HO INDOVINATO
			textRisultato.setText("HAI VINTO!!! \n Hai utilizzato " + this.model.getTentativiFatti() + " tentativi");
			//layoutProva.setDisable(true);
			//return;
		}
		/*
		if (risultato==) {
			// TENTATIVI ESAURITI
			textRisultato.setText(
					"HAI PERSO!!! \n Hai utilizzato tutti i tentativi\n Il numero da indovinare era: " + segreto);
			layoutProva.setDisable(true);
			this.inGioco = false;
			return;
		}*/
		// devo informare l'utente se il tentativo è troppo alto o basso
		if (risultato==-1) {
			// textRisultato.clear();
			textRisultato.appendText("Tentativo troppo basso! RIPROVA\n");

		} else {
			// textRisultato.clear();
			textRisultato.appendText("Tentativo troppo alto! RIPROVA\n");

		}

		textTentativi.setText(Integer.toString(this.model.getTMAX() - this.model.getTentativiFatti()));
	}

	@FXML
	void initialize() {
		assert textRisultato != null : "fx:id=\"textRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
		assert buttonNuova != null : "fx:id=\"buttonNuova\" was not injected: check your FXML file 'Scene.fxml'.";
		assert textTentativi != null : "fx:id=\"textTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert layoutProva != null : "fx:id=\"layoutProva\" was not injected: check your FXML file 'Scene.fxml'.";
		assert textProva != null : "fx:id=\"textProva\" was not injected: check your FXML file 'Scene.fxml'.";
		assert buttonProva != null : "fx:id=\"buttonProva\" was not injected: check your FXML file 'Scene.fxml'.";

		this.model = new Model();
	}

	public void setModel(Model model) {
		this.model = model;
	}
}
