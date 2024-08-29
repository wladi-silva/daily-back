FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install -y
RUN mvn clean install

FROM openjdk:17-slim

EXPOSE 8080

COPY --from=build /target/daily-1.0.0.jar app.jar

CMD [ "java", "-jar", "app.jar" ]