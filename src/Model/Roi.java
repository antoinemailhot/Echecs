package Model;

import Model.TypeCouleur.Couleur;

public class Roi extends Piece {
    // Constructeur
    public Roi(Model.TypePiece.Piece Piece, Couleur couleur) {
        super(Piece, couleur);
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
