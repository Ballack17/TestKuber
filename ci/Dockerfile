FROM openjdk:17.0.1
ARG JAR_FILE=build/libs/kubernetes-test.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.config.location=/deployments/config/application.yaml", "-jar","/app.jar"]
