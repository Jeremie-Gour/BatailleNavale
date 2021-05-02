package uqam.inf5153.batailleNavale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Test les méthodes de la classe Cellule.
 */
class CelluleTest {

    private final ByteArrayOutputStream outputSave = new ByteArrayOutputStream();

    /**
     * Attrape les outputs de chaque test.
     */
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputSave));
    }

    /**
     * Test l'occupation d'une case par un NAVIREINTACT.
     */
    @Test
    public void estOccupeeParNavireTest(){
        Ocean oceanHumain = new Ocean();
        assertFalse(oceanHumain.getOcean()[0][0].estOccupeeParNavire());
    }

    /**
     * Test l'occupation d'une case.
     */
    @Test
    public void estOccupeeTest(){
        Ocean oceanHumain = new Ocean();
        assertFalse(oceanHumain.getOcean()[6][8].estOccupeeParNavire());
    }

}