/* package View;
import javax.swing.JFrame;


public class JeuView extends JFrame {
    private int sourisX, sourisY; // Position de la souris

    public String joueur1Nom;
    public String joueur2Nom;
    public String score1;
    public String score2;
    

    public JeuView() {
        // Définition du titre.
        setTitle("Échec");
        // Définition de l'action de sortie.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Définition de la taille par défaut de la fenêtre.
        setSize(700,700);
        // Définition dela visibilité.
        setVisible(true);
        // Mets la fenetre au centre de l ecran.
        setLocationRelativeTo(null);
        
        
        
        
    }

} */
package View;
import Model.Joueur;
import Model.Plateau;
import Model.CaseEchec;
import Model.Piece;
import Model.Jeu;
import Model.TypeCouleur.Couleur;
import javax.swing.*;

import Controller.JeuController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JeuView extends JFrame {
    private Plateau plateau;
    private JLabel scoreLabel; // Affiche les scores des joueurs
    public  static JButton[][] buttonGrid = new JButton[8][8]; // Tableau pour stocker les références des boutons
    private boolean estVisible = false;

    public JeuView() {
        InitialiseJeuView();
        this.plateau = new Plateau();
    }

    private void InitialiseJeuView() {
        setTitle("Jeu d'échecs");
        setSize(800, 800); // Taille de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // Utilisation de BorderLayout pour organiser les composants

        JPanel boardPanel = new JPanel(new GridLayout(8, 8)); // Panneau pour l'échiquier
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                CaseEchec caseEchec = Plateau.cases[i][j]; // Obtient la case correspondante();
                JButton bouton = createButtonForCase(caseEchec);
                boardPanel.add(bouton);
                buttonGrid[i][j] = bouton; // Stocke le bouton dans le tableau
              
            }
        }
        // Création de la barre de menus
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar); // Ajoute la barre de menus au JFrame

        // Ajout d'un menu "Options"
        JMenu menuOptions = new JMenu("Options");
        menuBar.add(menuOptions);

        // Ajout de l'option "Nouvelle Partie" au menu
        JMenuItem menuItemNewGame = new JMenuItem("Nouvelle Partie");
        menuItemNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                plateau.initialiserPartie(); // Réinitialise le plateau
                updateBoard(); // Met à jour l'affichage du plateau
            }
        });
            menuOptions.add(menuItemNewGame);
       

        // Création du label pour afficher les scores
        scoreLabel = new JLabel(Jeu.joueurs[0].getNom() + " " + Jeu.joueurs[0].getVictoires() + " : " + Jeu.joueurs[1].getNom() + " : " + Jeu.joueurs[1].getVictoires());
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);

        add(scoreLabel, BorderLayout.NORTH);
        
        add(boardPanel, BorderLayout.CENTER);

        setVisible(estVisible);
    }

    public void miseAJourScore() {
        scoreLabel.setText(Jeu.joueurs[0].getNom() + " " + Jeu.joueurs[0].getVictoires() + " : " + Jeu.joueurs[1].getNom() + " : " + Jeu.joueurs[1].getVictoires());
        this.repaint();
    }

    public static void updateBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                CaseEchec caseEchec = Plateau.cases[i][j];
                JButton bouton = buttonGrid[i][j];
                if (caseEchec.estOccupee()) {
                    String nom = "";
                    if(caseEchec.getPiece().getCouleur() == Jeu.joueurs[0].getCouleur()) {
                        nom = Jeu.joueurs[0].getNom();
                    } else {
                        nom = Jeu.joueurs[1].getNom();
                    }   
                    bouton.setText(caseEchec.getPiece().toString() + "\n" + nom); // Met à jour avec la nouvelle pièce
                } else {
                    bouton.setText(""); // Efface le texte si la case n'est pas occupée
                }
                // Met à jour la couleur de fond en fonction du joueur ou d'une sélection
                if ((i + j) % 2 == 0) {
                    bouton.setBackground(Color.LIGHT_GRAY);
                } else {
                    bouton.setBackground(Color.DARK_GRAY);
                }
            }
        }
    }
  
    

    private JButton createButtonForCase(CaseEchec caseEchec, int i, int j) {
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JeuController.getcaSeselectioner() == null) {
                    // Première sélection : la pièce à déplacer
                    caseSelectionnee = caseEchec;
                    mouvementsPossibles = controller.obtenirMouvementsPossibles(caseEchec);
                    highlightMouvementsPossibles(mouvementsPossibles);
                } else {
                    // Deuxième sélection : la destination
                    if (mouvementsPossibles.contains(caseEchec)) {
                        controller.deplacerPiece(caseSelectionnee, caseEchec);
                        clearHighlights();
                        caseSelectionnee = null;
                        mouvementsPossibles = null;
                    } else {
                        // Si le clic n'est pas valide, réinitialisez la sélection ou affichez un message
                        clearHighlights();
                        caseSelectionnee = null;
                        mouvementsPossibles = null;
                        // Optionnel : Afficher un message d'erreur ou un signal visuel
                    }
                }
                jeuView.updateUI();  // Mettez à jour la vue pour refléter les nouveaux états des cases
            }
        });
        return button;
    }

    
    }

