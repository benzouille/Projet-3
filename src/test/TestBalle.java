package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.model.TypeCouleur;

public class TestBalle extends JFrame {

	private JPanel container = new JPanel();
	private Dimension size = new Dimension(125, 700);
	
	private TypeCouleur couleur;
	private JButton jbBleu, jbBrun, jbCyan, jbJaune, jbOrange, jbRose, jbRouge, jbVert, jbVertClair, jbViolet;
	private JButton buton[] = {jbBleu, jbBrun, jbCyan, jbJaune, jbOrange, jbRose, jbRouge, jbVert, jbVertClair, jbViolet};
	private Image bleu, brun, cyan, jaune, orange, rose, rouge, vert, vertClair, violet;
	private Image image[] = {bleu, brun, cyan, jaune, orange, rose, rouge, vert, vertClair, violet};
	private String path = "resources/images/mastermind/";
	private String nom[] = {"bleu", "brun", "cyan", "jaune", "orange", "rose", "rouge", "vert", "vertClair", "violet"};

	public TestBalle() {
		
		this.setTitle("test des boutons");
		this.setSize(new Dimension(130, 705));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(container);
		
		this.init();
		this.setVisible(true);     
		
	}
	
	public void init() {
		container.setSize(size);
		container.setBackground(Color.white);
		container.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
		//container.setLayout(new BorderLayout());
		
		JPanel test = new JPanel();
		test.setPreferredSize(new Dimension(70,70));
		
		try {
			bleu = ImageIO.read(new File("resources/images/mastermind/bleu.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i<buton.length; i++) {
			buton[i] = new JButton(new ImageIcon(path+ nom[i]+".png"));
			buton[i].setBackground(Color.WHITE);
			buton[i].setPreferredSize(new Dimension(60, 60));
			container.add(buton[i]);
		}	
	}
	
	public void newButton() {
		
		for(int i = 0; i<buton.length; i++) {
			try {
				image[i] = ImageIO.read(new File(path+image[i].getClass().getName()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		  TestBalle testBalle = new TestBalle();
		}  
}
