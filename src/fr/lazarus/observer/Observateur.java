package fr.lazarus.observer;

import fr.lazarus.model.Configuration;
import fr.lazarus.model.Jeu;
import fr.lazarus.model.Partie;

public interface Observateur {
	public void update(Configuration config);
	public void update(Jeu jeu);
	public void update(Partie partie);
	public void update(boolean test);
	public void update(String choixFinJeu);
	
}
