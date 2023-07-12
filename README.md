# StudyService

Currently service has 8 main endpoints:

**POST** **/api/v1/registration**

Request Body:
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `firstName` | `string` | **Required**. User first name. |
| `lastName` | `string` | **Required**. User last name. |
| `email` | `string` | **Required**. User email. |
| `password` | `string` | **Required**. User password. |

**GET** **api/v1/registration/confirm**

Request Body:
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `token` | `string` | **Required**. User confirmation token. |
 
**POST** **api/v1/login**

Request Body:
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `email` | `string` | **Required**. User email. |
| `password` | `string` | **Required**. User password. |

**GET** **api/v1/login/userDetails**

Request Param:
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `email` | `string` | **Required**. User email. |

**POST** **api/v1/posts**
Request Param:
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `userId` | `string` | **Required**. User id. |

Request Body:
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `serviceDate` | `LocalDateTime` | **Required(> now)**. Deadline by which work must be completed. |
| `jobTags` | `string` | **Required**. Type of jobs. |
| `isFinalPropose` | `string` | **Required**. Does this post negotiatable in terms of price. |
| `serviceAddress` | `string` | **Required**. Address where work must be completed. |
| `proposedPrice` | `BigDecimal` | **Required**. User proposed price. |

**GET** **api/v1/posts**

Request Param:
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `Id` | `string` | **Required**. Post id. |

**POST** **api/v1/comments**

Request Param:
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `userId` | `string` | **Required**. User id. |
| `postId` | `string` | **Required**. Post id. |

Request Body:
| Parameter | Type | Description |
| `proposedPrice` | `BigDecimal` | **Required**. Executor proposed price. |

**GET** **api/v1/comments**

Request Param:
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `Id` | `string` | **Required**. Comment Id. |

## Status Codes

App returns the following status codes in its API:

| Status Code | Description |
| :--- | :--- |
| 200 | `OK` |
| 201 | `CREATED` |
| 400 | `BAD REQUEST` |
| 404 | `NOT FOUND` |
| 500 | `INTERNAL SERVER ERROR` |


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
- Java 17
- Spring Boot
- Maven
- MySQL
- AWS CloudFormation, CodePipeline, CodeBuild, CodeDeploy, Fargate, ECR, EC2, ALB, VPC

## Contributing

Eager to contribute to this service?
Please pull master branch, create new for your feature and raise new PR.
