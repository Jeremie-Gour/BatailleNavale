package uqam.inf5153.batailleNavale;

import java.util.Scanner;
import static java.lang.Character.*;

/**
 * Cette classe contient toutes les méthodes de saisie et de validation.
 */
public class Saisie {
    private static final String DOCUMENT1 = "Quel type de document voulez-vous pour sauvegarder l'historique de la partie? ";
    private static final String DOCUMENT2 = "(texte, xml ou json)";
    private static final String DOCUMENTINVALIDE = "Veuillez entrer un type de document valide.";
    private static final String DIFFICULTE = "Choisissez le niveau de difficulté (1=facile, 2=intérmediaire ou 3=difficile";
    private static final String DIFFICULTEINVALIDE = "Veuillez entrer une difficulté valide.";
    private static final String AVECBOMBE = "Voulez-vous jouer avec des bombes? (oui ou non)";
    private static final String AVECBOMBEINVALIDE = "Veuillez entrer une réponse valide.";
    private static final String BOMBE = "Veuillez entrer les coordonnées de la bombe. Exemple: E5";
    private static final String COORDONNEEINVALIDE = "Veuillez entrer des coordonnées valides.";
    private static final String NAVIRE1 = "Veuillez entrer les coordonnées de la proue du ";
    private static final String NAVIRE2 = " et son alignement (H:horizontal vers l'est ou V:vertical vers le sud). Exemple: A5V";
    private static final String TIR = "Veuillez entrer la coordonnée de votre tir. Exemple: B4";
    private static final String VICTOIREJOUEUR = "Le joueur a remporté la partie!\n";
    private static final String VICTOIREORDINATEUR = "L'ordinateur a remporté la partie!\n";

    /**
     * Saisie et valide le type de document qui contient les messages affichés à la console.
     * @return le type de document
     */
    public static TypeDocument saisirTypeDocument() {
        String type ;
        TypeDocument typeDocument = null;
        boolean inputValide = false;
        while (!inputValide) {
            Scanner scan = new Scanner(System.in);
            System.out.println( DOCUMENT1 + DOCUMENT2);
            type = scan.nextLine();
            switch (type){
                case "texte":
                    typeDocument = TypeDocument.TEXTE;
                    inputValide = true;
                    break;
                case "xml":
                    typeDocument = TypeDocument.XML;
                    inputValide = true;
                    break;
                case "json":
                    typeDocument = TypeDocument.JSON;
                    inputValide = true;
                    break;
                default:
                    System.out.println(DOCUMENTINVALIDE);
                    break;
            }
        }
        return typeDocument;
    }

    /**
     * Saisie et valide le niveau de difficulté pour l'ordinateur.
     * @return le niveau de difficulté
     */
    public static Difficulte saisirNiveauDifficulte() {
        Difficulte difficulte = null;
        String difficulteSaisie;
        boolean inputValide = false;
        while (!inputValide) {
            Scanner scan = new Scanner(System.in);
            System.out.println(DIFFICULTE);
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
                    System.out.println(DIFFICULTEINVALIDE);
                    break;
            }
        }
        return difficulte;
    }

    /**
     * Saisie et valide l'option du jeu pour intégrer l'ajout de bombes.
     * @return true si l'option est implémentée, false sinon
     */
    public static boolean avecBombe() {
        boolean avecBombe = false;
        boolean inputValide = false;
        while (!inputValide) {
            Scanner scan = new Scanner(System.in);
            System.out.println(AVECBOMBE);
            String bombes = scan.nextLine();
            if (bombes.equals("oui")) {
                avecBombe = true;
                inputValide = true;
            } else if (bombes.equals("non")) {
                inputValide = true;
            } else {
                System.out.println(AVECBOMBEINVALIDE);
            }
        }
        return avecBombe;
    }

    /**
     * Saisie et valide les coordonnées d'une cellule où le joueur veut placer une bombe.
     * @return les coordonnées de la bombe
     */
    public static Coordonnee saisirBombe() {
        boolean inputValide = false;
        String bombe = null;

        while(!inputValide) {
            Scanner scan = new Scanner(System.in);
            System.out.println(BOMBE);
            bombe = scan.nextLine();

            if(bombe.length() == 2 && isDigit(bombe.charAt(1)) && bombe.charAt(0) >= 'A' && bombe.charAt(0) <= 'J'){
                inputValide = true;
            }else{
                System.out.println(COORDONNEEINVALIDE);
            }
        }
        return new Coordonnee(Integer.parseInt(String.valueOf(bombe.charAt(1))), Colonne.stringToColonne(bombe.charAt(0)));
    }

    /**
     * Saisie et valide les coordonnées d'une cellule où le joueur veut placer un navire.
     * @param navire le type de navire à placer.
     * @return Les coordonnées du naveau.
     */
    public static String saisirNavire(Navire navire) {
        String bateau = null;

        boolean inputValide = false;

        while(!inputValide) {
            Scanner scan = new Scanner(System.in);
            System.out.println(NAVIRE1 + navire.getTypeNavire() + NAVIRE2);
            bateau = scan.nextLine();
            if(bateau.length() == 3 && isDigit(bateau.charAt(1)) && bateau.charAt(0) >= 'A' && bateau.charAt(0) <= 'J' &&
                    (bateau.charAt(2) == 'H' || bateau.charAt(2) == 'V')) {
                inputValide = true;
            } else {
                System.out.println(COORDONNEEINVALIDE);
            }
        }
        return bateau;
    }

    /**
     * Détermine l'alignement du navire.
     * @param alignement l'alignement du navire
     * @return l'alignement du navire.
     */
    public static Alignement determinerAlignement(char alignement){
        Alignement alignementBateau = null;
        if(alignement == 'V'){
            alignementBateau = Alignement.VERTICAL;
        }else if(alignement == 'H'){
            alignementBateau = Alignement.HORIZONTAL;
        }
        return alignementBateau;
    }

    /**
     * Saisie et valide les coordonnées du tir que le joueur effectue.
     * @return la coordonnée du tir à effectuer
     */
    public static Coordonnee saisirTir() {
        String tir = null;
        boolean inputValide = false;
        while (!inputValide) {
            Scanner scan = new Scanner(System.in);
            System.out.println(TIR);
            tir = scan.nextLine();
            if(tir.length() == 2 && isDigit(tir.charAt(1)) && tir.charAt(0) >= 'A' && tir.charAt(0) <= 'J') {
                inputValide = true;
            } else {
                System.out.println(COORDONNEEINVALIDE);
            }
        }
        return new Coordonnee(Integer.parseInt(String.valueOf(tir.charAt(1))), Colonne.stringToColonne(tir.charAt(0)));
    }

    /**
     * Détermine et affiche le message de fin dépendamment du gagnant.
     * @param humain Le joueur humain et son statut de gagnant ou perdant
     * @param ordinateur L'ordinateur et son statut de gagnant ou perdant
     * @return le message qui affiche celui qui a gagné la partie
     */
    public static String afficherGagnant(boolean humain, boolean ordinateur) {
        String messageVictoire = null;
        if (humain) {
            messageVictoire = VICTOIREJOUEUR;
        } else if (ordinateur){
            messageVictoire = VICTOIREORDINATEUR;
        }
        return messageVictoire;
    }
}
