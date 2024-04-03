package Model;

import Model.TypeCouleur.Couleur;

public class Reine extends Piece {

    // Constructeur
    public Reine(Model.TypePiece.Piece Piece, Couleur couleur) {
        super(Piece, couleur);
    }

    /**
     * Écrit en string le type de la pièce.
     */
    public String toString() {
        return super.getType().toString();
    }
    
}
