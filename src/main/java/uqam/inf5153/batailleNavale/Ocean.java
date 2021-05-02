package uqam.inf5153.batailleNavale;

import java.util.Random;

/**
 * Cette classe représente un océan sous la forme d'une matrice de jeu.
 */
public class Ocean {
    private static final int TAILLE_HORIZONTALE_MAX = 10;
    private static final int TAILLE_VERTICALE_MAX = 10;
    int cellulesOccupeesParBateau = 0;
    Flotte flotte = new Flotte();
    private Cellule[][] ocean;
    int cellulesDeNavireIntactes = calculerNombreTirsRestants();


    public Cellule[][] getOcean() {
        return ocean;
    }

    /**
     * Construit la grille de jeu.
     */
    public Ocean() {
        // Initialise une grille de jeu avec les dimensions maximales.
        ocean = new Cellule[TAILLE_HORIZONTALE_MAX][TAILLE_VERTICALE_MAX];
        // Remplit la grille de jeu avec des cellules vides (de l'eau).
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

    public void afficherOceanMasque() {
        System.out.println();
        for (int h = 0; h < TAILLE_HORIZONTALE_MAX; h++) {
            System.out.print(h + " ");
            System.out.print("| ");
            for (int v = 0; v < TAILLE_VERTICALE_MAX; v++) {
                if (ocean[h][v].typeCellule == TypeCellule.BOMBE || ocean[h][v].typeCellule == TypeCellule.NAVIREINTACT) {
                    System.out.print(TypeCellule.EAU.getIcone() + " ");
                } else {
                    System.out.print(ocean[h][v].typeCellule.getIcone() + " ");
                }
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
     * Place tous les navires d'une flotte de façon aléatoire sur l'océan.
     */
    public void placerNaviresAleatoirement() {
        Alignement alignement;
        int tmpAlignement; // Si 0, alignement horizontal, 1 vertical
        int rangee;
        int colonne;

        for (Navire navire : flotte.getListeNavires()) {
            boolean estEmplacementValide;
            Coordonnee coordonnee;

            Random random = new Random();

            // Génère un alignement
            tmpAlignement = random.nextInt(2);
            if (tmpAlignement == 0) {
                alignement = Alignement.HORIZONTAL;
            } else {
                alignement = Alignement.VERTICAL;
            }

            do {
                rangee = random.nextInt((10));
                colonne = random.nextInt((10));
                coordonnee = new Coordonnee(rangee, colonne);
                estEmplacementValide = verifierBateau(coordonnee, alignement, navire);
            } while (!estEmplacementValide);
//            Coordonnee coordonnees = new Coordonnee(rangee, colonne);
            placerNavire(coordonnee, alignement, navire);
        }
    }

    /**
     * Vérifie le placement d'un navire.
     *
     * @param coordonne  Les coordonnées de la proue du navire
     * @param alignement L'alignement du navire
     * @param navire     le navire
     * @return true si le positionnement est valide, false sinon
     */
    public boolean verifierBateau(Coordonnee coordonne, Alignement alignement, Navire navire) {
        boolean estEmplacementValide = true;
        int rangee = coordonne.getRangee();
        int colonne = coordonne.getColonne();
        if (alignement == Alignement.HORIZONTAL) {
            //Verifie que ca ne peut pas depasser les bordures
            if (colonne + navire.getTypeNavire().getTaille() < TAILLE_HORIZONTALE_MAX) {

                // Vérifie pour chacune des cellules si elle n'est pas déjà occuppée par un bateau et si il n'y en a pas un collé
                for (int i = 0; i < navire.getTypeNavire().getTaille(); i++) {
                    if (i == 0) {
                        if (colonne != 0) {
                            if (ocean[rangee][colonne - 1].estOccupee()) {
                                estEmplacementValide = false;
                                break;
                            }
                        }
                    }
                    if (i == navire.getTypeNavire().getTaille() - 1) {
                        if (colonne != TAILLE_HORIZONTALE_MAX - 1) {
                            if (ocean[rangee][colonne + i + 1].estOccupee()) {
                                estEmplacementValide = false;
                                break;
                            }
                        }
                    }

                    // Vérifie si la cellule est libre
                    if (ocean[rangee][colonne + i].estOccupee()) {
                        estEmplacementValide = false;
                        break;

                    } else {
                        // Regarde en haut et en bas
                        // Si la rangée est la limite supérieure:
                        if (rangee == 0) {
                            if (ocean[rangee + 1][colonne + i].estOccupee()) {
                                estEmplacementValide = false;
                                break;
                            }
                            // Si la rangée est la limite inférieure
                        } else if (rangee == (TAILLE_HORIZONTALE_MAX - 1)) {
                            if (ocean[rangee - 1][colonne + i].estOccupee()) {
                                estEmplacementValide = false;
                                break;
                            }
                        } else {
                            if (ocean[rangee - 1][colonne + i].estOccupee() || ocean[rangee + 1][colonne + i].estOccupee()) {
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
            // Vérifie que ça ne peut pas dépasser les bordures
            if (rangee + navire.getTypeNavire().getTaille() < TAILLE_VERTICALE_MAX) {

                // Vérifie pour chacune des cellules si elle n'est pas déjà occuppée par un bateau et si il n'y en a pas un collé
                for (int i = 0; i < navire.getTypeNavire().getTaille(); i++) {

                    if (i == 0) {
                        if (rangee != 0) {
                            if (ocean[rangee - 1][colonne].estOccupee()) {
                                estEmplacementValide = false;
                                break;
                            }
                        }
                    }
                    if (i == navire.getTypeNavire().getTaille() - 1) {
                        if (colonne != TAILLE_VERTICALE_MAX - 1) {
                            if (ocean[rangee + i + 1][colonne].estOccupee()) {
                                estEmplacementValide = false;
                                break;
                            }
                        }
                    }

                    // Vérifie si la cellule est libre
                    if (ocean[rangee + i][colonne].estOccupee()) {
                        estEmplacementValide = false;
                        break;

                    } else {
                        // Regarde a gauche et a droite
                        // Si la colonne est la limite de gauche:
                        if (colonne == 0) {
                            if (ocean[rangee + i][colonne + 1].estOccupee()) {
                                estEmplacementValide = false;
                                break;
                            }
                        } else if (colonne == TAILLE_HORIZONTALE_MAX - 1) {
                            if (ocean[rangee + i][colonne - 1].estOccupee()) {
                                estEmplacementValide = false;
                                break;
                            }
                        } else if (ocean[rangee + i][colonne + 1].estOccupee() || ocean[rangee + i][colonne - 1].estOccupee()) {
                            estEmplacementValide = false;
                            break;
                        }
                    }
                }
            } else {
                estEmplacementValide = false;
            }
        }
        if (estEmplacementValide) {
            placerNavire(coordonne, alignement, navire);
        }
        return estEmplacementValide;
    }


    /**
     * Place un navire sur l'océan. Les coordonnées de la proue du navire sont prises en entrée.
     * Si le navire est placé horizontalement, la proue du navire fait face à l'ouest.
     * Si le navire est placée verticalement, la proue du navire fait face au nord.
     *
     * @param coordonnees Les coordonnées de la proue du navire sont prises en entrée.
     * @param alignement  L'alignement du navire sur la matrice.
     * @param navire      Le navire à placer sir la matrice.
     */
    // Il faut ajouter une methode de verification
    public void placerNavire(Coordonnee coordonnees, Alignement alignement, Navire navire) {
        if (alignement == Alignement.HORIZONTAL) {
            for (int i = 0; i < navire.getTypeNavire().getTaille(); i++) {
                ocean[coordonnees.getRangee()][coordonnees.getColonne() + i].typeCellule = TypeCellule.NAVIREINTACT;
            }
        } else if (alignement == Alignement.VERTICAL) {
            for (int i = 0; i < navire.getTypeNavire().getTaille(); i++) {
                ocean[coordonnees.getRangee() + i][coordonnees.getColonne()].typeCellule = TypeCellule.NAVIREINTACT;
            }
        }
        cellulesOccupeesParBateau++;
    }

    /**
     * Tire une cellule sur la grille de jeu.
     *
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


        return typeCellule;
    }

    /**
     * Place une bombe sur la grille de jeu.
     */
    public boolean placerBombe(Coordonnee coordonnee) {
        boolean positionBombeValide = true;
        if ((coordonnee.getColonne() < 0 || coordonnee.getColonne() > TAILLE_HORIZONTALE_MAX - 1) || (coordonnee.getRangee() < 0 || coordonnee.getRangee() > TAILLE_VERTICALE_MAX - 1)) {
            positionBombeValide = false;
        } else if (ocean[coordonnee.getRangee()][coordonnee.getColonne()].estOccupee()) {
            positionBombeValide = false;
        } else {
            ocean[coordonnee.getRangee()][coordonnee.getColonne()].typeCellule = TypeCellule.BOMBE;
        }
        return positionBombeValide;
    }


    public boolean resteDesNavires() {
        return cellulesDeNavireIntactes > 0;
    }

    /**
     * Calcul le nombre de tirs totaux à effectuer à partir de la taille de tous les bateaux d'une flotte
     *
     * @return Le nombre de cellules de type NAVIREINTACT restantes
     */
    private int calculerNombreTirsRestants() {
        int cellulesNavireIntactes = 0;
        for (Navire navire : flotte.getListeNavires()) {
            cellulesNavireIntactes += navire.getTypeNavire().getTaille();
        }
        return cellulesNavireIntactes;
    }
}
