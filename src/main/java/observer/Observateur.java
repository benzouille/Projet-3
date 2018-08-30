package main.java.observer;

import main.java.model.Configuration;
import main.java.model.Partie;

public interface Observateur {
	public void update(Configuration config);
	public void update(Partie partie);
	public void update(String choixFinJeu);
}
