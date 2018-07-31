package main.java.view.game.plusMoins;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.model.Configuration;
import main.java.view.PanelModel;
import test.ModelGamePanel;

public class PanelPlusMoins extends PanelModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ModelGamePanel gauche, droite;
	private Dimension dim; 
	private String mode; 
	private Configuration config;

	
	public PanelPlusMoins(Dimension dim, String mode,Configuration config) {
		super(dim);
		this.dim = dim;
		this.mode = mode;
		this.config = config;
		initPanel();
		this.setVisible(true);
	}

/**
 * Intitialisation du panel en fonction du mode de jeu
 */
	protected void initPanel() {
		this.setLayout(new BorderLayout());
		if(mode == "chal") {
			this.add(new ModelGamePanel("gauche", config,""), BorderLayout.WEST);
		}
		else if (mode == "def") {
			this.add(new ModelGamePanel("droite",config,""), BorderLayout.EAST);
		}
		else {
			this.add(new ModelGamePanel("gauche", config,""), BorderLayout.WEST);
			this.add(new ModelGamePanel("droite",config,""), BorderLayout.EAST);
		}
	}
}
