package View;
import Model.Jeu;
import Controller.JeuController;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MVCSwingEchec {

    public static void main(String[] args) {
        // Instance Model
        Jeu jeu = new Jeu();

        // Instance Views
        
        FenetreNomsJoueurs fenetreNomsJoueurs = new FenetreNomsJoueurs();

        JeuView jeuVue = new JeuView();
        
        JeuController jeuController = new JeuController(jeu, jeuVue, fenetreNomsJoueurs);
        
      // Ajouter un écouteur pour la fermeture de la fenêtre
      jeuController.attachEventHandlers(); // Call attachEventHandlers on the JeuController instance

    // Rendre la fenêtre visible
    fenetreNomsJoueurs.setVisible(true);
}
}