package main.java.model;

/**
 * Les differentes couleurs possible du mastermind ainsi que la valeur associé à chaque couleur pour l' utilisation dans le model.
 * @author Ben
 *
 */
public enum TypeCouleur {

	BLEU(0), BRUN(1), CYAN(2), JAUNE(3), ORANGE(4), ROSE(5), ROUGE(6), VERT(7), VERT_CLAIR(8), VIOLET(9);

	private final int valeur;

	private TypeCouleur(int valeur) {
		this.valeur = valeur;
	}

	public int getValeur() {
		return this.valeur;
	}
}
