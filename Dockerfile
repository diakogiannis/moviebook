FROM maven:3.6.0-jdk-11-slim AS build
MAINTAINER Alexius Diakogiannis
COPY . /usr/app/
RUN mvn -f /usr/app/pom.xml clean package

FROM adoptopenjdk:11-jdk-openj9
COPY --from=build /usr/app/target/moviebook-runnable.jar /usr/local/lib/moviebook-runnable.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/moviebook-runnable.jar"]