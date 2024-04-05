package Model;

import java.util.ArrayList;

public class Piece {
    // Les attributs :
    protected TypePiece.Piece type;
    protected TypeCouleur.Couleur couleur;
    protected ArrayList<Coups> coups;
    protected boolean deplacer;

    // Constructeur.
    public Piece(Model.TypePiece.Piece pion, TypeCouleur.Couleur couleur) {
        this.type = pion;
        this.couleur = couleur;
        this.coups = null;
        this.deplacer = false;
    }

    public TypePiece.Piece getType() {
        return type;
    }

    public void setType(TypePiece.Piece type) {
        this.type = type;
    }

    public TypeCouleur.Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(TypeCouleur.Couleur couleur) {
        this.couleur = couleur;
    }

    /*
    * Retourne les coups possible d'une pièce.
    */
    public ArrayList<Coups> getCoups() {
        Coups c = new Coups(this.getCasePiece());
        this.coups = c.getCoupsValides();
        return this.coups;
    }

    public void setCoups(ArrayList<Coups> coups) {
        this.coups = coups;
    }

    public boolean isDeplacer() {
        return deplacer;
    }

    public void setDeplacer(boolean deplacer) {
        this.deplacer = deplacer;
    }

    /**
     * Retourne la case de la piece.
     */
    public CaseEchec getCasePiece() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(Plateau.cases[i][j].getPiece() == this) {
                    return Plateau.cases[i][j];
                }
            }
        }
        return null;
    }

}
