package fr.lazarus.view.game.plusMoins;

import java.awt.BorderLayout;
import java.awt.Dimension;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.lazarus.controller.Controller;
import fr.lazarus.model.Configuration;
import fr.lazarus.model.ModeDePartie;
import fr.lazarus.model.Partie;
import fr.lazarus.view.PanelModel;

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
		if(partie.getModeDePartie() == ModeDePartie.PLUS_CHAL) {
			this.add(new ModelGamePanel("gauche", config, partie, "", controller), BorderLayout.WEST);
		}
		else if(partie.getModeDePartie().equals(ModeDePartie.PLUS_DEF)) {
			this.add(new ModelGamePanel("droite",config, partie, "", controller), BorderLayout.EAST);
		}
	}
}
