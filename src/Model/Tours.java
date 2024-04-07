    package Model;
    import java.util.Arrays;

    public class Tours {
        // Les attributs:
        private Joueur joueurActuel;
        private CaseEchec[][] cases;
        // Constructeur.
        public Tours(Joueur joueur, CaseEchec[][]cases) {
            this.joueurActuel = joueur;
            //this.cases = Arrays.stream(Plateau.cases).map(CaseEchec::clone).toArray(CaseEchec::new);
            this.cases = Arrays.copyOf(Plateau.cases, Plateau.cases.length);
        }
       
        // Les methodes:
        /**
         * Verification déplacement.
         * @return Retourne vraie si valide.
         */
        private boolean verificationDeplacement() {
            boolean estValide = false;
            return estValide;
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
