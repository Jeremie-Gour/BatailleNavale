package uqam.inf5153.batailleNavale;

/**
 * Cette classe représente un objet de type Navire.
 */
public class Navire {
    private TypeNavire typeNavire;

    public Navire(TypeNavire typeNavire) {
        this.typeNavire = typeNavire;
    }

    public TypeNavire getTypeNavire() {
        return typeNavire;
    }
}
