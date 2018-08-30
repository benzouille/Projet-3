package main.java.model;

public abstract class Joueur {
	

	abstract void generate(int nbre);
	
	public void resolve(int[] solution, int[] proposition) {
		/* methode resolve() : boucle de comparaison entre chaque int de la proposition et de la solution :
		 * 		si proposition < solution mettre +
		 * 		si proposition > solution mettre -
		 * 		si proposition = solution mettre =
		 *	ensuite la mettre la liste dans un string.
		 *
		 *	faire une autre methode pour condition de victoire ?
		 *
		 * Si l'indice[] n'a que des "=" c'est gagné
		 */
		
		for (int i = 0; i<proposition.length; i++) {
			if (proposition[i] < solution[i]) {
				// ajout de "+"
			}
			else if (proposition[i] > solution[i]) {
				// ajout de "-"
			}
			else {
				// ajout de "="
			}
		}
		
	}
	
}
