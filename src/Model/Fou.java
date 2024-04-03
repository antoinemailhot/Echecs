package Model;

import Model.TypeCouleur.Couleur;

public class Fou extends Piece {

    // Constructeur
    public Fou(Model.TypePiece.Piece Piece, Couleur couleur) {
        super(Piece, couleur);
    }

    /**
     * Écrit en string le type de la pièce.
     */
    public String toString() {
        return super.getType().toString();
    }
    
}
