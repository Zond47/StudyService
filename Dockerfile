FROM amazoncorretto:17
EXPOSE 8080
ADD app/target/app-0.0.1-SNAPSHOT.jar app-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app-0.0.1-SNAPSHOT.jar"]
