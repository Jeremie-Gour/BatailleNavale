package uqam.inf5153.batailleNavale;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;

/**
 * Cette classe contient toutes les fonctions nécessaires pour jouer une partie de Bataille Navale.
 */
public class Partie {
    private final String COORDONNEEINVALIDE = "Vous ne pouvez pas placer un bateau à ces coordonnées.";
    private final String BOMBETOUCHE = "Touché une bombe, passe le tour";


    public void jouerPartie() {
        TypeCellule celluleJoueurHumain;
        TypeCellule celluleOrdinateur = null;
        Ocean oceanHumain = new Ocean();
        Ocean oceanOrdinateur = new Ocean();

        // Sélection du niveau de difficulté
        Difficulte difficulte = Saisie.saisirNiveauDifficulte();
        Ordinateur ordinateur = new Ordinateur(difficulte);

        // On peut placer les bombes, mais il faut regarder pour les vérifications et tirer une bombe ne fait pas encore passer un tour.
        boolean bombeValideHumain;
        boolean bombeValideOrdinateur;

        oceanHumain.afficherOcean();



        // Placement des navires du joueur
        for (Navire navire : oceanHumain.flotte.getListeNavires()) {
            boolean placeNavire;
            do {
                String positionNavire = Saisie.saisirNavire(navire);
                Alignement alignementNavire = Saisie.determinerAlignement(positionNavire.charAt(2));

                int rangee = Integer.parseInt(Character.toString(positionNavire.charAt(1)));
                int colonne = Colonne.stringToColonne(positionNavire.charAt(0));
                Coordonnee coordonneeNavire = new Coordonnee(rangee, colonne);

                placeNavire = oceanHumain.verifierBateau(coordonneeNavire, alignementNavire, navire);
                if(placeNavire){
                    oceanHumain.placerNavire(coordonneeNavire, alignementNavire, navire);
                    oceanHumain.afficherOcean();
                } else {
                    System.out.println(COORDONNEEINVALIDE);
                }

            } while(!placeNavire);
        }


        // Placement des navires de l'ordinateur
        oceanOrdinateur.placerNaviresAleatoirement();

        // Placement des bombes
        if (Saisie.avecBombe()) {
            // Saisie et placement des bombes du joueur
            int i = 0;
            while (i < 5) {
                Coordonnee coordonneeBombe = Saisie.saisirBombe();
                bombeValideHumain = oceanHumain.placerBombe(coordonneeBombe);
                if (bombeValideHumain) {
                    oceanHumain.afficherOcean();
                    i++;
                } else {
                    System.out.println("Cet espace est occupé.");
                }
            }
            // Placement des bombes de l'ordinateur
            int j = 0;
            while (j < 5) {
                Random random = new Random();
                Coordonnee coordonneeBombeOrdinateur = new Coordonnee(random.nextInt(10), random.nextInt(10));
                bombeValideOrdinateur = oceanOrdinateur.placerBombe(coordonneeBombeOrdinateur);
                if (bombeValideOrdinateur) {
                    j++;
                }
            }
        }
        // On affiche l'océan
        System.out.println("Voici l'ocean de votre adversaire.");
        oceanOrdinateur.afficherOceanMasque();

        // Boucle principale de jeu
        // Alterne de joueur en joueur jusqu'a temps qu'il y n'y a pas de gagnant
        boolean humainBombe = false;
        boolean ordinateurBombe = false;
        while (oceanHumain.resteDesNavires() && oceanOrdinateur.resteDesNavires()) {
            System.out.println("ca c a toi");
            oceanHumain.afficherOcean();
            System.out.println("tire sur ca");
            oceanOrdinateur.afficherOcean();

            if (!humainBombe) {
                Coordonnee coordonneeNavireHumain = Saisie.saisirTir();

                //if bombe passe le tour
                celluleJoueurHumain = oceanOrdinateur.tirer(coordonneeNavireHumain);

                if (celluleJoueurHumain == TypeCellule.BOMBE) {
                    humainBombe = true;
                    System.out.println(BOMBETOUCHE);
                }
                oceanOrdinateur.afficherOceanMasque();
            } else {
                humainBombe = false;

            }

            //if bombe passe le tour
            if (!ordinateurBombe) {
                Coordonnee coordonneeNavireOrdinateur = ordinateur.prochaineAttaque();

                //if bombe passe le tour
                celluleOrdinateur = oceanHumain.tirer(coordonneeNavireOrdinateur);
                if (celluleOrdinateur == TypeCellule.BOMBE) {
                    ordinateurBombe = true;
                    System.out.println(BOMBETOUCHE);
                }
            } else {
                ordinateurBombe = false;
            }
            ordinateur.recevoirResultat(celluleOrdinateur);


        }
        System.out.println(Saisie.afficherGagnant(oceanHumain.resteDesNavires(), oceanOrdinateur.resteDesNavires()));
    }


    public static void main(String[] args){


        try
        {
            FileOutputStream fout= new FileOutputStream("stdout.xml");
            FileOutputStream ferr= new FileOutputStream("stderr.log");

            MultiOutputStream multiOut= new MultiOutputStream(System.out, fout);
            MultiOutputStream multiErr= new MultiOutputStream(System.err, ferr);

            PrintStream stdout= new PrintStream(multiOut);
            PrintStream stderr= new PrintStream(multiErr);

            System.setOut(stdout);
            System.setErr(stderr);
        }
        catch (FileNotFoundException ex)
        {
        }

        Partie partie = new Partie();
        partie.jouerPartie();

    }
}
