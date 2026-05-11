# Etapa 1: construir el JAR con Maven
FROM maven:3.8.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src/ ./src/
RUN mvn clean package -DskipTests

# Etapa 2: ejecutar el JAR con una imagen ligera de Java
FROM eclipse-temurin:17-jre-alpine

# Instalar curl para health checks (opcional)
RUN apk add --no-cache curl

WORKDIR /app

# Copiar el JAR construido
COPY --from=builder /app/target/*.jar app.jar

# Puerto expuesto (Render asigna dinámicamente)
EXPOSE 8080

# Health check para Render
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:$PORT/api/peliculas || exit 1

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]