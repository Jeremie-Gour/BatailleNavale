package uqam.inf5153.batailleNavale;

import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class PartieTest {

    @BeforeEach
    void setUp() {

    }

    private static final int TAILLE_HORIZONTALE_MAX = 10;
    private static final int TAILLE_VERTICALE_MAX = 10;

    // Vérifie la création d'un océan
    @Test public void creationOcean() {
        Ocean oceanHumain = new Ocean();
        for (int h = 0; h < TAILLE_HORIZONTALE_MAX; h++) {
            for (int v = 0; v < TAILLE_VERTICALE_MAX; v++) {
                assertEquals(TypeCellule.EAU, oceanHumain.getOcean()[h][v].typeCellule);
            }
        }
    }

/*
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
                "9 | ~ ~ ~ ~ ~  ~ ~ ~ ~ |\n" +
                "    A B C D E F G H I J ";
        assertEquals(test,oceanHumain.afficherOcean());
    }
    */

    @Test
    void main() {
    }



    @AfterEach
    void tearDown() {
    }

    @Test
    void jouerPartie() {
    }

    @Test
    void testMain() {
    }
}