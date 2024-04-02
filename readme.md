# Hotel Notification Management System

The Hotel Notification Management System is a Java-based application built with Spring Boot 3. It provides functionality for managing notifications for hotels, including creating, updating, deleting notifications, and tracking performance metrics such as clicks, impressions, and conversions.

## Technologies Used

- Java 17
- Spring Boot 3
- Swagger for API documentation

## Getting Started

To run the application locally, follow these steps:

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/boulamared/userguest-kata-backend.git

2. Navigate to the project directory: 
   ```bash
   cd hnms
3. Build the project using Gradle:
   ```bash
   clean build --exclude-task test
4. Run the application
   ```bash
   java -jar target/hnms.jar
   
5. Once the application is running, you can access the Swagger UI documentation at
   [Swagger Documentation](http://localhost:8080/swagger-ui/index.html)

# Usage

The application provides RESTful endpoints for managing notifications for hotels. You can use the provided Swagger documentation to explore and interact with the APIs.

## Endpoints

- `/api/hotels`: CRUD operations for managing hotels.
- `/api/notifications`: CRUD operations for managing notifications.
- `/api/notifications/{notificationId}/clicks`: Endpoint for adding clicks to a notification.
- `/api/notifications/{notificationId}/impressions`: Endpoint for adding impressions to a notification.
- `/api/notifications/{notificationId}/conversions`: Endpoint for adding conversions to a notification.
- `/api/notifications/{notificationId}/performance`: Endpoint for retrieving performance metrics for a notification.
