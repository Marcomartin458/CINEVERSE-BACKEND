# INSTRUCCIONES DE CONFIGURACIÓN - miGaleria

## 1. Configurar Base de Datos PostgreSQL

### Paso 1: Actualizar `application.properties`

Edita el archivo `src/main/resources/application.properties` y reemplaza tu contraseña de PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/migaleria_db
spring.datasource.username=postgres
spring.datasource.password=TU_CONTRASEÑA_POSTGRE_AQUI
```

### Paso 2: Las tablas se crean automáticamente

Gracias a `spring.jpa.hibernate.ddl-auto=create-drop`, las tablas se crean automáticamente cuando arranca la aplicación.

## 2. Cambios en la Aplicación

### Usuarios
- **Antes**: 5 usuarios precargados
- **Ahora**: 0 usuarios (tabla vacía). Los usuarios se registran a través de la API.

### Películas
- **Situación**: Siguen en memoria en el controlador (sin BD)
- **Razón**: Datos estáticos que no cambian

### Favoritos
- **Nueva**: Tabla `favoritos` en BD para guardar películas favoritas por usuario

## 3. Endpoints de la API

### Usuarios
```
POST /api/usuarios/registro
{
  "nombre": "Juan",
  "email": "juan@example.com",
  "password": "password123",
  "telefono": "123456789",
  "profileImage": "https://..."
}

POST /api/usuarios/login
{
  "email": "juan@example.com",
  "password": "password123"
}

GET /api/usuarios/{id}
```

### Películas
```
GET /api/peliculas          - Listar todas
POST /api/peliculas         - Agregar nueva
PUT /api/peliculas/{id}     - Actualizar
DELETE /api/peliculas/{id}  - Eliminar
```

### Favoritos
```
GET /api/favoritos/usuario/{usuarioId}  - Mis favoritos
POST /api/favoritos                      - Agregar a favoritos
{
  "usuarioId": 1,
  "peliculaId": 5
}
DELETE /api/favoritos/{usuarioId}/{peliculaId}  - Eliminar de favoritos
```

## 4. Usar JavaScript para Favoritos

Tu JavaScript puede:
1. Hacer GET a `/api/usuarios/login` y obtener el usuarioId
2. Hacer POST a `/api/favoritos` con `{usuarioId, peliculaId}`
3. Hacer DELETE a `/api/favoritos/{usuarioId}/{peliculaId}`
4. Hacer GET a `/api/favoritos/usuario/{usuarioId}` para listar favoritos

## 5. Notas Importantes

- NO hay cifrado de contraseñas (texto plano) - solo para desarrollo/alumno
- CORS habilitado en `http://localhost:*`
- La BD se recrea al iniciar (create-drop) - ideal para desarrollo

## 6. Estructura de BD que se crea

```sql
-- usuarios
id (SERIAL PK) | nombre | email (UNIQUE) | password | telefono | profile_image

-- peliculas
id (SERIAL PK) | titulo | director | genero | descripcion | imagen

-- favoritos
id (SERIAL PK) | usuario_id (FK) | pelicula_id | UNIQUE(usuario_id, pelicula_id)
```

