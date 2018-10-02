package main.java.view.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.observer.Observable;
import main.java.observer.Observateur;

public class PopUpFinPartie extends JDialog implements Observable{

	private static final long serialVersionUID = 1L;
	//-- Les logs
	private static final Logger logger = LogManager.getLogger();
	//-- L'observateur
	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
	private JButton nouvJeu, menuPrinc, quitter;
	private String choixFinJeu;


	public PopUpFinPartie(JFrame parent, String title, boolean modal, Observateur obs) {
		this.addObservateur(obs);
		this.setSize(870, 340);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponent();
		this.setVisible(true);
	}

	public void initComponent() {

		nouvJeu = new JButton("Recommencer");
		nouvJeu.addActionListener(new RejouerListener());
		menuPrinc = new JButton("Menu principal");
		menuPrinc.addActionListener(new MenuPrincipalListener());
		quitter = new JButton("Quitter");
		quitter.addActionListener(new QuitterListener());

		//-- Ajout de tout les composants au content
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(nouvJeu);
		content.add(menuPrinc);
		content.add(quitter);
		this.getContentPane().add(content, BorderLayout.CENTER);
	}

	class RejouerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			choixFinJeu = "nouvellePartie";
			logger.trace("choix : rejouer");
			updateObservateur();
		}
	}

	class MenuPrincipalListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			choixFinJeu = "menuPrincipal";
			logger.trace("choix : menu principal");
			updateObservateur();
		}
	}

	class QuitterListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			logger.trace("choix : quitter");
			System.exit(0);
		}
	}

	/**
	 * Ajout des observateurs
	 */
	public void addObservateur(Observateur obs) {
		listObservateur.add(obs);
		this.updateObservateur();
	}

	/**
	 * Renvoie aux observateurs le choix du pop up
	 */
	public void updateObservateur() {
		for(Observateur obs : listObservateur)
			obs.update(choixFinJeu);
	}

	public void delObservateur() {}
}
