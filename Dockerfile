FROM docker.io/openjdk:8u191-jdk-alpine3.9
EXPOSE 8080
COPY target/springboottest-0.0.1-SNAPSHOT.jar /apps/sb/app.jar
CMD java -jar /apps/sb/app.jar
