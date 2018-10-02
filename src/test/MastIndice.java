package test;

import main.java.model.Configuration;
import main.java.model.Partie;

public class MastIndice {

	private Partie partie;
	private Configuration config;
	private String proposition, solution, indices;
	
	public MastIndice() {
		/*
		 * TODO renvoyer des indices suite à une proposition :
		 * si bon chiffre mais pas à la bonne place indice blanc
		 * si bon chiffre à la bonne place indice noir
		 */
		config = new Configuration();
		//partie.setSolution("012345");
		//partie.setProposition("040000");
	}
	
	/**
	 * Compare les differences entre la proposition et la solution puis modifie dans l'objet partie l'indices avec les couleur noir et blanc
	 * @param partie
	 * @return partie 
	 */
	public String resolve(Partie partie) {
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
			if(propositionTab[i] == solutionTab[i]) {
				indices += "Noir";
			}
			
				//TODO comparer propositionTab[i] a solutionTab[y]

			
		}
		this.partie.setIndice(indices);
		this.partie.addTour();
		return indices;
	}
	
	public static void main(String[] args) {
		Partie partie = new Partie();
		partie.setSolution("012345");
		partie.setProposition("040000");
		MastIndice mastIndice = new MastIndice();
		
		System.out.println(mastIndice.resolve(partie));
	}
}
