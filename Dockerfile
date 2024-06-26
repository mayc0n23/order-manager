FROM maven:3.8.5-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package jacoco:report

FROM openjdk:17.0.1-jdk
WORKDIR /app
COPY --from=build ./app/target/site/jacoco ./jacoco
COPY --from=build ./app/target/*.jar ./app.jar

ENTRYPOINT java -jar app.jar