import java.util.Scanner;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

public class Opcion8Antiguos {
    /**
     * Paso 8: Mostrar registros antiguos a una fecha
     * 
     * @throws XMLDBException
     */
    public static void mostrarRegistrosAntiguos() throws XMLDBException {
        String nombreColeccion = "Proyecto";
        Programa.col = DatabaseManager.getCollection(Programa.URI + nombreColeccion, Programa.usu, Programa.usuPwd); 

        if (Programa.col == null) {
            System.out.println(" *** LA COLECCIÓN NO EXISTE. ***");
            return;
        }
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce el año: ");
        int año = teclado.nextInt();

        XPathQueryService servicio = (XPathQueryService) Programa.col.getService("XPathQueryService", "1.0");
        ResourceSet result = servicio.query("let $año := '" + año + "'"
                + "for $cd in //cd[año < $año]"
                + "order by $cd/año descending "
                + "return $cd/titulo/text()");
    }

}
