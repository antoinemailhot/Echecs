package Model;

import java.util.ArrayList;

public class Piece {
    // Les attributs :
    protected TypePiece.Piece type;
    protected TypeCouleur.Couleur couleur;
    protected ArrayList<Coups> coups;
    protected CaseEchec caseEchec;
    protected boolean deplacer;

    // Constructeur.
    public Piece(CaseEchec caseEchec, Model.TypePiece.Piece pion, TypeCouleur.Couleur couleur) {
        this.type = pion;
        this.couleur = couleur;
        this.coups = new ArrayList<Coups>();
        this.caseEchec = caseEchec;
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

    public ArrayList<Coups> getCoups() {
        return coups;
    }

    public void setCoups(ArrayList<Coups> coups) {
        this.coups = coups;
    }

    public CaseEchec getCaseEchec() {
        return caseEchec;
    }

    public void setCaseEchec(CaseEchec caseEchec) {
        this.caseEchec = caseEchec;
    }

    public boolean isDeplacer() {
        return deplacer;
    }

    public void setDeplacer(boolean deplacer) {
        this.deplacer = deplacer;
    }

}
