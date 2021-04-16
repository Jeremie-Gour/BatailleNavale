import java.util.Scanner;

/**
 * Cette classe contient toutes les méthodes de saisie et de validation.
 */
public class Affichage {
    private int minimum = 1;
    private int maximum = 10;
    private final static String documents = "";

    public static String typeDocument() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Quel type de document voulez-vous pour sauvegarder l'historique de la partie? " +
                "(texte, xml ou json)");
        String sauvegarde = scan.nextLine();
        return sauvegarde;
    }

    public static String choixDifficulte() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choisissez le niveau de difficulté (facile, intermédiaire ou difficile)");
        String difficulte = scan.nextLine();
        return difficulte;
    }

    public static String avecBombe() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Voulez-vous jouer avec des bombes? (oui ou non)");
        String bombes = scan.nextLine();
        return bombes;
    }

    public static Coordonnee placerBombe() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Veuillez entrer les coordonnées de la bombe. Exemple: E5");
        String bombe = scan.nextLine();

        String[] coordoSplit = bombe.split("");
        Coordonnee coordonneesBombe = new Coordonnee(Integer.parseInt(coordoSplit[1]), stringToColonne(coordoSplit[0]));
        return coordonneesBombe;
    }

    public static String messagePlacerBateau(Navire navire) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Veuillez entrer les coordonnées de la proue du " + navire.getTypeNavire() + " et son alignement (H ou V). Exemple: A5V");
        String bateau = scan.nextLine();

        return bateau;
    }

    public static Coordonnee saisirTir() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Veuillez entrer la coordonnée de votre tir. Exemple: B4");
        String tir = scan.nextLine();

        String[] coordoSplit = tir.split("");
        Coordonnee coordonneesTir = new Coordonnee(Integer.parseInt(coordoSplit[1]), stringToColonne(coordoSplit[0]));

        return coordonneesTir;
    }

    public static int stringToColonne(String stringColonne) {
        Colonne colonne = Colonne.valueOf(Character.toString(stringColonne.charAt(0)));

        return colonne.getColonne();
    }

    public static void afficherGagnant(boolean humainGagnant, boolean ordinateurGagnant) {
        if (!humainGagnant) {
            System.out.println("Le joueur a remporté la partie!\n");
        } else {
            System.out.println("L'ordinateur a remporté la partie!\n");
        }
    }
}
