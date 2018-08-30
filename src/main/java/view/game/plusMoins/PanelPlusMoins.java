package main.java.view.game.plusMoins;

import java.awt.BorderLayout;
import java.awt.Dimension;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.controller.Controller;
import main.java.model.Configuration;
import main.java.model.ModeDeJeu;
import main.java.model.Partie;
import main.java.view.PanelModel;

public class PanelPlusMoins extends PanelModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//-- Les logs
		private static final Logger logger = LogManager.getLogger();
	
	private ModelGamePanel gauche, droite;
	private Dimension dim;  
	private Configuration config;
	private Partie partie;
	private Controller controller;

	
	public PanelPlusMoins(Dimension dim, Configuration config, Partie partie, Controller controller) {
		super(dim);
		this.dim = dim;
		this.config = config;
		this.partie = partie;
		this.controller = controller;
		initPanel();
		this.setVisible(true);
	}

/**
 * Intitialisation du panel en fonction du mode de jeu
 */
	protected void initPanel() {
		this.setLayout(new BorderLayout());
		if(partie.getModeDeJeu() == ModeDeJeu.PlusChal) {
			this.add(new ModelGamePanel("gauche", config, partie, "", controller), BorderLayout.WEST);
		}
		else if(partie.getModeDeJeu().equals(ModeDeJeu.PlusDef)) {
			this.add(new ModelGamePanel("droite",config, partie, "", controller), BorderLayout.EAST);
		}
		else if(partie.getModeDeJeu() == ModeDeJeu.PlusDuel) {
			this.add(new ModelGamePanel("gauche", config, partie, "", controller), BorderLayout.WEST);
			this.add(new ModelGamePanel("droite",config, partie, "", controller), BorderLayout.EAST);
		}
	}
}
