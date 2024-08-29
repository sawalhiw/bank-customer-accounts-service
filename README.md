# Bank Customer Accounts Service

## Overview

The `bank-customer-accounts-service` is designed to manage customer accounts in a banking application. Key functionalities include:

- **Adding Accounts:** Create new customer accounts.
- **Updating Accounts:** Modify existing customer account details.
- **Deleting Accounts:** Remove customer accounts.

After performing these operations, the service sends notifications via Kafka to inform users. It uses a Feign client to communicate with the `bank-customer-service` for customer validation and interacts with the `bank-auth-service` to validate security tokens.

## Prerequisites

To run this service, you need the following:

- **Java 17**
- **Maven**
- **MySQL**
- **Kafka**

## Checking Test Coverage

1. **Build the Project:**
   Run the following command to build the project and generate test reports:
   ```sh
   mvn clean install
   ```
2. **View Test Coverage Report:**
   After building the project, navigate to the coverage report located at:
   ```sh
   /target/site/jacoco/index.html
   ```
   
## Running the Application

**Note:** Ensure to start the services in the following order:

1. **`bank-customer-service`**:
   - Start this service first. It is responsible for manging customer details.

2. **`bank-auth-service`**:
   - Start this service second. It handles token validation and security.

3. **`bank-customer-accounts-service`**:
   - Finally, start this service. It manages customer accounts.


To run the `bank-customer-accounts-service`, follow these steps:

1.**Set Up MySQL Database:**
   - Create a MySQL database. You can name it `bank`.

2. **Configure Database Access:**
   - Update your application configuration to match your MySQL setup. This typically involves setting the username and password in the `application.yml` file.

3. **(Optional) Configure Kafka:**
   - If you need to customize Kafka settings, update the configuration in `KafkaProducerConfig.java`. Adjust properties like `bootstrap.servers`, `key.serializer`, `value.serializer`, etc., according to your Kafka setup.

4. **Start the Application:**
   - Use Maven to run the application:
     ```sh
     mvn spring-boot:run
     ```

## Configuration Details

### Database Configuration

- **File to Update:** `application.yml`
- **Properties to Configure:**
  ```yml
  spring:
  datasource:
    url: jdbc:mysql://localhost:3306/bank
    username: your_database_username
    password: your_database_password
  ```
### Important Notes
1. **If you perform any write operation on this service you will receive an email so please make sure check spam tab in your email.**