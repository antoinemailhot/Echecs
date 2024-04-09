       
package Controller; 
import Model.CaseEchec;
import View.FenetreNomsJoueurs;
import View.JeuView;          
import Model.Plateau;
import Model.Jeu;
import javax.swing.JFrame;
import javax.swing.*;
import java.util.ArrayList;


    
public class JeuController extends JFrame {
    public String joueur1Nom;
    public String joueur2Nom;
    public String score1;
    public String score2;
    private Jeu jeu;
    private JeuView jeuView;
    private FenetreNomsJoueurs fenetreNomsJoueurs;
    public CaseEchec caseSelectionnee;
    private ArrayList<CaseEchec> mouvementsPossibles;


    public JeuController(Jeu jeu, JeuView jeuView, FenetreNomsJoueurs fnj) {
        this.jeu = jeu;
        this.jeuView = jeuView;
        this.fenetreNomsJoueurs = fnj;
    }

    public void attachEventHandlers() { // Call attachEventHandlers on the JeuController instance pour que quand la fenetre est fermer le board apparait
        fenetreNomsJoueurs.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                jeuView.miseAJourScore();
                jeuView.updateBoard();
                jeuView.setVisible(true);
            }
        });
    }

    public void caseClicked(int i, int j) { 
            
        }
    

     public CaseEchec getcaSeselectioner(){
        return caseSelectionnee;
     }

    public CaseEchec setcaSelectionner(CaseEchec caseEchec){
        caseSelectionnee = caseEchec;
        return caseSelectionnee;

    }









}
