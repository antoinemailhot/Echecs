package Model;

public abstract class Coups {
    
    // Constructeur.
    public Coups() {}

    /**
     * Retourne si le coups est valide. 
     * @return Retourne si le coups est valide. 
     */
    public abstract boolean estValide();

    /**
     * Retourne la case du déplacement.
     * @return Retourne la case du déplacement.
     */
    public abstract CaseEchec deplacement();

    /**
     * Retourne la case de l'attaque.
     * @return Retourne la case de l'attaque.
     */
    public abstract CaseEchec attaquer();

    /**
     * Retourne si la case est dangereuse pour la Piece.
     * @return Retourne si la case est dangereuse pour la Piece.
     */
    public abstract boolean estCaseDangereuse();

}
