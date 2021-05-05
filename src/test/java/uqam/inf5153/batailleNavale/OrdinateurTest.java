package uqam.inf5153.batailleNavale;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class OrdinateurTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testerStrategieChercherAlignementIntermediaire1(){
        Ordinateur ordinateurIntermediaire = new Ordinateur(Difficulte.INTERMEDIAIRE);
        Coordonnee coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();

        coordonneeSouhaité.deplacerEnHaut(); //sa prochaine attaque devrait être la cellule en haut
        ordinateurIntermediaire.recevoirResultat(TypeCellule.NAVIREINTACT);
        Coordonnee coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);

    }

    @Test
    public void testerStrategieChercherAlignementIntermediaire2(){
        Ordinateur ordinateurIntermediaire = new Ordinateur(Difficulte.INTERMEDIAIRE);
        Coordonnee coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
        coordonneeSouhaité.deplacerEnBas();//au deuxième essaie, il devrait attaquer la cellule en bas
        ordinateurIntermediaire.recevoirResultat(TypeCellule.NAVIREINTACT);
        Coordonnee coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        assertTrue(coordonneeRecu.equals(coordonneeSouhaité));

    }

    @Test
    public void testerStrategieChercherAlignementIntermediaire3(){
        Ordinateur ordinateurIntermediaire = new Ordinateur(Difficulte.INTERMEDIAIRE);
        Coordonnee coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
        coordonneeSouhaité.deplacerADroite();//au troisieme essaie, il devrait attaquer la cellule à droite

        ordinateurIntermediaire.recevoirResultat(TypeCellule.NAVIREINTACT);
        Coordonnee coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        assertTrue(coordonneeRecu.equals(coordonneeSouhaité));

    }



    @Test
    public void testerStrategieSuivreAlignementIntermediaire1(){
        Ordinateur ordinateurIntermediaire = new Ordinateur(Difficulte.INTERMEDIAIRE);
        Coordonnee coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
        coordonneeSouhaité.deplacerEnHaut(2); //sa prochaine attaque
        ordinateurIntermediaire.recevoirResultat(TypeCellule.NAVIREINTACT);
        Coordonnee coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.NAVIREINTACT);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);

    }

    @Test
    public void testerStrategieSuivreAlignementIntermediaire2(){
        Ordinateur ordinateurIntermediaire = new Ordinateur(Difficulte.INTERMEDIAIRE);
        Coordonnee coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
        coordonneeSouhaité.deplacerEnHaut(3); //sa prochaine attaque devrait être la cellule en haut
        ordinateurIntermediaire.recevoirResultat(TypeCellule.NAVIREINTACT);
        Coordonnee coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.NAVIREINTACT);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.NAVIREINTACT);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);
    }

    @Test
    public void testerStrategieSuivreAlignementIntermediaire3(){
        Ordinateur ordinateurIntermediaire = new Ordinateur(Difficulte.INTERMEDIAIRE);
        Coordonnee coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
        coordonneeSouhaité.deplacerADroite(); //sa prochaine attaque devrait être la cellule en haut
        coordonneeSouhaité.deplacerADroite();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.NAVIREINTACT);
        Coordonnee coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.NAVIREINTACT);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);
    }

    @Test
    public void testerStrategieSuivreAlignementIntermediaire4(){
        Ordinateur ordinateurIntermediaire = new Ordinateur(Difficulte.INTERMEDIAIRE);
        Coordonnee coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
        coordonneeSouhaité.deplacerADroite(); //sa prochaine attaque devrait être la cellule en haut
        coordonneeSouhaité.deplacerADroite();
        coordonneeSouhaité.deplacerADroite();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.NAVIREINTACT);
        Coordonnee coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.NAVIREINTACT);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.NAVIREINTACT);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);
    }

    @Test
    public void testerStrategieSuivreAutreBoutIntermediaire1(){
        Ordinateur ordinateurIntermediaire = new Ordinateur(Difficulte.INTERMEDIAIRE);
        Coordonnee coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
        coordonneeSouhaité.deplacerEnBas(1); //sa prochaine attaque devrait être la cellule en haut
        ordinateurIntermediaire.recevoirResultat(TypeCellule.NAVIREINTACT);
        Coordonnee coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.NAVIREINTACT);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);
    }

    @Test
    public void testerStrategieChercherAlignementDifficile1(){
        Ordinateur ordinateurIntermediaire = new Ordinateur(Difficulte.DIFFICILE);
        Coordonnee coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
        coordonneeSouhaité.deplacerEnHaut(); //sa prochaine attaque devrait être la cellule en haut

        ordinateurIntermediaire.recevoirResultat(TypeCellule.NAVIREINTACT);
        Coordonnee coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);
    }

    @Test
    public void testerStrategieChercherAlignementDifficile2(){
        Ordinateur ordinateurIntermediaire = new Ordinateur(Difficulte.DIFFICILE);
        Coordonnee coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
        coordonneeSouhaité.deplacerEnBas();//au deuxième essaie, il devrait attaquer la cellule en bas

        ordinateurIntermediaire.recevoirResultat(TypeCellule.NAVIREINTACT);
        Coordonnee coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        assertTrue(coordonneeRecu.equals(coordonneeSouhaité));

    }



    @Test
    public void testerStrategieChercherAlignementDifficile3(){
        Ordinateur ordinateurIntermediaire = new Ordinateur(Difficulte.DIFFICILE);
        Coordonnee coordonneeSouhaité =  ordinateurIntermediaire.prochaineAttaque();
        coordonneeSouhaité.deplacerADroite();//au troisieme essaie, il devrait attaquer la cellule à droite

        ordinateurIntermediaire.recevoirResultat(TypeCellule.NAVIREINTACT);
        Coordonnee coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        ordinateurIntermediaire.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurIntermediaire.prochaineAttaque();
        assertTrue(coordonneeRecu.equals(coordonneeSouhaité));

    }

    @Test
    public void testerStrategieSuivreAlignementDifficile1(){
        Ordinateur ordinateurDifficile = new Ordinateur(Difficulte.DIFFICILE);
        Coordonnee coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
        coordonneeSouhaité.deplacerEnHaut(2); //sa prochaine attaque
        ordinateurDifficile.recevoirResultat(TypeCellule.NAVIREINTACT);
        Coordonnee coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat(TypeCellule.NAVIREINTACT);
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);

    }

    @Test
    public void testerStrategieSuivreAlignementDifficile2(){
        Ordinateur ordinateurDifficile = new Ordinateur(Difficulte.DIFFICILE);
        Coordonnee coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
        coordonneeSouhaité.deplacerEnHaut(3); //sa prochaine attaque devrait être la cellule en haut
        ordinateurDifficile.recevoirResultat(TypeCellule.NAVIREINTACT);
        Coordonnee coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat(TypeCellule.NAVIREINTACT);
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat(TypeCellule.NAVIREINTACT);
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);
    }

    @Test
    public void testerStrategieSuivreAlignementDifficile3(){
        Ordinateur ordinateurDifficile = new Ordinateur(Difficulte.DIFFICILE);
        Coordonnee coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
        coordonneeSouhaité.deplacerADroite(); //sa prochaine attaque devrait être la cellule en haut
        coordonneeSouhaité.deplacerADroite();
        ordinateurDifficile.recevoirResultat(TypeCellule.NAVIREINTACT);
        Coordonnee coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat(TypeCellule.NAVIREINTACT);
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);
    }

    @Test
    public void testerStrategieSuivreAlignementDifficile4(){
        Ordinateur ordinateurDifficile = new Ordinateur(Difficulte.DIFFICILE);
        Coordonnee coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
        coordonneeSouhaité.deplacerADroite(); //sa prochaine attaque devrait être la cellule en haut
        coordonneeSouhaité.deplacerADroite();
        coordonneeSouhaité.deplacerADroite();
        ordinateurDifficile.recevoirResultat(TypeCellule.NAVIREINTACT);
        Coordonnee coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat(TypeCellule.NAVIREINTACT);
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat(TypeCellule.NAVIREINTACT);
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);
    }

    @Test
    public void testerStrategieSuivreAutreBoutDifficile1(){
        Ordinateur ordinateurDifficile = new Ordinateur(Difficulte.DIFFICILE);
        Coordonnee coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
        coordonneeSouhaité.deplacerEnBas(1); //sa prochaine attaque devrait être la cellule en haut
        ordinateurDifficile.recevoirResultat(TypeCellule.NAVIREINTACT);
        Coordonnee coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat(TypeCellule.NAVIREINTACT);
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);
    }
    @Test
    public void testerStrategieSuivreAutreBoutDifficile2(){
        Ordinateur ordinateurDifficile = new Ordinateur(Difficulte.DIFFICILE);
        Coordonnee coordonneeSouhaité =  ordinateurDifficile.prochaineAttaque();
        coordonneeSouhaité.deplacerAGauche(1); //sa prochaine attaque devrait être la cellule en haut
        ordinateurDifficile.recevoirResultat(TypeCellule.NAVIREINTACT);
        Coordonnee coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat(TypeCellule.NAVIREINTACT);
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();
        ordinateurDifficile.recevoirResultat(TypeCellule.EAU);
        coordonneeRecu = ordinateurDifficile.prochaineAttaque();

        assertEquals(coordonneeRecu,coordonneeSouhaité);
    }


}