import java.util.ArrayList;
import java.util.List;

public class Flotte {
    List<Navire> listeNavires = new ArrayList<>();

    public Flotte() {
        listeNavires.add(new ContreTorpilleur(TypeNavire.CONTRETORPILLEUR));
        listeNavires.add(new ContreTorpilleur(TypeNavire.CONTRETORPILLEUR));
        listeNavires.add(new Croiseur(TypeNavire.CROISEUR));
        listeNavires.add(new PorteAvions(TypeNavire.PORTEAVIONS));
        listeNavires.add(new Torpilleur(TypeNavire.TORPILLEUR));
    }
}
