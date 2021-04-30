package uqam.inf5153.batailleNavale;

import java.util.Scanner;

/**
 * Cette classe contient toutes les méthodes de saisie et de validation.
 */
public class Message {
    private final static String documents = "";

    public static String typeDocument() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Quel type de document voulez-vous pour sauvegarder l'historique de la partie? " +
                "(texte, xml ou json)");
        return scan.nextLine();
    }

    public static Difficulte choisirDifficulte() {
        Difficulte difficulte = Difficulte.FACILE;
        String difficulteSaisie;
        boolean inputValide = false;
        while (!inputValide) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Choisissez le niveau de difficulté (1=facile, 2=intérmediaire ou 3=difficile");
            difficulteSaisie = scan.nextLine();
            switch (difficulteSaisie) {
                case "1":
                    difficulte = Difficulte.FACILE;
                case "2":
                    difficulte = Difficulte.INTERMEDIAIRE;
                case "3":
                    difficulte = Difficulte.DIFFICILE;
                    inputValide = true;
                    break;
                default:
                    System.out.println("Veuillez entrer une difficulté valide.");
                    break;
            }
        }
        return difficulte;
    }

    public static boolean avecBombe() {
        boolean avecBombe = false;
        boolean inputValide = false;
        while (!inputValide) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Voulez-vous jouer avec des bombes? (oui ou non)");
            String bombes = scan.nextLine();
            if (bombes.equals("oui")) {
                avecBombe = true;
                inputValide = true;
            } else if (bombes.equals("non")) {
                inputValide = true;
            } else {
                System.out.println("Veuillez entrer une réponse valide.");
            }
        }
        return avecBombe;
    }

    public static Coordonnee saisirBombe() {
        //return si coordonnee est plus petite que 0 ou plus grande taillemax
        Scanner scan = new Scanner(System.in);
        System.out.println("Veuillez entrer les coordonnées de la bombe. Exemple: E5");
        String bombe = scan.nextLine();

        String[] coordoSplit = bombe.split("");
        return new Coordonnee(Integer.parseInt(coordoSplit[1]), stringToColonne(coordoSplit[0]));
    }

    public static String saisirNavire(Navire navire) {
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

        return new Coordonnee(Integer.parseInt(coordoSplit[1]), stringToColonne(coordoSplit[0]));
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
