package main.java.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.model.Configuration;
import main.java.observer.Observateur;
import main.java.view.game.plusMoins.PanelPlusMoins;



/**
 * Application qui permet de gerer de jouer à deux jeux different
 * @author Ben
 *
 */
public class MainFrame extends JFrame implements Observateur {

	private static final long serialVersionUID = -4971770858535210983L;

	//-- Les logs
	private static final Logger logger = LogManager.getLogger();

	//-- Les differents panels
		private PanelAccueil accueil;
		
	//-- La configuration
		private Configuration config;
	
	//-- Les différents objets de notre IHM
	private JMenuBar bar = new JMenuBar();
	private JMenu fichier = new JMenu("Fichier");
	private JMenu nouveauJeu = new JMenu("Nouveau");

	private JMenu lePlusMoins = new JMenu("Le + -");
	private JMenuItem lePlusMoinsChal = new JMenuItem("Challenger");
	private JMenuItem lePlusMoinsDef = new JMenuItem("Défenseur");
	private JMenuItem lePlusMoinsDuel = new JMenuItem("Duel");

	private JMenu mastermind = new JMenu("Mastermind");
	private JMenuItem mastermindChal = new JMenuItem("Challenger");
	private JMenuItem mastermindDef = new JMenuItem("Défenseur");
	private JMenuItem mastermindDuel = new JMenuItem("Duel");

	private JMenuItem quitter = new JMenuItem("Quitter");

	private JMenu configuration = new JMenu("Configuration");
	private JMenuItem configMenuI = new JMenuItem("configuration");

	private JMenu aProp = new JMenu("A Propos");
	private JMenuItem aPropItem = new JMenuItem("A propos");

	private Dimension size = new Dimension (1600, 1024);
	private Container contentPane;
	private PanelPlusMoins plusMoinsDef, plusMoinsDuel, plusMoinsChal;

	/**
	 * Constructeur par défaut qui permet d'initialiser la fenetre 
	 */
	public MainFrame() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Projet 3");
		this.setSize(size);
		config = new Configuration();
		initPanel();
		initMenu();
		logger.debug("Le contenu de la fenêtre a été initalisée");
	}

	/**
	 * initialisation du panel avec le panel accueil
	 */
	public void initPanel() {
		//-- Données

		accueil = new PanelAccueil(size);
		
		contentPane = this.getContentPane();
		contentPane.setBackground(Color.white);
		contentPane.add(accueil, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		this.size = new Dimension(this.getWidth(), this.getHeight());
	}


	/**
	 * Méthode qui initialise la barre de menu et les differents boutons la composant ainsi que les actionlistener pour chaque JMenuItem
	 */
	private void initMenu() {

		@SuppressWarnings("unused")
		Observateur obs = this;

		fichier.add(nouveauJeu);
		nouveauJeu.setMnemonic('n');

		//-- Le + -
		nouveauJeu.add(lePlusMoins);
		lePlusMoinsChal.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				plusMoinsChal = new PanelPlusMoins(size, "chal", config);
				contentPane.removeAll();
				contentPane.add(plusMoinsChal, BorderLayout.CENTER);
				contentPane.revalidate();
			}});
		lePlusMoins.add(lePlusMoinsChal);
		
		lePlusMoinsDef.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				plusMoinsDef = new PanelPlusMoins(size, "def", config);
				contentPane.removeAll();
				contentPane.add(plusMoinsDef, BorderLayout.CENTER);
				contentPane.revalidate();
			}});
		lePlusMoins.add(lePlusMoinsDef);
		
		lePlusMoinsDuel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				plusMoinsDuel = new PanelPlusMoins(size, "duel", config);
				contentPane.removeAll();
				contentPane.add(plusMoinsDuel, BorderLayout.CENTER);
				contentPane.revalidate();
			}});
		lePlusMoins.add(lePlusMoinsDuel);
		
		lePlusMoins.setMnemonic('p');

		//-- Le mastermind
		nouveauJeu.add(mastermind);
		mastermind.add(mastermindChal);
		mastermind.add(mastermindDef);
		mastermind.add(mastermindDuel);
		mastermind.setMnemonic('m');

		fichier.addSeparator();

		//-- Quitter
		fichier.add(quitter);
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,KeyEvent.CTRL_MASK + KeyEvent.SHIFT_DOWN_MASK));
		//Pour quitter l'application
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.exit(0);
			}
		});

		fichier.setMnemonic('f');

		configuration.add(configMenuI);
		configuration.setMnemonic('c');
		configMenuI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PopUpCfg ajout = new PopUpCfg(null, "Configuration", true, config , obs);

			}
		});

		aProp.add(aPropItem);
		aProp.setMnemonic('a');
		aPropItem.addActionListener(new ActionListener(){
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane jop = new JOptionPane();
				ImageIcon img = new ImageIcon("resources/images/banane.gif");        
				String mess = "Crée par la Banane \n Amusez vous bien !\n";
				mess += "Pour toute remaque ou suggestion concerant le jeu, contatez moi à :\n";
				mess += "\n benjamin@fa-tech.net";        
				jop.showMessageDialog(null, mess, "À propos", JOptionPane.INFORMATION_MESSAGE, img);        
			}            
		});


		bar.add(fichier);
		bar.add(configuration);
		bar.add(aProp);

		this.setJMenuBar(bar);
	}

	public void update(Configuration config) {
		this.config = config;
		logger.debug("configuration : "+ config.toString());
	}
}
