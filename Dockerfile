FROM maven:3.9.4-eclipse-temurin-17 as build
WORKDIR /app
COPY . /app/.
RUN mvn clean package
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/Project.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]