
package Model;

import Model.TypeCouleur.Couleur;
import Model.TypeDirection.Direction;

public class Plateau {
    private Piece[][] pieces = new Piece[1][15]; // 2 équipes, 16 pièces chacune
    private CaseEchec[][] cases = new CaseEchec[7][7];

    public Plateau() {

        // Initialiser les cases du plateau d'échecs
        initialiserCases();

        // Initialiser les pièces pour l'équipe 0
        initialiserPieces(0, Couleur.Blanc);

        // Initialiser les pièces pour l'équipe 1
        initialiserPieces(1, Couleur.Noir);
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

    private void initialiserPieces(int equipe, TypeCouleur.Couleur couleur) {
        // Index pour le tableau des pièces
        int index = 0;

        // Placer les pions
        if (couleur == Couleur.Blanc) {

            for (int i = 0; i < 8; i++) {
                pieces[equipe][index++] = new Pion(cases[1][i], Couleur.Blanc, Direction.Direction_Origin_Blanc);
            }

            // Placer les tours, chevaliers, fous, rois et reines.

            pieces[equipe][index++] = new Tour(cases[0][0], TypePiece.Piece.Tour, Couleur.Blanc);
            pieces[equipe][index++] = new Chevalier(cases[0][1], TypePiece.Piece.Chevalier, Couleur.Blanc);
            pieces[equipe][index++] = new Fou(cases[0][2], TypePiece.Piece.Fou, Couleur.Blanc);
            pieces[equipe][index++] = new Reine(cases[0][3], TypePiece.Piece.Reine, Couleur.Blanc);
            pieces[equipe][index++] = new Roi(cases[0][4], TypePiece.Piece.Roi, Couleur.Blanc);
            pieces[equipe][index++] = new Fou(cases[0][5], TypePiece.Piece.Fou, Couleur.Blanc);
            pieces[equipe][index++] = new Chevalier(cases[0][6], TypePiece.Piece.Chevalier, Couleur.Blanc);
            pieces[equipe][index] = new Tour(cases[0][7], TypePiece.Piece.Tour, Couleur.Blanc);
        }
        else {

            // Placer les pions
            for (int i = 0; i < 8; i++) {
                pieces[equipe][index++] = new Pion(cases[6][i], Couleur.Noir, Direction.Direction_Origin_Noir);
            }

            // Placer les tours, chevaliers, fous, rois et reines.

        
            pieces[equipe][index++] = new Tour(cases[7][0], TypePiece.Piece.Tour, Couleur.Noir);
            pieces[equipe][index++] = new Chevalier(cases[7][1], TypePiece.Piece.Chevalier, Couleur.Noir);
            pieces[equipe][index++] = new Fou(cases[7][2], TypePiece.Piece.Fou, Couleur.Noir);
            pieces[equipe][index++] = new Reine(cases[7][3], TypePiece.Piece.Reine, Couleur.Noir);
            pieces[equipe][index++] = new Roi(cases[7][4], TypePiece.Piece.Roi, Couleur.Noir);
            pieces[equipe][index++] = new Fou(cases[7][5], TypePiece.Piece.Fou, Couleur.Noir);
            pieces[equipe][index++] = new Chevalier(cases[7][6], TypePiece.Piece.Chevalier, Couleur.Noir);
            pieces[equipe][index] = new Tour(cases[7][7], TypePiece.Piece.Tour, Couleur.Noir);

    }
}
}
