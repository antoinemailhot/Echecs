package Model;

public class CaseEchec extends Position {

    // Les attributs :
    private Enum<TypeCouleur.Couleur> couleur;
    private Piece piece;

    // Constructeur par défaut.
    public CaseEchec(Enum<TypeCouleur.Couleur> couleur, int y, int x) {
        super(y, x);
        this.couleur = couleur;
    }

    /**
     * Prends une couleur qui n'est pas null.
     * 
     * @param couleur La couleur de la case.
     */
    public void setCouleur(Enum<TypeCouleur.Couleur> couleur) {
        if(couleur != null) {
            this.couleur = couleur;
        }
    }

    /**
     * accesseur de lobjet piece.
     */
    public Piece getPiece() {
        return this.piece;
    }

    /**
     * Placer une piece sur la case.
     * 
     * @param piece La piece à placer.
     */
    public void placerPiece(Piece piece) {
        this.piece = piece;
      
    }

    /*
    * Déplace la pièce vers la nouvelle case.
    */
    public void deplacerPiece(CaseEchec caseEchec) {
        caseEchec.piece = this.piece;
        this.piece = null;
    }

    /**
     * Boolean qui permet de savoir si une case est occupee
     * 
     */
    public boolean estOccupee() {
        return this.piece != null;
    }

    /**
     * Donne la couleur de la case.
     * 
     * @return Retourne la couleur de la case.
     */
    public Enum<TypeCouleur.Couleur> getCouleur() {
        return couleur;
    }

    /**
     * Retourne le y de la case.
     */
    public int getY() {
        return super.getY();
    }

    /**
     * Retourne le x de la case.
     */
    public int getX() {
        return super.getX();
    }
}
