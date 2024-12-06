package Vista;

import Controlador.LibroDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;


/*
    Esta interfaz se hizo manualmente con ayuda de herramientas de diseño, debido a que el IDE utilizado para el
    desarrollo de este proyecto fue Intellij IDEA no cuenta con una herramienta visual potente para crear una interfaz
    java grafica de la forma que permite NetBeans.
*/

public class Ventana extends JFrame {

    // Componentes de la Interfaz
    private JTextField txtTitulo, txtAutor, txtGenero, txtAnio, txtDisponible;
    private JTable tablaLibros;
    private DefaultTableModel modeloTabla;
    private JButton btnAgregar, btnEditar, btnEliminar, btnCargar;

    // Lógica para el CRUD
    private final LibroDAO libroDAO;

    public Ventana() {
        libroDAO = new LibroDAO();

        // Configuración de la ventana
        setTitle("Biblioteca - Gestión de Libros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Aplicar un Look and Feel moderno
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Panel superior con formulario
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelFormulario.setBackground(new Color(240, 240, 240));

        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtTitulo = new JTextField();
        panelFormulario.add(lblTitulo);
        panelFormulario.add(txtTitulo);

        JLabel lblAutor = new JLabel("Autor:");
        lblAutor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtAutor = new JTextField();
        panelFormulario.add(lblAutor);
        panelFormulario.add(txtAutor);

        JLabel lblGenero = new JLabel("Género:");
        lblGenero.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtGenero = new JTextField();
        panelFormulario.add(lblGenero);
        panelFormulario.add(txtGenero);

        JLabel lblAnio = new JLabel("Año de Publicación:");
        lblAnio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtAnio = new JTextField();
        panelFormulario.add(lblAnio);
        panelFormulario.add(txtAnio);

        JLabel lblDisponible = new JLabel("Disponible (true/false):");
        lblDisponible.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtDisponible = new JTextField();
        panelFormulario.add(lblDisponible);
        panelFormulario.add(txtDisponible);

        add(panelFormulario, BorderLayout.NORTH);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(new Color(230, 230, 230));
        btnAgregar = crearBoton("Agregar");
        btnEditar = crearBoton("Editar");
        btnEliminar = crearBoton("Eliminar");
        btnCargar = crearBoton("Listar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCargar);

        add(panelBotones, BorderLayout.SOUTH);

        // Panel para la tabla y el mensaje
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Mensaje sobre la tabla
        JLabel lblInstrucciones = new JLabel("Para editar o eliminar, se debe seleccionar el registro en la tabla.");
        lblInstrucciones.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblInstrucciones.setHorizontalAlignment(SwingConstants.CENTER);
        lblInstrucciones.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        panelCentral.add(lblInstrucciones, BorderLayout.NORTH);

        // Tabla para mostrar los libros
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Título", "Autor", "Género", "Año", "Disponible"}, 0);
        tablaLibros = new JTable(modeloTabla);
        tablaLibros.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaLibros.setRowHeight(25);
        tablaLibros.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaLibros.getTableHeader().setBackground(new Color(200, 200, 200));
        JScrollPane scrollTabla = new JScrollPane(tablaLibros);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelCentral.add(scrollTabla, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);

        // Eventos de los botones
        btnAgregar.addActionListener(this::agregarLibro);
        btnEditar.addActionListener(this::editarLibro);
        btnEliminar.addActionListener(this::eliminarLibro);
        btnCargar.addActionListener(this::cargarLibros);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBackground(new Color(100, 149, 237));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return boton;
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

            // Forzar la confirmación de la edición en la tabla
            if (tablaLibros.isEditing()) {
                tablaLibros.getCellEditor().stopCellEditing();
            }

            try {
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
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos. Verifique los campos.", "Error", JOptionPane.ERROR_MESSAGE);
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
