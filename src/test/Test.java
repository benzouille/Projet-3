package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import main.java.model.Configuration;
import main.java.view.PanelAccueil;

public class Test extends JFrame {
	private Dimension size = new Dimension (800, 1024);
	private Container contentPane;
	private ModelGamePanel gp;
	private Configuration config;

	public Test() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("test");
		this.setSize(size);
		initPanel();

	}

	private void initPanel() {
		config = new Configuration();
		gp = new ModelGamePanel("gauche", config,"");
		//accueil = new PanelAccueil(size);
		contentPane = this.getContentPane();
		contentPane.setBackground(Color.white);
		contentPane.add(gp, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		this.size = new Dimension(this.getWidth(), this.getHeight());

	}

	public static void main(String[] args) {
		Test test = new Test();
		test.setVisible(true);
	}

}
