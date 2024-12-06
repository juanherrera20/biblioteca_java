import Controlador.ConexionBD;
import Vista.Ventana;


//Aquí corro todo el codigo
public class Main {
    public static void main(String[] args) {
        //Realizo la conexion a base de datos
        System.out.println("Inicia el programa");
        ConexionBD conexion = new ConexionBD();
        conexion.conectar();

        //instacio y activo la vista
        Ventana interfaz = new Ventana();
        interfaz.setVisible(true);
    }
}