package test;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import main.java.model.Configuration;
import main.java.model.Partie;
import main.java.observer.Observateur;
import main.java.view.game.plusMoins.ModelGamePanel;
import main.java.view.game.plusMoins.PopUpCombi;

public class Test extends JFrame {
	private Dimension size = new Dimension (800, 1024);
	private Container contentPane;
	private ModelGamePanel gp;
	private Configuration config;
	private Partie partie;
	private TestCarac signe;
	private PopUpCombi combi;
	private Observateur obs;

	public Test() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("test");
		this.setSize(size);
		initPanel();

	}

	private void initPanel() {
		config = new Configuration();
		//combi = new PopUpCombi(null, "Combinaison", true, config ,partie, obs);
		signe = new TestCarac(null, "Signe", true, config , obs);
		//gp = new ModelGamePanel("gauche", config,"");
		//accueil = new PanelAccueil(size);
		//contentPane = this.getContentPane();
		//contentPane.setBackground(Color.white);
		//contentPane.add(gp, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		this.size = new Dimension(this.getWidth(), this.getHeight());

	}

	public static void main(String[] args) {
		Test test = new Test();
		test.setVisible(true);
	}

}
