package dam.code.migaleria.controller;

import dam.code.migaleria.models.Usuario;
import dam.code.migaleria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * POST /api/usuarios/registro - Registrar nuevo usuario
     */
    @PostMapping("/registro")
    public ResponseEntity<Object> registrar(@RequestBody Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El email ya existe");
        }

        Usuario nuevoUsuario = new Usuario(
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getTelefono(),
                usuario.getProfileImage()
        );

        Usuario guardado = usuarioRepository.save(nuevoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    /**
     * POST /api/usuarios/login - Login de usuario
     */
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(loginRequest.getEmail());

        if (usuario.isPresent() && usuario.get().getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok(usuario.get());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Email o contraseña incorrectos");
    }

    /**
     * GET /api/usuarios/{id} - Obtener usuario por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> obtener(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    }

    /**
     * Clase para login
     */
    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
