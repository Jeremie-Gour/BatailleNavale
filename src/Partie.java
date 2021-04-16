public class Partie {
    JoueurHumain joueur = new JoueurHumain();
    JoueurOrdinateur ordinateur = new JoueurOrdinateur();
    Ocean oceanHumain = new Ocean();
    Ocean oceanOrdinateur = new Ocean();



    private int calculerNombreTirsRestants(Ocean ocean){
        int cellulesNavireIntactes = 0;
        for (Navire navire: ocean.flotte.listeNavires){
            cellulesNavireIntactes += navire.getTypeNavire().getTaille();
        }

        return cellulesNavireIntactes;
    }

    public void jouerPartie() {
        TypeCellule tirHumain;
        TypeCellule tirOrdinateur;

        // Place
//        oceanHumain.placerNavires();
//        oceanOrdinateur.placerNavires();
//
//
//        oceanHumain.placerBombes();
//        oceanOrdinateur.placerBombes();
        oceanHumain.placerNaviresAleatoirement();


        int cellulesRestantesPourHumain = calculerNombreTirsRestants(oceanOrdinateur);
        int cellulesRestantesPourOrdinateur = calculerNombreTirsRestants(oceanHumain);

        // Boucle principale de jeu
        // Alterne de joueur en joueur jusqu'a temps qu'il y n'y a pas de gagnant

//        while (cellulesRestantesPourHumain > 0 && cellulesRestantesPourOrdinateur > 0){
//
//            do {
//                tirHumain = oceanOrdinateur.tirer();
//            } while ( !Validateur.estTirValide(tirHumain));
//            cellulesRestantesPourHumain--;
//
//            if(cellulesRestantesPourHumain > 0) {
//                do {
//                    tirOrdinateur = oceanOrdinateur.tirer();
//                } while (!Validateur.estTirValide(tirOrdinateur));
//                cellulesRestantesPourOrdinateur--;
//            }
//        }

        Affichage.afficherGagnant(cellulesRestantesPourHumain,cellulesRestantesPourOrdinateur);

    }


    public static void main(String[] args) {
        Partie partie = new Partie();
        partie.jouerPartie();
    }
}
