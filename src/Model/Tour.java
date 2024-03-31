package Model;

import Model.TypeCouleur.Couleur;

public class Tour extends Piece {

    public Tour(CaseEchec caseEchec, Model.TypePiece.Piece pion, Couleur couleur) {
        super(caseEchec, pion, couleur);
    }

    /**
     * Écrit en string le type de la pièce.
     */
    public String toString() {
        return super.getType().toString();
    }
}