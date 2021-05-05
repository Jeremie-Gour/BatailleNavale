package uqam.inf5153.batailleNavale;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class OrdinateurTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }




    /**
     * Test si l'ordinateur intermédiaire propose à sa prochaine attaque la coordonnée du haut après avoir
     * toucher un bateau pour la première fois.
     */
    @Test
    public void testerStrategieChercherAlignementIntermediaire1(){

        Ordinateur ordinateurIntermediaire = new Ordinateur( Difficulte.INTERMEDIAIRE );
        Coordonnee coordonneeSouhaité = ordinateurIntermediaire.prochaineAttaque();
        int rangee = coordonneeSouhaité.getRangee();
        int colonne= coordonneeSouhaité.getColonne();
        while( (  rangee > 7 || rangee < 2  ) ||  (  colonne > 7 || colonne < 2  )){
            ordinateurIntermediaire = new Ordinateur( Difficulte.INTERMEDIAIRE );
            coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
            rangee = coordonneeSouhaité.getRangee();
            colonne= coordonneeSouhaité.getColonne();
        }
        coordonneeSouhaité.deplacerEnHaut();
        ordinateurIntermediaire.recevoirResultat( TypeCellule.NAVIREINTACT );
        Coordonnee coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);

    }
    /**
     * Test si l'ordinateur intermédiaire propose à sa prochaine attaque la coordonnée du bas après avoir
     *  reçu l'information que le coordonnée en haut ne fait pas partie du bateau.
     */
    @Test
    public void testerStrategieChercherAlignementIntermediaire2(){
        Ordinateur ordinateurIntermediaire = new Ordinateur( Difficulte.INTERMEDIAIRE );
        Coordonnee coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
        int rangee = coordonneeSouhaité.getRangee();
        int colonne= coordonneeSouhaité.getColonne();
        while( (  rangee > 7 || rangee < 2  ) ||  (  colonne > 7 || colonne < 2  )){
            ordinateurIntermediaire = new Ordinateur( Difficulte.INTERMEDIAIRE );
            coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
            rangee = coordonneeSouhaité.getRangee();
            colonne= coordonneeSouhaité.getColonne();
        }
        coordonneeSouhaité.deplacerEnBas();//au deuxième essaie, il devrait attaquer la cellule en bas
        ordinateurIntermediaire.recevoirResultat( TypeCellule.NAVIREINTACT );
        Coordonnee coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        assertTrue(coordonneeRecu.equals(coordonneeSouhaité));

    }

    /**
     * Test si l'ordinateur intermédiaire propose à sa prochaine attaque la coordonnée à droite après avoir
     *  reçu l'information que le coordonnée du bas ne fait pas partie du bateau.
     */
    @Test
    public void testerStrategieChercherAlignementIntermediaire3(){
        Ordinateur ordinateurIntermediaire = new Ordinateur( Difficulte.INTERMEDIAIRE );
        Coordonnee coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
        int rangee = coordonneeSouhaité.getRangee();
        int colonne= coordonneeSouhaité.getColonne();
        while( (  rangee > 7 || rangee < 2  ) ||  (  colonne > 7 || colonne < 2  )){
            ordinateurIntermediaire = new Ordinateur( Difficulte.INTERMEDIAIRE );
            coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
            rangee = coordonneeSouhaité.getRangee();
            colonne= coordonneeSouhaité.getColonne();
        }
        coordonneeSouhaité.deplacerADroite();//au troisieme essaie, il devrait attaquer la cellule à droite

        ordinateurIntermediaire.recevoirResultat( TypeCellule.NAVIREINTACT );
        Coordonnee coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        assertTrue(coordonneeRecu.equals(coordonneeSouhaité));

    }


    /**
     * Test si l'ordinateur intermédiaire propose à sa prochaine attaque la coordonnée sequentielle du haut une fois
     * qu'il a repéré que le bateau est placé horizontalement.
     */
    @Test
    public void testerStrategieSuivreAlignementIntermediaire1(){
        Ordinateur ordinateurIntermediaire = new Ordinateur( Difficulte.INTERMEDIAIRE );
        Coordonnee coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
        int rangee = coordonneeSouhaité.getRangee();
        int colonne= coordonneeSouhaité.getColonne();
        while( (  rangee > 7 || rangee < 2  ) ||  (  colonne > 7 || colonne < 2  )){
            ordinateurIntermediaire = new Ordinateur( Difficulte.INTERMEDIAIRE );
            coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
            rangee = coordonneeSouhaité.getRangee();
            colonne= coordonneeSouhaité.getColonne();
        }
        coordonneeSouhaité.deplacerEnHaut(2); //sa prochaine attaque
        ordinateurIntermediaire.recevoirResultat( TypeCellule.NAVIREINTACT );
        Coordonnee coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat( TypeCellule.NAVIREINTACT );
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);

    }

    /**
     * Test si l'ordinateur intermédiaire continue à proposer la coordonnée sequentielle du haut
     * après avoir touché deux cellules du bateaux.
     */
    @Test
    public void testerStrategieSuivreAlignementIntermediaire2(){
        Ordinateur ordinateurIntermediaire = new Ordinateur( Difficulte.INTERMEDIAIRE );
        Coordonnee coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
        int rangee = coordonneeSouhaité.getRangee();
        int colonne= coordonneeSouhaité.getColonne();
        while( ( rangee > 6 || rangee < 3 ) ||  ( colonne > 6 || colonne < 3 )){
            ordinateurIntermediaire = new Ordinateur( Difficulte.INTERMEDIAIRE );
            coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
            rangee = coordonneeSouhaité.getRangee();
            colonne= coordonneeSouhaité.getColonne();
        }
        coordonneeSouhaité.deplacerEnHaut(3); //sa prochaine attaque devrait être la cellule en haut
        ordinateurIntermediaire.recevoirResultat( TypeCellule.NAVIREINTACT );
        Coordonnee coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat( TypeCellule.NAVIREINTACT );
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat( TypeCellule.NAVIREINTACT );
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);
    }
    /**
     * Test si l'ordinateur intermédiaire propose à sa prochaine attaque la coordonnée sequentielle à droite une fois
     * qu'il a repéré que le bateau est placé verticalement.
     */
    @Test
    public void testerStrategieSuivreAlignementIntermediaire3(){
        Ordinateur ordinateurIntermediaire = new Ordinateur( Difficulte.INTERMEDIAIRE );
        Coordonnee coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
        int rangee = coordonneeSouhaité.getRangee();
        int colonne= coordonneeSouhaité.getColonne();
        while( ( rangee > 7 || rangee < 2 ) ||  ( colonne > 7 || colonne < 2 )){
            ordinateurIntermediaire = new Ordinateur( Difficulte.INTERMEDIAIRE );
            coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
            rangee = coordonneeSouhaité.getRangee();
            colonne= coordonneeSouhaité.getColonne();
        }
        coordonneeSouhaité.deplacerADroite(); //sa prochaine attaque devrait être la cellule en haut
        coordonneeSouhaité.deplacerADroite();
        ordinateurIntermediaire.recevoirResultat( TypeCellule.NAVIREINTACT );
        Coordonnee coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat( TypeCellule.NAVIREINTACT );
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);
    }
    /**
     * Test si l'ordinateur intermédiaire continue à proposer la coordonnée sequentielle à droite
     * après avoir touché deux cellules du bateaux.
     */
    @Test
    public void testerStrategieSuivreAlignementIntermediaire4(){
        Ordinateur ordinateurIntermediaire = new Ordinateur( Difficulte.INTERMEDIAIRE );
        Coordonnee coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
        int rangee = coordonneeSouhaité.getRangee();
        int colonne= coordonneeSouhaité.getColonne();
        while( (rangee > 6 || rangee < 3) ||  (colonne > 6 || colonne < 3)){
            ordinateurIntermediaire = new Ordinateur( Difficulte.INTERMEDIAIRE );
            coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
            rangee = coordonneeSouhaité.getRangee();
            colonne= coordonneeSouhaité.getColonne();
        }
        coordonneeSouhaité.deplacerADroite(); //sa prochaine attaque devrait être la cellule en haut
        coordonneeSouhaité.deplacerADroite();
        coordonneeSouhaité.deplacerADroite();
        ordinateurIntermediaire.recevoirResultat( TypeCellule.NAVIREINTACT );
        Coordonnee coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat( TypeCellule.EAU );
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat( TypeCellule.EAU );
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat( TypeCellule.NAVIREINTACT );
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat( TypeCellule.NAVIREINTACT );
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);
    }
    /**
     * Test si l'ordinateur intermédiaire propose à sa prochaine attaque l'autre côté du bateau
     * après avoir touché une cellule de type eau.(Horizontal)
     */
    @Test
    public void testerStrategieSuivreAutreBoutIntermediaire1(){
        Ordinateur ordinateurIntermediaire = new Ordinateur( Difficulte.INTERMEDIAIRE );
        Coordonnee coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
        int rangee = coordonneeSouhaité.getRangee();
        int colonne= coordonneeSouhaité.getColonne();
        while( (rangee > 6 || rangee < 3) ||  (colonne > 6 || colonne < 3)){
            ordinateurIntermediaire = new Ordinateur( Difficulte.INTERMEDIAIRE );
            coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
            rangee = coordonneeSouhaité.getRangee();
            colonne= coordonneeSouhaité.getColonne();
        }
        coordonneeSouhaité.deplacerEnBas(1); //sa prochaine attaque devrait être la cellule en haut
        ordinateurIntermediaire.recevoirResultat( TypeCellule.NAVIREINTACT );
        Coordonnee coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat( TypeCellule.NAVIREINTACT );
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat( TypeCellule.EAU );
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);
    }

    /**
     * Test si l'ordinateur difficile propose à sa prochaine attaque l'autre côté du bateau
     * après avoir touché une cellule de type eau.(Vertical)
     */
    @Test
    public void testerStrategieSuivreAutreBoutIntermediaire2(){
        Ordinateur ordinateurDifficile = new Ordinateur( Difficulte.INTERMEDIAIRE );
        Coordonnee coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
        int rangee = coordonneeSouhaité.getRangee();
        int colonne= coordonneeSouhaité.getColonne();
        while( ( rangee > 6 || rangee < 3) ||  ( colonne > 6 || colonne < 3 ) ){
            ordinateurDifficile = new Ordinateur(Difficulte.INTERMEDIAIRE);
            coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
            rangee = coordonneeSouhaité.getRangee();
            colonne= coordonneeSouhaité.getColonne();
        }
        coordonneeSouhaité.deplacerAGauche(1); //sa prochaine attaque devrait être la cellule en haut
        ordinateurDifficile.recevoirResultat( TypeCellule.NAVIREINTACT );
        Coordonnee coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.EAU );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.EAU );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.NAVIREINTACT );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.EAU );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();

        assertEquals( coordonneeRecu, coordonneeSouhaité );
    }

    /**
     * Test si l'ordinateur difficile propose à sa prochaine attaque la coordonnée du haut après avoir
     * toucher un bateau pour la première fois.
     */
    @Test
    public void testerStrategieChercherAlignementDifficile1(){
        Ordinateur ordinateurDifficile = new Ordinateur( Difficulte.DIFFICILE );
        Coordonnee coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
        int rangee = coordonneeSouhaité.getRangee();
        int colonne= coordonneeSouhaité.getColonne();
        while( ( rangee > 7 || rangee < 2 ) ||  ( colonne > 7 || colonne < 2 )){
            ordinateurDifficile = new Ordinateur( Difficulte.DIFFICILE );
            coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
            rangee = coordonneeSouhaité.getRangee();
            colonne= coordonneeSouhaité.getColonne();
        }
        coordonneeSouhaité.deplacerEnHaut(); //sa prochaine attaque devrait être la cellule en haut
        ordinateurDifficile.recevoirResultat( TypeCellule.NAVIREINTACT );
        Coordonnee coordonneeRecu = ordinateurDifficile.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);
    }

    /**
     * Test si l'ordinateur difficile propose à sa prochaine attaque la coordonnée du bas après avoir
     *  reçu l'information que le coordonnée en haut ne fait pas partie du bateau.
     */
    @Test
    public void testerStrategieChercherAlignementDifficile2(){
        Ordinateur ordinateurDifficile = new Ordinateur( Difficulte.DIFFICILE );
        Coordonnee coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
        int rangee = coordonneeSouhaité.getRangee();
        int colonne= coordonneeSouhaité.getColonne();
        while( ( rangee > 7 || rangee < 2 ) ||  ( colonne > 7 || colonne < 2 )){
            ordinateurDifficile = new Ordinateur( Difficulte.DIFFICILE );
            coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
            rangee = coordonneeSouhaité.getRangee();
            colonne= coordonneeSouhaité.getColonne();
        }
        coordonneeSouhaité.deplacerEnBas();//au deuxième essaie, il devrait attaquer la cellule en bas

        ordinateurDifficile.recevoirResultat( TypeCellule.NAVIREINTACT );
        Coordonnee coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.EAU );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        assertTrue(coordonneeRecu.equals(coordonneeSouhaité));

    }


    /**
     * Test si l'ordinateur difficile propose à sa prochaine attaque la coordonnée à droite après avoir
     *  reçu l'information que le coordonnée du bas ne fait pas partie du bateau.
     */
    @Test
    public void testerStrategieChercherAlignementDifficile3(){
        Ordinateur ordinateurDifficile = new Ordinateur( Difficulte.DIFFICILE );
        Coordonnee coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
        int rangee = coordonneeSouhaité.getRangee();
        int colonne= coordonneeSouhaité.getColonne();
        while( (rangee > 6 || rangee < 3) ||  (colonne > 6 || colonne < 3)){
            ordinateurDifficile = new Ordinateur( Difficulte.DIFFICILE );
            coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
            rangee = coordonneeSouhaité.getRangee();
            colonne= coordonneeSouhaité.getColonne();
        }
        coordonneeSouhaité.deplacerADroite();//au troisieme essaie, il devrait attaquer la cellule à droite

        ordinateurDifficile.recevoirResultat( TypeCellule.NAVIREINTACT );
        Coordonnee coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.EAU );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.EAU );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        assertTrue(coordonneeRecu.equals(coordonneeSouhaité));

    }
    /**
     * Test si l'ordinateur difficile propose à sa prochaine attaque la coordonnée sequentielle du haut une fois
     * qu'il a repéré que le bateau est placé horizontalement.
     */
    @Test
    public void testerStrategieSuivreAlignementDifficile1(){
        Ordinateur ordinateurDifficile = new Ordinateur( Difficulte.DIFFICILE );
        Coordonnee coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
        int rangee = coordonneeSouhaité.getRangee();
        int colonne= coordonneeSouhaité.getColonne();
        while( (rangee > 6 || rangee < 3) ||  (colonne > 6 || colonne < 3)){
            ordinateurDifficile = new Ordinateur( Difficulte.DIFFICILE );
            coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
            rangee = coordonneeSouhaité.getRangee();
            colonne= coordonneeSouhaité.getColonne();
        }
        coordonneeSouhaité.deplacerEnHaut(2); //sa prochaine attaque
        ordinateurDifficile.recevoirResultat( TypeCellule.NAVIREINTACT );
        Coordonnee coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.NAVIREINTACT );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);

    }
    /**
     * Test si l'ordinateur difficile continue à proposer la coordonnée sequentielle du haut
     * après avoir touché deux cellules du bateaux.
     */
    @Test
    public void testerStrategieSuivreAlignementDifficile2(){
        Ordinateur ordinateurDifficile = new Ordinateur( Difficulte.DIFFICILE );
        Coordonnee coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
        int rangee = coordonneeSouhaité.getRangee();
        int colonne= coordonneeSouhaité.getColonne();
        while( (rangee > 6 || rangee < 3) ||  (colonne > 6 || colonne < 3)){
            ordinateurDifficile = new Ordinateur( Difficulte.DIFFICILE );
            coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
            rangee = coordonneeSouhaité.getRangee();
            colonne= coordonneeSouhaité.getColonne();
        }
        coordonneeSouhaité.deplacerEnHaut(3); //sa prochaine attaque devrait être la cellule en haut
        ordinateurDifficile.recevoirResultat( TypeCellule.NAVIREINTACT );
        Coordonnee coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.NAVIREINTACT );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.NAVIREINTACT );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);
    }

    @Test
    public void testerStrategieSuivreAlignementDifficile3(){
        Ordinateur ordinateurDifficile = new Ordinateur( Difficulte.DIFFICILE );
        Coordonnee coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
        int rangee = coordonneeSouhaité.getRangee();
        int colonne= coordonneeSouhaité.getColonne();
        while( (rangee > 6 || rangee < 3) ||  (colonne > 6 || colonne < 3)){
            ordinateurDifficile = new Ordinateur( Difficulte.DIFFICILE );
            coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
            rangee = coordonneeSouhaité.getRangee();
            colonne= coordonneeSouhaité.getColonne();
        }
        coordonneeSouhaité.deplacerADroite(); //sa prochaine attaque devrait être la cellule en haut
        coordonneeSouhaité.deplacerADroite();
        ordinateurDifficile.recevoirResultat( TypeCellule.NAVIREINTACT );
        Coordonnee coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.EAU );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.EAU );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.NAVIREINTACT );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);
    }
    /**
     * Test si l'ordinateur difficile continue à proposer la coordonnée sequentielle à droite
     * après avoir touché deux cellules du bateaux.
     */
    @Test
    public void testerStrategieSuivreAlignementDifficile4(){
        Ordinateur ordinateurDifficile = new Ordinateur( Difficulte.DIFFICILE );
        Coordonnee coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
        int rangee = coordonneeSouhaité.getRangee();
        int colonne= coordonneeSouhaité.getColonne();
        while( (rangee > 6 || rangee < 3) ||  (colonne > 6 || colonne < 3)){
            ordinateurDifficile = new Ordinateur( Difficulte.DIFFICILE );
            coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
            rangee = coordonneeSouhaité.getRangee();
            colonne= coordonneeSouhaité.getColonne();
        }
        coordonneeSouhaité.deplacerADroite(); //sa prochaine attaque devrait être la cellule en haut
        coordonneeSouhaité.deplacerADroite();
        coordonneeSouhaité.deplacerADroite();
        ordinateurDifficile.recevoirResultat( TypeCellule.NAVIREINTACT );
        Coordonnee coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.EAU );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.EAU );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.NAVIREINTACT );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.NAVIREINTACT );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);
    }
    /**
     * Test si l'ordinateur difficile propose à sa prochaine attaque l'autre côté du bateau
     * après avoir touché une cellule de type eau.(Horizontal)
     */
    @Test
    public void testerStrategieSuivreAutreBoutDifficile1(){
        Ordinateur ordinateurDifficile = new Ordinateur(  Difficulte.DIFFICILE  );
        Coordonnee coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
        int rangee = coordonneeSouhaité.getRangee();
        int colonne= coordonneeSouhaité.getColonne();
        while( (rangee > 6 || rangee < 3) ||  (colonne > 6 || colonne < 3)){
            ordinateurDifficile = new Ordinateur( Difficulte.DIFFICILE );
            coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
            rangee = coordonneeSouhaité.getRangee();
            colonne= coordonneeSouhaité.getColonne();
        }
        coordonneeSouhaité.deplacerEnBas(1); //sa prochaine attaque devrait être la cellule en haut
        ordinateurDifficile.recevoirResultat( TypeCellule.NAVIREINTACT );
        Coordonnee coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.NAVIREINTACT );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.EAU );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);
    }

    /**
     * Test si l'ordinateur difficile propose à sa prochaine attaque l'autre côté du bateau
     * après avoir touché une cellule de type eau.(Vertical)
     */
    @Test
    public void testerStrategieSuivreAutreBoutDifficile2(){
        Ordinateur ordinateurDifficile = new Ordinateur( Difficulte.DIFFICILE );
        Coordonnee coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
        int rangee = coordonneeSouhaité.getRangee();
        int colonne= coordonneeSouhaité.getColonne();
        while( ( rangee > 6 || rangee < 3) ||  ( colonne > 6 || colonne < 3 ) ){
            ordinateurDifficile = new Ordinateur(Difficulte.DIFFICILE);
            coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
            rangee = coordonneeSouhaité.getRangee();
            colonne= coordonneeSouhaité.getColonne();
        }
        coordonneeSouhaité.deplacerAGauche(1); //sa prochaine attaque devrait être la cellule en haut
        ordinateurDifficile.recevoirResultat( TypeCellule.NAVIREINTACT );
        Coordonnee coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.EAU );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.EAU );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.NAVIREINTACT );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat( TypeCellule.EAU );
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();

        assertEquals( coordonneeRecu, coordonneeSouhaité );
    }


}