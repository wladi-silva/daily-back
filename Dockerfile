FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/daily-1.0.0.jar daily-1.0.0.jar
EXPOSE 8080
CMD [ "java",  "-jar", "daily-1.0.0.jar" ]