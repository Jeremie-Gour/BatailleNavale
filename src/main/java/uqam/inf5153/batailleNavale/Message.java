package uqam.inf5153.batailleNavale;

import java.util.Scanner;

import static java.lang.Character.*;

/**
 * Cette classe contient toutes les méthodes de saisie et de validation.
 */
public class Message {
    private final static String documents = "";

    public static String typeDocument() {
        String type = null;
        boolean inputValide = false;
        while (!inputValide) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Quel type de document voulez-vous pour sauvegarder l'historique de la partie? " +
                    "(texte, xml ou json)");
            type = scan.nextLine();
            switch (type){
                case "texte":
                case "xml":
                case "json":
                    inputValide = true;
                    break;
                default:
                    System.out.println("Veuillez entrer un type de document valide.");
                    break;
            }
        }
        return type;
    }

    public static Difficulte choisirDifficulte() {
        Difficulte difficulte = null;
        String difficulteSaisie;
        boolean inputValide = false;
        while (!inputValide) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Choisissez le niveau de difficulté (1=facile, 2=intérmediaire ou 3=difficile");
            difficulteSaisie = scan.nextLine();
            switch (difficulteSaisie) {
                case "1":
                    difficulte = Difficulte.FACILE;
                    inputValide = true;
                    break;
                case "2":
                    difficulte = Difficulte.INTERMEDIAIRE;
                    inputValide = true;
                    break;
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
        boolean inputValide = false;
        String bombe = null;

        while(!inputValide) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Veuillez entrer les coordonnées de la bombe. Exemple: E5");
            bombe = scan.nextLine();

            if(bombe.length() == 2 && isDigit(bombe.charAt(1)) && bombe.charAt(0) >= 'A' && bombe.charAt(0) <= 'J'){
                inputValide = true;
            }else{
                System.out.println("Veuillez entrer des coordonnées valides.");
            }
        }
        return new Coordonnee(Integer.parseInt(String.valueOf(bombe.charAt(1))), stringToColonne(bombe.charAt(0)));
    }

    public static String saisirNavire(Navire navire) {
        String bateau = null;

        boolean inputValide = false;

        while(!inputValide) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Veuillez entrer les coordonnées de la proue du " + navire.getTypeNavire() +
                    " et son alignement (H:horizontal vers l'est ou V:vertical vers le sud). Exemple: A5V");
            bateau = scan.nextLine();
            if(bateau.length() == 3 && isDigit(bateau.charAt(1)) && bateau.charAt(0) >= 'A' && bateau.charAt(0) <= 'J' &&
                    (bateau.charAt(2) == 'H' || bateau.charAt(2) == 'V')) {
                inputValide = true;
            } else {
                System.out.println("Veuillez entrer des coordonnées valides.");
            }
        }
        return bateau;
    }

    public static Alignement saisirAlignement(char alignement){
        Alignement alignementBateau = null;
        if(alignement == 'V'){
            alignementBateau = Alignement.VERTICAL;
        }else if(alignement == 'H'){
            alignementBateau = Alignement.HORIZONTAL;
        }
        return alignementBateau;
    }

    public static Coordonnee saisirTir() {
        String tir = null;
        boolean inputValide = false;
        while (!inputValide) {
            Scanner scan = new Scanner(System.in);

            System.out.println("Veuillez entrer la coordonnée de votre tir. Exemple: B4");
            tir = scan.nextLine();
            if(tir.length() == 2 && isDigit(tir.charAt(1)) && tir.charAt(0) >= 'A' && tir.charAt(0) <= 'J') {
                inputValide = true;
            } else {
                System.out.println("Veuillez entrer des coordonnées valides.");
            }
        }
        return new Coordonnee(Integer.parseInt(String.valueOf(tir.charAt(1))), stringToColonne(tir.charAt(0)));
    }

    public static int stringToColonne(char charColonne) {
        Colonne colonne = Colonne.valueOf(Character.toString(charColonne));

        return colonne.getColonne();
    }

    public static String afficherGagnant(boolean humainGagnant, boolean ordinateurGagnant) {
        String messageVictoire = null;
        if (humainGagnant) {

            messageVictoire = "Le joueur a remporté la partie!\n";
        } else if (ordinateurGagnant){
            messageVictoire = "L'ordinateur a remporté la partie!\n";
        }
        return messageVictoire;
    }
}
