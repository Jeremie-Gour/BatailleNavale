public class ContreTorpilleur extends Bateau {
    private int longueur;
    private Case[] contreTorpilleur;

    public ContreTorpilleur(String[] _contreTorpilleur){
        longeur = 3;
        Case contreTorpilleur[] = new Case[3];

        for (int i = 0; i < longeur; i++){
            Latitude latitudeTmp = Latitude.valueOf(Character.toString(_contreTorpilleur[i].charAt(1)));
            Coordonnee coordonnee = new Coordonnee(latitudeTmp, Integer.parseInt(_contreTorpilleur[i].substring(1)));
            contreTorpilleur[i].coordonnee = coordonnee;
       }
    }


}

