package uqam.inf5153.batailleNavale;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe crée une flotte qui contient tous les différents navires à poser sur la grille de jeu.
 */
public class Flotte {
    private List<Navire> listeNavires = new ArrayList<>();

    public Flotte() {
        listeNavires.add(new Navire(TypeNavire.CONTRETORPILLEUR));
        listeNavires.add(new Navire(TypeNavire.CONTRETORPILLEUR));
        listeNavires.add(new Navire(TypeNavire.CROISEUR));
        listeNavires.add(new Navire(TypeNavire.PORTEAVIONS));
        listeNavires.add(new Navire(TypeNavire.TORPILLEUR));
    }

    public List<Navire> getListeNavires() {
        return listeNavires;
    }
}
