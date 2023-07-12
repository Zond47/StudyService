# StudyService

Currently service has 8 main endpoints:

**POST** **/api/v1/registration**
Request Body
FirstName
LastName
Email
Password

**GET** **api/v1/registration/confirm**
Request Param 
token

**POST** **api/v1/login**
Request Body
Email
Password

**GET** **api/v1/login/userDetails**
Request Param
email

**POST** **api/v1/posts**
Request Param
userId
Request Body
serviceDate (> now())
jobTags
isFinalPropose
serviceAddress
proposedPrice

**GET** **api/v1/posts**
Request Param
Id

**POST** **api/v1/comments**
Request Param
userId
postId
RequestBody
proposedPrice

**GET** **api/v1/comments**
Request Param
Id

## ERD Data Model

![ERD](https://github.com/Zond47/StudyService/assets/32875607/bc098510-4f32-477a-b5f8-0c183a21c759)

## AWS Network Diagram

![AWSND](https://github.com/Zond47/StudyService/assets/32875607/45d16e0a-0868-4b32-85cd-9ecfc29ae247)

## Getting Started with Local Development

#### Prerequisites

* [Amazon Corretto Java 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)
* [Maven 3.6.3 OR later](https://maven.apache.org/download.cgi)

#### How to run the application locally

From Terminal line run this command:
```
   mvn clean spring-boot:run -Dspring-boot.run.profiles=local
```
## Technologies Used
- Spring Boot
- MySQL

## Contributing

Eager to contribute to this service?
Please pull master branch, create new for your feature and raise new PR.
