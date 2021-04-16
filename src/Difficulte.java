/**
 * Cette énumération contient les différents niveaux de difficultés de l'ordinateur.
 */
public enum Difficulte {
    FACILE(1),
    INTERMEDIAIRE(2),
    DIFFICILE(3);

    private final int difficulte;

    Difficulte(int _difficulte) {
        this.difficulte = _difficulte;
    }

    public int getDifficulte() {
        return difficulte;
    }
}