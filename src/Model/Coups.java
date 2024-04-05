package Model;

import java.util.ArrayList;

import javax.sound.sampled.AudioFileFormat.Type;

public class Coups {
    private CaseEchec caseEchec;
    private TypePiece.Piece typepiece;

    // Constructeur.
    public Coups(CaseEchec caseEchec) {
        this.caseEchec = caseEchec;
        if (caseEchec.getPiece() != null) {
            this.typepiece = caseEchec.getPiece().getType();
        }

    }

    public ArrayList<Coups> getCoupsValides() {

        ArrayList<Coups> coupsValides = new ArrayList<Coups>();

        switch (typepiece) {
            case Pion:
                // Peut avancer de 1 case si elle n'est pas bloqué par l'ennemi.
                Pion p = (Pion) caseEchec.getPiece();
                CaseEchec caseSuivante;
                if (p.getDirection() == TypeDirection.Direction.Direction_Origin_Blanc) {
                    caseSuivante = Plateau.cases[caseEchec.getX()][caseEchec.getY() + 1];
                } else {
                    caseSuivante = Plateau.cases[caseEchec.getX()][caseEchec.getY() - 1];
                }

                // Si la pieces suivante est vide alors on l'ajoute comme coups valide.
                if (caseSuivante.getPiece() == null) {
                    coupsValides.add(new Coups(caseSuivante));
                }

                // Peut avancer de 2 case si c'est son premier tours.
                if (p.estPremierCoup()) {
                    if (p.getDirection() == TypeDirection.Direction.Direction_Origin_Blanc) {
                        caseSuivante = Plateau.cases[caseEchec.getX()][caseEchec.getY() + 2];
                    } else {
                        caseSuivante = Plateau.cases[caseEchec.getX()][caseEchec.getY() - 2];
                    }

                    coupsValides.add(new Coups(caseSuivante));
                }

                // Peut avancer de 1 case en diagonale s'il y a un ennemi en diagonale de 1
                // case.
                if (p.getDirection() == TypeDirection.Direction.Direction_Origin_Blanc) {
                    // Les blancs
                    if (Plateau.cases[caseEchec.getX() + 1][caseEchec.getY() + 1].getPiece() != null &&
                            Plateau.cases[caseEchec.getX() + 1][caseEchec.getY() + 1]
                                    .getPiece().couleur == TypeCouleur.Couleur.Noir) {
                        coupsValides.add(new Coups(Plateau.cases[caseEchec.getX() + 1][caseEchec.getY() + 1]));
                    }
                    if (Plateau.cases[caseEchec.getX() - 1][caseEchec.getY() + 1].getPiece() != null &&
                            Plateau.cases[caseEchec.getX() - 1][caseEchec.getY() + 1]
                                    .getPiece().couleur == TypeCouleur.Couleur.Noir) {
                        coupsValides.add(new Coups(Plateau.cases[caseEchec.getX() - 1][caseEchec.getY() + 1]));
                    }
                } else {
                    // Les noirs
                    if (Plateau.cases[caseEchec.getX() + 1][caseEchec.getY() - 1].getPiece() != null &&
                            Plateau.cases[caseEchec.getX() + 1][caseEchec.getY() - 1]
                                    .getPiece().couleur == TypeCouleur.Couleur.Blanc) {
                        coupsValides.add(new Coups(Plateau.cases[caseEchec.getX() + 1][caseEchec.getY() - 1]));
                    }
                    if (Plateau.cases[caseEchec.getX() - 1][caseEchec.getY() - 1].getPiece() != null &&
                            Plateau.cases[caseEchec.getX() - 1][caseEchec.getY() - 1]
                                    .getPiece().couleur == TypeCouleur.Couleur.Blanc) {
                        coupsValides.add(new Coups(Plateau.cases[caseEchec.getX() - 1][caseEchec.getY() - 1]));
                    }
                }

                break;
            case Tour:
                // Peut avancer en ligne droite si elle n'est pas bloqué par l'ennemi.

                // Directions: haut, bas, gauche, droite
                int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
                for (int[] direction : directions) {
                    int x = this.caseEchec.getX();
                    int y = this.caseEchec.getY();

                    while (true) {
                        x += direction[0];
                        y += direction[1];

                        // Vérifier si la nouvelle position est sur le plateau
                        if (x < 0 || x >= 8 || y < 0 || y >= 8)
                            break;

                        CaseEchec caseDestination = Plateau.cases[x][y];

                        if (caseDestination.estOccupee()) {
                            // Vérifier si la pièce sur la caseDestination est une pièce adverse
                            if (!caseDestination.getPiece().getCouleur()
                                    .equals(this.caseEchec.getPiece().getCouleur())) {
                                coupsValides.add(new Coups(caseDestination));
                            }
                            break; // Une pièce bloque le chemin
                        } else {
                            coupsValides.add(new Coups(caseDestination));
                        }
                    }
                }

                return coupsValides;
            case Fou:
                // Directions diagonales : haut gauche, haut droit, bas gauche, bas droit
                int[][] directionsDiagonales = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };
                for (int[] direction : directionsDiagonales) {
                    int x = this.caseEchec.getX();
                    int y = this.caseEchec.getY();

                    while (true) {
                        x += direction[0];
                        y += direction[1];

                        // Vérifier si la nouvelle position est sur le plateau
                        if (x < 0 || x >= 8 || y < 0 || y >= 8)
                            break; // Sortie du plateau

                        CaseEchec caseDestination = Plateau.cases[x][y];
                        if (caseDestination.estOccupee()) {
                            // Vérifier si la pièce sur la caseDestination est une pièce adverse
                            if (!caseDestination.getPiece().getCouleur()
                                    .equals(this.caseEchec.getPiece().getCouleur())) {
                                coupsValides.add(new Coups(caseDestination)); // Ajoute comme coup valide si c'est une
                                                                              // pièce adverse
                            }
                            break; // Une pièce bloque le chemin, donc arrêter de chercher plus loin dans cette
                                   // direction
                        } else {
                            coupsValides.add(new Coups(caseDestination)); // Ajoute comme coup valide si la case est
                                                                          // libre
                        }
                    }
                }
                break;

            case Chevalier:
                // Mouvements en L : 2 cases dans une direction puis 1 case perpendiculairement,
                // ou inversement.

                // Toutes les directions possibles pour un mouvement en L
                int[][] mouvements = { { -2, -1 }, { -1, -2 }, { -2, 1 }, { -1, 2 }, { 2, -1 }, { 1, -2 }, { 2, 1 },
                        { 1, 2 } };

                for (int[] mouvement : mouvements) {
                    int x = this.caseEchec.getX() + mouvement[0];
                    int y = this.caseEchec.getY() + mouvement[1];

                    // Vérifier si la nouvelle position est sur le plateau
                    if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                        CaseEchec caseDestination = Plateau.cases[x][y];

                        // Si la caseDestination n'est pas occupée ou est occupée par une pièce adverse,
                        // ajouter le coup
                        if (!caseDestination.estOccupee() || !caseDestination.getPiece().getCouleur()
                                .equals(this.caseEchec.getPiece().getCouleur())) {
                            coupsValides.add(new Coups(caseDestination));
                        }
                    }
                }
                break;

            case Reine:
                // peut avancer en ligne droite et diagonale si elle n'est pas bloqué par
                // l'ennemi.
                int[][] directionsreine = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } // les 4 directions verticales et
                                                                                     // horizontales
                        , { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } }; // les 4 directions diagonales
                for (int[] direction : directionsreine) {
                    int x = this.caseEchec.getX();
                    int y = this.caseEchec.getY();

                    while (true) {
                        x += direction[0];
                        y += direction[1];

                        // Vérifier si la nouvelle position est sur le plateau
                        if (x < 0 || x >= 8 || y < 0 || y >= 8)
                            break;

                        CaseEchec caseDestination = Plateau.cases[x][y];

                        if (caseDestination.estOccupee()) {
                            // Vérifier si la pièce sur la caseDestination est une pièce adverse
                            if (!caseDestination.getPiece().getCouleur()
                                    .equals(this.caseEchec.getPiece().getCouleur())) {
                                coupsValides.add(new Coups(caseDestination));
                            }
                            break; // Une pièce bloque le chemin
                        } else {
                            coupsValides.add(new Coups(caseDestination));
                        }
                    }
                }
                break;
            case Roi:
                // Le Roi peut se déplacer d'une case dans toutes les directions.

                // Directions pour les déplacements : horizontalement, verticalement, et en
                // diagonale
                int[][] directionsroi = {
                        { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, // Horizontalement et verticalement
                        { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } // En diagonale
                };

                for (int[] direction : directionsroi) {
                    int x = this.caseEchec.getX() + direction[0];
                    int y = this.caseEchec.getY() + direction[1];

                    // Vérifier si la nouvelle position est sur le plateau
                    if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                        CaseEchec caseDestination = Plateau.cases[x][y];

                        // Vérifier si la case est occupée par une pièce alliée
                        if (!caseDestination.estOccupee() || !caseDestination.getPiece().getCouleur()
                                .equals(this.caseEchec.getPiece().getCouleur())) {

                            coupsValides.add(new Coups(caseDestination));
                        }
                    }
                }
                break;

        }

        return coupsValides;
    }

    /**
     * Retourne si le roi est echec.
     * 
     * @return Retourne si le coups est valide.
     */
    private boolean echec(TypeCouleur couleurRoi) {
        // Si il y a au moins 1 case dangereuse pour le roi.
       CaseEchec positionroi = trouverPositionRoi(couleurRoi);
       for (int i=0 ; i < 8; i++){
        for (int j=0; j < 8; j++){

            Piece piece = Plateau.cases[i][j].getPiece();

            if (piece != null && !piece.getCouleur().equals(couleurRoi)) {

                ArrayList<Coups> coupsPossibles = piece.getCoups() ;

                for (Coups coup : coupsPossibles) {
                    if (coup.getCaseEchec().equals(positionroi)) {
                        return true; // Le roi est en échec
                    }
                    
                }
            }
        }
    }
    return false; // Le roi n'est pas en échec
   } 
       
        
    

    private boolean echecEtMat() {
        // Si toutes les cases sont dangereuses pour le roi.
        
        
    }

    /**
     * Trouve la position du roi
     *
     * @param  couleurRoi    description of parameter
     * @return              description of return value
     */
    CaseEchec trouverPositionRoi(TypeCouleur couleurRoi) {
       
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = Plateau.cases[i][j].getPiece();
                if (piece != null && piece instanceof Roi && piece.getCouleur().equals(couleurRoi)) {
                    return Plateau.cases[i][j];
                }
            }
        }
        return null; // Roi introuvable, devrait normalement jamais arriver
    }

    /**
     * Retourne si la case est dangereuse pour la Piece.
     * 
     * @return Retourne si la case est dangereuse pour la Piece.
     */
    public boolean estCaseDangereuse(CaseEchec casePossible) {
        if (casePossible.getPiece() != null && casePossible.getPiece().getCouleur().equals())
    }

    /*
    * Retourne la case du coups.
    */
    public CaseEchec getCaseEchec() {
        return this.caseEchec;
    }

}
