import java.util.Scanner;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;

public class Opcion2CrearColeccion {
    /**
     * Paso 2: Crear Colección
     * 
     * @param nombreColeccion
     * @throws XMLDBException
     */
    public static void crearColeccion() throws XMLDBException {

        Scanner teclado = new Scanner(System.in);
        System.out.println("Nombre de la colección a crear:");
        String nombreColeccion = teclado.nextLine();
        

        // Crear colección con el nombre dado
        try {
            CollectionManagementService mgtService = (CollectionManagementService) Programa.col
                    .getService("CollectionManagementService", "1.0");
            mgtService.createCollection(nombreColeccion);
            System.out.println("Colección creada correctamente");
        } catch (XMLDBException e) {
            System.out.println("Error al crear la colección " + nombreColeccion);
            e.printStackTrace();
        }

        Programa.col.close(); // Cerramos coleccion
    }

}
