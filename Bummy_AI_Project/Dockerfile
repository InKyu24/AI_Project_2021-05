FROM openjdk:11-jdk as builder
RUN mkdir -p root/.aws
ARG JAR_FILE=./Bummy_AI_Project-0.0.1-SNAPSHOT.war
COPY ${JAR_FILE} app.war
ENTRYPOINT ["java","-jar","app.war"]