package test.avant_implementation;

import main.java.model.Configuration;
import main.java.model.Model;
import main.java.model.Partie;

public class ControllerV2 {

	private Partie partie;
	private Configuration config;
	private Jeu jeu;


	public ControllerV2(Configuration config, Partie partie,Jeu jeu) {
		this.config =config;
		this.partie = partie;
		this.jeu = jeu;

	}

	public void sendProposition(Partie partie) {
		this.partie = partie;
		System.out.println("methode sendProposition de Controller");
		//TODO Verifier l'integrité des données
		jeu.indiceOrdi(partie);
		
	}
	public void sendIndice(Partie partie) {
		this.partie = partie;
		System.out.println("methode sendIndice de Controller");
		//TODO Verifier l'integrité des données
		jeu.propOrdi(partie);
	}
}

