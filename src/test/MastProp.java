package test;

import java.util.ArrayList;

import main.java.model.Configuration;
import main.java.model.Partie;
import main.java.model.mastermind.TypeCouleur;

public class MastProp {

	private TypeCouleur typeCouleur;
	private Partie partie;
	private Configuration configuration;

	private ArrayList<TypeCouleur> solution = new ArrayList<TypeCouleur>();
	private ArrayList<TypeCouleur> indices = new ArrayList<TypeCouleur>();
	private ArrayList<TypeCouleur> proposition = new ArrayList<TypeCouleur>();
	private ArrayList<TypeCouleur> couleur = new ArrayList<TypeCouleur>();
	private ArrayList<TypeCouleur> poubelle = new ArrayList<TypeCouleur>();

	public MastProp(Configuration configuration, Partie partie) {
		this.partie = partie;
		this.configuration = configuration;

		initCouleur(configuration);

		/*
		 * TODO renvoyer des des propositions suite aux indices donnée :
		 * dans l'indice un 0 est un noir donc bonne place et un 1 est un blanc donc présent mais mauvaise place
		 * tour 1 faire un tour a x fois 0
		 * tour 2 si noir présent inserer une autre couleur en plus sinon changer toute la ligne de couleur 
		 */

	}

	public void initCouleur(Configuration configuration) {
		for (int i = 0; i<configuration.getCouleurMast(); i++) {
			couleur.add(typeCouleur.getCouleur(i));
		}
	}

	public void formattage(Partie partie) {
		for (int i = 0; i<configuration.getCombiMast(); i++) {
			proposition.add(typeCouleur.getCouleur(Integer.valueOf(partie.getProposition().substring(i, i+1))));
			solution.add(typeCouleur.getCouleur(Integer.valueOf(partie.getSolution().substring(i, i+1))));
		}
	}

	public void initProposition() {
		if(partie.getIndice() == "vide") {
			String prop = "";
			for (int i = 0; i<configuration.getCombiMast(); i++) {
				prop += '0';
			}
			this.partie.setProposition(prop);
		}
	}

	public void toTrashColor(int color) {
		couleur.remove(typeCouleur.getCouleur(color));
		poubelle.add(typeCouleur.getCouleur(color));
	}
	

	public void proposition(Partie partie) {
		for (int i = 0; i<configuration.getCombiMast(); i++) {
			proposition.add(typeCouleur.getCouleur(Integer.valueOf(partie.getProposition().substring(i, i+1))));
			indices.add(typeCouleur.getCouleur(Integer.valueOf(partie.getProposition().substring(i, i+1))));
		}
			if(indices.isEmpty()) {
				toTrashColor(0);
				
			}
		}
	}

