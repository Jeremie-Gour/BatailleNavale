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
        TypeCellule celluletireFacile;
        TypeCellule celluletireIntermediaire;
        Difficulte difficulte = Difficulte.FACILE;

        // On peut placer les bombes, mais il faut regarder pour les vérifications et tirer une bombe ne fait pas encore passer un tour.
//        oceanHumain.placerBombes();
//        oceanOrdinateur.placerBombes();
        oceanFacile.placerNaviresAleatoirement(TypeJoueur.HUMAIN);
        oceanIntermediaire.placerNaviresAleatoirement(TypeJoueur.ORDINATEUR);
        //oceanOrdinateur.afficherOcean();



        // Boucle principale de jeu
        // Alterne de joueur en joueur jusqu'a temps qu'il y n'y a pas de gagnant
        // J'ai mis les tirs en boolean (un tir retourne true si il reste des bateaux à pew pew).
        // Va p-e falloir changer le retour des fonctions pour quand on shoot une bombe (typeCellule maybe)
        while (oceanIntermediaire.celluleDeNavireIntactesDispo() && oceanFacile.celluleDeNavireIntactesDispo() ){
            Coordonnee coordonneeFacile = facile.prochaineAttaque();
            celluletireFacile= oceanFacile.tirerOrdinateur(coordonneeFacile);

            Coordonnee coordonneeIntermediaire = difficile.prochaineAttaque();
            celluletireIntermediaire = oceanIntermediaire.tirerOrdinateur(coordonneeIntermediaire);

            facile.recevoirResultat(celluletireFacile);
            difficile.recevoirResultat(celluletireIntermediaire);
        }

        Affichage.afficherGagnant(oceanFacile.celluleDeNavireIntactesDispo(), oceanIntermediaire.celluleDeNavireIntactesDispo());

    }


    public static void main(String[] args) {
        Partie partie = new Partie();
        partie.jouerPartie();
    }
}
