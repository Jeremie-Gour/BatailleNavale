import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GrilleDeJeu grilleJoueur = new GrilleDeJeu();

        Scanner scan = new Scanner(System.in);

//        System.out.println("Quel type de document voulez-vous pour sauvegarder l'historique de la partie? " +
//                "(texte, xml ou json)");
//        String sauvegarde = scan.nextLine();
//        System.out.println("Choisissez le niveau de difficulté (facile, intermédiaire ou difficile)");
//        String difficulte = scan.nextLine();
//        System.out.println("Voulez-vous jouer avec des bombes? (oui ou non)");
//        String bombes = scan.nextLine();


        System.out.println("Veuillez entrez les coordonnées du Porte-Avions (ex: A1 A2 A3 A4 A5)");
        String porteAvions = scan.nextLine();
        System.out.println(porteAvions);
        String[] porteAvionsBateau = StringBateau.convertirString(porteAvions);
        for (String string: porteAvionsBateau){
            System.out.println(string);
        }
        PorteAvions porteAvion = new PorteAvions(porteAvionsBateau);
        grilleJoueur.placerBateau(porteAvion);
        System.out.println(grilleJoueur.grilleJoueur[0][0].occupee);


//        System.out.println("Veuillez entrez les coordonnées du Croiseur (ex: A1 A2 A3 A4)");
//        String croiseur = scan.nextLine();
//        System.out.println("Veuillez entrez les coordonnées du premier Contre-Torpilleur (ex: A1 A2 A3)");
//        String contreTorpilleurUn = scan.nextLine();
//        System.out.println("Veuillez entrez les coordonnées du deuxième Contre-Torpilleur (ex: A1 A2 A3)");
//        String contreTorpilleurDeux = scan.nextLine();
//        System.out.println("Veuillez entrez les coordonnées du Torpilleur (ex: A1 A2)");
//        String torpilleur = scan.nextLine();
//
//        grilleJoueur = new GrilleDeJeu();

//        grilleJoueur.placerBateau(po);
//        grilleJoueur.placerBateau(croiseurBateau);
//        grilleJoueur.placerBateau(contreTorpilleurUnBateau);
//        grilleJoueur.placerBateau(contreTorpilleurDeuxBateau);
//        grilleJoueur.placerBateau(torpilleurBateau);
    }
}
