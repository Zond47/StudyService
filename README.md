# StudyService

Currently service has 3 main endpoints:

**/api/v1/registration**
Request Body
FirstName
LastName
Email
Password

**api/v1/registration/confirm**
Request Param 
token

**api/v1/login**
Request Body
Email
Password

## ERD Data Model

![ERD](https://github.com/Zond47/StudyService/assets/32875607/bc098510-4f32-477a-b5f8-0c183a21c759)


## Getting Started with Local Development

#### Prerequisites

* [Amazon Corretto Java 11](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/what-is-corretto-11.html)
* [Maven 3.6.3 OR later](https://maven.apache.org/download.cgi)

#### How to run the application locally

From Terminal line run this command:
```
   mvn clean spring-boot:run -Dspring-boot.run.profiles=local
```
## Technologies Used
- Spring Boot
- H2

## Contributing

Eager to contribute to this service?
Please pull master branch, create new for your feature and raise new PR.
