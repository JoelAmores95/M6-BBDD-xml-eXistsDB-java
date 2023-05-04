import java.util.Scanner;

import org.xmldb.api.base.XMLDBException;

public class MostrarMenu {
    public static void mostrarMenu() throws XMLDBException {
        Scanner teclado = new Scanner(System.in);

        int opcion;
        boolean opcionSeleccionada = false;

        while (!opcionSeleccionada) {
            // Texto
            System.out.println("Selecciona una opción:");
            System.out.println("| 1 - Crear Archivo XML (2p)    |");
            System.out.println("| 2 - Crear Colección (1p)      |");
            System.out.println("| 3 - Subir Archivo (1p)        |");
            System.out.println("| 4 - Insertar elemento (2p)    NO FUNCIONA|");
            // System.out.println("| 5 - Modificar Precio (1p)     |");
            // System.out.println("| 6 - Mostrar Cantidad CD (1p)  |");
            // System.out.println("| 7 - Borrar CD (1p)            |");
            // System.out.println("| 8 - Mostrar registros anteriores a año (1p)  |");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    Opcion1CrearXML.crearXML();
                    break;

                case 2:
                    Opcion2CrearColeccion.crearColeccion();
                    break;

                case 3:
                    Opcion3SubirFichero.pujarDocument();
                    break;
                case 4:
                    Opcion4InsertarElemento.insertarElemento();
                    break;

                // case 5:
                //     System.out.println("Nombre del CD:");
                //     String nombreCD = teclado.nextLine();
                //     System.out.println("Nuevo precio:");
                //     double precioNuevo = teclado.nextDouble();
                //     modificarPrecio(nombreCD, precioNuevo);
                //     break;
                // case 6:
                //     mostrarCantidadCD();
                //     break;
                // case 7:
                //     borrarCD();
                //     break;
                // case 8:
                //     mostrarRegistrosAntiguos();
                //     break;
                default:
                    break;
            }
        }

    }

}
