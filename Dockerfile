# Stage 1: build con Maven oficial
FROM maven:3.10.1-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src src
RUN mvn clean package -DskipTests -B

# Stage 2: runtime ligero
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=build /app/target/tienda-crochet-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
