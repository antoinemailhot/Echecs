package Model;

public class Pion extends Piece {

    // Les attributs:
    private TypeDirection.Direction direction;

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
        Case caseActuel = null;

        for(int i = 0; i < Plateau.cases.length; i++) {
            for(int j = 0; j < Plateau.cases[i].length; j++) {
                if(Plateau.cases[i][j].getPiece() == this) {
                    caseActuel = Plateau.cases[i][j];
                }
            }
        }

        // Vérifie si le pion est sur la rangée de promotion.
        //  les pions blancs la promotion est en y = 0,
        //  les pions noirs la promotion est en y = 7.
        boolean casespromotion = (direction == TypeDirection.Direction.Direction_Origin_Noir &&  caseActuel.getY() == 0)
                || (direction == TypeDirection.Direction.Direction_Origin_Blanc && caseActuel.getY() == 7);
    
        if (casespromotion) {
            caseActuel.placerPiece(new Reine(TypePiece.Piece.Reine, couleur));
        }
    }
    /**
     * Écrit en string le type de la pièce.
     */
    public String toString() {
        return super.getType().toString();
    }
}
