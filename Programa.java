
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
