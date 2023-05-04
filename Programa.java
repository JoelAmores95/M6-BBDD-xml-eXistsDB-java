
// Conexion BBDD
import org.xmldb.api.*;
import org.xmldb.api.base.*;

public class Programa {

    public static String nombreColeccion;
    public static String nombreArchivo;
    public static String driver = "org.exist.xmldb.DatabaseImpl"; // Driver para eXist
    public static Collection col = null; // Colección
    public static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/"; // URI colección
    public static String usu = "admin"; // Usuario
    public static String usuPwd = "Admin1234"; // Clave

    public static void main(String[] args) throws XMLDBException {
        
        // Conexión BD
        try {
            Class cl = Class.forName(Programa.driver); // Cargar del driver
            Database database = (Database) cl.newInstance(); // Instancia de la BD
            DatabaseManager.registerDatabase(database); // Registro del driver

        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }

        col = DatabaseManager.getCollection(URI, usu, usuPwd); // Connexi� amb la nostra col�lecci�

        if (col == null) {
            System.out.println(" *** LA COLECCION NO EXISTE. ***");
            return;
        }

        MostrarMenu.mostrarMenu();
    }

   
    // /**
    //  * Paso 4: Insertar Elemento
    //  * 
    //  * @throws XMLDBException
    //  */
    // private static void insertarElemento() throws XMLDBException {
    //     try {
    //         Class cl = Class.forName(driver); // Cargar del driver
    //         Database database = (Database) cl.newInstance(); // Instancia de la BD
    //         DatabaseManager.registerDatabase(database); // Registro del driver
    //     } catch (Exception e) {
    //         System.out.println("Error al inicializar la BD eXist");
    //         e.printStackTrace();
    //     }

    //     col = DatabaseManager.getCollection(URI + "cataleg", usu, usuPwd); // Conexión con nuestra colección

    //     if (col == null) {
    //         System.out.println(" *** LA COLECCIÓN NO EXISTE. ***");
    //         return;
    //     }

    //     XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
    //     ResourceSet result = servicio.query("for $a in //titol return $a");
    //     // Exemple de com mostrar els resultats de la nostra consulta.
    //     ResourceIterator i;
    //     i = result.getIterator();
    //     if (!i.hasMoreResources())
    //         System.out.println(" LA CONSULTA NO DEVUELVE NADA.");

    //     // while (i.hasMoreResources()) {
    //     // Resource r = i.nextResource();
    //     // System.out.println((String) r.getContent());
    //     // }
    //     col.close(); // Cerramos coleccion
    // }

    
    // /**
    //  * Paso 6: Mostrar Cantidad de CD
    //  * 
    //  * @throws XMLDBException
    //  */
    // private static void mostrarCantidadCD() throws XMLDBException {
    //     try {
    //         Class cl = Class.forName(driver); // Cargar del driver
    //         Database database = (Database) cl.newInstance(); // Instancia de la BD
    //         DatabaseManager.registerDatabase(database); // Registro del driver
    //     } catch (Exception e) {
    //         System.out.println("Error al inicializar la BD eXist");
    //         e.printStackTrace();
    //     }

    //     col = DatabaseManager.getCollection(URI, usu, usuPwd); // Conexión con nuestra colección

    //     if (col == null) {
    //         System.out.println(" *** LA COLECCIÓN NO EXISTE. ***");
    //         return;
    //     }
    // }

    // /**
    //  * Paso 7: Borrar un CD
    //  * 
    //  * @throws XMLDBException
    //  */
    // private static void borrarCD() throws XMLDBException {
    //     try {
    //         Class cl = Class.forName(driver); // Cargar del driver
    //         Database database = (Database) cl.newInstance(); // Instancia de la BD
    //         DatabaseManager.registerDatabase(database); // Registro del driver
    //     } catch (Exception e) {
    //         System.out.println("Error al inicializar la BD eXist");
    //         e.printStackTrace();
    //     }

    //     col = DatabaseManager.getCollection(URI, usu, usuPwd); // Conexión con nuestra colección

    //     if (col == null) {
    //         System.out.println(" *** LA COLECCIÓN NO EXISTE. ***");
    //         return;
    //     }
    // }

    // /**
    //  * Paso 8: Mostrar registros antiguos a una fecha
    //  * 
    //  * @throws XMLDBException
    //  */
    // private static void mostrarRegistrosAntiguos() throws XMLDBException {
    //     try {
    //         Class cl = Class.forName(driver); // Cargar del driver
    //         Database database = (Database) cl.newInstance(); // Instancia de la BD
    //         DatabaseManager.registerDatabase(database); // Registro del driver
    //     } catch (Exception e) {
    //         System.out.println("Error al inicializar la BD eXist");
    //         e.printStackTrace();
    //     }

    //     col = DatabaseManager.getCollection(URI, usu, usuPwd); // Conexión con nuestra colección

    //     if (col == null) {
    //         System.out.println(" *** LA COLECCIÓN NO EXISTE. ***");
    //         return;
    //     }
    // }

}
