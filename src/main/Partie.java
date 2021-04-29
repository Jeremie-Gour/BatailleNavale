package main;

import java.util.Random;

/**
 * Cette classe contient toutes les fonctions nécessaires pour jouer une partie de Bataille Navale.
 */
public class Partie {
    JoueurHumain joueur = new JoueurHumain();
    JoueurOrdinateur ordinateur = new JoueurOrdinateur();
    Ocean oceanFacile = new Ocean();
    Ocean oceanIntermediaire = new Ocean();
    Ordinateur facile = new Ordinateur(Difficulte.FACILE);
    Ordinateur difficile = new Ordinateur(Difficulte.DIFFICILE);


    // En ce moment, deux ordis peuvent jouer l'un contre l'autre à Facile.
    // On s'occupera des saisies claviers plus tard, ça ne change presque rien à la conception.
    // ** Quand on affiche la grille de l'autre, il faudrait afficher de l'eau ('~') à la place des bateaux/bombes -- P-e faire 2 fonctions Partie.afficherOcean() **
    public void jouerPartie() {
        TypeCellule celluleJoueurHumain;
        TypeCellule celluleOrdinateur = null;
        Ocean oceanHumain = new Ocean();
        Ocean oceanOrdinateur = new Ocean();

        // Sélection du niveau de difficulté
        Difficulte difficulte = Message.choisirDifficulte();
        Ordinateur ordinateur = new Ordinateur(difficulte);


        // On peut placer les bombes, mais il faut regarder pour les vérifications et tirer une bombe ne fait pas encore passer un tour.
        boolean bombeValideHumain = false;
        boolean bombeValideOrdinateur = false;

        // Placement des bombes
        if (Message.avecBombe()) {
            // Saisie et placement des bombes du joueur
            int i = 0;
            while (i < 5) {
                Coordonnee coordonneeBombe = Message.saisirBombe();
                bombeValideHumain = oceanHumain.placerBombe(coordonneeBombe);
                if (bombeValideHumain) {
                    i++;
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

        // Placement des navires du joueur
        for (Navire navire : oceanHumain.flotte.listeNavires) {
            String positionNavire = Message.saisirNavire(navire);
            Alignement alignementNavire = Alignement.valueOf(Character.toString(positionNavire.charAt(2)));

            int rangee = Integer.parseInt(Character.toString(positionNavire.charAt(1)));
            int colonne = Message.stringToColonne(Character.toString(positionNavire.charAt(0)));
            Coordonnee coordonneeNavire = new Coordonnee(rangee, colonne);

            oceanHumain.placerNavire(coordonneeNavire, alignementNavire, navire);
        }


        // Placement des navires de l'ordinateur
        oceanOrdinateur.placerNaviresAleatoirement();

        // On affiche l'océan
        oceanHumain.afficherOcean();


        // Boucle principale de jeu
        // Alterne de joueur en joueur jusqu'a temps qu'il y n'y a pas de gagnant
        // J'ai mis les tirs en boolean (un tir retourne true si il reste des bateaux à pew pew).
        // Va p-e falloir changer le retour des fonctions pour quand on shoot une bombe (typeCellule maybe)
        boolean humainBombe = false;
        boolean ordinateurBombe = false;
        /**
         * RENDU ICI ***************************
         */
        while (oceanHumain.celluleDeNavireIntactesDispo() && oceanOrdinateur.celluleDeNavireIntactesDispo()) {

            if (!humainBombe) {
                Coordonnee coordonneeNavireHumain = Message.saisirTir();

                //if bombe passe le tour
                celluleJoueurHumain = oceanOrdinateur.tirer(coordonneeNavireHumain);
                if (celluleJoueurHumain == TypeCellule.BOMBE) {
                    humainBombe = true;
                    System.out.println("Touch/ une bombe, passe le tour");
                } else {
                    humainBombe = false;
                }
            }

            //if bombe passe le tour
            if (!ordinateurBombe) {
                Coordonnee coordonneeNavireOrdinateur = ordinateur.prochaineAttaque();

                //if bombe passe le tour
                celluleOrdinateur = oceanOrdinateur.tirer(coordonneeNavireOrdinateur);
                if (celluleOrdinateur == TypeCellule.BOMBE) {
                    ordinateurBombe = true;
                    System.out.println("Touch/ une bombe, passe le tour");
                } else {
                    ordinateurBombe = false;
                }
            }
            ordinateur.recevoirResultat(celluleOrdinateur);


        }
        Message.afficherGagnant(oceanHumain.celluleDeNavireIntactesDispo(), oceanOrdinateur.celluleDeNavireIntactesDispo());
    }


    public static void main(String[] args) {
        Partie partie = new Partie();
        partie.jouerPartie();
    }
}
