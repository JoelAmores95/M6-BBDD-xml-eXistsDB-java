import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

// Conexion BBDD
import org.xmldb.api.*;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;

public class Programa {
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
            

            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    crearXML();
                    break;

                case 2:
                    opcionSeleccionada = true;
                    System.out.println("Nombre de la Colección a crear:");
                    teclado.nextLine();
                    String nombreColeccion = teclado.nextLine();
                    crearColeccion(nombreColeccion);
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
            xmlStreamWriter.writeStartDocument();

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

    public static void crearColeccion(String nombreColeccion) throws XMLDBException {
        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI="xmldb:exist://localhost:8080/exist/xmlrpc/db/"; //URI colección
        String usu="admin"; //Usuario
        String usuPwd="Admin1234"; //Clave
        
        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD 
            DatabaseManager.registerDatabase(database); //Registro del driver
            
        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace(); 
        }
        
        col = DatabaseManager.getCollection(URI, usu, usuPwd); //Connexi� amb la nostra col�lecci�
       
        if(col == null) {
            System.out.println(" *** LA COLECCION NO EXISTE. ***");
            return;
        }
    
        // Crear colección con el nombre dado
        try {
            CollectionManagementService mgtService = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");
            mgtService.createCollection(nombreColeccion);
            System.out.println("Colección creada correctamente");
        } catch (XMLDBException e) {
            System.out.println("Error al crear la colección " + nombreColeccion);
            e.printStackTrace();
        }
        
        col.close(); //Cerramos coleccion
    }
    
}
