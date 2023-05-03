import java.io.File;
import org.xmldb.api.*;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;


public class ExeConecDB {




	

	// SOLO PARA CONSULTAR, NO EDITAR






	public static void main(String[] args) throws XMLDBException {
		
		String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
		Collection col = null; // Colecci�n
		String URI="xmldb:exist://localhost:8080/exist/xmlrpc/db/"; //URI colecci�n
		String usu="XXXXX"; //Usuario
		String usuPwd="XXXXXXX"; //Clave
		
		try {
			Class cl = Class.forName(driver); //Cargar del driver
			Database database = (Database) cl.newInstance(); //Instancia de la BD 
			DatabaseManager.registerDatabase(database); //Registro del driver
			
			} catch (Exception e) {
				System.out.println("Error al inicializar la BD eXist");
				e.printStackTrace(); }
		
	    col = DatabaseManager.getCollection(URI, usu, usuPwd); //Connexi� amb la nostra col�lecci�
	   
		if(col == null)
			System.out.println(" *** LA COLECCION NO EXISTE. ***");
		
		//Exemple de consulta XQuery

		XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
		
		//Ara farem i executarem la consulta xquery sobre el nostre fitxer
		ResourceSet result = servicio.query ("for $a in //title/text() return $a");
		
		// Exemple de com mostrar els resultats de la nostra consulta.
		ResourceIterator i;
		i = result.getIterator();
		if (!i.hasMoreResources())
			System.out.println(" LA CONSULTA NO DEVUELVE NADA."); 
		
		// while (i.hasMoreResources()) {
		// 	Resource r = i.nextResource();
		// 	System.out.println((String) r.getContent());
		// }
		col.close(); //Cerramos coleccion
	}
	// 
}
