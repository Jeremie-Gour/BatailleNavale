public abstract class Bateau {
    protected int longeur;
    protected Coordonnee[] coordonnees;

    protected  Coordonnee[] construireCoordonne(String[] _porteAvions, int longeur){
        coordonnees = new Coordonnee[longeur];
        for (int i = 0; i < longeur; i++){
            Latitude latitudeTmp = Latitude.valueOf(Character.toString(_porteAvions[i].charAt(0)));
            Coordonnee coordonnee = new Coordonnee( latitudeTmp,Integer.parseInt(_porteAvions[i].substring(1)));
            this.coordonnees[i] = coordonnee;
        }
        return coordonnees;
    }
}
