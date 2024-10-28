# 🛡️ Spring Security Project with JWT Authentication

A robust REST API project built with Java Spring Boot that implements secure authentication using JWT (JSON Web Tokens). This project includes error handling for smoother API interactions and is designed for secure user data management.

## 📋 Features
- **JWT Authentication**: Ensures secure, stateless access to the API.
- **User Authentication**: Allows user registration and login with password encryption.
- **Custom Error Handling**: Provides detailed error responses for authentication failures.
- **Role-Based Access**: Differentiates access to resources based on user roles.

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Spring Boot 3.x
- MySQL Database
- Postman (for API testing)

### Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/suprimpoudel/spring-security
   cd spring-security
   ```

2. **Configure Application Properties**:
   In `src/main/resources/application.yml`, update the database properties and JWT settings as shown below:
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/your_database_name  # Replace with your DB name
       username: your_username  # Replace with your DB username
       password: your_password  # Replace with your DB password
     jpa:
       hibernate:
         ddl-auto: update

   com:
     np:
       suprimpoudel:
         util:
           helper:
             secretKey: YOUR_SECRET_KEY_HERE  # Add a secure JWT secret key
             expirationTime: EXPIRATION_TIME_IN_MILLISECONDS  # Set token expiration time
   ```

3. **Run the Application**:
   Start the Spring Boot application by running:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Database Setup**:
   Ensure that your MySQL database is running and configured as specified in `application.yml`.

## 🧪 API Testing
A Postman collection has been added to help you test the API endpoints. Simply import the collection into Postman, set up your environment variables, and start making requests to validate functionality.

## 📖 Additional Resources
For further information, refer to the comprehensive guide on building a secure REST API with Spring Security, JWT, and custom error handling: [Read Article](https://medium.com/@suprempoudel/building-a-secure-rest-api-with-spring-security-jwt-and-custom-error-handling-4b0e79952f62).