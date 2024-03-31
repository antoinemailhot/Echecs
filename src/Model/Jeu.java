package Model;

import java.util.ArrayList;

public class Jeu {

    // Les attributs :
    private Plateau plateau;
    private ArrayList<Tours> tours;
    private Joueur[] joueurs;

    // Le constructeur par défaut.
    public Jeu() {
        this.plateau = new Jeu();
        this.tours = new ArrayList<Tours>();
    }

    /**
     * Création d'une nouvelle partie.
     */
    public void creerPartie() {

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
    public Tours deplacer() {
        Tours toursActuel = new Tours();
        return toursActuel;
    }

}