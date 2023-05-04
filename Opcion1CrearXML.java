import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Opcion1CrearXML {
    /**
     * Paso 1: Crear un archivo XML
     */
    public static void crearXML() {
        try {
            // Crear el objeto DocumentBuilderFactory
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    
            // Crear el archivo XML
            File archivo = new File("cataleg.xml");
    
            // Crear el objeto DocumentBuilder
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    
            // Crear el objeto Document
            Document document = documentBuilder.newDocument();
    
            // Crear el elemento raíz
            Element rootElement = document.createElement("cataleg");
            document.appendChild(rootElement);
    
            // Crear el elemento CD con sus atributos y elementos hijos
            Element cdElement = document.createElement("cd");
            cdElement.setAttribute("id", "1");
            rootElement.appendChild(cdElement);
    
            Element titolElement = document.createElement("titol");
            titolElement.setTextContent("Tinta roja");
            cdElement.appendChild(titolElement);
    
            Element artistaElement = document.createElement("artista");
            artistaElement.setTextContent("Andres Calamaro");
            cdElement.appendChild(artistaElement);
    
            Element paisElement = document.createElement("pais");
            paisElement.setTextContent("Argentina");
            cdElement.appendChild(paisElement);
    
            Element preuElement = document.createElement("preu");
            preuElement.setTextContent("10.90");
            cdElement.appendChild(preuElement);
    
            Element anyElement = document.createElement("any");
            anyElement.setTextContent("2006");
            cdElement.appendChild(anyElement);
    
            // Escribir el contenido del XML en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(archivo);
            transformer.transform(source, result);
    
            System.out.println("El archivo XML se ha creado correctamente.");
    
        } catch (ParserConfigurationException pce) {
            System.out.println("Error al crear el archivo XML: " + pce.getMessage());
        } catch (TransformerException tfe) {
            System.out.println("Error al crear el archivo XML: " + tfe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
