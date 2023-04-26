import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Scanner;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

// Conexion BBDD
import org.xmldb.api.*;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;

public class Programa {
    public static String nombreColeccion;
    public static String nombreArchivo;

    public static void main(String[] args) throws XMLDBException {

        mostrarMenu();
    }

    private static void mostrarMenu() throws XMLDBException {
        Scanner teclado = new Scanner(System.in);

        int opcion;
        boolean opcionSeleccionada = false;

        while (!opcionSeleccionada) {
            // Texto
            System.out.println("Selecciona una opción:");
            System.out.println("| 1 - Crear Archivo XML |");
            System.out.println("| 2 - Crear Colección |");
            System.out.println("| 3 - Subir Archivo |");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    crearXML();
                    break;

                case 2:
                    opcionSeleccionada = true;
                    System.out.println("Nombre de la Colección a crear:");
                    nombreColeccion = teclado.nextLine();
                    crearColeccion(nombreColeccion);
                    break;

                case 3:
                    opcionSeleccionada = true;
                    System.out.println("Nombre de la colección:");
                    nombreColeccion = teclado.nextLine();
                    System.out.println("Nombre de archivo:");
                    nombreArchivo = teclado.nextLine();
                    pujarDocument(nombreColeccion, nombreArchivo);
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * Paso 1: Crear un archivo XML
     */
    public static void crearXML() {
    try {
        // Crear el objeto XMLOutputFactory
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();

        // Crear el archivo XML
        File archivo = new File("cataleg.xml");

        // Crear el objeto XMLStreamWriter
        XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileWriter(archivo));

        // Escribir el encabezado del documento XML
        xmlStreamWriter.writeProcessingInstruction("xml", "version=\"1.0\" encoding=\"UTF-8\"");

        // Escribir el elemento raíz
        xmlStreamWriter.writeStartElement("cataleg");

        // Escribir el elemento CD con sus atributos y elementos hijos
        xmlStreamWriter.writeStartElement("cd");
        xmlStreamWriter.writeAttribute("id", "1");
        xmlStreamWriter.writeStartElement("titol");
        xmlStreamWriter.writeCharacters("Tinta roja");
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeStartElement("artista");
        xmlStreamWriter.writeCharacters("Andres Calamaro");
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeStartElement("pais");
        xmlStreamWriter.writeCharacters("Argentina");
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeStartElement("preu");
        xmlStreamWriter.writeCharacters("10.90");
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeStartElement("any");
        xmlStreamWriter.writeCharacters("2006");
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeEndElement();

        // Cerrar el elemento raíz
        xmlStreamWriter.writeEndElement();

        // Cerrar el documento XML
        xmlStreamWriter.writeEndDocument();

        // Cerrar el objeto XMLStreamWriter
        xmlStreamWriter.close();

        System.out.println("El archivo XML se ha creado correctamente.");

    } catch (XMLStreamException e) {
        System.out.println("Error al crear el archivo XML: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}


    /**
     * Paso 2: Crear Colección
     * 
     * @param nombreColeccion
     * @throws XMLDBException
     */
    public static void crearColeccion(String nombreColeccion) throws XMLDBException {
        String driver = "org.exist.xmldb.DatabaseImpl"; // Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/"; // URI colección
        String usu = "admin"; // Usuario
        String usuPwd = "Admin1234"; // Clave

        try {
            Class cl = Class.forName(driver); // Cargar del driver
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

        // Crear colección con el nombre dado
        try {
            CollectionManagementService mgtService = (CollectionManagementService) col
                    .getService("CollectionManagementService", "1.0");
            mgtService.createCollection(nombreColeccion);
            System.out.println("Colección creada correctamente");
        } catch (XMLDBException e) {
            System.out.println("Error al crear la colección " + nombreColeccion);
            e.printStackTrace();
        }

        col.close(); // Cerramos coleccion
    }

    /**
     * Paso 3: Subir fichero a colección
     */
    public static void pujarDocument(String nomColleccio, String nomFitxer) throws XMLDBException {
        String driver = "org.exist.xmldb.DatabaseImpl"; // Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/" + nomColleccio; // URI colección
        String usu = "admin"; // Usuario
        String usuPwd = "Admin1234"; // Clave

        try {
            Class cl = Class.forName(driver); // Cargar del driver
            Database database = (Database) cl.newInstance(); // Instancia de la BD
            DatabaseManager.registerDatabase(database); // Registro del driver
        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }

        col = DatabaseManager.getCollection(URI, usu, usuPwd); // Conexión con nuestra colección

        if (col == null) {
            System.out.println(" *** LA COLECCIÓN NO EXISTE. ***");
            return;
        }

        // Carga del fichero
        File file = new File(nomFitxer);
        if (!file.exists()) {
            System.out.println("El fichero no existe");
            return;
        }
        String filename = file.getName();
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo abrir el fichero: " + e.getMessage());
            return;
        }

        // Creación del recurso
        Resource res = col.createResource(filename, "XMLResource");
        res.setContent(inputStream);

        // Almacenamiento del recurso en la colección
        col.storeResource(res);

        // Cierre de la colección
        col.close();
        System.out.println("Fichero "+nomFitxer + " subido correctamente a " + nomColleccio);
    }

}
