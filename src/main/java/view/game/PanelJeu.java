package main.java.view.game;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import main.java.controller.Controller;
import main.java.model.Configuration;
import main.java.model.Jeu;
import main.java.model.ModeDeJeu;
import main.java.model.ModeDePartie;
import main.java.observer.Observateur;
import main.java.view.game.plusMoins.GamePanelPlusMoins;
import main.java.view.game.plusMoins.PopUpCombi;
import test.avant_implementation.GamePanelMastermind;

public class PanelJeu extends JPanel {

	private Jeu jeu;
	private	Controller controller, controller2;
	private Thread thread;
	private GamePanelPlusMoins jpChalPlus, jpDefPlus;
	private GamePanelMastermind jpChalMast, jpDefMast;
	private Configuration configuration;
	private Observateur obs;
	private Dimension bigSize = new Dimension (1710, 1050), smallSize = new Dimension (845, 1040);

	public PanelJeu(Jeu jeu, Configuration configuration, Observateur obs) {
		this.jeu = jeu;
		this.configuration = configuration;
		this.obs = obs;
		init();
	}

	public void init() {
		if(jeu.getModeDeJeu().equals(ModeDeJeu.PLUS_CHAL)){
			this.setPreferredSize(smallSize);
			controller = new Controller(configuration,jeu.getPartie1(), jeu);
			jpChalPlus = new GamePanelPlusMoins(configuration, jeu.getModeDeJeu(), jeu.getPartie1(), controller);
			jpChalPlus.setPreferredSize(smallSize);
			this.setLayout(new BorderLayout());
			this.add(jpChalPlus, BorderLayout.WEST);
		}
		else if(jeu.getModeDeJeu().equals(ModeDeJeu.PLUS_DEF)) {
			this.setPreferredSize(smallSize);
			controller = new Controller(configuration,jeu.getPartie1(), jeu);
			PopUpCombi popUpCombi = new PopUpCombi(null, "choix de la combinaison", true, configuration, jeu.getPartie1(), obs);
			jpDefPlus = new GamePanelPlusMoins(configuration, jeu.getModeDeJeu(), jeu.getPartie1(), controller);
			if(configuration.isDevModEnJeu() == true && jeu.getPartie1().getModeDePartie() == ModeDePartie.PLUS_DEF) {
				controller.sendProposition(jeu.getPartie1());
				jpDefPlus.devIndice();
				System.out.println("dans le if");
			}
			else {System.out.println("dans le else");}
			jpDefPlus.setPreferredSize(smallSize);
			this.add(jpDefPlus);
		}
		else if(jeu.getModeDeJeu().equals(ModeDeJeu.PLUS_DUEL)) {
			this.setPreferredSize(bigSize);
			controller = new Controller(configuration,jeu.getPartie1(), jeu);
			jpChalPlus = new GamePanelPlusMoins(configuration, jeu.getModeDeJeu(), jeu.getPartie1(), controller);
			jpChalPlus.setPreferredSize(smallSize);
			JPanel jpEspace = new JPanel();
			jpEspace.setPreferredSize(new Dimension(10, 1040));
			controller2 = new Controller(configuration,jeu.getPartie2(), jeu);
			PopUpCombi popUpCombi = new PopUpCombi(null, "choix de la combinaison", true, configuration, jeu.getPartie2(), obs);
			jeu.getPartie2().setActif(false);
			jpDefPlus = new GamePanelPlusMoins(configuration, jeu.getModeDeJeu(), jeu.getPartie2(), controller);
			jpDefPlus.setPreferredSize(smallSize);

			this.setLayout(new BorderLayout());
			this.add(jpChalPlus, BorderLayout.WEST);
			this.add(jpEspace, BorderLayout.CENTER);
			this.add(jpDefPlus, BorderLayout.EAST);
		}
		else if(jeu.getModeDeJeu().equals(ModeDeJeu.MAST_CHAL)){
			this.setPreferredSize(smallSize);
			controller = new Controller(configuration,jeu.getPartie1(), jeu);
			jpChalMast = new GamePanelMastermind(configuration, jeu.getModeDeJeu(), jeu.getPartie1(), controller);
			jpChalMast.setPreferredSize(smallSize);
			this.setLayout(new BorderLayout());
			this.add(jpChalMast, BorderLayout.WEST);
		}
		else if(jeu.getModeDeJeu().equals(ModeDeJeu.MAST_DEF)) {
			this.setPreferredSize(smallSize);
			controller = new Controller(configuration,jeu.getPartie1(), jeu);
			PopUpCombi popUpCombi = new PopUpCombi(null, "choix de la combinaison", true, configuration, jeu.getPartie1(), obs);
			jpDefMast = new GamePanelMastermind(configuration, jeu.getModeDeJeu(), jeu.getPartie1(), controller);
			if(configuration.isDevModEnJeu() == true && jeu.getPartie1().getModeDePartie() == ModeDePartie.PLUS_DEF) {
				controller.sendProposition(jeu.getPartie1());
				jpDefMast.devIndice();
				System.out.println("dans le if");
			}
			else {System.out.println("dans le else");}
			jpDefMast.setPreferredSize(smallSize);
			this.add(jpDefMast);
		}
		else if(jeu.getModeDeJeu().equals(ModeDeJeu.MAST_DUEL)) {
			this.setPreferredSize(bigSize);
			controller = new Controller(configuration,jeu.getPartie1(), jeu);
			jpChalMast = new GamePanelMastermind(configuration, jeu.getModeDeJeu(), jeu.getPartie1(), controller);
			jpChalMast.setPreferredSize(smallSize);
			JPanel jpEspace = new JPanel();
			jpEspace.setPreferredSize(new Dimension(10, 1040));
			controller2 = new Controller(configuration,jeu.getPartie2(), jeu);
			PopUpCombi popUpCombi = new PopUpCombi(null, "choix de la combinaison", true, configuration, jeu.getPartie2(), obs);
			jeu.getPartie2().setActif(false);
			jpDefMast = new GamePanelMastermind(configuration, jeu.getModeDeJeu(), jeu.getPartie2(), controller);
			jpDefMast.setPreferredSize(smallSize);

			this.setLayout(new BorderLayout());
			this.add(jpChalMast, BorderLayout.WEST);
			this.add(jpEspace, BorderLayout.CENTER);
			this.add(jpDefMast, BorderLayout.EAST);
		}
	}

	public void defTurn() {
		System.out.println("defTurn() de PanelLink");
		jpDefPlus.newTurn();
		try {
			Thread.sleep(1000);
			System.out.println("sleep defTurn");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jpChalPlus.stopTurn();
	}
	public void chalTurn() {
		System.out.println("chalTurn() de PanelLink");
		jpChalPlus.newTurn();
		try {
			Thread.sleep(1000);
			System.out.println("sleep chalTurn");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jpDefPlus.stopTurn();
	}
}


