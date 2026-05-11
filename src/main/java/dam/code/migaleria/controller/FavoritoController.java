package dam.code.migaleria.controller;

import dam.code.migaleria.models.Favorito;
import dam.code.migaleria.repository.FavoritoRepository;
import dam.code.migaleria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * GET /api/favoritos/usuario/{usuarioId} - Obtener favoritos del usuario
     */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Object> obtenerFavoritos(@PathVariable int usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no existe");
        }
        
        List<Favorito> favoritos = favoritoRepository.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(favoritos);
    }

    /**
     * POST /api/favoritos - Agregar película a favoritos
     */
    @PostMapping
    public ResponseEntity<Object> agregarFavorito(@RequestBody FavoritoRequest request) {
        if (!usuarioRepository.existsById(request.getUsuarioId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no existe");
        }

        if (favoritoRepository.existsByUsuarioIdAndPeliculaId(request.getUsuarioId(), request.getPeliculaId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("La película ya está en favoritos");
        }

        Favorito favorito = new Favorito(request.getUsuarioId(), request.getPeliculaId());
        Favorito guardado = favoritoRepository.save(favorito);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    /**
     * DELETE /api/favoritos/{usuarioId}/{peliculaId} - Eliminar de favoritos
     */
    @DeleteMapping("/{usuarioId}/{peliculaId}")
    public ResponseEntity<Object> eliminarFavorito(@PathVariable int usuarioId, @PathVariable int peliculaId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no existe");
        }

        if (!favoritoRepository.existsByUsuarioIdAndPeliculaId(usuarioId, peliculaId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Favorito no existe");
        }

        favoritoRepository.deleteByUsuarioIdAndPeliculaId(usuarioId, peliculaId);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/favoritos - Listar todos los favoritos
     */
    @GetMapping
    public ResponseEntity<List<Favorito>> listarFavoritos() {
        List<Favorito> favoritos = favoritoRepository.findAll();
        return ResponseEntity.ok(favoritos);
    }

    /**
     * Clase para recibir datos de favorito
     */
    public static class FavoritoRequest {
        private int usuarioId;
        private int peliculaId;

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
}

