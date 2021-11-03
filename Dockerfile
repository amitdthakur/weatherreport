FROM openjdk:8-jdk-alpine
WORKDIR /opt/app
COPY  target/sensors-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java","-jar","application.jar"]