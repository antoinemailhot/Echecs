
package Model;

import Model.TypeCouleur.Couleur;
import Model.TypeDirection.Direction;

public class Plateau {
    public static CaseEchec[][] cases = new CaseEchec[8][8];

    public Plateau() {
    }

    /**
     * Creer une partie.
     */
    public void initialiserPartie() {
        // Initialiser les cases du plateau d'échecs
        initialiserCases();

        // Initialiser les pièces
        initialiserPieces();
    }
           
     /*  x=0   x=1   x=2   x=3  x=4    x=5   x=6  x=7   
        (0,1) (1,1) (2,1) (3,1) (4,1) (5,1) (6,1) (7,1)   y = 1, row 2
        (0,2) (1,2) (2,2) (3,2) (4,2) (5,2) (6,2) (7,2)   y = 2, row 3
        (0,3) (1,3) (2,3) (3,3) (4,3) (5,3) (6,3) (7,3)   y = 3, row 4
        (0,4) (1,4) (2,4) (3,4) (4,4) (5,4) (6,4) (7,4)   y = 4, row 5
        (0,5) (1,5) (2,5) (3,5) (4,5) (5,5) (6,5) (7,5)   y = 5, row 6
        (0,6) (1,6) (2,6) (3,6) (4,6) (5,6) (6,6) (7,6)   y = 6, row 7
        (0,7) (1,7) (2,7) (3,7) (4,7) (5,7) (6,7) (7,7)   y = 7, row 8 */
    private void initialiserCases() {
        for (int y = 0; y < 8; y++) { // y pour les lignes
            for (int x = 0; x < 8; x++) { // x pour les colonnes
                // Alterne les couleurs entre Blanc et Noir
                Enum<Couleur> couleur = (x + y) % 2 == 0 ? Couleur.Blanc : Couleur.Noir;
                // Initialise la case avec sa couleur et ses coordonnées (x, y)
                cases[y][x] = new CaseEchec(couleur, y, x);
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
        int index = 0, extremiteEquipe = 0, rangeePions = 0;
        Direction direction = null;

        if(TypeCouleur.Couleur.Blanc == couleur){
            extremiteEquipe = 0;
            rangeePions = 1;
            direction = Direction.Direction_Origin_Blanc;
        } else if(TypeCouleur.Couleur.Noir == couleur) {
            extremiteEquipe = 7;
            rangeePions = 6;
            direction = Direction.Direction_Origin_Noir;
        }

        for (int i = 0; i < 8; i++) {
            //pieces[extremiteEquipe][index++] = new Pion(cases[1][i], Couleur.Blanc, Direction.Direction_Origin_Blanc);
            cases[rangeePions][i].placerPiece(new Pion(couleur, direction));
            
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

