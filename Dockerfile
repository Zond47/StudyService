FROM maven:3.8.3-openjdk-17
EXPOSE 8080
ADD app/target/task-done-app-0.0.1-SNAPSHOT.jar task-done-app-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/task-done-app-0.0.1-SNAPSHOT.jar"]
