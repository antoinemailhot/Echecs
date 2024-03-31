package Model;

public class Tours {
    // Les attributs:
    private Joueur joueurActuel;

    // Constructeur.
    public Tours(Joueur joueur) {
        this.joueurActuel = joueur;
    }

    /**
     * Verification déplacement.
     * @return Retourne vraie si valide.
     */
    private boolean verificationDeplacement() {
        boolean estValide = false;
        return estValide;
    }

    /**
     * Renvoie les joueurs en échec.
     * @return Retourne les joueurs en échec.
     */
    public Joueur[] echec() {
        Joueur[] joueursEnEchec = new Joueur[2];

        return joueursEnEchec;
    }

    /**
     * Renvoie les joueurs en échec et mat.
     * @return Retourne les joueurs en échec et mat.
     */
    public Joueur[] echecEtMat() {
        Joueur[] joueursEnEchecEtMat = new Joueur[2];
        return joueursEnEchecEtMat;
    }

    /**
     * Renvoie le joueur actuel.
     * @return Retourne le joueur actuel.
     */
    public Joueur getJoueurActuel() {
        return joueurActuel;
    }

    /**
     * Prends un joueur.
     * @param joueurActuel Prends un joueur.
     */
    public void setJoueurActuel(Joueur joueurActuel) {
        if(joueurActuel != null) {
            this.joueurActuel = joueurActuel;
        }
    }

    public String toString() {
        return this.getJoueurActuel().toString();
    }
}
