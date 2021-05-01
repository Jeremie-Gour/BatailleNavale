package uqam.inf5153.batailleNavale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static uqam.inf5153.batailleNavale.TypeNavire.CONTRETORPILLEUR;

class SaisieTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        // Attrape les outputs
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    // Vérifie le choix de document

    @Test
    public void typeDocumentTest(){
        InputStream save = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("texte".getBytes());
        System.setIn(input);

        assertEquals("texte", Saisie.saisirTypeDocument());
        System.setIn(save);
    }

    // Vérifie la sélection de la difficulté
    @Test public void choisirDifficulteTest(){
        InputStream save = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("3".getBytes());
        System.setIn(input);

        assertEquals(Difficulte.DIFFICILE, Saisie.saisirNiveauDifficulte());

        System.setIn(save);

    }

    // Vérifie le choix de jouer avec des bombes
    @Test public void avecBombeTest(){
        InputStream save = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("non".getBytes());
        System.setIn(input);

        assertFalse(Saisie.avecBombe());

        System.setIn(save);
    }

    // Vérifie la saisie des coordonnées d'une bombe
    @Test public void saisirBombeTest(){
        InputStream save = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("E5".getBytes());
        System.setIn(input);

        assertEquals(new Coordonnee(5,4), Saisie.saisirBombe());

        System.setIn(save);
    }

    // Vérifie la saisie des coordonnées d'un navire
    @Test public void saisirNavireTest(){
        InputStream save = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("A5V".getBytes());
        System.setIn(input);

        assertEquals("A5V", Saisie.saisirNavire(new Navire(CONTRETORPILLEUR)));
        System.setIn(save);
    }

    // Vérifie le changement d'un char à Alignement
    @Test public void saisirAlignementTest(){
        InputStream save = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("V".getBytes());
        System.setIn(input);

        assertEquals(Alignement.VERTICAL, Saisie.determinerAlignement('V'));

        System.setIn(save);
    }

    // Vérifie la saisie des coordonnées d'un tir
    @Test public void saisirTirTest(){
        InputStream save = System.in;
        ByteArrayInputStream input = new ByteArrayInputStream("B4".getBytes());
        System.setIn(input);

        assertEquals(new Coordonnee(4,1), Saisie.saisirTir());
        System.setIn(save);
    }

    // Vérifie le changement d'un char à une Colonne
    @Test public void stringToColonneTest(){
        assertEquals(3,Colonne.stringToColonne('D'));
    }

    // Vérifie le gagnant
    @Test public void afficherGagnantTest(){
        assertEquals("Le joueur a remporté la partie!\n", Saisie.afficherGagnant(true,false));
    }

}