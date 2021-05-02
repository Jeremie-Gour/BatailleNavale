package uqam.inf5153.batailleNavale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Test les méthodes dans l'enumération Colonne. .
 */
class ColonneTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Attrape les outputs de chaque test.
     */
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * Test le changement d'un char représentant une colonne à Colonne
     */
    @Test
    public void stringToColonneTest(){
        assertEquals(3,Colonne.stringToColonne('D'));
    }

}