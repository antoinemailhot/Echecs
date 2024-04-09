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
 /*     (0,1) (1,1) (2,1) (3,1) (4,1) (5,1) (6,1) (7,1)   y = 1, row 2
        (0,2) (1,2) (2,2) (3,2) (4,2) (5,2) (6,2) (7,2)   y = 2, row 3
        (0,3) (1,3) (2,3) (3,3) (4,3) (5,3) (6,3) (7,3)   y = 3, row 4
        (0,4) (1,4) (2,4) (3,4) (4,4) (5,4) (6,4) (7,4)   y = 4, row 5
        (0,5) (1,5) (2,5) (3,5) (4,5) (5,5) (6,5) (7,5)   y = 5, row 6
        (0,6) (1,6) (2,6) (3,6) (4,6) (5,6) (6,6) (7,6)   y = 6, row 7
        (0,7) (1,7) (2,7) (3,7) (4,7) (5,7) (6,7) (7,7)   y = 7, row 8 */
        switch (typepiece) {
            case Pion:
    // Déterminer la direction du mouvement du pion basé sur sa couleur
    int directionPion = this.caseEchec.getPiece().getCouleur() == Jeu.joueurs[0].getCouleur() ? 1 : -1;  // Supposons que Jeu.joueurs[0] est le joueur avec les blancs en bas

    x = this.caseEchec.getX();  // Colonne
    y = this.caseEchec.getY() + directionPion;  // Ligne, déplacement vertical

    // Mouvement d'une case vers l'avant si elle n'est pas occupée
    if (y >= 0 && y < 8) {
        CaseEchec caseEnAvant = Plateau.cases[y][x];
        if (!caseEnAvant.estOccupee()) {
            coupsValides.add(new Coups(caseEnAvant));

            // Mouvement de deux cases depuis la position de départ (pour le premier mouvement du pion uniquement)
            if ((directionPion == -1 && this.caseEchec.getY() == 6) || (directionPion == 1 && this.caseEchec.getY() == 1)) {
                int yDeuxCases = y + directionPion;  // Deuxième case en avant
                if (yDeuxCases >= 0 && yDeuxCases < 8) {
                    CaseEchec caseDeuxAvant = Plateau.cases[yDeuxCases][x];
                    if (!caseDeuxAvant.estOccupee()) {
                        coupsValides.add(new Coups(caseDeuxAvant));
                    }
                }
            }
        }
    }

    // Captures diagonales pour le pion
    int[] dx = {-1, 1};  // Capture à gauche et à droite
    for (int d : dx) {
        int nx = x + d;
        int ny = y;  // Utilisez 'y' déjà calculé pour la hauteur de la rangée
        if (nx >= 0 && nx < 8 && ny >= 0 && ny < 8) {
            CaseEchec caseCapture = Plateau.cases[ny][nx];
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

                        CaseEchec caseDestination = Plateau.cases[y][x];

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
                        x += directionFou[1];
                        y += directionFou[0];

                        // Vérifier si la nouvelle position est sur le plateau
                        if (x < 0 || x >= 8 || y < 0 || y >= 8)
                            break; // Sortie du plateau

                        CaseEchec caseDestination = Plateau.cases[y][x];
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
                    x = this.caseEchec.getX() + mouvement[1];
                    y = this.caseEchec.getY() + mouvement[0];

                    // Vérifier si la nouvelle position est sur le plateau
                    if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                        CaseEchec caseDestination = Plateau.cases[y][x];

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
                        x += direction[1];
                        y += direction[0];

                        // Vérifier si la nouvelle position est sur le plateau
                        if (x < 0 || x >= 8 || y < 0 || y >= 8)
                            break;

                        CaseEchec caseDestination = Plateau.cases[y][x];

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
                    x = this.caseEchec.getX() + direction[1];
                    y = this.caseEchec.getY() + direction[0];

                    // Vérifier si la nouvelle position est sur le plateau
                    if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                        CaseEchec caseDestination = Plateau.cases[y][x];

                        // Vérifier si la case est occupée par une pièce alliée
                        if (!caseDestination.estOccupee() || !caseDestination.getPiece().getCouleur()
                                .equals(this.caseEchec.getPiece().getCouleur()) || !estEchec(this.caseEchec.getPiece().getCouleur())) {

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
    public boolean estEchec(TypeCouleur.Couleur couleurRoi) {
        // Si il y a au moins 1 case dangereuse pour le roi.
        Boolean echec = false;
       CaseEchec positionroi = trouverPositionRoi(couleurRoi);
       ArrayList<Coups> coupsPossibles  = new ArrayList<Coups>();
       for (int i=0 ; i < 8; i++){
        for (int j=0; j < 8; j++){

            Piece piece = Plateau.cases[j][i].getPiece();

            if (piece != null && !piece.getCouleur().equals(couleurRoi)) {

                coupsPossibles = piece.getCoups() ;

                
            }


           



        }
    }
    for (Coups coup : coupsPossibles) {
        if (coup.getCaseEchec().equals(positionroi)) {
            echec = true; // Le roi est en échec
        }
        
    }

    return echec; // Le roi n'est pas en échec
   } 
       
        
    

    public boolean echecEtMat(TypeCouleur.Couleur couleurRoi ) {        
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
    CaseEchec trouverPositionRoi(TypeCouleur.Couleur couleurRoi) {
       
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
    public boolean estCaseDangereuse(CaseEchec casePossible, TypeCouleur.Couleur couleurRoi) {
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
