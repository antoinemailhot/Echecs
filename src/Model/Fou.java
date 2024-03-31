package Model;

import Model.TypeCouleur.Couleur;

public class Fou extends Piece {

    // Constructeur
    public Fou(CaseEchec caseEchec, Model.TypePiece.Piece pion, Couleur couleur) {
        super(caseEchec, pion, couleur);
    }

    /**
     * Écrit en string le type de la pièce.
     */
    public String toString() {
        return super.getType().toString();
    }
    
}
