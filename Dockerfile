FROM openjdk:8-jre-alpine

COPY ./target/api-*.jar /app.jar
COPY config.yml data/config.yml

CMD ["/usr/bin/java", "-jar", "/app.jar", "server", "data/config.yml"]

EXPOSE 8080