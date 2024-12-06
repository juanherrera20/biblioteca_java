package Modelo;

/*  Para la creación de la base de datos se uso el gestor de PHPMyAdmin de Xampp
    se uso el siguiente modelo y sentencias para crear la tabla con algunos valores:

    -- Crear la tabla Libros
    CREATE TABLE Libros (
    id_libro INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    genero VARCHAR(50) NOT NULL,
    anio_publicacion INT NOT NULL,
    disponible BOOLEAN NOT NULL
    );

    -- Insertar algunos datos de ejemplo
    INSERT INTO Libros (titulo, autor, genero, anio_publicacion, disponible)
    VALUES
    ('Cien Años de Soledad', 'Gabriel García Márquez', 'Realismo Mágico', 1967, TRUE),
    ('1984', 'George Orwell', 'Distopía', 1949, FALSE),
    ('El Principito', 'Antoine de Saint-Exupéry', 'Ficción', 1943, TRUE),
    ('Don Quijote de la Mancha', 'Miguel de Cervantes', 'Clásico', 1605, TRUE);
*/

public class Libro {
    //Defino los atributos
    private int id;
    private String titulo;
    private String autor;
    private String genero;
    private int anio_publicacion;
    private boolean disponible;  //Si el libro esta disponible para prestamo o no

    //Constructor vacio
    public Libro (){
    }

    //Constructor para inicializar un objeto
    public Libro(int id, String titulo, String autor, String genero, int anio_publicacion, boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.anio_publicacion = anio_publicacion;
        this.disponible = disponible;
    }

    //Creación de los Getters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public int getAnio_publicacion() {
        return anio_publicacion;
    }

    public boolean getDisponible() {
        return disponible;
    }

    //Creación de los Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setAnio_publicacion(int anio_publicacion) {
        this.anio_publicacion = anio_publicacion;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }



}
