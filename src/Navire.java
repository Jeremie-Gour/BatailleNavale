/**
 * Cette classe représente un objet de type Navire.
 */
public class Navire {
    private TypeNavire typeNavire;
    private Coordonnee[] positions;
    private Alignement alignement;

    public Navire(TypeNavire typeNavire) {
        this.typeNavire = typeNavire;
        this.positions = new Coordonnee[typeNavire.getTaille()];
    }

    public TypeNavire getTypeNavire() {
        return typeNavire;
    }

    public void setPositions(int i, Coordonnee coordonnee) {
        this.positions[i] = coordonnee;
    }

    public Coordonnee[] getCoordonnees() {
        return positions;
    }


}
