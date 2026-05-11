package dam.code.migaleria.controller;

import dam.code.migaleria.models.Pelicula;
import dam.code.migaleria.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaRepository peliculaRepository;

    /** GET /api/peliculas */
    @GetMapping
    public List<Pelicula> listarPeliculas() {
        return peliculaRepository.findAll();
    }

    /** GET /api/peliculas/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerPelicula(@PathVariable int id) {
        Optional<Pelicula> p = peliculaRepository.findById(id);
        return p.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Película no encontrada"));
    }

    /** GET /api/peliculas/genero/{genero} — filtro por género desde back si lo necesitas */
    @GetMapping("/genero/{genero}")
    public ResponseEntity<Object> listarPorGenero(@PathVariable String genero) {
        try {
            Pelicula.Genero g = Pelicula.Genero.valueOf(genero.toUpperCase());
            return ResponseEntity.ok(peliculaRepository.findByGenero(g));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Género no válido. Usa: ACCION, COMEDIA, DRAMA, FANTASIA, TERROR, CIENCIA_FICCION, AVENTURA");
        }
    }

    /** POST /api/peliculas */
    @PostMapping
    public ResponseEntity<Object> agregarPelicula(@RequestBody Pelicula pelicula) {
        // Reseteamos el ID para que JPA asigne uno nuevo automáticamente
        pelicula.setId(0);

        // Validación básica del género — si el JSON trae un valor inválido
        // Jackson ya lanzará un error antes de llegar aquí, pero por si acaso:
        if (pelicula.getGenero() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El género es obligatorio y debe ser uno de los valores predefinidos.");
        }

        Pelicula guardada = peliculaRepository.save(pelicula);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }

    /** PUT /api/peliculas/{id} */
    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarPelicula(@PathVariable int id,
                                                     @RequestBody Pelicula pelicula) {
        if (!peliculaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Película no encontrada");
        }
        pelicula.setId(id);
        return ResponseEntity.ok(peliculaRepository.save(pelicula));
    }

    /** DELETE /api/peliculas/{id} */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarPelicula(@PathVariable int id) {
        if (!peliculaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Película no encontrada");
        }
        peliculaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}