package Model;

import Model.TypeCouleur.Couleur;

public class Reine extends Piece {

    // Constructeur
    public Reine(CaseEchec caseEchec, Model.TypePiece.Piece pion, Couleur couleur) {
        super(caseEchec, pion, couleur);
    }

    /**
     * Écrit en string le type de la pièce.
     */
    public String toString() {
        return super.getType().toString();
    }
    
}
