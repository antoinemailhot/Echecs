package Model;

public class Pion extends Piece {

    // Les attributs:
    private TypeDirection.Direction direction;

    // Constructeur :
    public Pion(CaseEchec caseEchec, TypeCouleur.Couleur couleur, TypeDirection.Direction direction) {
        super(caseEchec, TypePiece.Piece.Pion, couleur);
        this.direction = direction;
    }
    
    /**
     * Renvoie l'évolution de la pièce lorsqu'elle atteint l'autre côté du jeu.
     * @return Retourne l'évolution de la pièce.
     */
    //public Piece evolution() {
        
    //}

    /**
     * Écrit en string le type de la pièce.
     */
    public String toString() {
        return super.getType().toString();
    }
}
