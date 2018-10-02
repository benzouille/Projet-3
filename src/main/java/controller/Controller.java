package main.java.controller;

import main.java.model.Configuration;
import main.java.model.Master;
import main.java.model.Model;
import main.java.model.Partie;
import test.avant_implementation.Jeu;

public class Controller {

	private Partie partie;
	private Configuration config;
	private Model model;
	private Jeu jeu;
	//private String solution, proposition, indices;


	public Controller(Configuration config, Partie partie,Model model,Jeu jeu) {
		this.config =config;
		this.partie = partie;
		this.model = model;
		this.jeu = jeu;

	}

	public void sendProposition(Partie partie) {
		this.partie = partie;
		System.out.println("methode sendProposition de Controller");
		//TODO Verifier l'integrité des données
		model.resolve(partie);
		//jeu.indiceOrdi(partie);
		
	}
	public void sendIndice(Partie partie) {
		this.partie = partie;
		System.out.println("methode sendIndice de Controller");
		//TODO Verifier l'integrité des données
		model.propositionOrdinateur(partie);
		//jeu.propOrdi(partie);
	}
}
