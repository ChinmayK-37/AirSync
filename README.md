# ✈️ AirSync

AirSync is a **flight booking and management backend system** built using Java and Spring Boot. The project is designed to provide REST APIs for managing users, airlines, flights, seats, and eventually the complete flight booking workflow.

## 🚀 Tech Stack

* **Java**
* **Spring Boot**
* **Spring Data JPA / Hibernate**
* **PostgreSQL**
* **Maven**
* **REST APIs**
* **Lombok**
* **Postman** for API testing

## 📌 Current Features

### 👤 User Management

* Create users
* Get a user by ID
* Get all users
* Update users
* Delete users

Users can have roles such as:

* `PASSENGER`
* `ADMIN`

### ✈️ Airline Management

* Create airlines
* Get an airline by ID
* Get all airlines
* Update airlines
* Delete airlines

Airlines contain information such as:

* IATA code
* Name
* Status

### 🛫 Flight Management

* Create flights
* Get a flight by ID
* Get all flights
* Update flights
* Delete flights

Each flight is associated with an airline and contains:

* Flight number
* Origin
* Destination
* Departure time
* Arrival time
* Economy base price
* Business base price
* Flight status

### 💺 Seat Management

* Create seats
* Get a seat by ID
* Get all seats
* Update seats
* Delete seats

Each seat is associated with a flight and contains:

* Seat number
* Seat class
* Seat status
* Optimistic locking using `@Version`

The database also prevents duplicate seat numbers within the same flight.

## 🏗️ Project Architecture

AirSync follows a layered Spring Boot architecture:

```text
                Client / Postman
                       │
                       ▼
              ┌─────────────────┐
              │   Controllers   │
              └─────────────────┘
                       │
                       ▼
              ┌─────────────────┐
              │    Services     │
              └─────────────────┘
                       │
                       ▼
              ┌─────────────────┐
              │   Repositories  │
              └─────────────────┘
                       │
                       ▼
              ┌─────────────────┐
              │  PostgreSQL DB  │
              └─────────────────┘
```

### Entity Relationships

```text
Airline
   │
   │ 1
   │
   │ *
 Flight
   │
   │ 1
   │
   │ *
 Seat
```

A flight belongs to an airline, while a flight can contain multiple seats.

## 📂 Project Structure

```text
src/
└── main/
    └── java/
        └── com/
            └── cp/
                └── airsync/
                    ├── controllers/
                    │   ├── UserController.java
                    │   ├── AirlineController.java
                    │   ├── FlightController.java
                    │   └── SeatController.java
                    │
                    ├── entity/
                    │   ├── User.java
                    │   ├── Airline.java
                    │   ├── Flight.java
                    │   └── Seat.java
                    │
                    ├── repository/
                    │   ├── UserRepository.java
                    │   ├── AirlineRepository.java
                    │   ├── FlightRepository.java
                    │   └── SeatRepository.java
                    │
                    ├── service/
                    │   ├── UserService.java
                    │   ├── AirlineService.java
                    │   ├── FlightService.java
                    │   └── SeatService.java
                    │
                    └── enums/
                        ├── AirlineStatus.java
                        ├── FlightStatus.java
                        ├── SeatClass.java
                        └── SeatStatus.java
```

## 🔗 API Endpoints

### Users

| Method | Endpoint          | Description    |
| ------ | ----------------- | -------------- |
| POST   | `/api/users`      | Create user    |
| GET    | `/api/users`      | Get all users  |
| GET    | `/api/users/{id}` | Get user by ID |
| PUT    | `/api/users/{id}` | Update user    |
| DELETE | `/api/users/{id}` | Delete user    |

### Airlines

| Method | Endpoint             | Description       |
| ------ | -------------------- | ----------------- |
| POST   | `/api/airlines`      | Create airline    |
| GET    | `/api/airlines`      | Get all airlines  |
| GET    | `/api/airlines/{id}` | Get airline by ID |
| PUT    | `/api/airlines/{id}` | Update airline    |
| DELETE | `/api/airlines/{id}` | Delete airline    |

### Flights

| Method | Endpoint            | Description      |
| ------ | ------------------- | ---------------- |
| POST   | `/api/flights`      | Create flight    |
| GET    | `/api/flights`      | Get all flights  |
| GET    | `/api/flights/{id}` | Get flight by ID |
| PUT    | `/api/flights/{id}` | Update flight    |
| DELETE | `/api/flights/{id}` | Delete flight    |

### Seats

| Method | Endpoint          | Description    |
| ------ | ----------------- | -------------- |
| POST   | `/api/seats`      | Create seat    |
| GET    | `/api/seats`      | Get all seats  |
| GET    | `/api/seats/{id}` | Get seat by ID |
| PUT    | `/api/seats/{id}` | Update seat    |
| DELETE | `/api/seats/{id}` | Delete seat    |

## 🧪 API Testing

The APIs can be tested using **Postman**.

### Example: Create Airline

```http
POST http://localhost:8080/api/airlines
Content-Type: application/json
```

```json
{
  "iataCode": "AI",
  "name": "Air India",
  "status": "ACTIVE"
}
```

### Example: Create Flight

```http
POST http://localhost:8080/api/flights
Content-Type: application/json
```

```json
{
  "flightNumber": "AI101",
  "airline": {
    "airlineId": 1
  },
  "origin": "Mumbai",
  "destination": "Delhi",
  "departureTime": "2026-10-15T08:30:00",
  "arrivalTime": "2026-10-15T10:45:00",
  "basePriceEconomy": 4500.00,
  "basePriceBusiness": 12000.00,
  "status": "SCHEDULED"
}
```

### Example: Create Seat

```http
POST http://localhost:8080/api/seats
Content-Type: application/json
```

```json
{
  "flight": {
    "id": 1
  },
  "seatNumber": "1A",
  "seatClass": "ECONOMY",
  "status": "AVAILABLE"
}
```

## ⚙️ Getting Started

### 1. Clone the repository

```bash
git clone <your-repository-url>
cd airsync
```

### 2. Configure PostgreSQL

Create a PostgreSQL database for the project.

Update the database configuration in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/airsync
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Build the project

```bash
./mvnw clean install
```

On Windows:

```bash
mvnw.cmd clean install
```

### 4. Run the application

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The application will start at:

```text
http://localhost:8080
```

## 🔄 Current Development Flow

The backend is being developed incrementally:

```text
User
  ↓
Airline
  ↓
Flight
  ↓
Seat
  ↓
Booking
  ↓
Payment
```

The current implementation focuses on establishing the core flight-management domain and its REST APIs before adding the complete booking and payment workflow.

## 🛠️ Future Improvements

Planned improvements include:

* User authentication and authorization
* JWT-based security
* Flight search and filtering
* Booking management
* Payment integration
* Ticket generation
* Booking history
* Cancellation and refund handling
* Admin management APIs
* Better exception handling
* Request validation
* DTO-based API responses
* API documentation using Swagger/OpenAPI
* Unit and integration testing
* Docker support

## 📄 License

This project is currently being developed as a personal software project.
