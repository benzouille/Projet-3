package test;

import main.java.model.Configuration;
import main.java.model.Partie;

public class MastProp {
	
	private Partie partie;
	private Configuration config;
	
	public MastProp() {
		/*
		 * TODO renvoyer des des propositions suite aux indices donnée :
		 * en premier lieu
		 */
		
	}
	
	public void initProposition() {
		if(partie.getIndice() == "vide") {
			String proposition = "";
			for (int i = 0; i<config.getCombiMast(); i++) {
				proposition += '5';
			}
			this.partie.setProposition(proposition);
		}
	}
	
	public String proposition(String indice) {
		String proposition = "";
		return proposition;
	}
}
