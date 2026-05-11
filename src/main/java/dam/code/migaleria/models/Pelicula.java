package dam.code.migaleria.models;

import jakarta.persistence.*;

@Entity
@Table(name = "peliculas")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String director;

    @Enumerated(EnumType.STRING)   // guarda "ACCION" en vez de 0, 1, 2...
    @Column(nullable = false)
    private Genero genero;

    @Column(length = 1000)
    private String descripcion;

    private String imagen;

    public enum Genero {
        ACCION, COMEDIA, DRAMA, FANTASIA, TERROR, CIENCIA_FICCION, AVENTURA
    }

    public Pelicula() {}

    // Constructor SIN id — para el DataLoader (JPA genera el id solo)
    public Pelicula(String titulo, String director, Genero genero,
                    String descripcion, String imagen) {
        this.titulo      = titulo;
        this.director    = director;
        this.genero      = genero;
        this.descripcion = descripcion;
        this.imagen      = imagen;
    }

    public int    getId()                  { return id; }
    public void   setId(int id)            { this.id = id; }
    public String getTitulo()              { return titulo; }
    public void   setTitulo(String t)      { this.titulo = t; }
    public String getDirector()            { return director; }
    public void   setDirector(String d)    { this.director = d; }
    public Genero getGenero()              { return genero; }
    public void   setGenero(Genero g)      { this.genero = g; }
    public String getDescripcion()         { return descripcion; }
    public void   setDescripcion(String d) { this.descripcion = d; }
    public String getImagen()              { return imagen; }
    public void   setImagen(String i)      { this.imagen = i; }
}