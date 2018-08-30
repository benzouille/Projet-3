package main.java.controller;

import main.java.model.Configuration;
import main.java.model.Model;
import main.java.model.Partie;

public class Controller {

	private Partie partie;
	private Configuration config;
	private Model model;
	//private String solution, proposition, indices;


	public Controller(Configuration config, Partie partie,Model model) {
		this.config =config;
		this.partie = partie;
		this.model = model;

	}

	public void sendProposition(Partie partie) {
		this.partie = partie;
		//TODO Verifier l'integrité des données
		model.resolve(partie);

	}
	public void sendIndice(Partie partie) {
		this.partie = partie;
		//TODO Verifier l'integrité des données
		model.propositionOrdinateur(partie);
	}
}
