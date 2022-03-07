# BUILD
FROM maven:adoptopenjdk as build

WORKDIR /wordsmith/api

COPY pom.xml .
RUN mvn verify -DskipTests --fail-never

COPY src ./src
RUN mvn verify

# RUN
FROM adoptopenjdk:8-jre

WORKDIR /app

COPY --from=build /wordsmith/api/target .

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx8m", "-Xms8m", "-jar", "/app/words.jar"]
