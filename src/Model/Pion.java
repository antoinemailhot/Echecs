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
    public Piece promotion() {
        // Vérifie si le pion est sur la rangée de promotion.
        //  les pions blancs la promotion est en y = 0,
        //  les pions noirs la promotion est en y = 7.
        boolean casespromotion = (direction == TypeDirection.Direction.Direction_Origin_Noir && caseEchec.getY() == 0)
                || (direction == TypeDirection.Direction.Direction_Origin_Blanc && caseEchec.getY() == 7);
    
        if (casespromotion) {
            

            return new Reine(caseEchec, TypePiece.Piece.Reine, couleur);
        } else {
            // Aucune promotion, retourne l'instance actuelle du pion.
            return this;
        }
    }
    /**
     * Écrit en string le type de la pièce.
     */
    public String toString() {
        return super.getType().toString();
    }
}
