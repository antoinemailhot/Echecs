
package Model;

import Model.TypeCouleur.Couleur;
import Model.TypeDirection.Direction;

public class Plateau {
    public static CaseEchec[][] cases = new CaseEchec[8][8];

    public Plateau() {

        // Initialiser les cases du plateau d'échecs
        initialiserCases();

        // Initialiser les pièces
        initialiserPieces();
    }

    private void initialiserCases() {
        for (int y = 0; y < 8; y++) { // y pour les lignes
            for (int x = 0; x < 8; x++) { // x pour les colonnes
                // Alterne les couleurs entre Blanc et Noir
                Enum<Couleur> couleur = (x + y) % 2 == 0 ? Couleur.Blanc : Couleur.Noir;
                // Initialise la case avec sa couleur et ses coordonnées (x, y)
                cases[y][x] = new CaseEchec(couleur, x, y);
            }
        }
    }

    private void initialiserPieces() {
        
        for (TypeCouleur.Couleur couleur : TypeCouleur.Couleur.values()) {
            initialiserPiecesEquipe(couleur);
        }
    }

    private void initialiserPiecesEquipe(TypeCouleur.Couleur couleur) {
        // Index pour le tableau des pièces.
        int index = 0, extremiteEquipe = 0, colonnesPions = 0;
        Direction direction = null;

        if(TypeCouleur.Couleur.Blanc == couleur){
            extremiteEquipe = 0;
            colonnesPions = 0;
            direction = Direction.Direction_Origin_Blanc;
        } else if(TypeCouleur.Couleur.Noir == couleur) {
            extremiteEquipe = 7;
            colonnesPions = 6;
            direction = Direction.Direction_Origin_Noir;
        }

        for (int i = 0; i < 8; i++) {
            //pieces[extremiteEquipe][index++] = new Pion(cases[1][i], Couleur.Blanc, Direction.Direction_Origin_Blanc);
            cases[colonnesPions][i].placerPiece(new Pion(couleur, direction));
        }

        cases[extremiteEquipe][index++].placerPiece(new Tour(TypePiece.Piece.Tour, couleur));
        cases[extremiteEquipe][index++].placerPiece(new Chevalier(TypePiece.Piece.Chevalier, couleur));
        cases[extremiteEquipe][index++].placerPiece(new Fou(TypePiece.Piece.Fou, couleur));
        cases[extremiteEquipe][index++].placerPiece(new Reine(TypePiece.Piece.Reine, couleur));
        cases[extremiteEquipe][index++].placerPiece(new Roi(TypePiece.Piece.Roi, couleur));
        cases[extremiteEquipe][index++].placerPiece(new Fou(TypePiece.Piece.Fou, couleur));
        cases[extremiteEquipe][index++].placerPiece(new Chevalier(TypePiece.Piece.Chevalier, couleur));
        cases[extremiteEquipe][index].placerPiece(new Tour(TypePiece.Piece.Tour, couleur));
    }
}

