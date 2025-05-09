package Model;

public class Joueur {
    // Les attrubuts :
    private String nom;
    private TypeCouleur.Couleur couleur;
    private int victoires ;

    // Constructeur.
    public Joueur(String nom, TypeCouleur.Couleur couleur) {
        this.nom = nom;
        this.couleur = couleur;
        this.victoires = 0;
    }

    /**
     * Prends un nom.
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Prends une couleur.
     * @param couleur
     */
    public void setCouleur(TypeCouleur.Couleur couleur) {
        this.couleur = couleur;
    }

    /**
     * Prends un nombre de victoire.
     * @param victoires
     */
    public void setVictoires(int victoires) {
        this.victoires = victoires;
    }

    /**
     * Obtient le nom.
     * @return Retourne le nom.
     */
    public String getNom() {
        return nom;
    }
 
    /**
     * Obtient la couleur.
     * @return Retourne la couleur.
     */
    public TypeCouleur.Couleur getCouleur() {
        return couleur;
    }
    
    /**
     * Obtient le nombre de victoire.
     * @return Retourne le nombre de victoire.
     */
    public int getVictoires() {
        return victoires;
    }

    public String toString() {
        return this.getNom() + " à la couleur " + this.getCouleur() + " et à " + this.getVictoires() + " victoires à son actif.";
    }
    
}
