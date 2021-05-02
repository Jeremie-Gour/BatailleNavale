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
    /**
     * Retourne la rangée de la coordonnée.
     * @ rangee est la rangée de la coordonnée
     */
    public int getRangee() {
        return rangee;
    }
    /**
     * Retourne la colonne de la coordonnée.
     * @ rangee est la colonne de la coordonnée
     */
    public int getColonne() {
        return colonne;
    }

    /**
     * Décale le curseur du coordonnée d'une case en bas dans la grille de jeu.
     */
    public void deplacerEnBas() {
        this.rangee--;
    }

    /**
     * Décalele curseur du coordonnée de int distance vers le bas dans la grille de jeu.
     * @param distance la distance du déplacement
     */
    public void deplacerEnBas(int pas) { this.rangee -= pas; }

    /**
     * Agmente le curseur du coordonnée d'une case en haut dans la grille de jeu.
     */
    public void deplacerEnHaut() { this.rangee++; }

    /**
     * Augmente le curseur du coordonnée de int distance vers le haut dans la grille de jeu.
     * @param distance la distance du déplacement
     */
    public void deplacerEnHaut(int pas) { this.rangee+= pas ; }

    /**
     * Decale le curseur du coordonnée de int distance vers la gauche dans la grille de jeu.
     * @param distance la distance du déplacement
     */
    public void deplacerAGauche(int pas) { this.colonne-= pas ; }

    /**
     * Augmente le curseur du coordonnée de int distance vers la droite dans la grille de jeu.
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
        if(obj == this){
            return true;
        }
        if(!(obj instanceof Coordonnee )){
            return false;
        }
        Coordonnee c = (Coordonnee) obj;

        return this.colonne == c.getColonne() && this.rangee == c.getRangee();
    }
}