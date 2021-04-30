package uqam.inf5153.batailleNavale;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ordinateur {
    private static final int TAILLE_HORIZONTALE_MAX = 10;
    private static final int TAILLE_VERTICALE_MAX = 10;


    /*----------cette section est utilisé pour l'ordinateur difficle, il faudra surment
    decouper cette classe parce que presentement il a des valeurs qui ne seront pas utiliser
    si l'ordinateur est facile ou intermediaire. Ca cause aussi de la repetition.

    */
    private static final int[] probabilite = {0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 8, 8, 9};
    static private int nbrChiffreEchecMat = (TAILLE_HORIZONTALE_MAX * TAILLE_VERTICALE_MAX) / 2; //change ca pour une methode
    private List<ArrayList<Coordonnee>> coordonneesPossiblesIntelligent;
    //-------------------------------------------------------------------------------------------


    protected enum Strategie {
        RECHERCHERBATEAU, CHERCHERALIGNEMENT, SUIVREALIGNEMENT, VERIFIERAUTREBOUT
    }

    private Strategie strategie;
    private Difficulte difficulte;
    private List<ArrayList<Coordonnee>> coordonneesPossibles;

    private Coordonnee derniereCoordonneAttaque;
    static private Alignement alignementRepere;//alignementRepere utiliseé une fois que l'alignment du tableau attaqué est decouvert
    /* Pas est utilisé une fois que l'ordinateur attaque un bateau selon son alignement, le nombre de pas va permettre de
    retourner a la première cellule attaqueé du bateau afin de verifier l'autre bout du bateau
    */
    static private int pas = 1;
    static private Coordonnee coordonneePivot;//utiliser comme coordonne milleu pour verifier les coordonnés aux alentours


    public Ordinateur(Difficulte difficulte) {
        coordonneesPossibles = new ArrayList<ArrayList<Coordonnee>>();
        this.difficulte = difficulte;
        if (difficulte == Difficulte.DIFFICILE) {
            coordonneesPossiblesIntelligent = new ArrayList<ArrayList<Coordonnee>>();
            Random random = new Random();
            int chiffreEchec = random.nextInt(2);
            int diffference;
            int nombreRange = 0;
            int nombreColonne = 0;
            for (int h = 0; h < TAILLE_HORIZONTALE_MAX; h++) {
                this.coordonneesPossibles.add(new ArrayList<>());
                this.coordonneesPossiblesIntelligent.add(new ArrayList<>());
                for (int v = 0; v < TAILLE_VERTICALE_MAX / 2; v++) {
                    for (int j = 0; j < (nombreRange + nombreColonne + 1); j++) {
                        this.coordonneesPossibles.get(h).add(new Coordonnee(h, v));
                        diffference = Math.abs(h - v);
                        if (diffference % 2 == chiffreEchec) {
                            this.coordonneesPossiblesIntelligent.get(h).add(new Coordonnee(h, v));
                        }
                    }

                    nombreColonne++;
                }
                nombreRange++;
            }
            nombreColonne--;
            nombreRange--;
            for (int h = 0; h < TAILLE_HORIZONTALE_MAX; h++) {
                this.coordonneesPossibles.add(new ArrayList<>());
                this.coordonneesPossiblesIntelligent.add(new ArrayList<>());
                for (int v = TAILLE_VERTICALE_MAX / 2; v < TAILLE_VERTICALE_MAX; v++) {
                    for (int j = 0; j < (nombreRange + nombreColonne + 1); j++) {

                        this.coordonneesPossibles.get(h).add(new Coordonnee(h, v));
                        diffference = Math.abs(h - v);
                        if ((diffference % 2) == chiffreEchec) {

                            this.coordonneesPossiblesIntelligent.get(h).add(new Coordonnee(h, v));
                        }
                    }

                    nombreColonne--;
                }

                nombreRange--;
            }
        } else {
            for (int h = 0; h < TAILLE_HORIZONTALE_MAX; h++) {
                this.coordonneesPossibles.add(new ArrayList<>());
                for (int v = 0; v < TAILLE_VERTICALE_MAX; v++) {
                    this.coordonneesPossibles.get(h).add(new Coordonnee(h, v));
                }
            }
        }


        this.strategie = Strategie.RECHERCHERBATEAU;
    }

    public Coordonnee prochaineAttaque() {
        if (this.difficulte == Difficulte.FACILE) {
            return attaquerFacile();
        } else if (this.difficulte == Difficulte.INTERMEDIAIRE) {
            return attaquerIntermediaire();
        } else if (this.difficulte == Difficulte.DIFFICILE) {
            return attaquerDifficile();
        }
        return new Coordonnee(2, 2);

    }

    /*
        private Cellule attaqueDifficile() {
        }

        protected abstract Cellule attaqueIntermediaire();
    */
    private Coordonnee attaquerFacile() {
        System.out.println("-----------facile--------------");
        return choisirCoordonneAleatoire();
    }

    private Coordonnee attaquerIntermediaire() {
        System.out.println("-----------intermediaire--------------");
        Coordonnee coordonneCible;
        if (this.strategie == Strategie.RECHERCHERBATEAU) {
            coordonneCible = choisirCoordonneAleatoire();
            retirerCoordonnePossible(coordonneCible);
            return coordonneCible;
        } else if (this.strategie == Strategie.CHERCHERALIGNEMENT) {
            coordonneCible = rechercherAlignement();
            retirerCoordonnePossible(coordonneCible);
            return coordonneCible;
        } else if (this.strategie == Strategie.SUIVREALIGNEMENT) {
            coordonneCible = suivreAlignement();
            retirerCoordonnePossible(coordonneCible);
            return coordonneCible;
        } else if (this.strategie == Strategie.VERIFIERAUTREBOUT) {
            coordonneCible = suivreAlignementAutreBout();
            retirerCoordonnePossible(coordonneCible);
            return coordonneCible;
        }
        return choisirCoordonneAleatoire();
    }

    private Coordonnee attaquerDifficile() {
        System.out.println("-----------difficilce--------------");
        Coordonnee coordonneCible;
        if (this.strategie == Strategie.RECHERCHERBATEAU) {
            coordonneCible = choisirCoordonneAleatoireIntelligent();
            retirerCoordonnePossibleIntel(coordonneCible);
            return coordonneCible;
        } else if (this.strategie == Strategie.CHERCHERALIGNEMENT) {
            coordonneCible = rechercherAlignement();
            retirerCoordonnePossibleIntel(coordonneCible);
            return coordonneCible;
        } else if (this.strategie == Strategie.SUIVREALIGNEMENT) {
            coordonneCible = suivreAlignement();
            retirerCoordonnePossibleIntel(coordonneCible);
            return coordonneCible;
        } else if (this.strategie == Strategie.VERIFIERAUTREBOUT) {
            coordonneCible = suivreAlignementAutreBout();
            retirerCoordonnePossibleIntel(coordonneCible);
            return coordonneCible;
        }
        return choisirCoordonneAleatoire();
    }

    private Coordonnee suivreAlignement() {
        Coordonnee coordonnePossible = new Coordonnee(this.derniereCoordonneAttaque.getRangee(), this.derniereCoordonneAttaque.getColonne());
        if (alignementRepere == Alignement.VERTICAL) {
            coordonnePossible.deplacerADroite();
            if (coordonnePossible.getColonne() < TAILLE_VERTICALE_MAX && coordonneEstDisponible(coordonnePossible)) {
                pas++;
                this.derniereCoordonneAttaque = coordonnePossible;
                return coordonnePossible;
            } else {
                this.strategie = Strategie.VERIFIERAUTREBOUT;
                return suivreAlignementAutreBout();
            }
        } else if (alignementRepere == Alignement.HORIZONTAL) {
            coordonnePossible.deplacerEnHaut();
            if (coordonnePossible.getColonne() < TAILLE_HORIZONTALE_MAX && coordonneEstDisponible(coordonnePossible)) {
                pas++;
                this.derniereCoordonneAttaque = coordonnePossible;
                return coordonnePossible;
            } else {
                this.strategie = Strategie.VERIFIERAUTREBOUT;
                return suivreAlignementAutreBout();
            }
        }
        this.strategie = Strategie.RECHERCHERBATEAU;
        return choisirCoordonneAleatoire();
    }

    private Coordonnee suivreAlignementAutreBout() {
        Coordonnee coordonnePossible = new Coordonnee(this.derniereCoordonneAttaque.getRangee(), this.derniereCoordonneAttaque.getColonne());
        if (alignementRepere == Alignement.VERTICAL) {
            coordonnePossible.deplacerAGauche(pas);
            if (coordonnePossible.getColonne() >= 0 && coordonneEstDisponible(coordonnePossible)) {
                this.derniereCoordonneAttaque = coordonnePossible;
                if (pas != 1) {
                    pas = 1;
                }
                return coordonnePossible;
            }
        } else if (alignementRepere == Alignement.HORIZONTAL) {
            coordonnePossible.deplacerEnBas(pas);
            if (coordonnePossible.getRangee() >= 0 && coordonneEstDisponible(coordonnePossible)) {

                if (pas != 1) {
                    pas = 1;
                }
                this.derniereCoordonneAttaque = coordonnePossible;
                return coordonnePossible;
            }
        }

        this.strategie = Strategie.RECHERCHERBATEAU;
        return choisirCoordonneAleatoire();
    }


    private Coordonnee rechercherAlignement() {
        Coordonnee coordonnePossible = new Coordonnee(coordonneePivot.getRangee() + 1, coordonneePivot.getColonne());
        if ((coordonnePossible.getRangee()) < TAILLE_HORIZONTALE_MAX && coordonneEstDisponible(coordonnePossible)) {
            alignementRepere = Alignement.HORIZONTAL;
            pas = 2;
            this.derniereCoordonneAttaque = coordonnePossible;
            return coordonnePossible;
        } else {
            coordonnePossible.deplacerEnBas(2);
        }
        if ((coordonnePossible.getRangee()) >= 0 && coordonneEstDisponible(coordonnePossible)) {
            alignementRepere = Alignement.HORIZONTAL;
            pas = 1;
            this.derniereCoordonneAttaque = coordonnePossible;
            return coordonnePossible;
        } else {
            coordonnePossible.deplacerEnHaut();
            coordonnePossible.deplacerADroite();
        }
        if (coordonnePossible.getColonne() < TAILLE_VERTICALE_MAX && coordonneEstDisponible(coordonnePossible)) {
            alignementRepere = Alignement.VERTICAL;
            pas = 2;
            this.derniereCoordonneAttaque = coordonnePossible;
            return coordonnePossible;
        } else {
            coordonnePossible.deplacerAGauche(2);
        }
        if (coordonnePossible.getColonne() >= 0 && coordonneEstDisponible(coordonnePossible)) {
            alignementRepere = Alignement.VERTICAL;
            pas = 1;
            this.derniereCoordonneAttaque = coordonnePossible;
            return coordonnePossible;
        }
        this.strategie = Strategie.RECHERCHERBATEAU;
        return choisirCoordonneAleatoire();
    }


    private Coordonnee choisirCoordonneAleatoire() {
        Random random = new Random();
        int rangee = random.nextInt(this.coordonneesPossibles.size());
        while (this.coordonneesPossibles.get(rangee).size() == 0) {
            rangee = random.nextInt(this.coordonneesPossibles.size());
        }
        int colonne = random.nextInt(this.coordonneesPossibles.get(rangee).size());
        Coordonnee coordonneeCible = this.coordonneesPossibles.get(rangee).get(colonne);
        derniereCoordonneAttaque = new Coordonnee(coordonneeCible.getRangee(), coordonneeCible.getColonne());
        return coordonneeCible;
    }

    private Coordonnee choisirCoordonneAleatoireIntelligent() {
        Random random = new Random();

        if (nbrChiffreEchecMat > 0) {
            int rangee = probabilite[random.nextInt(probabilite.length)];
            while (this.coordonneesPossiblesIntelligent.get(rangee).size() == 0) {
                rangee = probabilite[random.nextInt(probabilite.length)];
            }

            int colonne = random.nextInt(this.coordonneesPossiblesIntelligent.get(rangee).size());
            Coordonnee coordonneeCible = this.coordonneesPossiblesIntelligent.get(rangee).get(colonne);
            derniereCoordonneAttaque = new Coordonnee(coordonneeCible.getRangee(), coordonneeCible.getColonne());
            return coordonneeCible;
        } else {
            int rangee = probabilite[random.nextInt(probabilite.length)];
            while (this.coordonneesPossibles.get(rangee).size() == 0) {
                rangee = probabilite[random.nextInt(probabilite.length)];
            }
            int colonne = random.nextInt(this.coordonneesPossibles.get(rangee).size());
            Coordonnee coordonneeCible = this.coordonneesPossibles.get(rangee).get(colonne);
            derniereCoordonneAttaque = new Coordonnee(coordonneeCible.getRangee(), coordonneeCible.getColonne());
            return coordonneeCible;
        }

    }


    private boolean coordonneEstDisponible(Coordonnee coordonne) {
        return this.coordonneesPossibles.get(coordonne.getRangee()).contains(coordonne);
    }

    private void retirerCoordonnePossible(Coordonnee coordonne) {
        int celluleRetireIndex = this.coordonneesPossibles.get(coordonne.getRangee()).indexOf(coordonne);
        this.coordonneesPossibles.get(coordonne.getRangee()).remove(celluleRetireIndex);
    }

    private void retirerCoordonnePossibleIntel(Coordonnee coordonne) {
        int celluleRetireIndex;
        this.coordonneesPossibles.get(coordonne.getRangee()).removeIf(n -> (n.equals(coordonne)));
        celluleRetireIndex = this.coordonneesPossiblesIntelligent.get(coordonne.getRangee()).indexOf(coordonne);
        if (celluleRetireIndex != -1) {
            this.coordonneesPossiblesIntelligent.get(coordonne.getRangee()).removeIf(n -> (n.equals(coordonne)));
            nbrChiffreEchecMat--;
        }

    }

    public void recevoirResultat(TypeCellule celluleTouche) {
        if (this.difficulte == Difficulte.INTERMEDIAIRE) {
            recevoirResultatIntermediaire(celluleTouche);
        } else if (this.difficulte == Difficulte.DIFFICILE) {
            recevoirResultatDifficile(celluleTouche);
        }
    }

    public void recevoirResultatIntermediaire(TypeCellule celluleTouche) {
        if (this.strategie == Strategie.RECHERCHERBATEAU) {
            if (celluleTouche == TypeCellule.NAVIREINTACT) {
                coordonneePivot = new Coordonnee(this.derniereCoordonneAttaque.getRangee(), this.derniereCoordonneAttaque.getColonne());
                this.strategie = Strategie.CHERCHERALIGNEMENT;
            }
        } else if (this.strategie == Strategie.CHERCHERALIGNEMENT) {
            if (celluleTouche == TypeCellule.NAVIREINTACT) {
                this.strategie = Strategie.SUIVREALIGNEMENT;
            }
        } else if (this.strategie == Strategie.SUIVREALIGNEMENT) {
            if (celluleTouche == TypeCellule.EAU) {
                this.strategie = Strategie.VERIFIERAUTREBOUT;
            }
        } else if (this.strategie == Strategie.VERIFIERAUTREBOUT) {
            if (celluleTouche == TypeCellule.EAU) {
                this.strategie = Strategie.RECHERCHERBATEAU;
            }
        }
        if (this.difficulte == Difficulte.INTERMEDIAIRE) {
        }

    }

    public void recevoirResultatDifficile(TypeCellule celluleTouche) {

        if (this.strategie == Strategie.RECHERCHERBATEAU) {
            if (celluleTouche == TypeCellule.NAVIREINTACT) {
                coordonneePivot = new Coordonnee(this.derniereCoordonneAttaque.getRangee(), this.derniereCoordonneAttaque.getColonne());
                this.strategie = Strategie.CHERCHERALIGNEMENT;
            }
        } else if (this.strategie == Strategie.CHERCHERALIGNEMENT) {
            if (celluleTouche == TypeCellule.NAVIREINTACT) {
                this.strategie = Strategie.SUIVREALIGNEMENT;
            }
        } else if (this.strategie == Strategie.SUIVREALIGNEMENT) {
            if (celluleTouche == TypeCellule.EAU) {
                this.strategie = Strategie.VERIFIERAUTREBOUT;
            }
        } else if (this.strategie == Strategie.VERIFIERAUTREBOUT) {
            if (celluleTouche == TypeCellule.EAU) {
                this.strategie = Strategie.RECHERCHERBATEAU;
            }
        }
        if (this.difficulte == Difficulte.INTERMEDIAIRE) {
        }

    }

    public int nombreDePossibilites() {
        int sommePossibilite = 0;
        for (int i = 0; i < coordonneesPossibles.size(); i++) {
            sommePossibilite += coordonneesPossibles.get(i).size();
        }
        return sommePossibilite;
    }


}
