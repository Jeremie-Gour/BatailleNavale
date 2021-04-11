public class Torpilleur extends Bateau {
    private int longueur;
    private Case[] torpilleur;


    public Torpilleur(String[] _torpilleur){
        longeur = 2;
        Case torpilleur[] = new Case[2];
        for (int i = 0; i < longeur; i++){
            Latitude latitudeTmp = Latitude.valueOf(Character.toString(_torpilleur[i].charAt(1)));
            Coordonnee coordonnee = new Coordonnee(latitudeTmp, Integer.parseInt(_torpilleur[i].substring(1)));
            torpilleur[i].coordonnee = coordonnee;
        }
    }
}
