package main.java.model;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.observer.Observable;
import main.java.observer.Observateur;
import main.java.view.game.PopUpFinPartie;

/**
 * Classe du model permetant de comparer les proposition et solutions ainsi que les conditions de victoire et défaite.
 * @author Ben
 *
 */
public class Model implements Observable {

	//-- Les logs
	private static final Logger logger = LogManager.getLogger();

	protected ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
	private Observateur obs;
	private Configuration config;
	private Partie partie;
	private String solution, proposition, indices;
	private float intervaleMax[], intervaleMin[];

	/**
	 * Constructeur du model, il récupère les paramètres fourni par le controlleur
	 * @param config
	 * @param partie
	 * @param obs
	 */
	public Model(Configuration config, Partie partie, Observateur obs) {
		/*
		 * TODO METTRE LES MEHODES DU MASTER ET DU JOUEUR DANS LES CLASSES RESPECTIVES
		 * pour le mode duel instancier deux Model heritant de master et joueur
		 * tableau de int pour la combinaison
		 * 			si humain mettre le string dans le JFormattedTextField (le OkButton du ModelGamePanel ne fera que l'ajouter au tableau)
		 * 			si ordi mettre le string dans le Tableau puis resolution immediate
		 */
		this.obs = obs;
		this.config = config;
		this.partie = partie;
		this.addObservateur(obs);

	}

	/**
	 * Compare les differences entre la proposition et la solution puis modifie dans l'objet partie l'indices avec les signes + - =
	 * @param partie
	 * @return partie 
	 */
	public Partie resolve(Partie partie) {
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
		return this.partie;
	}

	public void initOrdinateur() {
		
		if (partie.getIndice() == "vide") {
			String proposition = "";
			for (int i = 0; i<config.getCombiPlusMoins(); i++) {
				proposition += '5';
			}
			this.partie.setProposition(proposition);
		}
		intervaleMax = new float[config.getCombiPlusMoins()];
		intervaleMin = new float[config.getCombiPlusMoins()];
		for (int i = 0; i<config.getCombiPlusMoins(); i++) {
			intervaleMax[i] =9.0f;
			intervaleMin[i] =0.0f;
		}
	}

	/**
	 * Methode permettant que l'ordinateur donne des proposition en fonction des indices et de la proposition précédament donnés
	 * @param partie
	 * @return 
	 */
	public String propositionOrdinateur(Partie partie) {
		Integer [] propositionTab = new Integer[config.getCombiPlusMoins()];
		char [] indiceTab = new char[config.getCombiPlusMoins()];
		String nouvelleProp = "";
		
		for (int i = 0; i<config.getCombiPlusMoins(); i++) {
			propositionTab[i] = Integer.valueOf(partie.getProposition().substring(i, i+1));
			System.out.print("proposition à l'enplacement "+i+" : "+propositionTab[i]+". ");
			indiceTab[i] = partie.getIndice().charAt(i);
			System.out.println(" indice à l'enplacement "+i+" : "+indiceTab[i]);
		}
		for(int i =0; i<partie.getSolution().length(); i++) {
			if(indiceTab[i] == '+') {
				this.intervaleMin[i] = propositionTab[i];
				nouvelleProp += propositionTab[i]+Math.round((intervaleMax[i]-intervaleMin[i])/2);
				//System.out.println(" nouvelle prop à l'enplacement "+i+" : "+nouvelleProp+" et interMax : "+intervaleMax[i]+". interMin : "+intervaleMin[i]);
			}
			else if(indiceTab[i] == '-') {
				this.intervaleMax[i] = propositionTab[i];
				nouvelleProp += propositionTab[i]-Math.round((intervaleMax[i]-intervaleMin[i])/2);
				//System.out.println(" nouvelle prop à l'enplacement "+i+" : "+nouvelleProp+" et interMax : "+intervaleMax[i]+". interMin : "+intervaleMin[i]);
			}
			else {
				nouvelleProp += propositionTab[i];
				//System.out.println(" nouvelle prop à l'enplacement "+i+" : "+nouvelleProp+" et interMax : "+intervaleMax[i]+". interMin : "+intervaleMin[i]);
			}
		}
		logger.debug(" nouvelle proposition et l'intervale Max et min", nouvelleProp, intervaleMax, intervaleMin);
		this.partie.setProposition(nouvelleProp);
		return nouvelleProp;
	}

	/**
	 * Verifie si l'objet partie correspond aux conditions de victoire ou défaite
	 * @return partie
	 */
	private Partie endGame() {
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
		this.updateObservateur();
	}

	public void updateObservateur() {
		for(Observateur obs : listObservateur)
			obs.update(partie);
	}

	public void delObservateur() {}
}