

```markdown
# Hotel Management Microservices System

This project is a microservices-based architecture for a **Hotel Management System**, consisting of:

- **User Service**
- **Hotel Service**
- **Rating Service**
- **Service Registry (Eureka)**
- **API Gateway**
- **Config Server**

## ✨ Project Structure

```bash
hotel-management/
├── api-gateway/
├── config-server/
├── eureka-server/
├── user-service/
├── hotel-service/
├── rating-service/
└── README.md
```

---

## 🧩 Microservices Overview

### 🧑 User Service

- **Port**: `8081`
- **Spring App Name**: `user-service`
- **Database**: MySQL (`hotel_management`)
- **Features**:
  - Manages user data
  - Registers with Eureka
  - Integrated with OpenTelemetry for tracing

---

### 🏨 Hotel Service

- **Port**: `8082`
- **Spring App Name**: `hotel-service`
- **Database**: MySQL (`hotel_management`)
- **Features**:
  - Manages hotel data
  - Registers with Eureka
  - Supports distributed tracing

---

### 🌟 Rating Service

- **Port**: `8083`
- **Spring App Name**: `rating-service`
- **Database**: MySQL (`hotel_management`)
- **Features**:
  - Handles user ratings for hotels
  - Eureka service discovery
  - OpenTelemetry tracing

---

## 🧭 API Gateway

- Acts as a single entry point for all microservices
- Handles routing and load balancing
- Simplifies client interactions

---

## 🛠 Config Server

- Centralized externalized configuration for all services
- Uses Git or local config repository

---

## 🔍 Eureka Service Registry

- URL: `http://localhost:8761`
- Registers and discovers all running services dynamically

---

## 📊 Distributed Tracing (OpenTelemetry)

- All services send traces to a collector (e.g., at `http://localhost:4318`)
- Compatible with tracing tools like **Jaeger** or **Zipkin**
- Helpful for debugging and monitoring

---

## 🚀 How to Run

1. Start MySQL Database (ensure `hotel_management` DB is created)
2. Run **Eureka Server**
3. Run **Config Server** (if external configs are used)
4. Start each service:
   - User Service (`8081`)
   - Hotel Service (`8082`)
   - Rating Service (`8083`)
   - API Gateway
5. Access services through API Gateway

---

## 🧪 Tech Stack

- Java 17
- Spring Boot
- Spring Cloud (Eureka, Config, Gateway)
- MySQL
- OpenTelemetry
- Maven

---

## 📂 Database Setup

Make sure you have a MySQL database named `hotel_management` running on:

- **Host**: `localhost`
- **Port**: `3306`
- **Username**: `root`
- **Password**: `Saurabh*789`

---

## 📞 Contact

For any queries, feel free to raise an issue or contribute to the repository!
```

Let me know if you want to include instructions for Postman, Docker setup, or sample API requests.
