package uqam.inf5153.batailleNavale;

/**
 * Cette classe représente une coordonnée sur la grille de jeu.
 */
public class Coordonnee {
    private int rangee;
    private int colonne;

    public Coordonnee(int rangee, int colonne) {
        this.rangee = rangee;
        this.colonne = colonne;
    }

    public int getRangee() {
        return rangee;
    }

    public int getColonne() {
        return colonne;
    }

    /**
     * Décale le curseur de l'ordinateur de int distance vers le bas dans la grille de jeu.
     * @param distance la distance du déplacement
     */
    public void deplacerEnBas(int distance) {
        this.rangee -= distance;
    }

    /**
     * Déplace le curseur de l'ordinateur d'une case en haut dans la grille de jeu.
     */
    public void deplacerEnHaut() {
        this.rangee++;
    }

    /**
     * Décale le curseur de l'ordinateur de int distance vers la gauche dans la grille de jeu.
     * @param distance la distance du déplacement
     */
    public void deplacerAGauche(int distance) {
        this.colonne -= distance;
    }

    /**
     * Déplace le curseur de l'ordinateur d'une case à droite dans la grille de jeu.
     */
    public void deplacerADroite() {
        this.colonne++;
    }

    /**
     * Compare deux coordonnées entre elles.
     * @param obj la coordonnée
     * @return true si les coordonnées sont identiques, false sinon.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Coordonnee)) {
            return false;
        }
        Coordonnee c = (Coordonnee) obj;

        return this.colonne == c.getColonne() && this.rangee == c.getRangee();
    }
}
