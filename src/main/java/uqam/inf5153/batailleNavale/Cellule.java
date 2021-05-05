package uqam.inf5153.batailleNavale;

/**
 * Cette classe représente une cellule de la grille de jeu.
 */
public class Cellule {
    protected TypeCellule typeCellule;

    public Cellule(TypeCellule typeCellule) {
        this.typeCellule = typeCellule;
    }

    /**
     * Indique si une cellule est occupée par un navire.
     *
     * @return true si un navire occupe la cellule, false sinon.
     */
    public boolean estOccupeeParNavire() {
        return this.typeCellule == TypeCellule.NAVIREINTACT;
    }

    /**
     * Indique si une cellule est occupée.
     *
     * @return true si la cellulle est occupée, false sinon.
     */
    public boolean estOccupee() {
        boolean estOccupee;
        estOccupee = this.typeCellule == TypeCellule.NAVIREINTACT || this.typeCellule == TypeCellule.NAVIRECOULE
                || this.typeCellule == TypeCellule.BOMBE || this.typeCellule == TypeCellule.NAVIRETOUCHE;
        return estOccupee;
    }
}