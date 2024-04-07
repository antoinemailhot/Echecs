package View;
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
        
        
        
    }

}