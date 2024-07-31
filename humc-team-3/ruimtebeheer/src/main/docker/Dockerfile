# Use the latest Alpine Linux base image.
FROM alpine:latest

# install Java 17 JRE
RUN apk add openjdk17-jre-headless=17.0.7_p7-r1 --no-cache

COPY /ruimtebeheer/target/ruimtebeheer*.jar ruimtebeheer.jar

ENTRYPOINT ["java", "-jar", "/ruimtebeheer.jar"]


