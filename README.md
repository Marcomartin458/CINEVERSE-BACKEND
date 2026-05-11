# Mi Galería - API REST con Spring Boot

Aplicación web para gestión de películas con sistema de usuarios y favoritos.

## 🚀 Características

- ✅ **API REST** completa para películas
- ✅ **Sistema de usuarios** con registro y login
- ✅ **Favoritos** guardados en base de datos
- ✅ **Películas en memoria** (datos estáticos)
- ✅ **CORS habilitado** para frontend
- ✅ **Docker support** completo
- ✅ **Deploy en Render** listo

## 📋 Requisitos

- Java 17+
- Maven 3.6+
- PostgreSQL (local o Docker)

## 🛠️ Configuración

### Opción 1: Desarrollo Local

1. **Instalar PostgreSQL** y crear base de datos:
   ```sql
   CREATE DATABASE migaleria;
   ```

2. **Configurar conexión** en `src/main/resources/application.properties`:
   ```properties
   spring.datasource.password=tu_password_aqui
   ```

3. **Compilar y ejecutar**:
   ```bash
   ./mvnw clean package -DskipTests
   ./mvnw spring-boot:run
   ```

### Opción 2: Con Docker (Recomendado)

1. **Levantar servicios**:
   ```bash
   docker-compose up --build
   ```

2. **Acceder a la aplicación**:
   - API: http://localhost:8080
   - Base de datos: localhost:5432 (usuario: postgres, password: postgres)

### Opción 3: Deploy en Render 🚀

1. **Subir código a GitHub** (ver sección "Archivos para GitHub")

2. **Crear cuenta en Render**: https://render.com

3. **Crear base de datos PostgreSQL**:
   - Ir a Dashboard > New > PostgreSQL
   - **Name**: `miGaleria-db`
   - **Database**: `migaleria`
   - Copiar la **External Database URL** (algo como: `postgresql://user:pass@host:5432/db`)

4. **Crear Web Service**:
   - Ir a Dashboard > New > Web Service
   - Conectar tu repositorio GitHub
   - Seleccionar branch `main`
   - **Name**: `miGaleria-api`
   - **Runtime**: `Docker`
   - **Build Command**: `docker build -t miGaleria .`
   - **Start Command**: `docker run -p $PORT:8080 miGaleria`

5. **Configurar variables de entorno**:
   - En tu Web Service, ir a Environment
   - Agregar: `DATABASE_URL` = (pega la **External Database URL** de tu BD PostgreSQL)

6. **Deploy**: Hacer click en "Create Web Service"

**⚠️ Importante**: Asegúrate de usar la **External Database URL**, no la Internal. Debe empezar con `postgresql://`.

## 📚 API Endpoints

### Usuarios
- `POST /api/usuarios/registro` - Registrar usuario
- `POST /api/usuarios/login` - Login
- `GET /api/usuarios` - Listar usuarios

### Películas
- `GET /api/peliculas` - Listar películas
- `POST /api/peliculas` - Agregar película
- `PUT /api/peliculas/{id}` - Actualizar película
- `DELETE /api/peliculas/{id}` - Eliminar película

### Favoritos
- `GET /api/favoritos/usuario/{userId}` - Ver favoritos de usuario
- `POST /api/favoritos` - Agregar a favoritos
- `DELETE /api/favoritos/{userId}/{peliculaId}` - Eliminar de favoritos

## 🎨 Frontend

El archivo `src/main/resources/static/api-ejemplo.js` contiene ejemplos completos de cómo consumir la API desde JavaScript.

### Funciones principales:
- `registrarUsuario()` - Registro
- `loginUsuario()` - Login
- `agregarAFavoritos()` - Agregar película a favoritos
- `misFavoritos()` - Ver favoritos del usuario

## 🗄️ Base de Datos

### Tablas creadas automáticamente:
- `usuarios` - Gestión de usuarios
- `favoritos` - Relación usuario-película

### Esquema:
```sql
-- Usuarios
CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    telefono VARCHAR(50),
    profile_image VARCHAR(500)
);

-- Favoritos
CREATE TABLE favoritos (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    pelicula_id INT NOT NULL,
    fecha_agregado TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    UNIQUE(usuario_id, pelicula_id)
);
```

## 🔧 Desarrollo

### Ejecutar tests:
```bash
./mvnw test
```

### Ejecutar con perfil Docker:
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=docker
```

### Ver logs de la aplicación:
```bash
docker-compose logs -f app
```

## 📁 Archivos para GitHub

### ✅ Archivos que SÍ subir:
```
miGaleria/
├── src/
│   └── main/
│       ├── java/dam/code/migaleria/  # Todo el código Java
│       └── resources/
│           ├── static/api-ejemplo.js
│           └── application.properties  # Configurado para Render
├── .gitignore
├── .dockerignore
├── Dockerfile
├── render.yaml          # Configuración Render
├── pom.xml
└── README.md
```

### ❌ Archivos que NO subir (ya están en .gitignore):
- `target/` - Archivos compilados
- `application-local.properties` - Config local con contraseñas
- `.env` - Variables de entorno locales
- Archivos IDE (.idea/, .vscode/, etc.)

## 📁 Estructura del Proyecto

```
miGaleria/
├── src/main/java/dam/code/migaleria/
│   ├── controller/          # Controladores REST
│   ├── models/             # Entidades JPA
│   ├── repository/         # Repositorios JPA
│   └── config/             # Configuraciones
├── src/main/resources/
│   ├── static/             # Archivos estáticos (JS, CSS)
│   └── application.properties  # Configuración
├── Dockerfile              # Docker image
├── docker-compose.yml      # Servicios Docker (local)
├── render.yaml            # Config Render
├── .gitignore             # Exclusiones Git
├── .dockerignore          # Exclusiones Docker
└── pom.xml                 # Dependencias Maven
```

## 🐛 Solución de Problemas

### Error de compilación con properties:
- Asegúrate de que los archivos `.properties` no tengan caracteres especiales
- El proyecto está configurado para NO filtrar estos archivos

### Error de conexión a BD:
- Verifica que PostgreSQL esté corriendo
- Revisa las credenciales en `application.properties`
- Para Docker: usa `docker-compose logs db` para ver logs de PostgreSQL

### Puerto ocupado:
- Cambia el puerto en `application.properties`: `server.port=8081`

### Problemas con Render:
- Verifica que `DATABASE_URL` esté configurada correctamente
- Revisa los logs en Render Dashboard
- Asegúrate de que el health check esté funcionando

## 📄 Licencia

Proyecto educativo - Uso libre para aprendizaje.
