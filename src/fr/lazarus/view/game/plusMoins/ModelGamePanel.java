package fr.lazarus.view.game.plusMoins;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.lazarus.controller.Controller;
import fr.lazarus.model.Configuration;
import fr.lazarus.model.Partie;

public class ModelGamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	//-- Les logs
	private static final Logger logger = LogManager.getLogger();

	private Configuration config;	
	private Partie partie;
	private Controller controller;

	private JPanel jpInfo, jpGrid, jpProp, jpSolution;
	private JLabel jlTour, jlJoueur, jlSolution;
	private JButton buttOk;
	private JFormattedTextField jfProp;
	private JTextField jtIndice;
	private Dimension size = new Dimension(800,1024), size_info = new Dimension(800, 200);
	private int nbreChiffre, nbretour;
	private String position;
	private boolean indiceIsOk;

	public ModelGamePanel(String position, Configuration config,Partie partie, String modJeu, Controller controller) {
		this.position = position;
		this.config = config;
		this.partie = partie;
		this.controller = controller;
		nbretour = config.getTourPlusMoins();
		nbreChiffre = config.getCombiPlusMoins();
		this.setBackground(Color.white);
		this.setMinimumSize(size);
		this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		initPanel();
		this.setVisible(true);
	}

	public void initPanel(){

		//-- Panel info
		jpInfo = new JPanel();
		jpInfo.setPreferredSize(size_info);
		jpInfo.setLayout(new BorderLayout());
		jpInfo.setBackground(Color.white);
		jpSolution = new JPanel();
		jpSolution.setBackground(Color.white);

		jlTour = new JLabel("NBR DE TOUR");
		jlTour.setFont(new Font("Lucida Console", Font.BOLD, 30));
		jlTour.setBorder(BorderFactory.createTitledBorder("Nombre de tour restant"));
		jlTour.setPreferredSize(new Dimension(386, 100));
		jlTour.setHorizontalAlignment(JLabel.CENTER);

		jlSolution = new JLabel(partie.getSolution());
		jlSolution.setFont(new Font("Lucida Console", Font.BOLD, 30));
		jlSolution.setBorder(BorderFactory.createTitledBorder("La solution"));
		jlSolution.setPreferredSize(new Dimension(386, 100));
		jlSolution.setHorizontalAlignment(JLabel.CENTER);

		//La solution : si le panel de gauche avec le devMode desactivé, mise en non visible.
		if (position == "droite")
			jlSolution.setVisible(true);
		else if (position == "gauche" && config.isDevModEnJeu() == true)
			jlSolution.setVisible(true);
		else
			jlSolution.setVisible(false);
		jpSolution.add(jlTour);
		jpSolution.add(jlSolution);

		if (position == "gauche")
			jlJoueur = new JLabel("Humain");
		else
			jlJoueur = new JLabel("Ordinateur");

		jlJoueur.setFont(new Font("Lucida Console", Font.BOLD, 30));
		jlJoueur.setBorder(BorderFactory.createTitledBorder("joueur"));
		jlJoueur.setPreferredSize(new Dimension(350, 80));
		jlJoueur.setHorizontalAlignment(JLabel.CENTER);
		jlJoueur.setBackground(Color.white);

		jpInfo.add(jpSolution, BorderLayout.NORTH);
		jpInfo.add(jlJoueur, BorderLayout.SOUTH);

		//-- Panel grid
		jpGrid = new JPanel();
		jpGrid.setPreferredSize(new Dimension(800, 800));
		jpGrid.setBackground(Color.WHITE);
		jpGrid.setLayout(new GridLayout(nbretour, 2, 3, 3));

		if(position == "droite") {
			JPanel b2 = new JPanel();
			b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
			b2.setBackground(Color.WHITE);
			b2.setMinimumSize(new Dimension(790, 80));

			JLabel t1 = new JLabel();
			t1.setPreferredSize(new Dimension(386, 40));
			t1.setFont(new Font("Lucida Console", Font.BOLD, 30));        
			t1.setBorder(BorderFactory.createBevelBorder(0));
			t1.setSize(new Dimension(386, 100));                 
			t1.setHorizontalAlignment(JLabel.CENTER);    
			t1.setBackground(Color.LIGHT_GRAY);
			t1.setText(partie.getProposition());

			b2.add(t1);
			jpGrid.add(b2);
		}


		//-- Panel proposition/indices
		jpProp = new JPanel();
		jpProp.setSize(new Dimension(800, 150));
		if (position == "gauche") {
			jpProp.setBorder(BorderFactory.createTitledBorder("Propostition"));
			try{	
				char [] charac = new char[nbreChiffre];
				for (int i = 0; i<nbreChiffre; i++) {
					charac[i] = '#';
				}
				String str = new String(charac);
				MaskFormatter mask = new MaskFormatter(str);
				jfProp = new JFormattedTextField(mask);
			}catch(ParseException e){e.printStackTrace();}
			jfProp.setFont(new Font("Lucida Console", Font.BOLD, 30));
			jfProp.setHorizontalAlignment(JLabel.CENTER);
			jfProp.setPreferredSize(new Dimension(550, 50));
			jpProp.add(jfProp);
		}
		else {
			jpProp.setBorder(BorderFactory.createTitledBorder("indice"));
			jtIndice = new JTextField();
			jtIndice.setFont(new Font("Lucida Console", Font.BOLD, 30));
			jtIndice.setHorizontalAlignment(JLabel.CENTER);
			jtIndice.setPreferredSize(new Dimension(550, 50));
			jpProp.add(jtIndice);
		}
		jpProp.setBackground(Color.white);


		buttOk = new JButton("ok");
		buttOk.setPreferredSize(new Dimension(150, 50));
		buttOk.addActionListener(new OkListener());
		jpProp.add(buttOk);


		this.setLayout(new BorderLayout());
		this.add(jpInfo, BorderLayout.NORTH);
		this.add(jpGrid, BorderLayout.CENTER);
		this.add(jpProp, BorderLayout.SOUTH);
	}

	/**
	 * Verifie l'integrité de l'indice avant l'envoi.
	 * @param indice
	 */
	public void isOkIndice(String indice) {
		indiceIsOk = true;

		if (indice.length() != nbreChiffre) {
			JOptionPane.showMessageDialog(null, "Erreur ! \n Veuillez entrer une solution à "+ nbreChiffre +".", "ERREUR", JOptionPane.ERROR_MESSAGE);
			indiceIsOk = false;
			jtIndice.setText("");
		}
		else if (Pattern.matches("^[+-=]{7}$", indice)){
			JOptionPane.showMessageDialog(null, "Erreur ! \n Veuillez n'entrer que les signes \"+\", \"-\" ou \"=\".", "ERREUR", JOptionPane.ERROR_MESSAGE);
			indiceIsOk = false;
			jtIndice.setText("");
		}
		else {
			indiceIsOk = true;
		}
	}

	/**
	 * l'action de OkButton, envoi la proposition au controller
	 * @author Ben
	 *
	 */
	class OkListener implements ActionListener{
		public void actionPerformed(ActionEvent x) {

			JPanel b2 = new JPanel();
			b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
			b2.setBackground(Color.WHITE);
			b2.setMinimumSize(new Dimension(790, 80));

			//proposition
			JLabel t1 = new JLabel();
			t1.setPreferredSize(new Dimension(386, 40));
			t1.setFont(new Font("Lucida Console", Font.BOLD, 30));        
			t1.setBorder(BorderFactory.createBevelBorder(0));
			t1.setSize(new Dimension(386, 100));                 
			t1.setHorizontalAlignment(JLabel.CENTER);    
			t1.setBackground(Color.LIGHT_GRAY);

			//indication
			JLabel t2 = new JLabel();
			t2.setPreferredSize(new Dimension(386, 40));
			t2.setFont(new Font("Lucida Console", Font.BOLD, 30));
			t2.setBorder(BorderFactory.createBevelBorder(0));
			t2.setSize(new Dimension(386, 100));                 
			t2.setHorizontalAlignment(JLabel.CENTER);    
			t2.setBackground(Color.LIGHT_GRAY);

			if (position == "gauche") {
				String proposition = jfProp.getText();
				partie.setProposition(proposition);
				controller.sendProposition(partie);
				t1.setText(proposition);
				t2.setText(partie.getIndice());
				logger.debug("envoi de la proposition : "+proposition+"  au controleur");
				//nombre de tour
				jlTour.setText(Integer.toString(partie.getTour()));
				//vider jfprop
				jfProp.setText("");
				b2.add(t1);
				b2.add(t2);
				jpGrid.add(b2);
				jpGrid.revalidate();
			}
			else {
				//TODO pour le coté droit : modifier l'entrée avec indice et un JTextField qui sera remplit par le model et verifié par le controller
				String indice = jtIndice.getText();
				isOkIndice(indice);

				if (indiceIsOk) {
					partie.setIndice(indice);
					controller.sendIndice(partie);
					t1.setText(partie.getProposition());
					t2.setText(indice);
					logger.debug("envoi de l'indice : "+indice+" au controleur");
					//nombre de tour
					jlTour.setText(Integer.toString(partie.getTour()));
					//vider jtIndice
					jtIndice.setText("");
					b2.add(t1);
					b2.add(t2);
					jpGrid.add(b2);
					jpGrid.revalidate();
				}	
			}
		}
	}
}
