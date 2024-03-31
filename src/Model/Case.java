package Model;

public class Case extends Position {

    // Les attributs :
    private Enum couleur;

    // Constructeur par défaut.
    Case(Enum couleur, int x, int y) {
        super(x,y);
        this.couleur = couleur;
    }
    
    /**
     * Prends une couleur.
     * @param couleur La couleur de la case.
     */
    public void setCouleur(Enum couleur) {
        this.couleur = couleur;
    }

    /**
     * Donne la couleur de la case.
     * @return Retourne la couleur de la case.
     */
    public Enum getCouleur() {
        return couleur;
    }
}
