package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {

    //Creo un elemento tipo Connection
    private static Connection conn = null;

    private static final String DATABASE = "biblioteca_java";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE;

    // Método para retornar la conexion a la base de datos
    public static Connection conectar() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);  //Metodo simplificado
            System.out.println("Base de datos conectada con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error al momento de conectarse a la base de datos.");
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conn; // Devuelve la conexión
    }
}
