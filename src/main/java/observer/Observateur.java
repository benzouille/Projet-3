package main.java.observer;

import main.java.model.Configuration;
import main.java.model.Partie;
import test.avant_implementation.Jeu;

public interface Observateur {
	public void update(Configuration config);
	public void update(Jeu jeu);
	public void update(Partie partie);
	public void update(boolean test);
	public void update(String choixFinJeu);
	
}
