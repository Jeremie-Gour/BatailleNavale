package uqam.inf5153.batailleNavale;

/**
 * Cette énumération contient les différents niveaux de difficultés de l'ordinateur.
 */
public enum Difficulte {
    FACILE(1),
    INTERMEDIAIRE(2),
    DIFFICILE(3);

    private int _difficulte;

    Difficulte(int _difficulte) {
        this._difficulte = _difficulte;
    }
}