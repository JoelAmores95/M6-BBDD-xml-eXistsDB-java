import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

public class Opcion6MostrarCantidad {
    /**
     * Paso 6: Mostrar Cantidad de CD
     * 
     * @throws XMLDBException
     */
    public static void mostrarCantidadCD() throws XMLDBException {

        String nombreColeccion = "Proyecto";
        Programa.col = DatabaseManager.getCollection(Programa.URI + nombreColeccion, Programa.usu, Programa.usuPwd); 

        if (Programa.col == null) {
            System.out.println(" *** LA COLECCIÓN NO EXISTE. ***");
            return;
        }

        XPathQueryService servicio = (XPathQueryService) Programa.col.getService("XPathQueryService", "1.0");
        ResourceSet result = servicio.query(" let $cantCD := count (//cd) return <NumTotalCDs>{$cantCD}</NumTotalCDs>");

        // recorrer les dades del recurs.
        ResourceIterator i;
        i = result.getIterator();
        if (!i.hasMoreResources())
            System.out.println(" LA CONSULTA NO RETORNA RES.");
        while (i.hasMoreResources()) {
            Resource r = i.nextResource();
            System.out.println((String) r.getContent());
        }
    }

}
