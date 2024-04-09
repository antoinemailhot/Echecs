package Model;

import Model.TypeCouleur.Couleur;

public class Chevalier extends Piece {

    // Construteur
    public Chevalier(Model.TypePiece.Piece Piece, Couleur couleur) {
        super(Piece, couleur);
    }

    /**
     * Écrit en string le type de la pièce.
     */
    public String toString() {
        return super.getType().toString();
    }

    public String toStringComplet() {
        return this.toString() + super.getCouleur().toString();
    }
    
}
