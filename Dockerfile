# Stage 1: build
FROM maven:3.10.1-jdk-21-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src src
RUN mvn clean package -DskipTests -B

# Stage 2: run
FROM openjdk:21-jre-slim
WORKDIR /app
COPY --from=build /app/target/tienda-crochet-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
