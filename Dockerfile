FROM adoptopenjdk:11-jdk-openj9
MAINTAINER Alexius Diakogiannis
COPY ./target/moviebook-runnable.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch moviebook-runnable.jar'
ENTRYPOINT ["java","-jar","moviebook-runnable.jar"]