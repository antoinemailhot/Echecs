package Model;

import java.util.ArrayList;

public abstract class Coups {
    private CaseEchec caseEchec;
    private TypePiece.Piece typepiece;
    
    
    // Constructeur.
    public Coups(CaseEchec caseEchec) {
        this.caseEchec = caseEchec; 
        this.typepiece = caseEchec.getPiece().getType();
        
    }

    public ArrayList<Coups> getCoupsValides() {

        ArrayList<Coups> coupsValides = new ArrayList<Coups>();

        switch (typepiece) {
            case Pion:
                // Supposons que 'position' est la position actuelle du pion
                // et que 'plateau' est le plateau de jeu

                // Déplacement vers l'avant
                Position positionAvant = new CaseEchec(x, y + 1);
                if (plateau.estVide(positionAvant)) {
                    coupsValides.add(new Coup(position, positionAvant));
                }

                // Capture en diagonale gauche
                Position positionDiagonaleGauche = new Position(position.x - 1, position.y + 1);
                if (plateau.estOccupeParAdversaire(positionDiagonaleGauche)) {
                    coupsValides.add(new Coup(position, positionDiagonaleGauche));
                }

                // Capture en diagonale droite
                Position positionDiagonaleDroite = new Position(position.x + 1, position.y + 1);
                if (plateau.estOccupeParAdversaire(positionDiagonaleDroite)) {
                    coupsValides.add(new Coup(position, positionDiagonaleDroite));
                }

                break;
            case Tour:

                break;

            case Fou:

                break;
            case Chevalier:

                break;
            case Reine:

                break;
            case Roi:

                break;
        }

        return coupsValides;
    }

    /**
     * Retourne si le roi est echec.
     * @return Retourne si le coups est valide. 
     */
    private  boolean echec() {
        // Si il y a au moins 1 case dangereuse pour le roi.
    }

    private boolean echecEtMat() {
        // Si toutes les cases sont dangereuses pour le roi.
    }
        
    /**
     * Deplace la piece.
     */
    public void deplacement(Coups coups) {

    }

    /**
     * Retourne la case de l'attaque.
     * @return Retourne la case de l'attaque.
     */
    public void attaquer(CaseEchec caseEchec , CaseEchec caseDestination) {

    }

    /**
     * Retourne si la case est dangereuse pour la Piece.
     * @return Retourne si la case est dangereuse pour la Piece.
     */
    public abstract boolean estCaseDangereuse();

}
