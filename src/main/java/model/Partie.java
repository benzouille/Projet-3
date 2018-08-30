package main.java.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Java Bean contenant tout ce qui est nécessaire pour jouer une aux jeux.
 * @author Ben
 *
 */
public class Partie {

	//-- Les logs
	private static final Logger logger = LogManager.getLogger();

	private ModeDeJeu modeDeJeu;
	private String joueur, solution, proposition, indice;
	private int tour;
	private boolean enCours = true;

	public Partie() {
		joueur = "vide";
		solution = "vide";
		proposition = "vide";
		indice = "vide";
		modeDeJeu = null;
		tour = 0;
	}

	public Partie(String solution) {
		this.solution = solution;
		joueur = "vide";
		proposition = "vide";
		indice = "vide";
		modeDeJeu = null;
		tour = 0;
	}

	public void ordiPartie(int nbreChiffre) {
		setSolution(random(nbreChiffre));
	}

	/**
	 * Génère une combinaison aléatoire d'une longueur égale à combiPlusMoins de l'objet configuration
	 * @param nbreChiffre
	 * @return random
	 */
	public String random(int nbreChiffre) {
		int [] combi = new int[nbreChiffre];
		for (int i = 0; i<nbreChiffre; i++) {
			combi[i] = (int)(Math.random()*9);
		}
		String random = convertTabIntToString(combi);
		return random;
	}

	/**
	 * Convertit un tableau d'int en string
	 * @param tab
	 * @return
	 */
	public String convertTabIntToString(int [] tab) {
		String str= "";
		for(int i = 0; i<tab.length; i++) {
			str += Integer.toString(tab[i]);
		}
		return str;
	}

	/**
	 * Ajoute un tour au compteur de tour
	 */
	public void addTour() {
		tour += 1;
	}

	/**
	 * Decrit ce que contient le Bean.
	 */
	public String toString() {
		String str = "Partie : [joueur : "+joueur+", solution : "+solution+", proposition : "+proposition+", indice : "+indice
				+", tour : "+tour+", enCours : "+enCours+"]";
		return str;
	}


	public String getJoueur() {return joueur;}
	public void setJoueur(String joueur) {this.joueur = joueur;}

	public String getSolution() {return solution;}
	public void setSolution(String solution) {this.solution = solution;}

	public String getProposition() {return proposition;}
	public void setProposition(String proposition) {this.proposition = proposition;}

	public String getIndice() {return indice;}
	public void setIndice(String indice) {this.indice = indice;}

	public int getTour() {return tour;}
	public void setTour(int tour) {this.tour = tour;}

	public boolean isEnCours() {return enCours;}
	public void setEnCours(boolean enCours) {this.enCours = enCours;}

	public ModeDeJeu getModeDeJeu() {return modeDeJeu;}
	public void setModeDeJeu(ModeDeJeu modeJeu) {this.modeDeJeu = modeJeu;}	
}