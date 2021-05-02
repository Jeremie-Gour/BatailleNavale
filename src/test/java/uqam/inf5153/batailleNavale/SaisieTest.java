package uqam.inf5153.batailleNavale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static uqam.inf5153.batailleNavale.TypeNavire.CONTRETORPILLEUR;

/**
 * Test les méthodes de la classe Saisie.
 */
class SaisieTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Attrape les outputs de chaque test.
     */
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * Test la saisie du choix de document.
     */
    @Test
    public void typeDocumentTest(){
        InputStream save = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("texte".getBytes());
        System.setIn(input);

        assertEquals("texte", Saisie.saisirTypeDocument());
        System.setIn(save);
    }

    /**
     * Test la saisie de la difficulté.
     */
    @Test public void choisirDifficulteTest(){
        InputStream save = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("3".getBytes());
        System.setIn(input);

        assertEquals(Difficulte.DIFFICILE, Saisie.saisirNiveauDifficulte());

        System.setIn(save);

    }

    /**
     * Test la saisie du choix de jouer avec des bombes.
     */
    @Test public void avecBombeTest(){
        InputStream save = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("non".getBytes());
        System.setIn(input);

        assertFalse(Saisie.avecBombe());

        System.setIn(save);
    }

    /**
     * Test la saisie des coordonnées d'une bombe.
     */
    @Test public void saisirBombeTest(){
        InputStream save = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("E5".getBytes());
        System.setIn(input);

        assertEquals(new Coordonnee(5,4), Saisie.saisirBombe());

        System.setIn(save);
    }

    /**
     * Test la saisie des coordonnées d'un navire.
     */
    @Test public void saisirNavireTest(){
        InputStream save = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("A5V".getBytes());
        System.setIn(input);

        assertEquals("A5V", Saisie.saisirNavire(new Navire(CONTRETORPILLEUR)));
        System.setIn(save);
    }

    /**
     * Test le changement du char représentant l'alignement à Alignement.
     */
    @Test public void saisirAlignementTest(){
        InputStream save = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("V".getBytes());
        System.setIn(input);

        assertEquals(Alignement.VERTICAL, Saisie.determinerAlignement('V'));

        System.setIn(save);
    }

    /**
     * Test la saisie des coordonnées d'un tir.
     */
    @Test public void saisirTirTest(){
        InputStream save = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("B4".getBytes());
        System.setIn(input);

        assertEquals(new Coordonnee(4,1), Saisie.saisirTir());
        System.setIn(save);
    }

    /**
     * Test l'affichage du message de fin.
     */
    @Test public void afficherGagnantTest(){
        assertEquals("Le joueur a remporté la partie!\n", Saisie.afficherGagnant(true,false));
    }

}