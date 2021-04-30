package uqam.inf5153.batailleNavale;

import org.junit.jupiter.api.*;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static uqam.inf5153.batailleNavale.TypeNavire.CONTRETORPILLEUR;


public class PartieTest {

    @BeforeEach
    void setUp() {

    }

    private static final int TAILLE_HORIZONTALE_MAX = 10;
    private static final int TAILLE_VERTICALE_MAX = 10;

    // Vérifie la création d'un océan
    @Test public void creationOceanTest() {
        Ocean oceanHumain = new Ocean();
        for (int h = 0; h < TAILLE_HORIZONTALE_MAX; h++) {
            for (int v = 0; v < TAILLE_VERTICALE_MAX; v++) {
                assertEquals(TypeCellule.EAU, oceanHumain.getOcean()[h][v].typeCellule);
            }
        }
    }

    //// Tests de la classe Message

    // Vérifie le choix de document
    @Test public void typeDocumentTest(){
        InputStream save = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("texte".getBytes());
        System.setIn(input);

        assertEquals("texte", Message.typeDocument());
        System.setIn(save);
    }

    // Vérifie la sélection de la difficulté
    @Test public void choisirDifficulteTest(){
        InputStream save = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("3".getBytes());

        System.setIn(input);
        assertEquals(Difficulte.DIFFICILE, Message.choisirDifficulte());

        System.setIn(save);

    }

    // Vérifie le choix de jouer avec des bombes
    @Test public void avecBombeTest(){
        InputStream save = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("non".getBytes());
        System.setIn(input);

        assertFalse(Message.avecBombe());

        System.setIn(save);
    }

    // Vérifie la saisie des coordonnées d'une bombe
    @Test public void saisirBombeTest(){
        InputStream save = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("E5".getBytes());
        System.setIn(input);

        assertEquals(new Coordonnee(5,4), Message.saisirBombe());

        System.setIn(save);
    }

    // Vérifie la saisie des coordonnées d'un navire
    @Test public void saisirNavireTest(){
        InputStream save = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("A5V".getBytes());
        System.setIn(input);

        assertEquals("A5V", Message.saisirNavire(new Navire(CONTRETORPILLEUR)));
        System.setIn(save);
    }

    // Vérifie le changement d'un char à Alignement
    @Test public void saisirAlignementTest(){
        InputStream save = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("V".getBytes());
        System.setIn(input);

        assertEquals(Alignement.VERTICAL, Message.saisirAlignement('V'));

        System.setIn(save);
    }

    @Test public void saisirTirTest(){
        InputStream save = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("B4".getBytes());
        System.setIn(input);

        assertEquals(new Coordonnee(4,1), Message.saisirTir());
        System.setIn(save);
    }

    @Test public void stringToColonneTest(){
        assertEquals(3,Message.stringToColonne('D'));
    }

    @Test public void afficherGagnantTest(){

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