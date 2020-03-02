FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} google-civic-info-api-integration.jar
ENTRYPOINT ["java","-jar","google-civic-info-api-integration.jar"]