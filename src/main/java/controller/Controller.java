package main.java.controller;

import main.java.model.Configuration;
import main.java.model.Jeu;
import main.java.model.Partie;

public class Controller {

	private Partie partie;
	private Configuration config;
	private Jeu jeu;


	public Controller(Configuration config, Partie partie,Jeu jeu) {
		this.config =config;
		this.partie = partie;
		this.jeu = jeu;

	}

	public void sendProposition(Partie partie) {
		this.partie = partie;
		jeu.indiceOrdi(partie);
		
	}
	public void sendIndice(Partie partie) {
		this.partie = partie;
		jeu.propOrdi(partie);
	}
}

