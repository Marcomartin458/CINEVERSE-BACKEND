package dam.code.migaleria.models;

import jakarta.persistence.*;

@Entity
@Table(name = "favoritos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usuario_id", "pelicula_id"})
})
public class Favorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "usuario_id", nullable = false)
    private int usuarioId;

    @Column(name = "pelicula_id", nullable = false)
    private int peliculaId;

    public Favorito() {
    }

    public Favorito(int usuarioId, int peliculaId) {
        this.usuarioId = usuarioId;
        this.peliculaId = peliculaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(int peliculaId) {
        this.peliculaId = peliculaId;
    }
}

