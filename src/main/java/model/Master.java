package main.java.model;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.observer.Observable;
import main.java.observer.Observateur;
import main.java.view.game.PopUpFinPartie;

public class Master implements Observable {

	//-- Les logs
	private static final Logger logger = LogManager.getLogger();

	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
	private Observateur obs;

	private Partie partie;
	private Configuration config;
	private String solution, proposition, indices;

	//TODO le master propose la solution et donne les indications pour la proposition
	
	public Master(Configuration config, Partie partie, Observateur obs) {
		this.obs = obs;
		this.config = config;
		this.partie = partie;
		this.addObservateur(obs);
		if (partie.getModeDePartie().equals(ModeDePartie.PLUS_CHAL)) {
			this.partie.ordiPartie(config.getCombiPlusMoins());
		}
	}

	/**
	 * Compare les differences entre la proposition et la solution puis modifie dans l'objet partie l'indices avec les signes + - =
	 * @param partie
	 * @return partie 
	 */
	public void resolve(Partie partie) {
		System.out.println("resolve() de Master");
		this.partie = partie;
		solution = partie.getSolution();
		proposition = partie.getProposition();
		Integer [] propositionTab = new Integer[config.getCombiPlusMoins()];
		Integer [] solutionTab = new Integer[config.getCombiPlusMoins()];
		for (int i = 0; i<config.getCombiPlusMoins(); i++) {
			propositionTab[i] = Integer.valueOf(proposition.substring(i, i+1));
			solutionTab[i] = Integer.valueOf(solution.substring(i, i+1));
		}
		indices = "";
		for (int i = 0; i<config.getCombiPlusMoins(); i++) {
			if(propositionTab[i] < solutionTab[i]) {
				indices += "+";
			}
			else if(propositionTab[i] > solutionTab[i]) {
				indices += "-";
			}	
			else {
				indices += "=";
			}
		}
		this.partie.setIndice(indices);
		this.partie.addTour();
		endGame();
		logger.debug(partie.toString());
		updateObservateur();
	}

	/**
	 * Verifie si l'objet partie correspond aux conditions de victoire ou défaite
	 * @return partie
	 */
	private Partie endGame() {
		System.out.println("endGame() de Master");
		int prop = Integer.parseInt(proposition);
		int sol = Integer.parseInt(solution);
		if (sol == prop) {
			partie.setEnCours(false);
			@SuppressWarnings("unused")
			PopUpFinPartie pufp = new PopUpFinPartie(null, "Gagné", true, obs);
		}
		else {
			if(partie.getTour() <= config.getTourPlusMoins()) {
			}
			else {
				partie.setEnCours(false);
				@SuppressWarnings("unused")
				PopUpFinPartie pufp = new PopUpFinPartie(null, "Perdu", true, obs);
			}
		}
		return partie;
	}
	
	public void addObservateur(Observateur obs) {
		listObservateur.add(obs);
	}

	public void updateObservateur() {
		for(Observateur obs : listObservateur)
			System.out.println("updateObservateur() de Master");
			obs.update(partie);
		
	}

	public void delObservateur() {}
}
