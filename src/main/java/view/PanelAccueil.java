package main.java.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelAccueil extends PanelModel {
	
	private JPanel jpRegles = new JPanel();
	private JPanel jpCommentaires = new JPanel();
	
	private JLabel jlTitre, jlComm;
	private JTextArea jtTexte;

	public PanelAccueil(Dimension dim) {
		super (dim);
		initPanel();
	}

	public void initPanel(){
		jlTitre = new JLabel("PROJET 3");
		jlTitre.setPreferredSize(new Dimension(800, 300));
		jlTitre.setHorizontalAlignment(JLabel.CENTER);
		jlTitre.setFont(new Font("Lucida Console", Font.BOLD, 50));

		jtTexte = new JTextArea("Bienvenue dans la suite de jeu du plus-moins et du mastermind.\n" +
				"\n" +
				"\n" +
				"\n" +
				"Les règles sont les suivantes :\n" +
				"\n" +
				"\n" +
				"En Mode Challenger : Vous devez trouver la combinaison secrète pour gagner.\n" +
				"\n" +
				"En Mode Défenseur : Vous devez créer une combinaison que l'ordinateur devra trouver.\n" +
				"\n" +
				"En Mode Duel : Vous devez trouver la combinaison secrète avant l'ordinateur pour gagner.\n" +
				"\n" +
				"\n");
		jtTexte.setFont(new Font("Lucida Console", Font.BOLD, 25));
		jtTexte.setEditable(false);
		jtTexte.setBackground(Color.white);
		
		jpRegles.setLayout(new BorderLayout());
		jtTexte.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));
		jpRegles.add(jtTexte, BorderLayout.CENTER);
		
		jlComm = new JLabel("Devenez le Kasparov du mastermind !");
		jlComm.setFont(new Font("Lucida Console", Font.BOLD, 20));
		jpCommentaires.setBackground(Color.white);
		jpCommentaires.add(jlComm);
		
		this.setLayout(new BorderLayout());
		this.add(jlTitre, BorderLayout.NORTH);
		this.add(jpRegles, BorderLayout.WEST);
		this.add(jpCommentaires, BorderLayout.SOUTH);
	}
}
