# Microservices with Spring Boot

This repository demonstrates the development and deployment of a comprehensive **microservices architecture** using **Spring Boot** and **Spring Cloud**. The project showcases a collection of microservices, each designed to illustrate essential concepts and real-world practices in modern software development.

Key features of the project include:

- **Microservices Architecture**: Built with **Spring Boot** to create a collection of **loosely coupled services**, allowing for modular and scalable applications. Each service is developed to handle a specific business capability, promoting **separation of concerns** and independent scaling.

- **Configuration Management**: Centralized configuration handling using **Spring Cloud Config Server**. This setup ensures that configuration changes can be managed and propagated across all microservices efficiently, enhancing consistency and reducing configuration overhead.

- **Service Discovery**: Implemented with **Spring Eureka Server**, allowing services to register themselves and discover other services dynamically. This facilitates seamless interaction between microservices without the need for hard-coded service locations.

- **Resiliency**: Incorporates **RESILIENCE4J** to build **fault-tolerant microservices**. This includes circuit breakers, retries, and bulkheads to handle failures gracefully and ensure high availability.

- **Routing and Cross-Cutting Concerns**: Managed with **Spring Cloud Gateway**, which provides a robust routing solution and handles cross-cutting concerns such as authentication, authorization, and logging. This centralizes the management of routing and security policies.

- **Distributed Tracing and Log Aggregation**: Utilizes **Zipkin** for tracking and visualizing the flow of requests across multiple services. This setup aids in debugging and monitoring by aggregating logs and tracing the path of requests through the system.

- **Containerization**: Each microservice is **containerized using Docker**, streamlining deployment and ensuring consistency across different environments. Docker images are created for each service, simplifying the setup and scaling processes.

- **Security**: Incorporates **OAuth2** and **Okta** to secure microservices. This provides robust authentication and authorization mechanisms, ensuring that access to services is properly managed and secured.

### **Project Structure:**

I've organized the project into modular components, each dedicated to a specific microservice or technology:

- **cloud-gateway**: The entry point for API access, **managing requests and routing them to the appropriate services**. It handles all incoming requests and directs them to the correct microservice based on the routing configuration.

- **eureka-server**: The **service registry**, enabling microservices to register themselves and discover other services. It maintains a directory of available services and their locations, facilitating dynamic service interaction.

- **order-service**: Manages **order creation, processing, and fulfillment**. This service handles all aspects of order management, including order placement, status tracking, and processing.

- **payment-service**: Ensures **secure and reliable payment processing**. It handles transactions, payment validation, and integration with payment gateways.

- **product-service**: Provides **product information and inventory management**. This service is responsible for managing product data, including details, availability, and inventory levels.

## **Key Features**

- **Microservices Architecture**: Implemented with **Spring Boot** and **Spring Cloud**.
- **Configuration Management**: Using **Spring Cloud Config Server**.
- **Service Discovery**: Configured with **Spring Eureka Server**.
- **Resiliency**: Built with **RESILIENCE4J**.
- **Routing**: Managed with **Spring Cloud Gateway**.
- **Distributed Tracing**: Enabled with **Zipkin**.
- **Docker**: Containerized microservices with **Docker**.
- **Security**: Implemented **OAuth2** and **Okta** for secure microservices.
