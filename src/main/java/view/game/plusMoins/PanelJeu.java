package main.java.view.game.plusMoins;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import main.java.controller.Controller;
import main.java.model.Configuration;
import main.java.model.Jeu;
import main.java.model.ModeDeJeu;
import main.java.model.ModeDePartie;
import main.java.observer.Observateur;

public class PanelJeu extends JPanel {

    private Jeu jeu;
    private	Controller controller, controller2;
    private Thread thread;
    private GamePanelPlusMoins jpChal, jpDef;
    private Configuration configuration;
    private Observateur obs;
    private Dimension bigSize        = new Dimension (1710, 1050),
                      smallSize     = new Dimension (845, 1040),
                      bourrageSize  = new Dimension(430, 1040);

    public PanelJeu(Jeu jeu, Configuration configuration, Observateur obs) {
        this.jeu = jeu;
        this.configuration = configuration;
        this.obs = obs;
        init();
    }

    public void init() {
        if(jeu.getModeDeJeu().equals(ModeDeJeu.PLUS_CHAL)){
            this.setPreferredSize(smallSize);
            controller = new Controller(configuration,jeu.getPartie1(), jeu);
            jpChal = new GamePanelPlusMoins(configuration, jeu.getModeDeJeu(), jeu.getPartie1(), controller);

            ajouterPanneauxDeBourrageDeChaqueCote();
            this.add(jpChal, BorderLayout.CENTER);
        }
        else if(jeu.getModeDeJeu().equals(ModeDeJeu.PLUS_DEF)) {
            this.setPreferredSize(smallSize);
            controller = new Controller(configuration,jeu.getPartie1(), jeu);
            PopUpCombi popUpCombi = new PopUpCombi(null, "choix de la combinaison", true, configuration, jeu.getPartie1(), obs);
            jpDef = new GamePanelPlusMoins(configuration, jeu.getModeDeJeu(), jeu.getPartie1(), controller);
            if(configuration.isDevModEnJeu() == true && jeu.getPartie1().getModeDePartie() == ModeDePartie.PLUS_DEF) {
                controller.sendProposition(jeu.getPartie1());
                jpDef.devIndice();
                System.out.println("dans le if");
            }
            else {System.out.println("dans le else");}

            ajouterPanneauxDeBourrageDeChaqueCote();
            this.add(jpDef, BorderLayout.CENTER );
        }
        else if(jeu.getModeDeJeu().equals(ModeDeJeu.PLUS_DUEL)) {
            this.setPreferredSize(bigSize);
            controller = new Controller(configuration,jeu.getPartie1(), jeu);
            jpChal = new GamePanelPlusMoins(configuration, jeu.getModeDeJeu(), jeu.getPartie1(), controller);
            jpChal.setPreferredSize(smallSize);
            JPanel jpEspace = new JPanel();
            jpEspace.setPreferredSize(new Dimension(10, 1040));
            controller2 = new Controller(configuration,jeu.getPartie2(), jeu);
            PopUpCombi popUpCombi = new PopUpCombi(null, "choix de la combinaison", true, configuration, jeu.getPartie2(), obs);
            jeu.getPartie2().setActif(false);
            jpDef = new GamePanelPlusMoins(configuration, jeu.getModeDeJeu(), jeu.getPartie2(), controller);
            jpDef.setPreferredSize(smallSize);
            if(configuration.isDevModEnJeu() == true ) {
                controller.sendProposition(jeu.getPartie2());
                jpDef.devIndice();
            }

            this.setLayout(new BorderLayout());
            this.add(jpChal, BorderLayout.WEST);
            this.add(jpEspace, BorderLayout.CENTER);
            this.add(jpDef, BorderLayout.EAST);
        }
    }

    /**
     *  Les panneaux de bourrage
     */
    private void ajouterPanneauxDeBourrageDeChaqueCote() {
        this.setLayout(new BorderLayout());
        JPanel jpLeft = new JPanel();
        JPanel jpRight = new JPanel();
        jpLeft.setPreferredSize(bourrageSize);
        jpRight.setPreferredSize(bourrageSize);
        this.add(jpLeft, BorderLayout.WEST);
        this.add(jpRight, BorderLayout.EAST);
    }

    /**
     * Le focus du challenger passe en rouge (tout est en rouge) et une seconde plus tard, le focus de la partie Défenseur passe en vert.
     */
    public void defTurn() {
        jpChal.stopTurn();
        updateFocus(jpDef, 2);

    }
    public void chalTurn() {
        jpDef.stopTurn();
        updateFocus(jpChal, 2);
    }

    /**
     * Mettre à jour le focus du second panneau de jeu via un Thread Indépendant.
     * @param jpTarget
     */
    public static void updateFocus(GamePanelPlusMoins jpTarget, Integer secondes) {
        new Thread(new Runnable() {
            public void run() {
                sleep(secondes);
                //-- Modification de notre composant dans l'EDT
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        jpTarget.newTurn();
                    }
                });
                //-- Si l'EDT est actif, le Thread est lancée sinon il le sera par l'EDT plus tard
                if (SwingUtilities.isEventDispatchThread())
                    t.start();
                else {
                    SwingUtilities.invokeLater(t);
                }
            }
        }).start();
    }

    /**
     * Lance une temporisation de x secondes.
     */
    private static void sleep(Integer secondes) {
        try {
            Thread.sleep(secondes * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


