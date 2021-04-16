public enum TypeNavire {
    PORTEAVIONS(5),
    CROISEUR(4),
    CONTRETORPILLEUR(3),
    TORPILLEUR(2);

    private int taille;

    TypeNavire(int _taille) {
        this.taille = _taille;
    }

    public int getTaille() {
        return taille;
    }

    @Override
    public String toString() {
        String nom = "Navire";
        if (this.name().equals(PORTEAVIONS.name())){
            nom = "Porte-Avions";
        } else if (this.name().equals(CROISEUR.name())){
            nom = "Croiseur";
        } else if (this.name().equals(CONTRETORPILLEUR.name())){
            nom = "Contre-Torpilleur";
        } else if (this.name().equals(TORPILLEUR.name())){
            nom = "Torpilleur";
        }
        return nom;
    }
}
