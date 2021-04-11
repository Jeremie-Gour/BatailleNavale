public class Croiseur extends Bateau {
    private int longeur;
    private Case[] croiseur;

    public Croiseur(String[] _croiseur) {
        longeur = 4;
        Case croiseur[] = new Case[4];

        // Transforme la liste de Strings de coordonnes en liste de coordonnes
        for (int i = 0; i < longeur; i++) {
            Latitude latitudeTmp = Latitude.valueOf(Character.toString(_croiseur[i].charAt(1)));
            Coordonnee coordonnee = new Coordonnee(latitudeTmp, Integer.parseInt(_croiseur[i].substring(1)));
            croiseur[i].coordonnee = coordonnee;
        }
    }
}
