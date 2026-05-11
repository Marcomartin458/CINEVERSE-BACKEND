package dam.code.migaleria.repository;

import dam.code.migaleria.models.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
    List<Pelicula> findByTituloContainingIgnoreCase(String titulo);
    List<Pelicula> findByGenero(Pelicula.Genero genero);
    List<Pelicula> findByDirectorContainingIgnoreCase(String director);
}


