package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ModelGamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel jlInfo;
	private JPanel jpInfo, jpGrid, jpProp, jpGame;
	private JLabel jtTour, jlSolution;
	private JButton buttOk;
	private JFormattedTextField jfProp;
	private Dimension size = new Dimension(800,1024);

	private int nbreChiffre;
	
	public ModelGamePanel() {
		initPanel();
		this.setVisible(true);
	}
	
	public void initPanel(){
		jlInfo = new JLabel("Bienvenue dans TrumpLand\n");
		jlInfo.setHorizontalAlignment(JLabel.CENTER);
		jpInfo.setFont(new Font("Comics Sans MS", Font.BOLD, 30));
		
		

		this.setLayout(new BorderLayout());
		this.add(jpInfo, BorderLayout.NORTH);

	}


	/**
	 * l'action de OkButton, envoi la proposition au controller
	 * @author Ben
	 *
	 */
	class OkListener implements ActionListener{
		public void actionPerformed(ActionEvent x) {
			//controler.send(proposition);
			//((JButton)x.getSource()).setEnabled(false);
		}
	}
	
}
