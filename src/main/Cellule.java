package main;

/**
 * Cette classe représente une cellule de la grille de jeu.
 */
public class Cellule {
    TypeCellule typeCellule;

    public Cellule(TypeCellule typeCellule) {
        this.typeCellule = typeCellule;
    }

    /**
     * Indique si une cellule est occupée par un navire.
     *
     * @return true si un navire occupe la cellule, false sinon.
     */
    public boolean estOccupeeParNavire() {
        // La fonction ne fait que checker pour un bateau, faudrait faire plus en détails pour check avec bombe etc aussi.
        // Pour l'instant c'est correct parce que le programme ne tient pas encore compte des tirs.
        return this.typeCellule == TypeCellule.NAVIREINTACT;
    }

    public boolean estOccupee() {
        boolean estOccupee;
        estOccupee = this.typeCellule == TypeCellule.NAVIREINTACT || this.typeCellule == TypeCellule.NAVIRECOULE
                || this.typeCellule == TypeCellule.BOMBE || this.typeCellule == TypeCellule.NAVIRETOUCHE;
        return estOccupee;
    }
}