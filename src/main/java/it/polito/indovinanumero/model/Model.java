package it.polito.indovinanumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {

	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;

	private Set<Integer> tentativi;
	
	public Model() {
		// quando parte l'applicazione non sono in gioco
		this.inGioco = false;
		this.tentativiFatti = 0;

	}
	
	public void NuovaPartita() {
		//fa quello previsto dal bottone
		//riporto solo la logica del gioco e non dell'interfaccia
		this.segreto= (int)(Math.random()*NMAX+1);    //ritona un numero compreso tra 0 e il max(escluso)
    	this.tentativiFatti=0; 
    	this.inGioco=true; 
    	
    	//ogni volta che inizio una partita creo un nuovo contenitore
    	this.tentativi = new HashSet<Integer>(); 
    	//hash set utile quando si vuole controllare se in un contenitore
    	//c'è un determinato elemento
    	
    	
	}
	
	
	
	public int tentativo(int tentativo) {
		//controllo se la partita è effettivamente in corso 
		if(!inGioco) {
			throw new IllegalStateException("La partita è già terminata"); //stato illegale del sistema, la partita è già terminata
		}
		
		//controllo l'input
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inserire un numero che non hai ancora utilizzato tra 1 e "+NMAX);
		}
		
		//tentativo valido, possiamo provarlo
		this.tentativiFatti++;
		this.tentativi.add(tentativo); 
		
		
		if(this.tentativiFatti== TMAX) {
			this.inGioco=false; //la partita è terminata
		}
		
		if (tentativo == segreto) {
			this.inGioco = false;
			return 0; //ritorno 0 se ho vinto
		}
		
		if (tentativo < this.segreto) {
			return -1;
		} else {
			return 1;
		}
		
	}
	
	public boolean tentativoValido(int tentativo) {
		
		if(tentativo <1 || tentativo>NMAX) {
			return false;
		} else {
			if(this.tentativi.contains(tentativo)) {
				return false;
			}
			return true;
		}
		
	}

	public int getSegreto() {
		return segreto;
	}

	public void setSegreto(int segreto) {
		this.segreto = segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public void setTentativiFatti(int tentativiFatti) {
		this.tentativiFatti = tentativiFatti;
	}

	public boolean isInGioco() {
		return inGioco;
	}

	public void setInGioco(boolean inGioco) {
		this.inGioco = inGioco;
	}

	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	
	
	
	
}
