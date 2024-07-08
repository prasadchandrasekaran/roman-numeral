FROM amazoncorretto:22.0.0-alpine
ARG JAR_FILE=build/libs/roman-numeral-*.jar
COPY ${JAR_FILE} /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]