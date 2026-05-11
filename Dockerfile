# Etapa 1: construir el JAR con Maven
FROM maven:3.8.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src/ ./src/
RUN mvn clean package -DskipTests

# Etapa 2: ejecutar el JAR con una imagen ligera de Java
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiar el JAR construido
COPY --from=builder /app/target/*.jar app.jar

# Puerto expuesto (Render asigna dinámicamente)
EXPOSE 8080


# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]