package main.java.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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
import main.java.model.Jeu;
import main.java.model.ModeDeJeu;
import main.java.model.Partie;
import main.java.observer.Observateur;
import main.java.view.game.PanelJeu;
import main.java.view.game.plusMoins.PopUpCombi;


/**
 * Fenetre principale racine de la vue appelant le model, le controller et la vue.
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
	//-- L'observateur
	private Observateur obs = this;
	//-- Le Jeu
	private Jeu jeu;
	//-- La Vue
	private PanelJeu panelLink;

	//-- Le Pop up pour choisir la combinaison 
	@SuppressWarnings("unused")
	private PopUpCombi popUpCombi;
	//-- Les différents objets de notre IHM
	private JMenuBar bar = new JMenuBar();
	private JMenu fichier = new JMenu("Fichier"), nouveauJeu = new JMenu("Nouveau");
	private JMenuItem quitter = new JMenuItem("Quitter");

	private JMenu lePlusMoins = new JMenu("Le + -");
	private JMenuItem lePlusMoinsChal = new JMenuItem("Challenger"), lePlusMoinsDef = new JMenuItem("Défenseur"), lePlusMoinsDuel = new JMenuItem("Duel");

	private JMenu mastermind = new JMenu("Mastermind");
	private JMenuItem mastermindChal = new JMenuItem("Challenger"), mastermindDef = new JMenuItem("Défenseur"), mastermindDuel = new JMenuItem("Duel");

	private JMenu configuration = new JMenu("Configuration");
	private JMenuItem configMenuI = new JMenuItem("configuration");

	private JMenu aProp = new JMenu("A Propos");
	private JMenuItem aPropItem = new JMenuItem("A propos");

	private Dimension size = new Dimension (1710, 1070);
	private Container contentPane;

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
		logger.debug("configuration : "+ config.toString());
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
		
		fichier.add(nouveauJeu);
		nouveauJeu.setMnemonic('n');

		//-- Le + -
		nouveauJeu.add(lePlusMoins);
		lePlusMoinsChal.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				jeu = new Jeu(ModeDeJeu.PLUS_CHAL, config, obs);
				logger.debug(jeu.getPartie1().getSolution());
				panelLink = new PanelJeu(jeu, config, obs);
				contentPane.removeAll();
				contentPane.add(panelLink, BorderLayout.CENTER);
				contentPane.revalidate();
			}});
		lePlusMoins.add(lePlusMoinsChal);

		lePlusMoinsDef.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				jeu = new Jeu(ModeDeJeu.PLUS_DEF, config, obs);
				panelLink = new PanelJeu(jeu, config, obs);
				contentPane.removeAll();
				contentPane.add(panelLink, BorderLayout.CENTER);
				contentPane.revalidate();
			}});
		lePlusMoins.add(lePlusMoinsDef);

		lePlusMoinsDuel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				jeu = new Jeu(ModeDeJeu.PLUS_DUEL, config, obs);
				panelLink = new PanelJeu(jeu, config, obs);
				contentPane.removeAll();
				contentPane.add(panelLink, BorderLayout.CENTER);
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
				@SuppressWarnings("unused")
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

	public void update(Partie partie) {
		if(partie.getNom() == jeu.getPartie1().getNom()) {
			System.out.println("methode update partie1 de TestSwitchPanel");
			jeu.setPartie1(partie);
			if(jeu.getModeDeJeu() == ModeDeJeu.PLUS_DUEL && panelLink != null) {
				panelLink.defTurn();
			}
		}
		else {
			System.out.println("methode update partie2 de TestSwitchPanel");
			jeu.setPartie2(partie);
			if(jeu.getModeDeJeu() == ModeDeJeu.PLUS_DUEL && panelLink != null) {
				panelLink.chalTurn();
			}
		}
	}

	/**
	 * Vérification du choix de fin de partie si nouvelle partie ou menu principal
	 */
	public void update(String choixFinJeu) {
		if 	(choixFinJeu == "nouvellePartie") {
			System.out.println("nouvelle partie");

			if (jeu.getModeDeJeu().equals(ModeDeJeu.PLUS_CHAL)) {
				lePlusMoinsChal.doClick();
			}
			else if (jeu.getModeDeJeu().equals(ModeDeJeu.PLUS_DEF)) {
				lePlusMoinsDef.doClick();
			}
			else if (jeu.getModeDeJeu().equals(ModeDeJeu.PLUS_DUEL)) {
				lePlusMoinsDuel.doClick();
			}
			else if (jeu.getModeDeJeu().equals(ModeDeJeu.MAST_CHAL)) {
				mastermindChal.doClick();
			}
			else if (jeu.getModeDeJeu().equals(ModeDeJeu.MAST_DEF)) {
				mastermindDef.doClick();
			}
			else {
				mastermindDuel.doClick();
			}
		}
		else if (choixFinJeu == "menuPrincipal"){
			contentPane.removeAll();
			contentPane.add(accueil, BorderLayout.CENTER);
			contentPane.repaint();
		}
	}

	public void update(boolean test) {
		// TODO a tester 
		
	}


	public void update(Jeu jeu) {
		// TODO Auto-generated method stub
		
	}
}
