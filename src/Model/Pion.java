package Model;

public class Pion extends Piece {

    // Les attributs:
    private TypeDirection.Direction direction;
    private boolean estPremierCoup = true;

    // Constructeur :
    public Pion(TypeCouleur.Couleur couleur, TypeDirection.Direction direction) {
        super(TypePiece.Piece.Pion, couleur);
        this.direction = direction;
    }
    
    /**
     * Renvoie l'évolution de la pièce lorsqu'elle atteint l'autre côté du jeu.
     * @return Retourne l'évolution de la pièce.
     */
    public void promotion() {

        // Obtenir la case du Pion.
        CaseEchec caseActuel = null;

        for(int i = 0; i < Plateau.cases.length; i++) {
            for(int j = 0; j < Plateau.cases[i].length; j++) {
                if(Plateau.cases[i][j].getPiece() == this) {
                    caseActuel = Plateau.cases[i][j];
                }
            }
        }

        // Verification que caseActuel n est pas null.
        if(caseActuel != null) {
            if(caseActuel.getY() == 0 || caseActuel.getY() == 7) {
                caseActuel.placerPiece(new Reine(TypePiece.Piece.Reine, this.couleur));
            }
        }
    }

    /**
     * Renvoie la direction.
     * @return Retourne la direction.
     */
    public TypeDirection.Direction getDirection() {
        return this.direction;
    }

    /**
     * Renvoie le premier coups.
     * @return Retourne le premier coups.
     */
    public boolean estPremierCoup() {
        return this.estPremierCoup;
    }

    /**
     * Écrit en string le type de la pièce.
     */
    public String toString() {
        return super.getType().toString();
    }

    /**
     * Écrit en string le type de la pièce et sa couleur.
     */
    public String toStringComplet() {
        return this.toString() + super.getCouleur().toString();
    }
}
