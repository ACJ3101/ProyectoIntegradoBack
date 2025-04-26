# --- ETAPA 1: Build con Maven + JDK 21 ---
FROM maven:3.10.1-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos solamente pom y descargamos dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiamos el código fuente y compilamos
COPY src ./src
RUN mvn clean package -DskipTests -B

# --- ETAPA 2: Imagen de ejecución con JRE 21 Alpine ---
FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app

# Opcional: crea un usuario no-root por seguridad
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copiamos el JAR “fat” generado
COPY --from=build /app/target/tienda-crochet-0.0.1-SNAPSHOT.jar app.jar

# Puertos expuestos (igual que tu app Spring Boot)
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]
