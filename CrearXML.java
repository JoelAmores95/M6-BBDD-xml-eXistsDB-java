import java.io.File;
import java.io.FileWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class CrearXML {

    public static void main(String[] args) {
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
}