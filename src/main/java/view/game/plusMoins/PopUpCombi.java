package main.java.view.game.plusMoins;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.model.Configuration;
import main.java.observer.Observable;
import main.java.observer.Observateur;


public class PopUpCombi extends JDialog implements Observable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1975827366674767282L;
	//-- Les logs
	private static final Logger logger = LogManager.getLogger();

	protected ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
	private JPanel content;
	private JLabel jlCombi;
	private Configuration config;
	private JFormattedTextField jfCombi;
	private int nbreChiffre, combi;

	public PopUpCombi(JFrame parent, String title, boolean modal, Configuration config, Observateur obs) {
		super(parent, title, modal);
		this.config =config;
		nbreChiffre = config.getCombiPlusMoins();
		this.addObservateur(obs);
		this.setSize(520, 160);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponent();
		this.setVisible(true);
	}

	public void initComponent() {

		jlCombi = new JLabel("Choisissez votre code à " + config.getCombiPlusMoins() + " chiffres :");

		try{	
			char [] charac = new char[nbreChiffre];
			for (int i = 0; i<nbreChiffre; i++) {
				charac[i] = '#';
			}
			String str = new String(charac);
			MaskFormatter mask = new MaskFormatter(str);
			jfCombi = new JFormattedTextField(mask);
		}catch(ParseException e){e.printStackTrace();}
		jfCombi.setFont(new Font("Lucida Console", Font.BOLD, 30));

		JButton randomButton = new JButton("Aléatoire");
		randomButton.addActionListener(new RandomListener());

		//Choix de la combinaison
		JPanel panCombi = new JPanel();
		panCombi.setBackground(Color.white);
		panCombi.setPreferredSize(new Dimension(500, 80));
		panCombi.setBorder(BorderFactory.createTitledBorder("Choix de la combinaison"));
		panCombi.add(jlCombi);
		panCombi.add(jfCombi);
		panCombi.add(randomButton);

		//-- Ajout de tout les composants au content
		content = new JPanel();
		content.setBackground(Color.white);
		content.add(panCombi);

		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");

		okBouton.addActionListener(new RunListener());

		JButton cancelBouton = new JButton("Annuler");
		cancelBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}      
		});

		control.setBackground(Color.white);
		control.add(okBouton);
		control.add(cancelBouton);

		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}

	class RunListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			combi = Integer.parseInt(jfCombi.getText());
			System.out.println("la combinaison : "+combi);
			updateObservateur();
			setVisible(false);
			logger.debug("cliqué sur ok ");
		}
	}

	class RandomListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			logger.info("cliqué sur random ");
			int [] combi = new int[nbreChiffre];
			for (int i = 0; i<nbreChiffre; i++) {
				combi[i] = (int)(Math.random()*9);
				System.out.println(combi[i]);
			}
			content.removeAll();
			jfCombi.setText(String.valueOf(combi));
			content.revalidate();
		}
	}
	
	public void addObservateur(Observateur obs) {
		// TODO Auto-generated method stub	
	}

	public void updateObservateur() {
		// TODO Auto-generated method stub
	}

	public void delObservateur() {
		// TODO Auto-generated method stub	
	}
}
