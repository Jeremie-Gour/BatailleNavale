package uqam.inf5153.batailleNavale;

/**
 * Cette énumération énumère les différentes colonnes de la grille de jeu.
 */
public enum Colonne {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7),
    I(8),
    J(9);

    private int colonne;

    Colonne(int colonne) {
        this.colonne = colonne;
    }

    public int getColonne() {
        return colonne;
    }

    /**
     * Transforme le char correspondant à la colonne en integer.
     * @param charColonne le caractère qui représente la colonne.
     * @return La valeur en int de la colonne.
     */
    public static int stringToColonne(char charColonne) {
        Colonne colonne = null;
        switch(charColonne) {
            case 'A':
                colonne = A;
                break;
            case 'B':
                colonne = B;
                break;
            case 'C':
                colonne = C;
                break;
            case 'D':
                colonne = D;
                break;
            case 'E':
                colonne = E;
                break;
            case 'F':
                colonne = F;
                break;
            case 'G':
                colonne = G;
                break;
            case 'H':
                colonne = H;
                break;
            case 'I':
                colonne = I;
                break;
            case 'J':
                colonne = J;
                break;
        }

        return colonne.getColonne();
    }
}
