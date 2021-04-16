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
}
