FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /opt/app

COPY --from=build /app/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
