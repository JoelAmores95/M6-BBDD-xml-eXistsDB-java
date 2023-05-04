import java.util.Scanner;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

public class Opcion7Borrar {
    /**
     * Paso 7: Borrar un CD
     * 
     * @throws XMLDBException
     */

    public static void borrarCD() throws XMLDBException {
        String nombreColeccion = "Proyecto";
        Programa.col = DatabaseManager.getCollection(Programa.URI + nombreColeccion, Programa.usu, Programa.usuPwd); 

        if (Programa.col == null) {
            System.out.println(" *** LA COLECCIÓN NO EXISTE. ***");
            return;
        }
        Scanner teclado = new Scanner(System.in);
        System.out.println("Titulo del CD a eliminar:");
        String tituloBorrar = teclado.nextLine();
        XPathQueryService servicio = (XPathQueryService) Programa.col.getService("XPathQueryService", "1.0");
        ResourceSet result = servicio.query("let $titulo := '" + tituloBorrar + "'return update delete //cd[titol = $titulo]");
    }

}
