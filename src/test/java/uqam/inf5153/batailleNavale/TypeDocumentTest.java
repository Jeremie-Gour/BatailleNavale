package uqam.inf5153.batailleNavale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test les méthodes de la classe Ocean.
 */

class TypeDocumentTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Attrape les outputs de chaque test.
     */
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * Test la sauvegarde des outputs dans un fichier texte.
     * @throws IOException Pour Files.deleteIfExists(path) et Files.readString(path).
     */
    @Test
    public void recordingTxtTest() throws IOException {
        TypeDocument.recording(TypeDocument.TEXTE);
        System.out.print("Test enregistrement du output!");
        Path path = Paths.get("sauvegarde.txt");
        assertTrue(Files.exists(path));
        assertEquals("Test enregistrement du output!", Files.readString(path));
        Files.deleteIfExists(path);
    }

    /**
     * Test la sauvegarde des outputs dans un fichier XML.
     * @throws IOException Pour Files.deleteIfExists(path) et Files.readString(path).
     */
    @Test
    public void recordingXmlTest() throws IOException {
        TypeDocument.recording(TypeDocument.XML);
        System.out.print("Test enregistrement du output!");
        Path path = Paths.get("sauvegarde.xml");
        assertTrue(Files.exists(path));
        assertEquals("Test enregistrement du output!", Files.readString(path));
        Files.deleteIfExists(path);
    }

    /**
     * Test la sauvegarde des outputs dans un fichier Json.
     * @throws IOException Pour Files.deleteIfExists(path) et Files.readString(path).
     */
    @Test
    public void recordingJsonTest() throws IOException {
        TypeDocument.recording(TypeDocument.JSON);
        System.out.print("Test enregistrement du output!");
        Path path = Paths.get("sauvegarde.json");
        assertTrue(Files.exists(path));
        assertEquals("Test enregistrement du output!", Files.readString(path));
        Files.deleteIfExists(path);
    }

}