package Vista;

import Controlador.LibroDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class Ventana extends JFrame {

    // Componentes de la Interfaz
    private JTextField txtTitulo, txtAutor, txtGenero, txtAnio, txtDisponible;
    private JTable tablaLibros;
    private DefaultTableModel modeloTabla;
    private JButton btnAgregar, btnEditar, btnEliminar, btnCargar;

    // Lógica para el CRUD
    private LibroDAO libroDAO;

    public Ventana() {
        libroDAO = new LibroDAO();

        // Configuración de la ventana
        setTitle("Biblioteca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLayout(new BorderLayout());

        // Panel superior con formulario
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelFormulario.add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        panelFormulario.add(txtTitulo);

        panelFormulario.add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        panelFormulario.add(txtAutor);

        panelFormulario.add(new JLabel("Género:"));
        txtGenero = new JTextField();
        panelFormulario.add(txtGenero);

        panelFormulario.add(new JLabel("Año de Publicación:"));
        txtAnio = new JTextField();
        panelFormulario.add(txtAnio);

        panelFormulario.add(new JLabel("Disponible (true/false):"));
        txtDisponible = new JTextField();
        panelFormulario.add(txtDisponible);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnCargar = new JButton("Listar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCargar);

        panelFormulario.add(panelBotones);

        add(panelFormulario, BorderLayout.NORTH);

        // Tabla para mostrar los libros
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Título", "Autor", "Género", "Año", "Disponible"}, 0);
        tablaLibros = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaLibros);

        add(scrollTabla, BorderLayout.CENTER);

        // Eventos de los botones
        btnAgregar.addActionListener(this::agregarLibro);
        btnEditar.addActionListener(this::editarLibro);
        btnEliminar.addActionListener(this::eliminarLibro);
        btnCargar.addActionListener(this::cargarLibros);

        // Mostrar la ventana
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método para cargar los libros en la tabla
    private void cargarLibros(ActionEvent e) {
        modeloTabla.setRowCount(0); // Limpia la tabla
        List<String[]> libros = libroDAO.obtenerLibros();
        for (String[] libro : libros) {
            modeloTabla.addRow(libro);
        }
    }

    // Método para agregar un libro
    private void agregarLibro(ActionEvent e) {
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String genero = txtGenero.getText();
        int anio = Integer.parseInt(txtAnio.getText());
        boolean disponible = Boolean.parseBoolean(txtDisponible.getText());

        if (libroDAO.agregarLibro(titulo, autor, genero, anio, disponible)) {
            JOptionPane.showMessageDialog(this, "Libro agregado exitosamente.");
            cargarLibros(null);
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar el libro.");
        }
    }

    // Método para editar un libro
    private void editarLibro(ActionEvent e) {
        int filaSeleccionada = tablaLibros.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int id = Integer.parseInt((String) modeloTabla.getValueAt(filaSeleccionada, 0));
            String titulo = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
            String autor = (String) modeloTabla.getValueAt(filaSeleccionada, 2);
            String genero = (String) modeloTabla.getValueAt(filaSeleccionada, 3);
            int anio = Integer.parseInt((String) modeloTabla.getValueAt(filaSeleccionada, 4));
            boolean disponible = Boolean.parseBoolean((String) modeloTabla.getValueAt(filaSeleccionada, 5));

            if (libroDAO.editarLibro(id, titulo, autor, genero, anio, disponible)) {
                JOptionPane.showMessageDialog(this, "Libro actualizado exitosamente.");
                cargarLibros(null);
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el libro.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un libro para editar.");
        }
    }

    // Método para eliminar un libro
    private void eliminarLibro(ActionEvent e) {
        int filaSeleccionada = tablaLibros.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int id = Integer.parseInt((String) modeloTabla.getValueAt(filaSeleccionada, 0));
            if (libroDAO.eliminarLibro(id)) {
                JOptionPane.showMessageDialog(this, "Libro eliminado exitosamente.");
                cargarLibros(null);
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el libro.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un libro para eliminar.");
        }
    }

}
