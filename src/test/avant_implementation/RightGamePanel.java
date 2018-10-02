package test.avant_implementation;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RightGamePanel extends JPanel {

	private Container contentPane = this;
	private Dimension size = new Dimension(125, 700);
	
	private JButton jbBleu, jbBrun, jbCyan, jbJaune, jbOrange, jbRose, jbRouge, jbVert, jbVertClair, jbViolet;
	private JButton buton[] = {jbBleu, jbBrun, jbCyan, jbJaune, jbOrange, jbRose, jbRouge, jbVert, jbVertClair, jbViolet};
	private Image bleu, brun, cyan, jaune, orange, rose, rouge, vert, vertClair, violet;
	private Image image[] = {bleu, brun, cyan, jaune, orange, rose, rouge, vert, vertClair, violet};
	private String path = "resources/images/mastermind/balle_";
	
	public RightGamePanel() {
		
		for(int i = 0; i<buton.length; i++) {
		try {
			image[i] = ImageIO.read(new File(path+image[i].getClass().getName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	}
}
