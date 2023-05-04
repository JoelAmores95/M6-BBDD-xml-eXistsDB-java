import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

public class Opcion5ModificarPrecio {
    /**
     * Paso 5: Modificar Precio
     * 
     * @throws XMLDBException
     */
    public static void modificarPrecio(String titulo, double nuevoPrecio) throws XMLDBException {

        String nombreColeccion = "Proyecto";
        Programa.col = DatabaseManager.getCollection(Programa.URI + nombreColeccion, Programa.usu, Programa.usuPwd); // Conexión con nuestra colección

        if (Programa.col == null) {
            System.out.println(" *** LA COLECCIÓN NO EXISTE. ***");
            return;
        }

        XPathQueryService servicio = (XPathQueryService) Programa.col.getService("XPathQueryService", "1.0");
        ResourceSet result = servicio.query("let $titulo := '" + titulo
                + "' let $precioNuevo := '" + nuevoPrecio
                + "' for $cd in /cataleg/cd[titol = $titulo]"
                + " return"
                + "   ("
                + "     update value $cd/preu with $precioNuevo"
                + "   )");
    }

}
