import java.io.File;
import java.util.Scanner;

import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;

public class Opcion3SubirFichero {

    // /**
    // * Paso 3: Subir fichero a colección
    // */
    public static void pujarDocument() throws XMLDBException {

        Scanner teclado = new Scanner(System.in);

        System.out.println("Nombre de archivo:");
        String nombreArchivo = teclado.nextLine();

        // Carga del fichero
        File arxiu = new File(nombreArchivo);
        if (!arxiu.canRead())
            System.out.println("ERROR AL LLEIGIR ARXIU");
        else {
            Resource nouRecurs = Programa.col.createResource(arxiu.getName(), "XMLResource");
            nouRecurs.setContent(arxiu); // Comprova que es un arxiu
            Programa.col.storeResource(nouRecurs);
            System.out.println(nombreArchivo + " añadido correctamente");
            teclado.close();
        }
    }

}
