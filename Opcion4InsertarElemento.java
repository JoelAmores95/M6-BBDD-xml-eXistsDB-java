import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

public class Opcion4InsertarElemento {
    /**
     * Paso 4: Insertar Elemento
     * 
     * @throws XMLDBException
     */
    public static void insertarElemento() throws XMLDBException {

        Programa.col = DatabaseManager.getCollection(Programa.URI + "Proyecto", Programa.usu, Programa.usuPwd); // Conexión con nuestra colección

        if (Programa.col == null) {
            System.out.println(" *** LA COLECCIÓN NO EXISTE. ***");
            return;
        }

        XPathQueryService servicio = (XPathQueryService) Programa.col.getService("XPathQueryService", "1.0");
        ResourceSet result = servicio.query("update insert"
        + " <cd id='3'>"
        + "<titol>Titulo Inventado</titol>"
        + "<artista>Artista Inventado</artista>"
        + "<pais>Pais Inventado</pais>"
        + "<preu>Precio Inventado</preu>"
        + "<any>Año Inventado</any>"
        + "</cd>"
        + "into doc ('cataleg.xml')");

        System.out.println(result);

        
        Programa.col.close(); // Cerramos coleccion
    }

}
