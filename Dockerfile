FROM openjdk:8-jdk-alpine
MAINTAINER Lalit Kulkarni
COPY target/account-management-0.0.1-SNAPSHOT.jar account-management-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/account-management-0.0.1-SNAPSHOT.jar"]