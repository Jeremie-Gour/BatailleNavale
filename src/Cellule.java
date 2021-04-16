public class Cellule {
    TypeCellule typeCellule;

    public Cellule(TypeCellule typeCellule) {
        this.typeCellule = typeCellule;
    }

    public Cellule(){
        typeCellule = TypeCellule.EAU;
    }

    /**
     * Indique si une cellule est occupée par un navire.
     * @return si un bateau occupe la cellule.
     */
    public boolean estOccupeeParNavire(){
        // La fonction ne fait que checker pour un bateau, faudrait faire plus en détail pour check avec bombe etc aussi.
        return this.typeCellule == TypeCellule.NAVIREINTACT;
    }
}
