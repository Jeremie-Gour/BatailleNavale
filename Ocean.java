public class Ocean {
    private static final int tailleHorizontaleMax = 10;
    private static final int tailleVerticaleMax = 10;
    Flotte flotte = new Flotte();

    int cellulesTotales = tailleHorizontaleMax * tailleVerticaleMax;
    int cellulesOccupeesParBateau = 0;

    private Cellule[][] ocean;

    /**
     * Construit la grille de jeu.
     */
    public Ocean() {
        // Initialise une grille de jeu avec les dimensions maximales
        ocean = new Cellule[tailleHorizontaleMax][tailleVerticaleMax];
        // Remplit la grille de jeu avec des cellules vides (de l'eau)
        for (int h = 0; h < tailleHorizontaleMax; h++) {
            for (int v = 0; v < tailleVerticaleMax; v++) {
                ocean[h][v] = new Cellule(TypeCellule.EAU);
            }
        }
    }

    /**
     * Affiche la grille de jeu.
     */
    public void afficherOcean() {

        System.out.println();
        for (int h = 0; h < tailleHorizontaleMax; h++) {
            System.out.print(h + " ");
            System.out.print("| ");
            for (int v = 0; v < tailleVerticaleMax; v++) {
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
            Coordonnee coordonnees = new Coordonnee(1,1);
            Alignement alignement = Alignement.VERTICAL;

            placerNavire(coordonnees, alignement, navire);
        }
    }



    /**
     *
     * Place un navire sur l'océan. La proue du navire est prise en entrée.
     * Si le navire est placé horizontalement, la proue du navire fait face à l'ouest.
     * Si le navire est placée verticalement, la proue du navire fait face au nord.
     */
    // Il faut ajouter une methode de verification
    public void placerNavire(Coordonnee coordonnees, Alignement alignement, Navire navire) {
        if (alignement.name().equals("VERTICAL")) {
            for (int i = 0; i < navire.getTypeNavire().getTaille(); i++) {
                ocean[coordonnees.getRangee()][coordonnees.getColonne() + i].typeCellule = TypeCellule.NAVIREINTACT;
                navire.setPositions(i,new Coordonnee(coordonnees.getRangee(),coordonnees.getColonne() + i));
            }
        } else if (alignement.name().equals("HORIZONTAL")){
            for (int i = 0; i < navire.getTypeNavire().getTaille(); i++) {
                ocean[coordonnees.getRangee() + i][coordonnees.getColonne()].typeCellule = TypeCellule.NAVIREINTACT;
                navire.setPositions(i,new Coordonnee(coordonnees.getRangee(),coordonnees.getColonne()));
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
    public TypeCellule tirer(){
        TypeCellule typeCellule = null;

        Coordonnee coordonneeTir1 = new Coordonnee(1,1);
        if (ocean[coordonneeTir1.getRangee()][coordonneeTir1.getColonne()].typeCellule == TypeCellule.EAU){
           typeCellule = TypeCellule.EAU;
        } else if (ocean[coordonneeTir1.getRangee()][coordonneeTir1.getColonne()].typeCellule == TypeCellule.NAVIREINTACT){
            ocean[coordonneeTir1.getRangee()][coordonneeTir1.getColonne()].typeCellule = TypeCellule.NAVIRETOUCHE;
            typeCellule = TypeCellule.NAVIREINTACT;
            // Tiré sur bombe non touchée
        } else if (ocean[coordonneeTir1.getRangee()][coordonneeTir1.getColonne()].typeCellule == TypeCellule.BOMBE){
            typeCellule = TypeCellule.BOMBE;
        }
        else if (ocean[coordonneeTir1.getRangee()][coordonneeTir1.getColonne()].typeCellule == TypeCellule.NAVIRETOUCHE){
            typeCellule = TypeCellule.NAVIRETOUCHE;
        }
        else if (ocean[coordonneeTir1.getRangee()][coordonneeTir1.getColonne()].typeCellule == TypeCellule.NAVIRECOULE){
            typeCellule = TypeCellule.NAVIRECOULE;
        }
        else if (ocean[coordonneeTir1.getRangee()][coordonneeTir1.getColonne()].typeCellule == TypeCellule.NAVIRERATE){
            typeCellule = TypeCellule.NAVIRERATE;
        }

        afficherOcean();
        return typeCellule;

    }

    public void placerBombes() {
        Coordonnee coordonneeTir1 = new Coordonnee(5,5);
        ocean[coordonneeTir1.getRangee()][coordonneeTir1.getColonne()].typeCellule = TypeCellule.BOMBE;

    }
}
