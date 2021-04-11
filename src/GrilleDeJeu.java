import java.util.ArrayList;
import java.util.List;

public class GrilleDeJeu {
    final static int TAILLE_MAX = 10;
    Case grilleJoueur[][];

    List<Bateau> bateaux = new ArrayList<>();

    public GrilleDeJeu() {
        grilleJoueur = new Case[TAILLE_MAX][TAILLE_MAX];
    }

    public void placerBateau(PorteAvions _porteAvions) {
        System.out.println("Print dans placeBateau: latitude = " + _porteAvions.coordonnees[0].getLatitude());
        System.out.println("Print dans placeBateau: longitude = " + _porteAvions.coordonnees[0].getLongitude());

        for (Coordonnee coordonnee : _porteAvions.coordonnees) {


            int j = coordonnee.getLongitude() - 1;
            int i = coordonnee.getLatitude().ordinal();
            System.out.println("i = " + i);
            System.out.println("j = " + j);
            Case caseTest = new Case(true,coordonnee);

            grilleJoueur[i][j] = caseTest;

        }
    }

    public void ajouterBateau(Bateau _bateau) {
        this.bateaux.add(_bateau);
    }
}
