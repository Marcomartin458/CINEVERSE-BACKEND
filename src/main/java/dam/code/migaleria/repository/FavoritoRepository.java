package dam.code.migaleria.repository;

import dam.code.migaleria.models.Favorito;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
    List<Favorito> findByUsuarioId(int usuarioId);
    Optional<Favorito> findByUsuarioIdAndPeliculaId(int usuarioId, int peliculaId);
    boolean existsByUsuarioIdAndPeliculaId(int usuarioId, int peliculaId);
    @Transactional
    void deleteByUsuarioIdAndPeliculaId(int usuarioId, int peliculaId);
}

