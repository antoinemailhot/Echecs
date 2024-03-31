package Model;

import Model.TypeCouleur.Couleur;

public class Roi extends Piece {

    // Constructeur
    public Roi(CaseEchec caseEchec, Model.TypePiece.Piece pion, Couleur couleur) {
        super(caseEchec, pion, couleur);
    }

    /**
     * Écrit en string le type de la pièce.
     */
    public String toString() {
        return super.getType().toString();
    }
    
}
