package uqam.inf5153.batailleNavale;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ordinateur {
    private static final int TAILLE_HORIZONTALE_MAX = 10;
    private static final int TAILLE_VERTICALE_MAX = 10;

//--------------cette section n'est juste lorsque la difficulté est innitialisé à zéro----------
    private static final int[] probabilite = {0,1,1,2,2,2,3,3,3,3,4,4,4,4,4,5,5,5,5,5,6,6,6,6,7,7,7,8,8,9}; //utilisé la technique densité de probabilité(expliqué dans le README)
    static private int nbrChiffreEchecMat = (TAILLE_HORIZONTALE_MAX * TAILLE_VERTICALE_MAX)/2; //utilisé la technique échec et math(expliqué dans le README)
    private List<ArrayList<Coordonnee>> coordonneesDisponiblesIntelligent; //utilisé la technique échec et math(expliqué dans le README)
    //------------------------------------------------------------------------------------------




    //Les differents modes de stratégie selon lequels l'ordinateur choisira sa prochaine coordonné
    protected enum Strategie {
        RECHERCHERBATEAU, CHERCHERALIGNEMENT, SUIVREALIGNEMENT, VERIFIERAUTREBOUT
    }


    private Strategie strategie;
    private Difficulte difficulte;
    private List<ArrayList<Coordonnee>> coordonneesDisponibles;

    private Coordonnee derniereCoordonneAttaque;
    static private Alignement alignementRepere; //est récupeéreé lorsque l'ordinateur est en mode CHERCHERALIGNEMENT et est utilseé en mode SUIVREALIGNEMENT et VERIFIERAUTREBOUT
    static private int pas = 1; //incrémenté lorsqu'on est en mode SUIVREALIGNEMENT, et utiliser lorsque l'ordinateur est en mode VERIFIERAUTREBOUT
    static private Coordonnee coordonneePivot; //utilisé en mode CHERCHERALIGNEMENT comme coordonne référence au milleu pour verifier les coordonnés aux alentours


    /**
     * Constructeur de l'ordinateur. Lorsque l'ordinateur est innitialisé avec la difficulté INTERMEDIAIRE ou FACILE,
     * coordonneesDisponibles sera construit avec tous les coordonnées d'une grille de bataille royale selon leur placement
     * et sans répétion. Lorsque l'ordinateur est innitialisé avec la difficulté DIFFICILE, le constructeur va bâtir
     * un tabelau et répéter les coordonnés où on place plus souvent les bateaux dans leur rangée respectueuse. De plus,
     * il instancie également coordonneesDisponiblesIntelligent qui aura les coordonnées disponibles en forme de échec et mat.
     * Lorsque l'ordinateur est en difficile cherchera alétoirement dans ses coordonnées disponibles, il fera sa recherche dans cette
     * liste(coordonneesDisponiblesIntelligent). Si la liste devient vide, il cherchera dans la liste coordonneesDisponibles.
     * This.strategie est initialisé à RECHERCHERBATEAU.
     *
     * @param difficulte sera la difficulté de l'ordinateur instancié
     */
    public Ordinateur ( Difficulte difficulte ) {
        coordonneesDisponibles = new ArrayList<ArrayList<Coordonnee>>();
        this.difficulte = difficulte;
        if(difficulte == Difficulte.DIFFICILE){
            //Dans cette section on instancie une deuxième grille (coordonneesPossiblesIntelligent) qui n'aura  que
            // les coordonnée qui respectent la technique échec et mat. De plus on reajoute plusieurs coordonnés dans les
            // 2 grilles selon la probalité de chaque coordonnée respectant la règle de densité de probabilité.
            coordonneesDisponiblesIntelligent = new ArrayList<ArrayList<Coordonnee>>();
            Random random = new Random();
            int chiffreEchec = random.nextInt(2);
            int diffference;
            int nombreRange = 0;
            int nombreColonne = 0;
            for ( int h = 0; h < TAILLE_HORIZONTALE_MAX; h++ ) {
                this.coordonneesDisponibles.add( new ArrayList<>() );
                this.coordonneesDisponiblesIntelligent.add( new ArrayList<>() );
                for (int v = 0; v < TAILLE_VERTICALE_MAX/2; v++) {
                    for( int j = 0; j < ( nombreRange + nombreColonne + 1 ); j++ ){
                        this.coordonneesDisponibles.get( h ).add( new Coordonnee( h, v ));
                        diffference = Math.abs(h - v);
                        if(diffference % 2 == chiffreEchec){
                            this.coordonneesDisponiblesIntelligent.get( h ).add( new Coordonnee( h, v ) );
                        }
                    }
                    nombreColonne++;
                }
                nombreRange++;
            }
            nombreColonne--;
            nombreRange--;
            for ( int h = 0; h < TAILLE_HORIZONTALE_MAX; h++ ) {
                this.coordonneesDisponibles.add( new ArrayList<>() );
                this.coordonneesDisponiblesIntelligent.add( new ArrayList<>() );
                for (int v = TAILLE_VERTICALE_MAX/2; v < TAILLE_VERTICALE_MAX; v++) {
                    for(int j = 0; j < (nombreRange + nombreColonne + 1);j++){

                        this.coordonneesDisponibles.get(h).add( new Coordonnee( h, v ) );
                        diffference = Math.abs( h - v );
                        if(( diffference % 2 ) == chiffreEchec){

                            this.coordonneesDisponiblesIntelligent.get( h ).add( new Coordonnee( h, v ) );
                        }
                    }

                    nombreColonne--;
                }

                nombreRange--;
            }
        }else{
            for ( int h = 0; h < TAILLE_HORIZONTALE_MAX; h++ ) {
                this.coordonneesDisponibles.add( new ArrayList<>() );
                for ( int v = 0; v < TAILLE_VERTICALE_MAX; v++ ) {
                    this.coordonneesDisponibles.get( h ).add( new Coordonnee( h, v )) ;
                }
            }
        }


        this.strategie = Strategie.RECHERCHERBATEAU;
    }

    /**
     * Cette methode sera la méthode utilisée à l'extérieur de la méthode pour retourner la prochaine
     * coordonnée que l'ordinateur voudrait attaquer. Dépendent de la difficulté initialisée, la méthode
     * retournera la méthode appropriée à sa difficulté et qui retournera à son tour la coordonnée proposée selon son
     * algorithme et les coordonnées disponibles.
     * @return coordonné que l'ordinateur souhaite attaqué
     */
    public Coordonnee prochaineAttaque() {
        if ( this.difficulte == Difficulte.FACILE ) {
            return attaquerFacile();
        } else if ( this.difficulte == Difficulte.INTERMEDIAIRE ) {
            return attaquerIntermediaire();
        } else {
            return attaquerDifficile();
        }
    }
    /**
     * Cette methode sera la méthode utilisée pour un ordinateur initialisé à facile à travers la
     * méthode prochaineAttaque(). Il ne fera que choisir une coordonné aléatoirement quelque soit la situation
     * de l'océan de l'opposant.
     * @return coordonné que l'ordinateur facile souhaite attaqué
     */
    private Coordonnee attaquerFacile() {
        return choisirCoordonneAleatoire();
    }

    /**
     * Cette methode sera la méthode utilisée pour un ordinateur initialisé à intermédiaire à travers la
     * méthode prochaineAttaque(). Dépendamenent de la stratégie que l'ordinateu se retrouve au moment de
     * l'appel de cette méthode, il utilisera la methode addéquate pour proposé une coordonné.De plus, avant de retourner
     * la coordonnée, il le retire de la liste de coordonneesDisponibles.
     * @return coordonné que l'ordinateur intermediaire souhaite attaqué.
     */
    private Coordonnee attaquerIntermediaire() {
        Coordonnee coordonneCible;
        if ( this.strategie == Strategie.RECHERCHERBATEAU ) {
            coordonneCible =  choisirCoordonneAleatoire();
            retirerCoordonne( coordonneCible );
            return coordonneCible;
        } else if ( this.strategie == Strategie.CHERCHERALIGNEMENT ) {
            coordonneCible = rechercherAlignement();
            retirerCoordonne( coordonneCible );
            return coordonneCible;
        } else if ( this.strategie == Strategie.SUIVREALIGNEMENT ) {
            coordonneCible = suivreAlignement();
            retirerCoordonne( coordonneCible );
            return coordonneCible;
        } else if ( this.strategie == Strategie.VERIFIERAUTREBOUT ) {
            coordonneCible = suivreAlignementAutreBout();
            retirerCoordonne( coordonneCible );
            return coordonneCible;
        }
        return choisirCoordonneAleatoire();
    }
    /**
     * Cette methode sera la méthode utilisée pour un ordinateur initialisé à intermédiaire à travers la
     * méthode prochaineAttaque(). Dépendamenent de la stratégie que l'ordinateur au moment de
     * l'appel de cette méthode, il utilisera la methode addéquate pour proposé une coordonné.De plus, avant de retourner
     * la coordonnée, il le retire de la liste de coordonneesDisponibles et de la liste coordonneesDisponiblesIntelligent si
     * le coordonnée y fait partie.
     * @return coordonné que l'ordinateur difficle souhaite attaqué.
     */
    private Coordonnee attaquerDifficile() {
        Coordonnee coordonneCible;
        if ( this.strategie == Strategie.RECHERCHERBATEAU ) {
            coordonneCible =  choisirCoordonneAleatoireIntelligent();
            retirerCoordonnePossibleIntelligent( coordonneCible );
            return coordonneCible;
        }else if( this.strategie == Strategie.CHERCHERALIGNEMENT ) {
            coordonneCible = rechercherAlignement();
            retirerCoordonnePossibleIntelligent( coordonneCible );
            return coordonneCible;
        } else if ( this.strategie == Strategie.SUIVREALIGNEMENT ) {
            coordonneCible = suivreAlignement();
            retirerCoordonnePossibleIntelligent( coordonneCible );
            return coordonneCible;
        }else if ( this.strategie == Strategie.VERIFIERAUTREBOUT ) {
            coordonneCible = suivreAlignementAutreBout();
            retirerCoordonnePossibleIntelligent( coordonneCible );
            return coordonneCible;
        }
        return choisirCoordonneAleatoire();
    }
    /**
     * Cette methode sera utilisé pour retourner des coordonnées basées sur une suite d'alignement(this.alignment)
     * et de la dernière coordonnée attaquée(this.derniereCoordonneAttaque). Avant que cette méthode retourne
     * une coordonné, la variable static "pas" est incrimenté. Ainsi, lorsque l'ordinateur va tanter d'attaquer l'autre
     * bout de l'alignement, il pourra reculer la (dernière coordonné - pas) dans la méthode suivreAlignementAutreBout().
     * Si la coordonnée n'est pas disponilbe ou si la coordonné proposé depasserait la largeur ou la longueur de la grille,
     * elle retourne la méthode suivreAlignementAutreBout().
     * suivreAlignementAutreBout().
     * @return coordonné que l'ordinateur souhaite attaqué.
     */
    private Coordonnee suivreAlignement() {
        Coordonnee coordonnePossible= new Coordonnee( this.derniereCoordonneAttaque.getRangee() ,this.derniereCoordonneAttaque.getColonne() );
        if ( alignementRepere == Alignement.VERTICAL ) {
            coordonnePossible.deplacerADroite();
            if ( coordonnePossible.getColonne() < TAILLE_VERTICALE_MAX && coordonneEstDisponible( coordonnePossible ) ){
                pas++;
                this.derniereCoordonneAttaque = coordonnePossible;
                return coordonnePossible;
            } else {
                this.strategie = Strategie.VERIFIERAUTREBOUT;
                return suivreAlignementAutreBout();
            }
        } else if ( alignementRepere == Alignement.HORIZONTAL ) {
            coordonnePossible.deplacerEnHaut();
            if ( coordonnePossible.getRangee() < TAILLE_HORIZONTALE_MAX && coordonneEstDisponible( coordonnePossible ) ) {
                pas++;
                this.derniereCoordonneAttaque = coordonnePossible;
                return coordonnePossible;
            } else {
                this.strategie = Strategie.VERIFIERAUTREBOUT;
                return suivreAlignementAutreBout();
            }
        }

        if ( this.difficulte == Difficulte.DIFFICILE ){
            return choisirCoordonneAleatoireIntelligent();
        }
        this.strategie = Strategie.RECHERCHERBATEAU;
        return choisirCoordonneAleatoire();
    }
    /**
     * Cette methode sera utilisé pour retourner des coordonnées basées sur une suite d'alignement(this.alignment)
     * et de la dernière coordonnée attaquée(this.derniereCoordonneAttaque) mais l'autre bout. Après avoir attaqué
     * tous les coordonnées  dans l'autre sense à travers suivreAlignement(). La première fois que cette méthode retourne
     * une coordonné, elle utilise la variable static "pas" pour trouver l'autre bout du bateau(dernière coordonné - pas).
     * La deuxième fois, elle l'initialise "pas" à 1 pour les prochains utilisations pendant que this.strategie est VERIFIERAUTREBOUT.
     * Aussi pour pouvoir l'incrémenter plus tard pour des futurs utilsations lorsque this.strategie sera SUIVREALIGNEMENT.
     * Si la coordonnée n'est pas disponilbe ou si la coordonné proposé depasserait la largeur ou la longueur de la grille,
     * elle retourne la méthode choisirCoordonneAleatoire() ou return choisirCoordonneAleatoireIntelligent().
     * @return coordonné que l'ordinateur souhaite attaqué.
     */
    private Coordonnee suivreAlignementAutreBout() {
        Coordonnee coordonnePossible= new Coordonnee(this.derniereCoordonneAttaque.getRangee() ,this.derniereCoordonneAttaque.getColonne());
        if ( alignementRepere == Alignement.VERTICAL ){
            coordonnePossible.deplacerAGauche(pas);
            if(coordonnePossible.getColonne() >= 0 && coordonneEstDisponible(coordonnePossible)){
                this.derniereCoordonneAttaque = coordonnePossible;
                if( pas != 1){
                    pas = 1;
                }
                return coordonnePossible;
            }
        } else if ( alignementRepere == Alignement.HORIZONTAL ) {
            coordonnePossible.deplacerEnBas( pas );
            if(coordonnePossible.getRangee() >= 0 && coordonneEstDisponible(coordonnePossible)){

                if ( pas != 1 ) {
                    pas = 1;
                }
                this.derniereCoordonneAttaque = coordonnePossible;
                return coordonnePossible;
            }
        }
        this.strategie = Strategie.RECHERCHERBATEAU;

        if ( this.difficulte == Difficulte.DIFFICILE ) {
            return choisirCoordonneAleatoireIntelligent();
        }
        return choisirCoordonneAleatoire();
    }



    /**
     * Cette methode sera utilisé pour trouver l'alignement d'un bateau qui vient de réperer. La variable static
     * coordonnéPivot innitialisé lorsque le premier TypeCellule.NAVIREINTACT sera répéré. Il attaquera en haut la première
     * fois qu'elle sera utilisé. En bas la deuxième fois, à droite la troisième fois et à gauche la deuxième fois.
     * Si rien est trouvé, this.strategie est mis à RECHERCHERBATEAU
     * et la méthode choisirCoordonneAleatoireIntelligent() ou choisirCoordonneAleatoire() est retourné.
     *  @return coordonné que l'ordinateur souhaite attaqué.
     */
    private Coordonnee rechercherAlignement() {
        Coordonnee coordonnePossible= new Coordonnee(coordonneePivot.getRangee() + 1, coordonneePivot.getColonne() );
        if ( ( coordonnePossible.getRangee() ) < TAILLE_HORIZONTALE_MAX && coordonneEstDisponible( coordonnePossible ) ) {
            alignementRepere = Alignement.HORIZONTAL;
            pas = 2;
            this.derniereCoordonneAttaque = coordonnePossible;
            return coordonnePossible;
        } else {
            coordonnePossible.deplacerEnBas(2);
        }
        if( ( coordonnePossible.getRangee() ) >= 0 && coordonneEstDisponible( coordonnePossible) ) {
            alignementRepere = Alignement.HORIZONTAL;
            pas = 1;
            this.derniereCoordonneAttaque = coordonnePossible;
            return coordonnePossible;
        }else{
            coordonnePossible.deplacerEnHaut();
            coordonnePossible.deplacerADroite();
        }
        if ( coordonnePossible.getColonne() < TAILLE_VERTICALE_MAX && coordonneEstDisponible( coordonnePossible ) ) {
            alignementRepere = Alignement.VERTICAL;
            pas = 2;
            this.derniereCoordonneAttaque = coordonnePossible;
            return coordonnePossible;
        } else {
            coordonnePossible.deplacerAGauche(2);
        }
        if ( coordonnePossible.getColonne() >= 0 && coordonneEstDisponible( coordonnePossible ) ) {
            alignementRepere = Alignement.VERTICAL;
            pas = 1;
            this.derniereCoordonneAttaque = coordonnePossible;
            return coordonnePossible;
        }
        this.strategie = Strategie.RECHERCHERBATEAU;

        if ( this.difficulte == Difficulte.DIFFICILE ) {
            return choisirCoordonneAleatoireIntelligent();
        }
        return choisirCoordonneAleatoire();
    }

    /**
     * Cette methode sera utilisée pour proposer une coordonnée aléatoirement selon les coordonnées disponible dans
     * la liste coordonneesDisponibles
     * @return coordonné que l'ordinateur souhaite attaqué.
     */
    private Coordonnee choisirCoordonneAleatoire() {
        Random random = new Random();
        int rangee = random.nextInt( this.coordonneesDisponibles.size() );
        while ( this.coordonneesDisponibles.get( rangee ).size() == 0 ){
            rangee = random.nextInt( this.coordonneesDisponibles.size() );
        }
        int colonne = random.nextInt( this.coordonneesDisponibles.get( rangee ).size() );
        Coordonnee coordonneeCible = this.coordonneesDisponibles.get( rangee ).get( colonne );
        derniereCoordonneAttaque = new Coordonnee( coordonneeCible.getRangee(), coordonneeCible.getColonne() );
        return coordonneeCible;
    }
    /**
     * Cette methode sera utilisée pour proposer une coordonnée aléatoirement selon les coordonnées disponible dans
     * la liste coordonneesDisponibles et coordonneesDisponiblesIntelligent. Cette méthode sera utilisé pour l'ordinateur
     * lorsqu'il sera difficile. Ce qui différentie cette méthode de choisirCoordonneAleatoire() et qu'il propose les
     * coordonnées de la liste coordonneesDisponiblesIntelligent jusqu'à temps qu'elle soit vide. Puis ensuite, il propose
     * à partir de coordonneesDisponibles. De plus, il choisit la rangée aleatoirement à partir des index dans le tableau
     * static probabilite.
     */
    private Coordonnee choisirCoordonneAleatoireIntelligent() {
        Random random = new Random();
        if ( nbrChiffreEchecMat > 0 ) {
            int rangee = probabilite[ random.nextInt( probabilite.length ) ];
            while ( this.coordonneesDisponiblesIntelligent.get( rangee ).size() == 0 ) {
                rangee = probabilite[ random.nextInt( probabilite.length ) ];
            }
            int colonne = random.nextInt( this.coordonneesDisponiblesIntelligent.get( rangee ).size() );
            Coordonnee coordonneeCible = this.coordonneesDisponiblesIntelligent.get( rangee ).get( colonne );
            derniereCoordonneAttaque = new Coordonnee( coordonneeCible.getRangee(), coordonneeCible.getColonne() );
            return coordonneeCible;
        }else{

            int rangee = probabilite[ random.nextInt( probabilite.length ) ];
            while( this.coordonneesDisponibles.get( rangee ).size() == 0 ) {
                rangee = probabilite[ random.nextInt( probabilite.length ) ];

            }
            int colonne = random.nextInt( this.coordonneesDisponibles.get( rangee ).size() );
            Coordonnee coordonneeCible = this.coordonneesDisponibles.get( rangee ).get( colonne );
            derniereCoordonneAttaque = new Coordonnee( coordonneeCible.getRangee(), coordonneeCible.getColonne() );
            return coordonneeCible;
        }

    }


    /**
     * Cette méthode retourne vrai ou faux si une coordonnée est dans la liste coordonneesDisponibles.
     * @param coordonne  la coordonnée qu'on voudrait vérifier s'il est dans la liste
     * @return true s'il est dans la liste, retourn false s'il n'est pas dans la liste
     */
    private boolean coordonneEstDisponible(Coordonnee coordonne){
        return this.coordonneesDisponibles.get(coordonne.getRangee()).contains(coordonne);
    }
    /**
     * Cette méthode retire une coordonné de la liste coordonneesDisponibles.
     * @param coordonne  la coordonnée qu'on voudrait retirer s'il est dans la liste
     */
    private void retirerCoordonne( Coordonnee coordonne ){
        int celluleRetireIndex = this.coordonneesDisponibles.get( coordonne.getRangee() ).indexOf( coordonne );

        this.coordonneesDisponibles.get( coordonne.getRangee()).remove( celluleRetireIndex );
    }
    /**
     * Cette méthode retire une coordonné de la liste coordonneesDisponibles et
     * coordonneesDisponiblesIntelligent(s'il est la) pour l'ordinateur initialisé à Difficile.
     */
    private void retirerCoordonnePossibleIntelligent( Coordonnee coordonne ) {
        int celluleRetireIndex;
        this.coordonneesDisponibles.get( coordonne.getRangee() ).removeIf( n -> ( n.equals( coordonne ) ) );
        celluleRetireIndex = this.coordonneesDisponiblesIntelligent.get( coordonne.getRangee() ).indexOf( coordonne );
        if( celluleRetireIndex != -1 ){
            this.coordonneesDisponiblesIntelligent.get( coordonne.getRangee() ).removeIf( n -> ( n.equals(coordonne ) ) );
            nbrChiffreEchecMat--;
        }

    }

    /**
     * Cette méthode pour aviser à l'ordinateur quelle type de cellule à été attaqué. Dépendament, de
     * la difficulté de l'ordinateur, il est redirigé à la méthode pour accomplir cette action selon
     * la difficultée.
     * @param celluleTouche le type de cellule qui à été dans la dernière attaque
     */
    public void recevoirResultat( TypeCellule celluleTouche ) {
        if ( this.difficulte == Difficulte.INTERMEDIAIRE ) {
            recevoirResultatIntermediaire(celluleTouche);
        } else if ( this.difficulte == Difficulte.DIFFICILE ) {
            recevoirResultatIntelligent( celluleTouche );
        }
    }
    /**
     * Cette méthode pour aviser à l'ordinateur intermédiare quelle type de cellule à été attaqué. Dépendament, de
     * de la cellule attaqué et this.strategie, il modifiera la strategie si la type de cellule attaquée necessite de
     * le faire.
     * @param celluleTouche le type de cellule qui à été dans la dernière attaque
     */
    private void recevoirResultatIntermediaire( TypeCellule celluleTouche ){
        if ( this.strategie == Strategie.RECHERCHERBATEAU ) {
            if ( celluleTouche == TypeCellule.NAVIREINTACT ) {
                coordonneePivot = new Coordonnee( this.derniereCoordonneAttaque.getRangee(), this.derniereCoordonneAttaque.getColonne() );
                this.strategie = Strategie.CHERCHERALIGNEMENT;
            }
        } else if ( this.strategie == Strategie.CHERCHERALIGNEMENT ) {
            if ( celluleTouche == TypeCellule.NAVIREINTACT ) {
                this.strategie = Strategie.SUIVREALIGNEMENT;
            }
        } else if ( this.strategie == Strategie.SUIVREALIGNEMENT ) {
            if ( celluleTouche == TypeCellule.EAU || celluleTouche == TypeCellule.BOMBE ) {
                this.strategie = Strategie.VERIFIERAUTREBOUT;
            }
        } else if ( this.strategie == Strategie.VERIFIERAUTREBOUT ) {
            if ( celluleTouche == TypeCellule.EAU || celluleTouche == TypeCellule.BOMBE ) {
                this.strategie = Strategie.RECHERCHERBATEAU;
            }
        }

    }
    /**
     * Cette méthode pour aviser à l'ordinateur difficile quelle type de cellule à été attaqué. Dépendament, de
     * de la cellule attaqué et this.strategie, il modifiera la strategie si la type de cellule attaquée necessite de
     * le faire. Ce qui différentie cette méthode de recevoirResultatIntermediaire() est que lorsque le type attaqué
     * est NAVIREINTACT, il va retirer les cellules voisins selon alignementRepere puisque le jeu ne permet pas d'avoir
     * des bateaux collés.
     * @param celluleTouche le type de cellule qui à été dans la dernière attaque
     */
    public void recevoirResultatIntelligent( TypeCellule celluleTouche ) {
        Coordonnee coordonneAlentour = new Coordonnee( this.derniereCoordonneAttaque.getRangee() , this.derniereCoordonneAttaque.getColonne() );
        if ( this.strategie == Strategie.RECHERCHERBATEAU ) {
            if ( celluleTouche == TypeCellule.NAVIREINTACT ) {
                coordonneePivot = new Coordonnee( this.derniereCoordonneAttaque.getRangee(), this.derniereCoordonneAttaque.getColonne() );
                this.strategie = Strategie.CHERCHERALIGNEMENT;
            }
        } else if ( this.strategie == Strategie.CHERCHERALIGNEMENT ) {
            if ( celluleTouche == TypeCellule.NAVIREINTACT ) {
                if ( this.alignementRepere == Alignement.VERTICAL ) {
                    coordonneAlentour.deplacerEnBas();
                    if ( coordonneAlentour.getRangee() >= 0 && coordonneEstDisponible(coordonneAlentour) ) {
                        retirerCoordonnePossibleIntelligent( coordonneAlentour );
                    }
                    coordonneAlentour.deplacerEnHaut(2);
                    if ( coordonneAlentour.getRangee() < TAILLE_HORIZONTALE_MAX && coordonneEstDisponible( coordonneAlentour ) ) {
                        retirerCoordonnePossibleIntelligent( coordonneAlentour );
                    }
                    coordonneAlentour = coordonneePivot;
                    coordonneAlentour.deplacerEnBas();

                    if ( coordonneAlentour.getRangee() >= 0 && coordonneEstDisponible( coordonneAlentour ) ) {
                        retirerCoordonnePossibleIntelligent( coordonneAlentour );
                    }

                    coordonneAlentour.deplacerEnHaut(2);
                    if ( coordonneAlentour.getRangee() < TAILLE_HORIZONTALE_MAX && coordonneEstDisponible( coordonneAlentour ) ) {
                        retirerCoordonnePossibleIntelligent(coordonneAlentour);
                    }

                } else if ( this.alignementRepere == Alignement.HORIZONTAL ) {

                    coordonneAlentour.deplacerADroite();
                    if ( coordonneAlentour.getColonne() < TAILLE_VERTICALE_MAX && coordonneEstDisponible( coordonneAlentour ) ) {
                        retirerCoordonnePossibleIntelligent(coordonneAlentour);
                    }
                    coordonneAlentour.deplacerAGauche(2);
                    if ( coordonneAlentour.getColonne() >= 0 && coordonneEstDisponible( coordonneAlentour ) ) {
                        retirerCoordonnePossibleIntelligent( coordonneAlentour );
                    }
                    coordonneAlentour = coordonneePivot;
                    coordonneAlentour.deplacerADroite();
                    if ( coordonneAlentour.getColonne() < TAILLE_VERTICALE_MAX && coordonneEstDisponible( coordonneAlentour ) ) {
                        retirerCoordonnePossibleIntelligent( coordonneAlentour );
                    }
                    coordonneAlentour.deplacerAGauche(2);
                    if ( coordonneAlentour.getColonne() >= 0 && coordonneEstDisponible( coordonneAlentour ) ) {
                        retirerCoordonnePossibleIntelligent( coordonneAlentour );
                    }
                }
                this.strategie = Strategie.SUIVREALIGNEMENT;
            }
        } else if ( this.strategie == Strategie.SUIVREALIGNEMENT ) {
            if ( celluleTouche == TypeCellule.EAU || celluleTouche == TypeCellule.BOMBE ) {
                this.strategie = Strategie.VERIFIERAUTREBOUT;
            } else if ( celluleTouche == TypeCellule.NAVIREINTACT ){
                if ( this.alignementRepere == Alignement.VERTICAL ) {
                    coordonneAlentour.deplacerEnBas();
                    if ( coordonneAlentour.getRangee() >= 0 && coordonneEstDisponible( coordonneAlentour ) ) {
                        retirerCoordonnePossibleIntelligent( coordonneAlentour );
                    }
                    coordonneAlentour.deplacerEnHaut(2);
                    if ( coordonneAlentour.getRangee() < TAILLE_HORIZONTALE_MAX && coordonneEstDisponible( coordonneAlentour ) ) {
                        retirerCoordonnePossibleIntelligent( coordonneAlentour );
                    }
                } else if ( this.alignementRepere == Alignement.HORIZONTAL ) {
                    coordonneAlentour.deplacerADroite();
                    if ( coordonneAlentour.getColonne() < TAILLE_VERTICALE_MAX && coordonneEstDisponible( coordonneAlentour ) ) {
                        retirerCoordonnePossibleIntelligent( coordonneAlentour) ;
                    }
                    coordonneAlentour.deplacerAGauche(2);
                    if ( coordonneAlentour.getColonne() >= 0 && coordonneEstDisponible( coordonneAlentour ) ) {
                        retirerCoordonnePossibleIntelligent(coordonneAlentour);
                    }
                }
            }
        } else if (this.strategie == Strategie.VERIFIERAUTREBOUT) {
            if (celluleTouche == TypeCellule.EAU || celluleTouche == TypeCellule.BOMBE) {
                this.strategie = Strategie.RECHERCHERBATEAU;
            } else if ( celluleTouche == TypeCellule.NAVIREINTACT ) {
                if ( this.alignementRepere == Alignement.VERTICAL ) {

                    coordonneAlentour.deplacerEnBas();
                    if ( coordonneAlentour.getRangee() >= 0 && coordonneEstDisponible(coordonneAlentour) ) {
                        retirerCoordonnePossibleIntelligent(coordonneAlentour);
                    }
                    coordonneAlentour.deplacerEnHaut(2);
                    if ( coordonneAlentour.getRangee() < TAILLE_HORIZONTALE_MAX && coordonneEstDisponible(coordonneAlentour) ) {
                        retirerCoordonnePossibleIntelligent(coordonneAlentour);
                    }
                } else if (this.alignementRepere == Alignement.HORIZONTAL) {
                    coordonneAlentour.deplacerADroite();
                    if ( coordonneAlentour.getColonne() < TAILLE_VERTICALE_MAX && coordonneEstDisponible( coordonneAlentour ) ) {
                        retirerCoordonnePossibleIntelligent(coordonneAlentour);
                    }
                    coordonneAlentour.deplacerAGauche(2);
                    if ( coordonneAlentour.getColonne() >= 0 && coordonneEstDisponible( coordonneAlentour ) ) {
                        retirerCoordonnePossibleIntelligent( coordonneAlentour );
                    }
                }
            }
        }
    }





}
