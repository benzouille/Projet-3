package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import main.java.view.PanelAccueil;

public class Test extends JFrame {
	private Dimension size = new Dimension (1600, 1024);
	private Container contentPane;
	private ModelGamePanel gp;
	private PanelAccueil accueil;

	public Test() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("test");
		this.setSize(size);
		initPanel();

	}

	private void initPanel() {
		//gp = new ModelGamePanel();
		//accueil = new PanelAccueil(size);
		contentPane = this.getContentPane();
		contentPane.setBackground(Color.white);
		contentPane.add(accueil, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		this.size = new Dimension(this.getWidth(), this.getHeight());

	}

	public static void main(String[] args) {
		Test test = new Test();
		test.setVisible(true);
	}

}
