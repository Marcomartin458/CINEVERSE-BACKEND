package dam.code.migaleria.controller;

import dam.code.migaleria.repository.FavoritoRepository;
import dam.code.migaleria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class HealthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FavoritoRepository favoritoRepository;

    /**
     * GET /api/health - Verificar estado de la aplicación y BD
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> status = new HashMap<>();

        try {
            // Verificar conexión a BD
            long usuariosCount = usuarioRepository.count();
            long favoritosCount = favoritoRepository.count();

            status.put("status", "OK");
            status.put("database", "connected");
            status.put("usuarios_count", usuariosCount);
            status.put("favoritos_count", favoritosCount);

            return ResponseEntity.ok(status);
        } catch (Exception e) {
            status.put("status", "ERROR");
            status.put("database", "disconnected");
            status.put("error", e.getMessage());

            return ResponseEntity.internalServerError().body(status);
        }
    }
}
