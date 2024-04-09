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
import Model.Coups;
import Model.Piece;
import Model.Jeu;
import Model.TypeCouleur.Couleur;
import javax.swing.*;

import Controller.JeuController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JeuView extends JFrame {
    private Plateau plateau;
    private JLabel scoreLabel; // Affiche les scores des joueurs
    public  static JButton[][] buttonGrid = new JButton[8][8]; // Tableau pour stocker les références des boutons
    private boolean estVisible = false;
    private CaseEchec caseSelectionnee = null;
    private ArrayList<CaseEchec> listeCasesPossible;
    
    public JeuView() {
        InitialiseJeuView();
        this.plateau = new Plateau();
        this.listeCasesPossible = new ArrayList<CaseEchec>();
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
                JButton bouton = createButtonForCase(caseEchec, i, j);
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
                    
                    ImageIcon iconPionBlanche;
                    ImageIcon icontourBlanche;
                    ImageIcon iconFouBlanche;
                    ImageIcon iconChevalierBlanche;
                    ImageIcon iconReineBlanche;
                    ImageIcon iconRoiBlanche;
                    ImageIcon iconPionNoir;
                    ImageIcon icontourNoir;
                    ImageIcon iconFouNoir;
                    ImageIcon iconChevalierNoir;
                    ImageIcon iconReineNoir;
                    ImageIcon iconRoiNoir;
                    if(caseEchec.getPiece().getCouleur() == Jeu.joueurs[0].getCouleur()) {
                       if (caseEchec.getPiece().getType() == Model.TypePiece.Piece.Pion) {
                        
                        iconPionBlanche = new ImageIcon("src\\View\\img\\wP.png");
                        iconPionBlanche.setImage(iconPionBlanche.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                        bouton.setIcon(iconPionBlanche);
                       }
                       if (caseEchec.getPiece().getType() == Model.TypePiece.Piece.Tour) {
                        
                        icontourBlanche = new ImageIcon("src\\View\\img\\wR.png");
                        icontourBlanche.setImage(icontourBlanche.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                        bouton.setIcon(icontourBlanche);
                       }
                       if (caseEchec.getPiece().getType() == Model.TypePiece.Piece.Fou) {
                       
                        iconFouBlanche = new ImageIcon("src\\View\\img\\wB.png");
                        iconFouBlanche.setImage(iconFouBlanche.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                        bouton.setIcon(iconFouBlanche);
                       }
                       if (caseEchec.getPiece().getType() == Model.TypePiece.Piece.Chevalier) {
                       
                        iconChevalierBlanche = new ImageIcon("src\\View\\img\\wN.png");
                        iconChevalierBlanche.setImage(iconChevalierBlanche.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                        bouton.setIcon(iconChevalierBlanche);
                       }
                       if (caseEchec.getPiece().getType() == Model.TypePiece.Piece.Roi) {
                        
                        iconRoiBlanche = new ImageIcon("src\\View\\img\\wK.png");
                        iconRoiBlanche.setImage(iconRoiBlanche.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                        bouton.setIcon(iconRoiBlanche);
                       }
                       if (caseEchec.getPiece().getType() == Model.TypePiece.Piece.Reine) {
                       
                        iconReineBlanche = new ImageIcon("src\\View\\img\\wQ.png");
                        iconReineBlanche.setImage(iconReineBlanche.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                        bouton.setIcon(iconReineBlanche);
                       }
                    } else {
                        if (caseEchec.getPiece().getType() == Model.TypePiece.Piece.Pion) {
                        
                            iconPionNoir = new ImageIcon("src\\View\\img\\bP.png");
                            iconPionNoir.setImage(iconPionNoir.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                            bouton.setIcon(iconPionNoir);
                           }
                           if (caseEchec.getPiece().getType() == Model.TypePiece.Piece.Tour) {
                            
                            icontourNoir = new ImageIcon("src\\View\\img\\bR.png");
                            icontourNoir.setImage(icontourNoir.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                            bouton.setIcon(icontourNoir);
                           }
                           if (caseEchec.getPiece().getType() == Model.TypePiece.Piece.Fou) {
                           
                            iconFouNoir = new ImageIcon("src\\View\\img\\bB.png");
                            iconFouNoir.setImage(iconFouNoir.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                            bouton.setIcon(iconFouNoir);
                           }
                           if (caseEchec.getPiece().getType() == Model.TypePiece.Piece.Chevalier) {
                           
                            iconChevalierNoir = new ImageIcon("src\\View\\img\\bN.png");
                            iconChevalierNoir.setImage(iconChevalierNoir.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                            bouton.setIcon(iconChevalierNoir);
                           }
                           if (caseEchec.getPiece().getType() == Model.TypePiece.Piece.Roi) {
                            
                            iconRoiNoir = new ImageIcon("src\\View\\img\\bK.png");
                            iconRoiNoir.setImage(iconRoiNoir.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                            bouton.setIcon(iconRoiNoir);
                           }
                           if (caseEchec.getPiece().getType() == Model.TypePiece.Piece.Reine) {
                           
                            iconReineNoir = new ImageIcon("src\\View\\img\\bQ.png");
                            iconReineNoir.setImage(iconReineNoir.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                            bouton.setIcon(iconReineNoir);
                           }
                    }   
                    //bouton.setText(caseEchec.getPiece().toString() + "\n" + nom); // Met à jour avec la nouvelle pièce
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
            /**
             * Va chercher la première case selectionner qui a une Piece.
             * Va chercher ses coups possible avec getCoupsValides et highligh les possibilités.
             * Va positioner la pièce a l'endroit selectionner.
             */
            public void actionPerformed(ActionEvent e) {
                JButton caseCliqueButton = (JButton) e.getSource(); // Récupère le bouton qui a déclenché l'événement
                
                CaseEchec caseEchec = obtenirCaseEchecParButton(caseCliqueButton);
                if (caseEchec.getPiece() != null && caseSelectionnee == null) {
                    // Première sélection : la pièce à déplacer
                    caseSelectionnee = caseEchec;
                    for(Coups c: caseEchec.getPiece().getCoups()) {
                        listeCasesPossible.add(c.getCaseEchec());
                    }

                    highlightMouvementsPossibles();
                    
                } else {
                    // Deuxième sélection : la destination

                    if (listeCasesPossible.contains(caseEchec)) {
                        caseEchec.placerPiece(caseSelectionnee.getPiece());
                        caseSelectionnee.placerPiece(null);
                        clearHighlights();
                        
                        listeCasesPossible.clear();
                    } else {
                        // Si le clic n'est pas valide, réinitialisez la sélection ou affichez un message
                        clearHighlights();
                        caseSelectionnee = null;
                        listeCasesPossible = null;
                        // Optionnel : Afficher un message d'erreur ou un signal visuel
                    }

                    

                }
                updateBoard();  // Mettez à jour la vue pour refléter les nouveaux états des cases
            }
        });
        return button;
    }

    /**
     * Retourne la caseEchec correcpondant a la place du bouton.
     */
    private CaseEchec obtenirCaseEchecParButton(JButton jButton) {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(buttonGrid[i][j] == jButton) {
                    return Plateau.cases[i][j];
                }
            }
        }

        return null;
    }
    public void highlightMouvementsPossibles(){

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (listeCasesPossible.contains(Plateau.cases[i][j])) {
                    buttonGrid[i][j].setBackground(Color.GREEN);
                }
            }
        }
    }

    public void clearHighlights() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    buttonGrid[i][j].setBackground(Color.LIGHT_GRAY);
                } else {
                    buttonGrid[i][j].setBackground(Color.DARK_GRAY);
                }
            }
        }
       
    }
}

