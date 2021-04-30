package uqam.inf5153.batailleNavale;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe contient tous les différents navires qui constituent une flotte.
 */
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
