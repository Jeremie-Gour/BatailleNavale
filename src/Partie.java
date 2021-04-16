/**
 * Cette classe contient toutes les fonctions nécessaires pour jouer une partie de Bataille Navale.
 */
public class Partie {
    JoueurHumain joueur = new JoueurHumain();
    JoueurOrdinateur ordinateur = new JoueurOrdinateur();
    Ocean oceanHumain = new Ocean();
    Ocean oceanOrdinateur = new Ocean();


    // En ce moment, deux ordis peuvent jouer l'un contre l'autre à Facile.
    // On s'occupera des saisies claviers plus tard, ça ne change presque rien à la conception.
    // ** Quand on affiche la grille de l'autre, il faudrait afficher de l'eau ('~') à la place des bateaux/bombes -- P-e faire 2 fonctions Partie.afficherOcean() **
    public void jouerPartie() {
        boolean tirHumain = true;
        boolean tirOrdinateur = true;
        Difficulte difficulte = Difficulte.FACILE;

        // On peut placer les bombes, mais il faut regarder pour les vérifications et tirer une bombe ne fait pas encore passer un tour.
//        oceanHumain.placerBombes();
//        oceanOrdinateur.placerBombes();
        oceanHumain.placerNaviresAleatoirement(TypeJoueur.ORDINATEUR);
        oceanOrdinateur.placerNaviresAleatoirement(TypeJoueur.HUMAIN);
        oceanOrdinateur.afficherOcean();



        // Boucle principale de jeu
        // Alterne de joueur en joueur jusqu'a temps qu'il y n'y a pas de gagnant
        // J'ai mis les tirs en boolean (un tir retourne true si il reste des bateaux à pew pew).
        // Va p-e falloir changer le retour des fonctions pour quand on shoot une bombe (typeCellule maybe)
        while (tirHumain && tirOrdinateur ){


            tirHumain = oceanOrdinateur.tirerOrdinateur(Difficulte.FACILE);
            tirOrdinateur = oceanHumain.tirerOrdinateur(Difficulte.FACILE);


        }

        Affichage.afficherGagnant(tirHumain, tirOrdinateur);

    }


    public static void main(String[] args) {
        Partie partie = new Partie();
        partie.jouerPartie();
    }
}
