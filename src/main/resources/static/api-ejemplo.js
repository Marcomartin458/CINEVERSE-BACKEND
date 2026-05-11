/**
 * Ejemplo de cómo usar la API desde JavaScript
 * miGaleria - API REST
 */

const API_BASE = 'http://localhost:8080/api';

// ============================================
// GESTIÓN DE USUARIOS (Registro/Login)
// ============================================

/**
 * Registrar nuevo usuario
 */
async function registrarUsuario(nombre, email, password, telefono = '', profileImage = '') {
    try {
        const response = await fetch(`${API_BASE}/usuarios/registro`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                nombre,
                email,
                password,
                telefono,
                profileImage
            })
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error);
        }

        const usuario = await response.json();
        // Guardar ID en localStorage
        localStorage.setItem('userId', usuario.id);
        localStorage.setItem('userName', usuario.nombre);
        console.log('Usuario registrado:', usuario);
        return usuario;
    } catch (error) {
        console.error('Error al registrar:', error);
        throw error;
    }
}

/**
 * Login de usuario
 */
async function loginUsuario(email, password) {
    try {
        const response = await fetch(`${API_BASE}/usuarios/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ email, password })
        });

        if (!response.ok) {
            throw new Error('Credenciales inválidas');
        }

        const usuario = await response.json();
        // Guardar ID en localStorage
        localStorage.setItem('userId', usuario.id);
        localStorage.setItem('userName', usuario.nombre);
        console.log('Login exitoso:', usuario);
        return usuario;
    } catch (error) {
        console.error('Error al login:', error);
        throw error;
    }
}

/**
 * Obtener usuario actual (si está en localStorage)
 */
function obtenerUsuarioActual() {
    const userId = localStorage.getItem('userId');
    const userName = localStorage.getItem('userName');
    
    if (!userId) {
        throw new Error('No hay usuario registrado. Debes registrarte o iniciar sesión.');
    }
    
    return { id: parseInt(userId), nombre: userName };
}

/**
 * Logout
 */
function logoutUsuario() {
    localStorage.removeItem('userId');
    localStorage.removeItem('userName');
    console.log('Sesión cerrada');
}

// ============================================
// GESTIÓN DE PELÍCULAS
// ============================================

/**
 * Obtener lista de películas
 */
async function obtenerPeliculas() {
    try {
        const response = await fetch(`${API_BASE}/peliculas`);
        const peliculas = await response.json();
        console.log('Películas cargadas:', peliculas);
        return peliculas;
    } catch (error) {
        console.error('Error al obtener películas:', error);
        throw error;
    }
}

/**
 * Agregar película nueva
 * REQUIERE estar logueado
 */
async function agregarPelicula(id, titulo, director, genero, descripcion, imagen) {
    try {
        // Verificar que el usuario está logueado
        const usuario = obtenerUsuarioActual();
        
        const response = await fetch(`${API_BASE}/peliculas`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                id,
                titulo,
                director,
                genero,
                descripcion,
                imagen
            })
        });

        if (!response.ok) {
            throw new Error('Error al agregar película');
        }

        const pelicula = await response.json();
        console.log('Película agregada:', pelicula);
        return pelicula;
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

/**
 * Actualizar película
 * REQUIERE estar logueado
 */
async function actualizarPelicula(id, titulo, director, genero, descripcion, imagen) {
    try {
        // Verificar que el usuario está logueado
        const usuario = obtenerUsuarioActual();
        
        const response = await fetch(`${API_BASE}/peliculas/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                id,
                titulo,
                director,
                genero,
                descripcion,
                imagen
            })
        });

        if (!response.ok) {
            throw new Error('Error al actualizar película');
        }

        const pelicula = await response.json();
        console.log('Película actualizada:', pelicula);
        return pelicula;
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

/**
 * Eliminar película
 * REQUIERE estar logueado
 */
async function eliminarPelicula(id) {
    try {
        // Verificar que el usuario está logueado
        const usuario = obtenerUsuarioActual();
        
        const response = await fetch(`${API_BASE}/peliculas/${id}`, {
            method: 'DELETE'
        });

        if (!response.ok) {
            throw new Error('Error al eliminar película');
        }

        console.log('Película eliminada:', id);
        return true;
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

// ============================================
// GESTIÓN DE FAVORITOS
// ============================================

/**
 * Verificar si usuario está logueado
 */
function verificarLogin() {
    try {
        obtenerUsuarioActual();
        return true;
    } catch {
        return false;
    }
}

/**
 * Obtener mis películas favoritas de la BD
 * REQUIERE estar logueado
 */
async function misFavoritos() {
    try {
        const usuario = obtenerUsuarioActual();
        const response = await fetch(`${API_BASE}/favoritos/usuario/${usuario.id}`);
        const favoritos = await response.json();
        console.log('Mis favoritos desde BD:', favoritos);
        return favoritos.map(fav => fav.peliculaId); // Retorna solo los IDs
    } catch (error) {
        console.error('Error al obtener favoritos:', error);
        throw error;
    }
}

/**
 * Agregar película a favoritos en la BD
 * REQUIERE estar logueado
 */
async function agregarAFavoritos(peliculaId) {
    try {
        const usuario = obtenerUsuarioActual();

        const response = await fetch(`${API_BASE}/favoritos`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                usuarioId: usuario.id,
                peliculaId: peliculaId
            })
        });

        if (!response.ok) {
            if (response.status === 409) {
                throw new Error('La película ya está en favoritos');
            }
            throw new Error('Error al agregar a favoritos');
        }

        const favorito = await response.json();
        console.log('Agregado a favoritos en BD:', favorito);
        return favorito;
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

/**
 * Eliminar película de favoritos de la BD
 * REQUIERE estar logueado
 */
async function eliminarDeFavoritos(peliculaId) {
    try {
        const usuario = obtenerUsuarioActual();

        const response = await fetch(`${API_BASE}/favoritos/${usuario.id}/${peliculaId}`, {
            method: 'DELETE'
        });

        if (!response.ok) {
            throw new Error('Error al eliminar de favoritos');
        }

        console.log('Eliminado de favoritos en BD:', peliculaId);
        return true;
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

// ============================================
// EJEMPLO DE USO COMPLETO
// ============================================

/*
// 1. Registrar usuario
const usuario1 = await registrarUsuario(
    'Juan García',
    'juan@example.com',
    'password123',
    '123456789',
    'https://via.placeholder.com/150?text=Juan'
);

// 2. Login con otro usuario
const usuario2 = await loginUsuario('juan@example.com', 'password123');

// 3. Obtener películas (sin login)
const peliculas = await obtenerPeliculas();

// 4. Agregar película (REQUIERE LOGIN)
const nuevaPelicula = await agregarPelicula(
    22, 
    'Mi Película', 
    'Mi Director', 
    'ACCION', 
    'Descripción', 
    'https://...'
);

// 5. Agregar a favoritos (REQUIERE LOGIN)
await agregarAFavoritos(1); // película con id 1

// 6. Ver mis favoritos
const misIds = await misFavoritos(); // Retorna [1, 5, 7, ...]

// 7. Eliminar de favoritos
await eliminarDeFavoritos(1);

// 8. Logout
logoutUsuario();
*/



