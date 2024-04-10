package View;
import Model.Plateau;
import Model.Tours;
import Model.TypePiece;
import Model.CaseEchec;
import Model.Coups;
import Model.Jeu;
import Model.Joueur;
import Model.Pion;

import javax.swing.*;
import java.io.File;

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
    private Joueur joueurActuel;
    
    public JeuView() {
        InitialiseJeuView();
        this.plateau = new Plateau();
        this.listeCasesPossible = new ArrayList<CaseEchec>();
        this.joueurActuel = Jeu.joueurs[0]; // Mets le joueurs blanc au commencement.
    }
/**
 * Initialise le jeu.
 */
    private void InitialiseJeuView() {
        setTitle("Jeu d'échecs");
        setSize(800, 800); // Taille de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // Utilisation de BorderLayout pour organiser les composants

        JPanel boardPanel = new JPanel(new GridLayout(8, 8)); // Panneau pour l'échiquier
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                CaseEchec caseEchec = Plateau.cases[j][i]; // Obtient la case correspondante();
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
        if(joueurActuel == Jeu.joueurs[0]){
            scoreLabel = new JLabel("->" + Jeu.joueurs[0].getNom() + " " + Jeu.joueurs[0].getVictoires() + " : " + Jeu.joueurs[1].getNom() + " : " + Jeu.joueurs[1].getVictoires()); 
        } else { 
            scoreLabel = new JLabel(Jeu.joueurs[0].getNom() + " " + Jeu.joueurs[0].getVictoires() + " : " + "->" + Jeu.joueurs[1].getNom() + " : " + Jeu.joueurs[1].getVictoires()); 
        }
        scoreLabel = new JLabel(Jeu.joueurs[0].getNom() + " " + Jeu.joueurs[0].getVictoires() + " : " + Jeu.joueurs[1].getNom() + " : " + Jeu.joueurs[1].getVictoires());
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);

        add(scoreLabel, BorderLayout.NORTH);
        
        add(boardPanel, BorderLayout.CENTER);

        setVisible(estVisible);
    }

    public void miseAJourScore() {
        if(joueurActuel == Jeu.joueurs[0]){
            scoreLabel.setText("->" + Jeu.joueurs[0].getNom() + " " + Jeu.joueurs[0].getVictoires() + " : " + Jeu.joueurs[1].getNom() + " : " + Jeu.joueurs[1].getVictoires()); 
        } else { 
            scoreLabel.setText(Jeu.joueurs[0].getNom() + " " + Jeu.joueurs[0].getVictoires() + " : " + "->" + Jeu.joueurs[1].getNom() + " : " + Jeu.joueurs[1].getVictoires()); 
        }
        this.repaint();
    }

/* 
 * Met à jour l'affichage du plateau 
 */

    public void updateBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                CaseEchec caseEchec = Plateau.cases[i][j];
                JButton bouton = buttonGrid[i][j];
                updateButtonIcon(bouton, caseEchec);
                // Met à jour la couleur de fond en fonction du joueur ou d'une sélection
                updateButtonBackground(bouton, i, j);
            }
        }
        miseAJourScore();
        repaint();
        revalidate();
    }
    
/**
 * Met à jour l'icône d'une case
 * @param bouton
 * @param caseEchec
 */
private void updateButtonIcon(JButton bouton, CaseEchec caseEchec) {
    if (caseEchec.estOccupee()) {
        
        // Obtient le chemin de l'image de la pièce
        	
        String iconName = caseEchec.getPiece().toStringComplet() + ".png";
        String imagePath = "src" + File.separator + "View" + File.separator + "img" + File.separator + iconName;
        ImageIcon icon = new ImageIcon(imagePath);
        icon.setImage(icon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        bouton.setIcon(icon);
       // System.out.println(imagePath);
    } else {
        bouton.setIcon(null);  // Supprime l'icône
        bouton.setText("");    // Supprime le texte

    }
}
/**
 * Met à jour la couleur de fond en fonction du joueur ou d'une sélection
 * @param bouton
 * @param row
 * @param col
 */
    private void updateButtonBackground(JButton bouton, int row, int col) {
        if (caseSelectionnee == null) {
            clearHighlights();
        } else {
            highlightMouvementsPossibles();
        }
        Color bgColor = (row + col) % 2 == 0 ? Color.LIGHT_GRAY : Color.DARK_GRAY;
        bouton.setBackground(bgColor);
    }
    

  
    /**
     * Création d'un bouton pour une case
     */
    private JButton createButtonForCase(CaseEchec caseEchec, int i, int j) {
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                JButton caseCliqueButton = (JButton) e.getSource(); // Récupère le bouton qui a déclenché l'événement
                
                CaseEchec caseEchec = obtenirCaseEchecParButton(caseCliqueButton);
                
                if (caseEchec.getPiece() != null && caseSelectionnee == null && caseEchec.getPiece().getCouleur() == joueurActuel.getCouleur()) {
                    // Première sélection : la pièce à déplacer

                    caseSelectionnee = caseEchec;
                    for(Coups c: caseEchec.getPiece().getCoups()) {
                        
                    listeCasesPossible.add(c.getCaseEchec());
                }
                    
                } else {
                    // Deuxième sélection : la destination

                    if (listeCasesPossible.contains(caseEchec)) {
                        caseSelectionnee.deplacerPiece(caseEchec);
                        Tours t = new Tours(joueurActuel, Plateau.cases);
                        Jeu.tours.add(t);
                        joueurActuel = (joueurActuel == Jeu.joueurs[0]) ? Jeu.joueurs[1] : Jeu.joueurs[0];
                        }
                        
                       if(caseEchec.getPiece().getType() == TypePiece.Piece.Pion) {
                            Pion pion = (Pion)caseEchec.getPiece();
                            pion.promotion(); 
                       }

                       
                        
                        updateBoard();
                        
                    

                    
                    caseSelectionnee = null;

                    listeCasesPossible = new ArrayList<CaseEchec>();
                    // Mettez à jour la vue pour refléter les nouveaux états des cases
                    revalidate();
                    repaint(); 
                    
                }
                updateBoard();
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

       /** 
        * change les couleur des cases possibles en vert
        */

    public void highlightMouvementsPossibles(){

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (listeCasesPossible.contains(Plateau.cases[i][j])) {
                    buttonGrid[i][j].setBackground(Color.GREEN);
                }
            }
        }
    }

    /**
     *  remet les cases a la couleur par defaut
     */
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

