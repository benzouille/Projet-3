package main.java.view.game.plusMoins;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.view.PanelModel;
import test.ModelGamePanel;

public class PanelPlusMoins extends PanelModel {
	
	private ModelGamePanel gauche, droite;
	private Dimension dim; 
	private String mode;
	private boolean devModEnJeu; 
	private int tourPlusMoins, combiPlusMoins;

	
	public PanelPlusMoins(Dimension dim, String mode,boolean devModEnJeu, int tourPlusMoins, int combiPlusMoins) {
		super(dim);
		this.dim = dim;
		this.mode = mode;
		this.devModEnJeu = devModEnJeu;
		this.tourPlusMoins = tourPlusMoins;
		this.combiPlusMoins = combiPlusMoins;
		initPanel();
		this.setVisible(true);
		//this.add(new JLabel("le plus moins"));
	}

	//protected void initPanel() {
		//setLayout(new BorderLayout());
		//add(gauche = new ModelGamePanel("gauche", tourPlusMoins, combiPlusMoins) , BorderLayout.WEST);
		//add(droite = new ModelGamePanel("droite", tourPlusMoins, combiPlusMoins) , BorderLayout.EAST);
	/}

}
