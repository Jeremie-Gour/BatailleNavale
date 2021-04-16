import java.util.Random;

public class Ocean {
    private static final int TAILLE_HORIZONTALE_MAX = 10;
    private static final int TAILLE_VERTICALE_MAX = 10;
    Flotte flotte = new Flotte();

    int cellulesTotales = TAILLE_HORIZONTALE_MAX * TAILLE_VERTICALE_MAX;
    int cellulesOccupeesParBateau = 0;

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
     * Place tous les navires sur l'océan.
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
     * Place une flotte de façon aléatoire sur l'océan
     */
    public void placerNaviresAleatoirement() {
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

                            // Verifie si la cellule est libre
                            if (ocean[rangee][colonne + i].estOccupeeParNavire()) {
                                estEmplacementValide = false;
                                break;

                            } else {
                                // Regarde en haut et en bas
                                // Si la rangee est la limite superieure:
                                if (rangee == 0) {
                                    if (ocean[rangee + 1][colonne].estOccupeeParNavire()) {
                                        estEmplacementValide = false;
                                        break;
                                    }
                                    // Si la rangee est la limite inferieure
                                } else if (rangee == (TAILLE_HORIZONTALE_MAX - 1)) {
                                    if (ocean[rangee - 1][colonne].estOccupeeParNavire()) {
                                        estEmplacementValide = false;
                                        break;
                                    }
                                } else {
                                    if (ocean[rangee - 1][colonne].estOccupeeParNavire() || ocean[rangee + 1][colonne].estOccupeeParNavire()) {
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

                            // Verifie si la cellule est libre
                            if (ocean[rangee + i][colonne].estOccupeeParNavire()) {
                                estEmplacementValide = false;
                                break;

                            } else {
                                // Regarde a gauche et a droite
                                // Si la colonne est la limite de gauche:
                                if (colonne == 0) {
                                    if (!ocean[rangee][colonne + 1].estOccupeeParNavire()){
                                        estEmplacementValide = false;
                                        break;
                                    }
                                } else if (colonne == TAILLE_HORIZONTALE_MAX - 1) {
                                    if (ocean[rangee][colonne - 1].estOccupeeParNavire()) {
                                        estEmplacementValide = false;
                                        break;
                                    }
                                } else {
                                    if (ocean[rangee][colonne + 1].estOccupeeParNavire() || ocean[rangee][colonne - 1].estOccupeeParNavire()) {
                                        estEmplacementValide = false;
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        estEmplacementValide = false;
                    }
                }
                System.out.println("Fin du while, Alignement = " + alignement.name() + " coord(" + rangee + ", " + colonne + ")");
            } while (!estEmplacementValide);


                Coordonnee coordonnees = new Coordonnee(rangee, colonne);
                placerNavire(coordonnees, alignement, navire);
        }
    }


    /**
     * Place un navire sur l'océan. La proue du navire est prise en entrée.
     * Si le navire est placé horizontalement, la proue du navire fait face à l'ouest.
     * Si le navire est placée verticalement, la proue du navire fait face au nord.
     */
// Il faut ajouter une methode de verification
    public void placerNavire(Coordonnee coordonnees, Alignement alignement, Navire navire) {
        if (alignement == Alignement.HORIZONTAL) {
            for (int i = 0; i < navire.getTypeNavire().getTaille(); i++) {
                ocean[coordonnees.getRangee()][coordonnees.getColonne() + i].typeCellule = TypeCellule.NAVIREINTACT;
//                navire.setPositions(i, new Coordonnee(coordonnees.getRangee(), coordonnees.getColonne() + i));
            }
        } else if (alignement == Alignement.VERTICAL) {
            for (int i = 0; i < navire.getTypeNavire().getTaille(); i++) {
                ocean[coordonnees.getRangee() + i][coordonnees.getColonne()].typeCellule = TypeCellule.NAVIREINTACT;
//                navire.setPositions(i, new Coordonnee(coordonnees.getRangee(), coordonnees.getColonne()));
            }
        }
        cellulesOccupeesParBateau++;
        afficherOcean();
    }

    /**
     * Effectue un tir.
     * Retourne -1 si le tir est invalide.
     * Retourne 0 si le tir est valide.
     * Retourne 1 si le joueur a tiré sur une bombe.
     */
    public TypeCellule tirer() {
        TypeCellule typeCellule = null;

        Coordonnee coordonneeTir1 = new Coordonnee(1, 1);
        if (ocean[coordonneeTir1.getRangee()][coordonneeTir1.getColonne()].typeCellule == TypeCellule.EAU) {
            typeCellule = TypeCellule.EAU;
        } else if (ocean[coordonneeTir1.getRangee()][coordonneeTir1.getColonne()].typeCellule == TypeCellule.NAVIREINTACT) {
            ocean[coordonneeTir1.getRangee()][coordonneeTir1.getColonne()].typeCellule = TypeCellule.NAVIRETOUCHE;
            typeCellule = TypeCellule.NAVIREINTACT;
            // Tiré sur bombe non touchée
        } else if (ocean[coordonneeTir1.getRangee()][coordonneeTir1.getColonne()].typeCellule == TypeCellule.BOMBE) {
            typeCellule = TypeCellule.BOMBE;
        } else if (ocean[coordonneeTir1.getRangee()][coordonneeTir1.getColonne()].typeCellule == TypeCellule.NAVIRETOUCHE) {
            typeCellule = TypeCellule.NAVIRETOUCHE;
        } else if (ocean[coordonneeTir1.getRangee()][coordonneeTir1.getColonne()].typeCellule == TypeCellule.NAVIRECOULE) {
            typeCellule = TypeCellule.NAVIRECOULE;
        } else if (ocean[coordonneeTir1.getRangee()][coordonneeTir1.getColonne()].typeCellule == TypeCellule.NAVIRERATE) {
            typeCellule = TypeCellule.NAVIRERATE;
        }

        afficherOcean();
        return typeCellule;
    }

    /**
     * Place une bombe sur l'océan.
     */
    public void placerBombes() {
        Coordonnee coordonneeTir1 = new Coordonnee(5, 5);
        ocean[coordonneeTir1.getRangee()][coordonneeTir1.getColonne()].typeCellule = TypeCellule.BOMBE;
    }
}
