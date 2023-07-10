FROM amazoncorretto:17
EXPOSE 8080
ADD app/target/app-0.0.1-SNAPSHOT.jar task-done-app-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/task-done-app-0.0.1-SNAPSHOT.jar"]
