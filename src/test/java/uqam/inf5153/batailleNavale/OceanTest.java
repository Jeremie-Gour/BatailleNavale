package uqam.inf5153.batailleNavale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class OceanTest {

    private static final int TAILLE_HORIZONTALE_MAX = 10;
    private static final int TAILLE_VERTICALE_MAX = 10;

    private final ByteArrayOutputStream outputSave = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        // Attrape les outputs
        System.setOut(new PrintStream(outputSave));
    }


    // Vérifie la création d'un océan
    @Test
    public void creationOceanTest() {
        Ocean oceanHumain = new Ocean();
        for (int h = 0; h < TAILLE_HORIZONTALE_MAX; h++) {
            for (int v = 0; v < TAILLE_VERTICALE_MAX; v++) {
                assertEquals(TypeCellule.EAU, oceanHumain.getOcean()[h][v].typeCellule);
            }
        }
    }

    // Vérifie l'affichage d'un océan
    @Test public void afficherOceanTest(){

        Ocean oceanHumain = new Ocean();
        String test = "0 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |\n" +
                      "1 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |\n" +
                      "2 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |\n" +
                      "3 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |\n" +
                      "4 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |\n" +
                      "5 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |\n" +
                      "6 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |\n" +
                      "7 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |\n" +
                      "8 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |\n" +
                      "9 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |\n" +
                      "    A B C D E F G H I J";
        oceanHumain.afficherOcean();
        assertEquals(test,outputSave.toString().trim());
    }

    // Vérifie le placage des bateaux par l'ordinateur
    @Test public void placerNaviresAleatoirementTest(){
        Ocean oceanOrdinateur = new Ocean();
        oceanOrdinateur.placerNaviresAleatoirement();
        int nbrCasesNavireIntact = 0;
        for (int h = 0; h < TAILLE_HORIZONTALE_MAX; h++) {
            for (int v = 0; v < TAILLE_VERTICALE_MAX; v++) {
                if(oceanOrdinateur.getOcean()[h][v].typeCellule == TypeCellule.NAVIREINTACT){
                    nbrCasesNavireIntact++;
                }
            }
        }
        assertEquals(17 , nbrCasesNavireIntact);
    }

    // Vérifie un emplacement valide pour placer un bateau
    @Test
    void verifierBateauTest1() {
        Ocean oceanHumain = new Ocean();
        assertTrue(oceanHumain.verifierBateau(new Coordonnee(0,0), Alignement.VERTICAL,new Navire(TypeNavire.PORTEAVIONS)));
    }

    // Vérifie un emplacement non-valide qui va dépasser les bordures
    @Test
    void verifierBateauTest2() {
        Ocean oceanHumain = new Ocean();
        assertFalse(oceanHumain.verifierBateau(new Coordonnee(9,0), Alignement.VERTICAL,new Navire(TypeNavire.PORTEAVIONS)));
    }

    // Vérifie un emplacement non-valide qui est collé à un autre bateau
    @Test
    void verifierBateauTest3() {
        Ocean oceanHumain = new Ocean();
        oceanHumain.placerNavire(new Coordonnee(0,0), Alignement.VERTICAL, new Navire(TypeNavire.PORTEAVIONS));
        assertFalse(oceanHumain.verifierBateau(new Coordonnee(0,1), Alignement.VERTICAL,new Navire(TypeNavire.PORTEAVIONS)));
    }

    // Vérifie le placement vertical d'un navire dans l'océan
    @Test
    void placerNavireTest1() {
        Ocean oceanHumain = new Ocean();
        oceanHumain.placerNavire(new Coordonnee(0,0), Alignement.VERTICAL, new Navire(TypeNavire.PORTEAVIONS));
        assertEquals(TypeCellule.NAVIREINTACT, oceanHumain.getOcean()[0][0].typeCellule);
        assertEquals(TypeCellule.NAVIREINTACT, oceanHumain.getOcean()[1][0].typeCellule);
        assertEquals(TypeCellule.NAVIREINTACT, oceanHumain.getOcean()[2][0].typeCellule);
        assertEquals(TypeCellule.NAVIREINTACT, oceanHumain.getOcean()[3][0].typeCellule);
        assertEquals(TypeCellule.NAVIREINTACT, oceanHumain.getOcean()[4][0].typeCellule);
    }

    // Vérifie le placement horizontal d'un navire dans l'océan
    @Test
    void placerNavireTest2() {
        Ocean oceanHumain = new Ocean();
        oceanHumain.placerNavire(new Coordonnee(0,0), Alignement.HORIZONTAL, new Navire(TypeNavire.PORTEAVIONS));
        assertEquals(TypeCellule.NAVIREINTACT, oceanHumain.getOcean()[0][0].typeCellule);
        assertEquals(TypeCellule.NAVIREINTACT, oceanHumain.getOcean()[0][1].typeCellule);
        assertEquals(TypeCellule.NAVIREINTACT, oceanHumain.getOcean()[0][2].typeCellule);
        assertEquals(TypeCellule.NAVIREINTACT, oceanHumain.getOcean()[0][3].typeCellule);
        assertEquals(TypeCellule.NAVIREINTACT, oceanHumain.getOcean()[0][4].typeCellule);
    }

    // Test tirer dans l'eau et le changement du typeCellule EAU à NAVIRERATE
    @Test
    void tirerTest1() {
        Ocean oceanOrdinateur = new Ocean();
        assertEquals(TypeCellule.EAU, oceanOrdinateur.tirer(new Coordonnee(5,5)));
        assertEquals(TypeCellule.NAVIRERATE, oceanOrdinateur.getOcean()[5][5].typeCellule);
    }

    // Test tirer sur un navire et le changement du typeCellule NAVIREINTACT à NAVIRETOUCHE
    @Test
    void tirerTest2() {
        Ocean oceanOrdinateur = new Ocean();
        oceanOrdinateur.placerNavire(new Coordonnee(0,0), Alignement.HORIZONTAL, new Navire(TypeNavire.PORTEAVIONS));
        assertEquals(TypeCellule.NAVIREINTACT, oceanOrdinateur.tirer(new Coordonnee(0,0)));
        assertEquals(TypeCellule.NAVIRETOUCHE, oceanOrdinateur.getOcean()[0][0].typeCellule);
    }

    // Test tirer sur une bombe et le changement du typeCellule BOMBE à NAVIRERATE
    @Test
    void tirerTest3() {
        Ocean oceanOrdinateur = new Ocean();
        oceanOrdinateur.placerBombe(new Coordonnee(0,0));
        assertEquals(TypeCellule.BOMBE, oceanOrdinateur.tirer(new Coordonnee(0,0)));
        assertEquals(TypeCellule.NAVIRERATE, oceanOrdinateur.getOcean()[0][0].typeCellule);
    }

    // Test placer une bombe à un emplacement valide
    @Test
    void placerBombeTest1() {
        Ocean oceanHumain = new Ocean();
        assertTrue(oceanHumain.placerBombe(new Coordonnee(0,0)));
        assertEquals(TypeCellule.BOMBE,oceanHumain.getOcean()[0][0].typeCellule);
    }

    // Test placer une bombe à un emplacement invalide à l'extérieur de l'océan
    @Test
    void placerBombeTest2() {
        Ocean oceanHumain = new Ocean();
        assertFalse(oceanHumain.placerBombe(new Coordonnee(15,0)));
    }

    // Test placer une bombe à un emplacement invalide qui est déjà occupé
    @Test
    void placerBombeTest3() {
        Ocean oceanHumain = new Ocean();
        oceanHumain.placerBombe(new Coordonnee(0,0));
        assertFalse(oceanHumain.placerBombe(new Coordonnee(0,0)));
    }

    // Test s'il reste des navires qui ne sont pas coulésgi
    @Test
    void celluleDeNavireIntactesDispoTest1() {
        Ocean oceanOrdinateur = new Ocean();
        oceanOrdinateur.placerNaviresAleatoirement();
        assertTrue(oceanOrdinateur.resteDesNavires());
    }

    // Test quand il ne reste plus de navires à couler
    @Test
    void celluleDeNavireIntactesDispoTest2() {
        Ocean oceanOrdinateur = new Ocean();
        oceanOrdinateur.placerNavire(new Coordonnee(0,0), Alignement.HORIZONTAL, new Navire(TypeNavire.PORTEAVIONS));
        oceanOrdinateur.placerNavire(new Coordonnee(2,0), Alignement.HORIZONTAL, new Navire(TypeNavire.CROISEUR));
        oceanOrdinateur.placerNavire(new Coordonnee(4,0), Alignement.HORIZONTAL, new Navire(TypeNavire.CONTRETORPILLEUR));
        oceanOrdinateur.placerNavire(new Coordonnee(6,0), Alignement.HORIZONTAL, new Navire(TypeNavire.CONTRETORPILLEUR));
        oceanOrdinateur.placerNavire(new Coordonnee(8,0), Alignement.HORIZONTAL, new Navire(TypeNavire.TORPILLEUR));
        for(int i = 0; i <= 8; i=i+2) {
            for(int j = 0; j < 5; j++) {
                oceanOrdinateur.tirer(new Coordonnee(i, j));
            }
        }
        assertFalse(oceanOrdinateur.resteDesNavires());
    }
}