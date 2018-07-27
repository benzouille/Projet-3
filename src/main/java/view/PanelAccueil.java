package main.java.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelAccueil extends PanelModel {
	
	private JPanel gauche = new JPanel();
	private JPanel droite = new JPanel();
	
	private JLabel titre;
	private JTextArea texte;
	//private ImageIcon img = new ImageIcon("resources/images/trump_accueil.jpg");
	//private JLabel accueilImg = new JLabel(img);
	private Dimension dim = new Dimension(1200, 800);

	public PanelAccueil(Dimension dim) {
		super (dim);
		initPanel();
	}

	public void initPanel(){
		titre = new JLabel("Bienvenue dans TrumpLand\n");
		titre.setHorizontalAlignment(JLabel.CENTER);
		titre.setFont(comics30);

		texte = new JTextArea(	"Dans ce merveilleux monde les hordes de méchants migrants tente de passer la frontière\n" +
				"De plus le code de la grosse grosse bombe à été perdu\n" +
				"Aide Trump à résoudre ses problèmes\n");
		texte.setFont(arial);
		texte.setEditable(false);
		texte.setBackground(Color.white);
		
		//accueilImg.setPreferredSize(dim);
		
		gauche.setLayout(new BorderLayout());
		//gauche.add(titre, BorderLayout.NORTH);
		gauche.add(texte, BorderLayout.CENTER);
		
		//droite.add(accueilImg, BorderLayout.SOUTH);
		
		this.setLayout(new BorderLayout());
		this.add(titre, BorderLayout.NORTH);
		this.add(gauche, BorderLayout.WEST);
		this.add(droite, BorderLayout.EAST);
	}
}
