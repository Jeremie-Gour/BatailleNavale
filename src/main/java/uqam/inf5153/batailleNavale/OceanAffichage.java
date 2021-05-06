package uqam.inf5153.batailleNavale;

public class OceanAffichage {

    /**
     * Affiche la grille de jeu.
     */
    public static void afficherOcean(Ocean ocean) {
        System.out.println();
        for (int h = 0; h < Ocean.TAILLE_HORIZONTALE_MAX; h++) {
            System.out.print(h + " ");
            System.out.print("| ");
            for (int v = 0; v < Ocean.TAILLE_VERTICALE_MAX; v++) {
                System.out.print(ocean.getOcean()[h][v].typeCellule.getIcone() + " ");
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
     * Affiche la grille de jeu, mais seulement avec les cellules déjà tirées.
     */
    public static void afficherOceanMasque(Ocean ocean) {
        System.out.println();
        for (int h = 0; h < Ocean.TAILLE_HORIZONTALE_MAX; h++) {
            System.out.print(h + " ");
            System.out.print("| ");
            for (int v = 0; v < Ocean.TAILLE_VERTICALE_MAX; v++) {
                if (ocean.getOcean()[h][v].typeCellule == TypeCellule.BOMBE || ocean.getOcean()[h][v].typeCellule == TypeCellule.NAVIREINTACT) {
                    System.out.print(TypeCellule.EAU.getIcone() + " ");
                } else {
                    System.out.print(ocean.getOcean()[h][v].typeCellule.getIcone() + " ");
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
}
