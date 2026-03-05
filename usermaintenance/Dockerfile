# 1. fázis: Build
FROM maven:3.9.12-eclipse-temurin-21 AS build
WORKDIR /app
# Csak a pom.xml-t másoljuk először, hogy a függőségek cache-elődjenek
COPY pom.xml .
RUN mvn dependency:go-offline
# Forráskód másolása és fordítás
COPY src ./src
RUN mvn clean package -DskipTests

# 2. fázis: Runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Csak a kész JAR fájlt hozzuk át az előző fázisból
COPY --from=build /app/target/usermaintenance-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]