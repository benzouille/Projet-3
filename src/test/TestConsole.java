package test;

import main.java.controller.Controller;
import main.java.model.Configuration;
import main.java.model.Model;
import main.java.model.Partie;
import main.java.observer.Observateur;

public class TestConsole implements Observateur {
	
	private Partie partie;
	private Configuration configuration;
	private Controller controller;
	private Model model;
	
	public TestConsole() {
		partie = new Partie();
		partie.setSolution("123789");
		configuration = new Configuration();
		model = new Model(configuration, partie, this);
		controller = new Controller(configuration, partie, model);
		autoPartie();
	}
	
	public void autoPartie() {
		model.initOrdinateur();
		System.out.println(model.resolve(partie));
		System.out.println(model.propositionOrdinateur(partie));
		System.out.println(model.resolve(partie));
		System.out.println(model.propositionOrdinateur(partie));
		System.out.println(model.resolve(partie));
		System.out.println(model.propositionOrdinateur(partie));
		System.out.println(model.resolve(partie));
		System.out.println(model.propositionOrdinateur(partie));
	}
	
	public static void main(String[] args) {TestConsole testConsole = new TestConsole();}

	public void update(Configuration config) {
		
	}

	public void update(Partie partie) {
		
	}

	public void update(String choixFinJeu) {
		
	}
}
