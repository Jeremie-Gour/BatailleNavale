package main;

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

    public void deplacerEnBas() {
        this.rangee--;
    }

    public void deplacerEnBas(int pas) { this.rangee -= pas; }

    public void deplacerEnHaut() { this.rangee++; }

    public void deplacerEnHaut(int pas) { this.rangee+= pas ; }

    public void deplacerAGauche() {
        this.colonne--;
    }

    public void deplacerAGauche(int pas) { this.colonne-= pas ; }

    public void deplacerADroite() {
        this.colonne++;
    }

    public void deplacerADroite(int pas) { this.colonne+= pas ; }

    public void changerCoordonnes(int rangee, int colonne) {
        this.rangee = rangee;
        this.colonne = colonne;
    }

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
