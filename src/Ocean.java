import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Cette classe représente un océan sous la forme d'une matrice de jeu.
 */
public class Ocean {
    private static final int TAILLE_HORIZONTALE_MAX = 10;
    private static final int TAILLE_VERTICALE_MAX = 10;
    Flotte flotte = new Flotte();

    int cellulesTotales = TAILLE_HORIZONTALE_MAX * TAILLE_VERTICALE_MAX;
    int cellulesOccupeesParBateau = 0;
    int cellulesDeNavireIntactes = calculerNombreTirsRestants();
    Set<Coordonnee> ce = new HashSet<>();

    private Cellule[][] ocean;

    /**
     * Construit la grille de jeu.
     */
    public Ocean() {
        // Initialise une grille de jeu avec les dimensions maximales
        ocean = new Cellule[TAILLE_HORIZONTALE_MAX][TAILLE_VERTICALE_MAX];
        // Remplit la grille de jeu avec des cellules vides (de l'eau)
        for (int h = 0; h < TAILLE_HORIZONTALE_MAX; h++) {
            for (int v = 0; v < TAILLE_VERTICALE_MAX; v++) {
                ocean[h][v] = new Cellule(TypeCellule.EAU);
            }
        }
    }

    /**
     * Affiche la grille de jeu.
     */
    public void afficherOcean() {

        System.out.println();
        for (int h = 0; h < TAILLE_HORIZONTALE_MAX; h++) {
            System.out.print(h + " ");
            System.out.print("| ");
            for (int v = 0; v < TAILLE_VERTICALE_MAX; v++) {
                System.out.print(ocean[h][v].typeCellule.getIcone() + " ");
            }
            System.out.print('|');
            System.out.println();
        }
        System.out.print("    ");
        for (int i = 0; i < 10; i++) {
            System.out.print(Character.toString('A' + i) + " ");
        }
        System.out.println();
    }


    /**
     * Place tous les navires d'une flotte sur l'océan.
     */
    public void placerNavires() {
        for (Navire navire : flotte.listeNavires) {
            System.out.println("\n Veuillez entrer les coordonnées de la proue du " + navire.getTypeNavire() + " et son alignement (H ou V). Exemple: A5V");

            // A changer pour construire les coordonnees et l'alignement avec les entrees de l'utilisateur
            Coordonnee coordonnees = new Coordonnee(1, 1);
            Alignement alignement = Alignement.VERTICAL;

            placerNavire(coordonnees, alignement, navire);
        }
    }


    /**
     * Place tous les navires d'une flotte de façon aléatoire sur l'océan.
     */
    public void placerNaviresAleatoirement(TypeJoueur typeJoueur) {
        Alignement alignement;
        int tmpAlignement; // Si 0, alignement horizontal, 1 vertical
        int rangee;
        int colonne;

        for (Navire navire : flotte.listeNavires) {
            boolean estEmplacementValide;

            Random random = new java.util.Random();

            // Génère un alignement
            tmpAlignement = random.nextInt(2);
            if (tmpAlignement == 0) {
                alignement = Alignement.HORIZONTAL;
            } else {
                alignement = Alignement.VERTICAL;
            }

            do {
                estEmplacementValide = true;
                rangee = random.nextInt((10));
                colonne = random.nextInt((10));
                if (alignement == Alignement.HORIZONTAL) {
                    //Verifie que ca ne peut pas depasser les bordures
                    if (colonne + navire.getTypeNavire().getTaille() < TAILLE_HORIZONTALE_MAX) {

                        // Verifie pour chacune des cellules si il n'y a pas deja un bateau qui overlap et si il n'y en a pas un collé
                        for (int i = 0; i < navire.getTypeNavire().getTaille(); i++) {
                            if (i == 0) {
                                if (colonne != 0) {
                                    if (ocean[rangee][colonne - 1].estOccupeeParNavire()) {
                                        estEmplacementValide = false;
                                        break;
                                    }
                                }
                            }
                            if (i == navire.getTypeNavire().getTaille() - 1) {
                                if (colonne != TAILLE_HORIZONTALE_MAX - 1) {
                                    if (ocean[rangee][colonne + i + 1].estOccupeeParNavire()) {
                                        estEmplacementValide = false;
                                        break;
                                    }
                                }
                            }

                            // Verifie si la cellule est libre
                            if (ocean[rangee][colonne + i].estOccupeeParNavire()) {
                                estEmplacementValide = false;
                                break;

                            } else {
                                // Regarde en haut et en bas
                                // Si la rangee est la limite superieure:
                                if (rangee == 0) {
                                    if (ocean[rangee + 1][colonne + i].estOccupeeParNavire()) {
                                        estEmplacementValide = false;
                                        break;
                                    }
                                    // Si la rangee est la limite inferieure
                                } else if (rangee == (TAILLE_HORIZONTALE_MAX - 1)) {
                                    if (ocean[rangee - 1][colonne + i].estOccupeeParNavire()) {
                                        estEmplacementValide = false;
                                        break;
                                    }
                                } else {
                                    if (ocean[rangee - 1][colonne + i].estOccupeeParNavire() || ocean[rangee + 1][colonne + i].estOccupeeParNavire()) {
                                        estEmplacementValide = false;
                                        break;
                                    }
                                }
                            }

                        }
                    } else {
                        estEmplacementValide = false;
                    }
                } else {
                    // Verifie que ca ne peut pas depasser les bordures
                    if (rangee + navire.getTypeNavire().getTaille() < TAILLE_VERTICALE_MAX) {

                        // Verifie pour chacune des cellules si il n'y a pas deja un bateau qui overlap et si il n'y en a pas un collé
                        for (int i = 0; i < navire.getTypeNavire().getTaille(); i++) {

                            if (i == 0) {
                                if (rangee != 0) {
                                    if (ocean[rangee - 1][colonne].estOccupeeParNavire()) {
                                        estEmplacementValide = false;
                                        break;
                                    }
                                }
                            }
                            if (i == navire.getTypeNavire().getTaille() - 1) {
                                if (colonne != TAILLE_VERTICALE_MAX - 1) {
                                    if (ocean[rangee + i + 1][colonne].estOccupeeParNavire()) {
                                        estEmplacementValide = false;
                                        break;
                                    }
                                }
                            }

                            // Verifie si la cellule est libre
                            if (ocean[rangee + i][colonne].estOccupeeParNavire()) {
                                estEmplacementValide = false;
                                break;

                            } else {
                                // Regarde a gauche et a droite
                                // Si la colonne est la limite de gauche:
                                if (colonne == 0) {
                                    if (!ocean[rangee + i][colonne + 1].estOccupeeParNavire()) {
                                        estEmplacementValide = false;
                                        break;
                                    }
                                } else if (colonne == TAILLE_HORIZONTALE_MAX - 1) {
                                    if (ocean[rangee + i][colonne - 1].estOccupeeParNavire()) {
                                        estEmplacementValide = false;
                                        break;
                                    }
                                } else if (ocean[rangee + i][colonne + 1].estOccupeeParNavire() || ocean[rangee + i][colonne - 1].estOccupeeParNavire()) {
                                    estEmplacementValide = false;
                                    break;
                                }

                            }
                        }
                    } else {
                        estEmplacementValide = false;
                    }
                }
            } while (!estEmplacementValide);


            Coordonnee coordonnees = new Coordonnee(rangee, colonne);
            placerNavire(coordonnees, alignement, navire);
        }
    }


    /**
     * Place un navire sur l'océan. Les coordonnées de la proue du navire sont prises en entrée.
     * Si le navire est placé horizontalement, la proue du navire fait face à l'ouest.
     * Si le navire est placée verticalement, la proue du navire fait face au nord.
     * @param coordonnees Les coordonnées de la proue du navire sont prises en entrée.
     * @param alignement L'alignement du navire sur la matrice.
     * @param navire Le navire à placer sir la matrice.
     */
    // Il faut ajouter une methode de verification
    public void placerNavire(Coordonnee coordonnees, Alignement alignement, Navire navire) {
        if (alignement == Alignement.HORIZONTAL) {
            for (int i = 0; i < navire.getTypeNavire().getTaille(); i++) {
                ocean[coordonnees.getRangee()][coordonnees.getColonne() + i].typeCellule = TypeCellule.NAVIREINTACT;
                navire.setPositions(i, new Coordonnee(coordonnees.getRangee(), coordonnees.getColonne() + i));
            }
        } else if (alignement == Alignement.VERTICAL) {
            for (int i = 0; i < navire.getTypeNavire().getTaille(); i++) {
                ocean[coordonnees.getRangee() + i][coordonnees.getColonne()].typeCellule = TypeCellule.NAVIREINTACT;
                navire.setPositions(i, new Coordonnee(coordonnees.getRangee() + i, coordonnees.getColonne()));
            }
        }
        cellulesOccupeesParBateau++;
    }

    /**
     * Tire une cellule sur la grille de jeu.
     * @param coordonnee La coordonnée de la cellule à tirer.
     * @return le type de la cellule qui a été tirée.
     */
    public TypeCellule tirer(Coordonnee coordonnee) {
        TypeCellule typeCellule = TypeCellule.EAU;

        if (ocean[coordonnee.getRangee()][coordonnee.getColonne()].typeCellule == TypeCellule.EAU) {
            ocean[coordonnee.getRangee()][coordonnee.getColonne()].typeCellule = TypeCellule.NAVIRERATE;
            typeCellule = TypeCellule.EAU;
        } else if (ocean[coordonnee.getRangee()][coordonnee.getColonne()].typeCellule == TypeCellule.NAVIREINTACT) {
            ocean[coordonnee.getRangee()][coordonnee.getColonne()].typeCellule = TypeCellule.NAVIRETOUCHE;
            typeCellule = TypeCellule.NAVIREINTACT;
            cellulesDeNavireIntactes--;
            // Tiré sur bombe non touchée
        } else if (ocean[coordonnee.getRangee()][coordonnee.getColonne()].typeCellule == TypeCellule.BOMBE) {
            ocean[coordonnee.getRangee()][coordonnee.getColonne()].typeCellule = TypeCellule.NAVIRERATE;

            typeCellule = TypeCellule.BOMBE;
        } else if (ocean[coordonnee.getRangee()][coordonnee.getColonne()].typeCellule == TypeCellule.NAVIRETOUCHE) {

            typeCellule = TypeCellule.NAVIRETOUCHE;
        } else if (ocean[coordonnee.getRangee()][coordonnee.getColonne()].typeCellule == TypeCellule.NAVIRECOULE) {
            ocean[coordonnee.getRangee()][coordonnee.getColonne()].typeCellule = TypeCellule.NAVIRERATE;

            typeCellule = TypeCellule.NAVIRECOULE;
        } else if (ocean[coordonnee.getRangee()][coordonnee.getColonne()].typeCellule == TypeCellule.NAVIRERATE) {
            ocean[coordonnee.getRangee()][coordonnee.getColonne()].typeCellule = TypeCellule.NAVIRERATE;

            typeCellule = TypeCellule.NAVIRERATE;
        }

        ocean[coordonnee.getRangee()][coordonnee.getColonne()].typeCellule.setTiree(true);

        afficherOcean();
        return typeCellule;
    }

    /**
     * Place une bombe sur la grille de jeu.
     */
    // Il faut vérifier qu'on ne tire pas en dehors de la matrice.
    public void placerBombes() {
        Coordonnee coordonneeTir1 = new Coordonnee(5, 5);
        ocean[coordonneeTir1.getRangee()][coordonneeTir1.getColonne()].typeCellule = TypeCellule.BOMBE;
    }

    /**
     * Tire une cellule sur la grille de jeu.
     * @param difficulte Le niveau de difficulté du tir effectué par l'ordinateur
     * @return true si il reste des cellules occupées par un navire et qui n'ont pas déjà été tirées.
     */
    public boolean tirerOrdinateur(Difficulte difficulte) {
        boolean tir = false;
        if (difficulte == Difficulte.FACILE) {
            tir = tirerOrdinateurFacile();
        } else if (difficulte == Difficulte.INTERMEDIAIRE) {
            tir = tirerOrdinateurIntermediaire();
        } else if (difficulte == Difficulte.DIFFICILE) {
            tir = tirerOrdinateurDifficile();
        }
        return tir;
    }

    private boolean tirerOrdinateurDifficile() {
        boolean tir = false;
        return tir;
    }


    private boolean tirerOrdinateurIntermediaire() {
        Random random = new Random();
        int rangee = random.nextInt((10));
        int colonne = random.nextInt((10));
        Coordonnee coordonnee = new Coordonnee(rangee, colonne);

        // Ici, on a plusieurs choix de design d'algorithme:

        // Si la coordonne a deja ete prise, on en prend une autre
        // On pourrait en générer aléatoirement une nouvelle à chaque fois,
        // OU on pourrait avoir un Set/ensemble de coordonnées ou cellules uniques.
        // On pourrait prendre aléatoirement une coordonnée dans cet ensemble et la retirer de l'ensemble
        // Après, on pourrait repiger la prochaine coordonnée au lieu de toujours générer et d'aller checker dans la grille


        // Ensuite, il faut trouver comment on décide de tirer sur le même bateau.
        // Les TypeCellules de chacune des cellules de la grille devraient être utiles pour déterminer cela.

        // Il faut évidemment toujours vérifier si on n'est pas en train de tirer en dehors de la grille.

        if (ocean[rangee][colonne].typeCellule.isTiree()) {

        }



        return cellulesDeNavireIntactes > 0;
    }

    /**
     * Effectue un tir aléatoire sur l'océan. Peut tirer au même endroit.
     *
     * @return le résultat du tir.
     */
    private boolean tirerOrdinateurFacile() {
        Random random = new Random();
        int rangee = random.nextInt((10));
        int colonne = random.nextInt((10));
        Coordonnee coordonnee = new Coordonnee(rangee, colonne);
        tirer(coordonnee);
        return cellulesDeNavireIntactes > 0;
    }

    /**
     * Calcul le nombre de tirs totaux a effectuer a partir de la taille de tous les bateaux d'une flotte
     *
     * @return
     */
    private int calculerNombreTirsRestants() {
        int cellulesNavireIntactes = 0;
        for (Navire navire : flotte.listeNavires) {
            cellulesNavireIntactes += navire.getTypeNavire().getTaille();
        }

        return cellulesNavireIntactes;
    }
}
