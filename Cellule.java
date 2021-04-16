public class Cellule {
    TypeCellule typeCellule;

    public Cellule(TypeCellule typeCellule) {
        this.typeCellule = typeCellule;
    }

    public Cellule(){
        typeCellule = TypeCellule.EAU;
    }
}
