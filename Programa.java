import java.io.File;
import java.io.FileWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

// Conexion BBDD
import org.xmldb.api.*;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;

public class Programa {
    public static void main(String[] args) {
        
        try {
            crearXML();
            conectarExistDB();            
        } catch (XMLDBException e) {
            e.printStackTrace();
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

    public static void conectarExistDB() throws XMLDBException {
        String driver = "org.exist.xmldb.DatabaseImpl"; // Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/La vostra col·leccio"; // URI colección
        String usu = "admin"; // Usuario
        String usuPwd = "admin"; // Clave

        try {
            Class cl = Class.forName(driver); // Cargar del driver
            Database database = (Database) cl.newInstance(); // Instancia de la BD
            DatabaseManager.registerDatabase(database); // Registro del driver

        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }

        col = DatabaseManager.getCollection(URI, usu, usuPwd); // Carreguem la nostra col.lecció

        if (col == null)
            System.out.println(" *** LA COLECCION NO EXISTE. ***");

        XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");

        // Ara farem i executarem la consulta xquery sobre el nostre fitxer
        ResourceSet result = servicio.query("for $a in //title/text() return $a");

        // recorrer los datos del recurso.
        ResourceIterator i;
        i = result.getIterator();
        if (!i.hasMoreResources())
            System.out.println(" LA CONSULTA NO DEVUELVE NADA.");

        while (i.hasMoreResources()) {
            Resource r = i.nextResource();
            System.out.println((String) r.getContent());
        }
        col.close(); // Cerramos coleccion
    }

}
