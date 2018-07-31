package main.java.model;

public abstract class Joueur {
	

	abstract void generate(int nbre);
	
	public void resolve(int[] list) {
		/* methode resolve() : boucle de comparaison entre chaque int de la proposition et de la solution :
		 * 		si proposition < solution mettre +
		 * 		si proposition > solution mettre -
		 * 		si proposition = solution mettre =
		 *	ensuite la mettre la liste dans un string.
		 */
		for (int i = 0; i<list.length; i++) {
			
		}
		
	}
	
}
