package Model;

import java.util.ArrayList;
import Model.TypeCouleur.Couleur;
public class Jeu {

    // Les attributs :
    private Plateau plateau;
    private ArrayList<Tours> tours;
    public static Joueur[] joueurs;

    // Le constructeur par défaut.
    public Jeu() {
        this.plateau = new Plateau();
        this.tours = new ArrayList<Tours>();
        creerPartie();
        joueurs = new Joueur[2];
        joueurs[0] = new Joueur("joueur 1",TypeCouleur.Couleur.Blanc);
        joueurs[1] = new Joueur("Joueur 2", Couleur.Noir);
        
    }

    /**
     * Création d'une nouvelle partie.a
     */
    public void creerPartie() {
      this.plateau.initialiserPartie();
    }

    /**
     * Termine une partie.
     */
    public void terminerPartie() {

    }

    /**
     * Déplacement
     * @return Retourne le déplacement (Tours) effectué.
     */
   // public Tours deplacer() {

        // Il faut obtenir le joueur actuel.

        // Ensuite on crée son tours.
   //     Tours toursActuel = new Tours();
   //     return toursActuel;
  //  }

}