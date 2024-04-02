package Model;

import Model.TypeCouleur.Couleur;

public class Chevalier extends Piece {

    // Construteur
    public Chevalier(CaseEchec caseEchec, Model.TypePiece.Piece Piece, Couleur couleur) {
        super(caseEchec, Piece, couleur);
    }

    /**
     * Écrit en string le type de la pièce.
     */
    public String toString() {
        return super.getType().toString();
    }
    
}
