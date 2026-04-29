# 🚚 Logistics Optimization System (Spring Boot)

A real-world backend system built using Spring Boot that manages orders, drivers, and delivery lifecycle with smart driver assignment using distance and ETA optimization.

---

## 🔥 Features

* 🔐 JWT Authentication (Secure APIs)
* 👥 Role-Based Access (ADMIN / DRIVER)
* 📦 Order Management (Create, Update, Delete)
* 🚗 Driver Management
* 🧠 Smart Driver Assignment (Haversine + ETA)
* 🚚 Delivery Lifecycle (ASSIGNED → PICKED → DELIVERED)
* ⚠️ Global Exception Handling

---

## 🧠 Tech Stack

* Java 17
* Spring Boot
* Spring Security (JWT)
* MySQL
* JPA / Hibernate
* Lombok
  
---

## 📁 Project Structure

```
controller/ → API layer  
service/ → business logic  
repository/ → database access  
entity/ → database models  
dto/ → request/response  
security/ → JWT & auth  
config/ → configurations  
```

---

## 🔐 Authentication Flow

1. User registers
2. Login to get JWT token
3. Send token in headers:

```
Authorization: Bearer TOKEN
```

4. Access secured APIs

---

## 👥 Roles

| Role   | Access                  |
| ------ | ----------------------- |
| ADMIN  | Manage orders & drivers |
| DRIVER | Update delivery status  |

---

## 🚀 API Endpoints

### 🔐 Auth

* POST `/auth/register`
* POST `/auth/login`

### 📦 Orders

* POST `/orders`
* GET `/orders`
* POST `/orders/{id}/assign-driver`
* PUT `/orders/{id}/picked`
* PUT `/orders/{id}/delivered`

### 🚗 Drivers

* POST `/drivers`
* GET `/drivers`

---

## 🧪 Testing (Postman)

1. Register user
2. Login → get token
3. Add token in header
4. Test APIs

---

## 📊 Smart Driver Assignment

* Uses **Haversine formula** to calculate real distance
* Calculates **ETA (Estimated Time)**
* Assigns driver with **minimum ETA**

---

## ⚙️ Setup Instructions

```bash
git clone https://github.com/your-username/springboot-logistics-platform.git
cd springboot-logistics-platform
```

### Configure MySQL

Update `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/logistics_db
spring.datasource.username=root
spring.datasource.password=your_password
```

### Run Application

```
mvn spring-boot:run
```

## 🏆 Key Learnings

* Designing real-world backend systems
* Implementing JWT authentication
* Role-based access control
* Handling business logic and edge cases
* Writing clean, maintainable code

---

## 🚀 Future Improvements

* Real-time tracking (WebSockets)
* Route optimization (Dijkstra Algorithm)
* Redis caching
* Docker deployment

---

## 👨‍💻 Author

Vishvatej Surve

---

⭐ If you like this project, give it a star!
