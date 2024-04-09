package Model;

import java.util.ArrayList;

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
        int x;
        int y;

        ArrayList<Coups> coupsValides = new ArrayList<Coups>();

        switch (typepiece) {
            case Pion:
                int directionPion = this.caseEchec.getPiece().getCouleur() == Jeu.joueurs[0].getCouleur() ? -1 : 1;
                // Mouvement d'une case en avant si elle n'est pas occupée
                x = this.caseEchec.getX() + directionPion;
                y = this.caseEchec.getY();
                if (x >= 0 && x < 8) {
                    CaseEchec caseEnAvant = Plateau.cases[x][y];
                    if (!caseEnAvant.estOccupee()) {
                        coupsValides.add(new Coups(caseEnAvant));
                        // Mouvement initial de deux cases
                        if ((directionPion == -1 && this.caseEchec.getX() == 6) || (directionPion == 1 && this.caseEchec.getX() == 1)) {
                            CaseEchec caseDeuxAvant = Plateau.cases[x + directionPion][y];
                            if (!caseDeuxAvant.estOccupee()) {
                                coupsValides.add(new Coups(caseDeuxAvant));
                            }
                        }
                    }
                }
                // Captures diagonales
                int[] dx = {-1, 1};
                for (int d : dx) {
                    int nx = x;
                    int ny = y + d;
                    if (nx >= 0 && nx < 8 && ny >= 0 && ny < 8) {
                        CaseEchec caseCapture = Plateau.cases[nx][ny];
                        if (caseCapture.estOccupee() && caseCapture.getPiece().getCouleur() != this.caseEchec.getPiece().getCouleur()) {
                            coupsValides.add(new Coups(caseCapture));
                        }
                    }
                }
    break;

            case Tour:
                // Peut avancer en ligne droite si elle n'est pas bloqué par l'ennemi.

                // Directions: haut, bas, gauche, droite
                int[][] directionsTour = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
                for (int[] direction : directionsTour) {
                     x = this.caseEchec.getX();
                     y = this.caseEchec.getY();

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
                for (int[] directionFou : directionsDiagonales) {
                    x = this.caseEchec.getX();
                    y = this.caseEchec.getY();

                    while (true) {
                        x += directionFou[0];
                        y += directionFou[1];

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
                    x = this.caseEchec.getX() + mouvement[0];
                    y = this.caseEchec.getY() + mouvement[1];

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
                    x = this.caseEchec.getX();
                    y = this.caseEchec.getY();

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
                int[][] directionsRoi = {
                        { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, // Horizontalement et verticalement
                        { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } // En diagonale
                };

                for (int[] direction : directionsRoi) {
                    x = this.caseEchec.getX() + direction[0];
                    y = this.caseEchec.getY() + direction[1];

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
       
        
    

    private boolean echecEtMat(TypeCouleur couleurRoi) {        
        // Si il y a toutes les cases qui sont dangereuse pour le roi.
        CaseEchec positionroi = trouverPositionRoi(couleurRoi);
         
        for (int i=0 ; i < 8; i++){
            for (int j=0; j < 8; j++){

                Piece piece = Plateau.cases[i][j].getPiece();

                if (piece != null && !piece.getCouleur().equals(couleurRoi)) {

                    ArrayList<Coups> coupsPossibles = piece.getCoups() ;
                    ArrayList<Coups> coupsPossiblesroi = positionroi.getPiece().getCoups();
                    for (Coups coup : coupsPossibles) {
                        if(coupsPossiblesroi.contains(coup)) {
                            coupsPossiblesroi.remove(coup);
                        }
                        
                    }

                    if(coupsPossiblesroi.size() == 0) {
                        return true;
                    }
                }
            }
        }

        return false; // Le roi n'est pas en échec et mat.
    }

    /**
     * Trouve la position du roi
     *
     * @param  couleurRoi Prends la couleur du roi.
     * @return Retourne la case du roi.
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
    public boolean estCaseDangereuse(CaseEchec casePossible, TypeCouleur couleurRoi) {
        if (casePossible.getPiece() == null) {


        // Si il y a toutes les cases qui sont dangereuse pour le roi.
        CaseEchec positionRoi = trouverPositionRoi(couleurRoi);
         
        for (int i=0 ; i < 8; i++){
            for (int j=0; j < 8; j++){

                Piece piece = Plateau.cases[i][j].getPiece();

                if (piece != null && !piece.getCouleur().equals(couleurRoi)) {

                    ArrayList<Coups> coupsPossibles = piece.getCoups() ;
                    ArrayList<Coups> coupsPossiblesroi = positionRoi.getPiece().getCoups();
                    for (Coups coup : coupsPossibles) {
                        if(coupsPossiblesroi.contains(coup)) {
                            
                           return true; // 
                        }
                        
                    }
                }
            }
        }
        }

        return false; 
    }

    /*
    * Retourne la case du coups.
    */
    public CaseEchec getCaseEchec() {
        return this.caseEchec;
    }

}
