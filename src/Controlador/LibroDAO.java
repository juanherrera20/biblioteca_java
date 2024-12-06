package Controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LibroDAO {

    private final ConexionBD conexionBD = new ConexionBD(); // Instanciar la clase

    public List<String[]> obtenerLibros() {
        List<String[]> libros = new ArrayList<>();
        String query = "SELECT * FROM Libros";
        try (Connection conn = conexionBD.conectar(); // Llamada al método no estático
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                libros.add(new String[]{
                        String.valueOf(rs.getInt("id_libro")),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("genero"),
                        String.valueOf(rs.getInt("anio_publicacion")),
                        String.valueOf(rs.getBoolean("disponible"))
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

    public boolean agregarLibro(String titulo, String autor, String genero, int anioPublicacion, boolean disponible) {
        String query = "INSERT INTO Libros (titulo, autor, genero, anio_publicacion, disponible) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, titulo);
            pstmt.setString(2, autor);
            pstmt.setString(3, genero);
            pstmt.setInt(4, anioPublicacion);
            pstmt.setBoolean(5, disponible);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean editarLibro(int idLibro, String titulo, String autor, String genero, int anioPublicacion, boolean disponible) {
        String query = "UPDATE Libros SET titulo = ?, autor = ?, genero = ?, anio_publicacion = ?, disponible = ? WHERE id_libro = ?";
        try (Connection conn = conexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, titulo);
            pstmt.setString(2, autor);
            pstmt.setString(3, genero);
            pstmt.setInt(4, anioPublicacion);  // Cambiar a setInt
            pstmt.setBoolean(5, disponible);
            pstmt.setInt(6, idLibro);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean eliminarLibro(int idLibro) {
        String query = "DELETE FROM Libros WHERE id_libro = ?";
        try (Connection conn = conexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idLibro);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
