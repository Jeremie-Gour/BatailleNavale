package uqam.inf5153.batailleNavale;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Cette énumération contient les différents type de documents à utiliser pour sauvegarder les parties.
 */
public enum TypeDocument {
    TEXTE("sauvegarde.txt"),
    XML("sauvegarde.xml"),
    JSON("sauvegarde.json");

    private String _typeDocument;

    TypeDocument(String _typeDocument) {
        this._typeDocument = _typeDocument;
    }

    /**
     * Cette méthode pointe le output stream vers un document et sauvegarde la partie.
     * @param typeDocument Le tyoe de document utilisé pour la sauvegarde.
     */
    public static void recording(TypeDocument typeDocument){

        try
        {
            FileOutputStream fout = new FileOutputStream(typeDocument._typeDocument);

            MultiOutputStream multiOut= new MultiOutputStream(System.out, fout);
            MultiOutputStream multiErr= new MultiOutputStream(System.err, fout);

            PrintStream stdout= new PrintStream(multiOut);
            PrintStream stderr= new PrintStream(multiErr);

            System.setOut(stdout);
            System.setErr(stderr);
        }
        catch (FileNotFoundException ex)
        {
        }
    }
}
