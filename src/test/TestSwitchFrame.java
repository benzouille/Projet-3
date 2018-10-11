package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.java.controller.Controller;
import main.java.model.Configuration;
import main.java.model.Jeu;
import main.java.model.ModeDeJeu;
import main.java.model.Partie;
import main.java.observer.Observateur;
import main.java.view.game.plusMoins.PanelJeu;

public class TestSwitchFrame extends JFrame implements Observateur{

	private static final long serialVersionUID = 1L;

	private Configuration config;
	private PanelJeu panelLink;
	private Jeu jeu;
	private Container container = this.getContentPane();
	private Observateur obs;

	private Dimension bigSize = new Dimension (1710, 1050), smallSize = new Dimension (845, 1040);

	public TestSwitchFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Test switch");
		obs = this;
		config = new Configuration();
		config.setDevModEnJeu(false);
		jeu = new Jeu(ModeDeJeu.PLUS_DUEL, config, obs);
		
		if(jeu.getModeDeJeu().equals(ModeDeJeu.PLUS_CHAL) || jeu.getModeDeJeu().equals(ModeDeJeu.PLUS_DEF) || jeu.getModeDeJeu().equals(ModeDeJeu.MAST_CHAL) || jeu.getModeDeJeu().equals(ModeDeJeu.MAST_DEF)) {
			this.setSize(smallSize);
		}
		else {
			this.setSize(bigSize);
		}
		
		panelLink = new PanelJeu(jeu, config, obs);
		container.add(panelLink, BorderLayout.CENTER);
	}

	public void update(Configuration config) {}
	public void update(Jeu jeu) {}
	public void update(Partie partie) {
		if(partie.getNom() == jeu.getPartie1().getNom()) {
			System.out.println("methode update partie1 de TestSwitchPanel");
			jeu.setPartie1(partie);
			if(jeu.getModeDeJeu() == ModeDeJeu.PLUS_DUEL) {
			panelLink.defTurn();
			}
		}
		else {
			System.out.println("methode update partie2 de TestSwitchPanel");
			jeu.setPartie2(partie);
			if(jeu.getModeDeJeu() == ModeDeJeu.PLUS_DUEL) {
			panelLink.chalTurn();
			}
		}
	}
	public void update(boolean test) {}
	public void update(String choixFinJeu) {}

	public static void main(String[] args) {
		TestSwitchFrame test = new TestSwitchFrame();
		test.setVisible(true);
	}
}
