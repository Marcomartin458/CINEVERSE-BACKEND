# 📽️ miGaleria - Configuración Completada

## ✅ Lo que se ha hecho

### 1. **Base de Datos PostgreSQL**
- Tabla `usuarios` - para registro/login
- Tabla `favoritos` - para películas favoritas por usuario
- Tabla `peliculas` - EN MEMORIA (no en BD)
- Se crean automáticamente al iniciar la aplicación

### 2. **Cambios en la Aplicación**
- ❌ Se eliminaron los 5 usuarios precargados
- ✅ La aplicación inicia con 0 usuarios
- ✅ Los usuarios se deben registrar o hacer login
- ✅ Los favoritos requieren estar logueado

### 3. **Modelos (Entidades JPA)**
```
Usuario.java         - @Entity con anotaciones JPA
Favorito.java       - @Entity para la tabla favoritos
Pelicula.java       - POJO sin JPA (datos en memoria)
```

### 4. **Repositorios JPA**
```
UsuarioRepository   - findByEmail(), existsByEmail()
FavoritoRepository  - búsqueda por usuario y película
```

### 5. **Controladores REST**
```
UsuarioController       - /api/usuarios/registro, /login
FavoritoController      - /api/favoritos (CRUD)
PeliculaController      - /api/peliculas (igual que antes, ahora con ruta /api)
```

## 🔧 PASOS PARA EMPEZAR

### Paso 1: Actualizar contraseña PostgreSQL
Edita `src/main/resources/application.properties`:

```properties
spring.datasource.password=TU_CONTRASEÑA_POSTGRE_AQUI
```

### Paso 2: Iniciar la aplicación
```bash
mvn spring-boot:run
```

Las tablas se crean automáticamente. ¡Listo!

### Paso 3: Usa JavaScript para llamar a los endpoints
Mira el archivo `src/main/resources/static/api-ejemplo.js` para ver ejemplos de cómo usar la API.

## 📋 Endpoints principales

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST   | `/api/usuarios/registro` | Registrar usuario |
| POST   | `/api/usuarios/login` | Login usuario |
| GET    | `/api/usuarios/{id}` | Obtener usuario |
| GET    | `/api/peliculas` | Listar películas |
| POST   | `/api/favoritos` | Agregar a favoritos ⚠️ Requiere login |
| GET    | `/api/favoritos/usuario/{id}` | Mis favoritos ⚠️ Requiere login |
| DELETE | `/api/favoritos/{userId}/{peliculaId}` | Eliminar de favoritos ⚠️ Requiere login |

## ⚠️ Importante
- **SIN seguridad JWT** - Contraseñas en texto plano (solo para desarrollo)
- **SIN cifrado de contraseñas** - No recomendado para producción
- **CORS habilitado** - Puedes llamar desde JavaScript

## 🎯 Ejemplo de Flujo

1. Usuario se registra: `POST /api/usuarios/registro`
2. Usuario hace login: `POST /api/usuarios/login` → obtiene su `id`
3. Usuario intenta agregar película a favoritos: `POST /api/favoritos` (necesita su `id`)
4. Si no está logueado → error 401 "Usuario no registrado"
5. Si está logueado → la película se guarda en la tabla `favoritos`

## 📂 Estructura de archivos nuevos/modificados

```
src/main/java/dam/code/migaleria
├── models/
│   ├── Usuario.java (actualizado con JPA)
│   ├── Favorito.java (nuevo)
│   └── Pelicula.java (sin cambios sustanciales)
├── controller/
│   ├── UsuarioController.java (completamente nuevo)
│   ├── FavoritoController.java (nuevo)
│   └── PeliculaController.java (actualizado con ruta /api)
└── repository/
    ├── UsuarioRepository.java (nuevo)
    └── FavoritoRepository.java (nuevo)

src/main/resources/
├── application.properties (actualizado con config de BD)
└── static/
    └── api-ejemplo.js (nuevo - ejemplos JavaScript)

(raíz del proyecto)
├── SETUP_INSTRUCTIONS.md (documentación)
├── database_setup.sql (obsoleto, se crea automáticamente)
└── pom.xml (actualizado con dependencias JPA)
```

## 🚀 Para tu HTML/JavaScript

Copia este archivo en tu HTML para usar los ejemplos:

```html
<script src="/api-ejemplo.js"></script>
<script>
  // Registrar usuario
  registrarUsuario('Juan', 'juan@gmail.com', 'pass123', '123456789');
  
  // Login
  loginUsuario('juan@gmail.com', 'pass123');
  
  // Agregar a favoritos
  agregarAFavoritos(1); // película con id 1
</script>
```

---

**Documentación completa**: Ver `SETUP_INSTRUCTIONS.md`
**Ejemplos JavaScript**: Ver `src/main/resources/static/api-ejemplo.js`

