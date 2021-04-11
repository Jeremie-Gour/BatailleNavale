public class PorteAvions extends Bateau {
    private int longeur;
    protected Coordonnee[] coordonnees;

    public PorteAvions(String[] _porteAvions){
        longeur = 5;
        coordonnees = construireCoordonne(_porteAvions, longeur);
    }
}
