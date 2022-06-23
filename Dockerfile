FROM openjdk:11-slim-buster as build
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY openapi.yaml .
COPY src src
RUN ./mvnw -B  -DskipTests package
FROM openjdk:11-jre-slim-buster

COPY --from=build target/YandexCase-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "YandexCase-0.0.1-SNAPSHOT.jar"]