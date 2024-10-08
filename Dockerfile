FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
#COPY ${JAR_FILE} app.jar
#ADD ${JAR_FILE} app.jar
COPY build/libs/Din-k8s-0.0.1-SNAPSHOT.jar app.jar

RUN mkdir destination-dir-for-add
ADD sample.tar.gz /destination-dir-for-add

ENTRYPOINT ["java","-jar","/app.jar"]
