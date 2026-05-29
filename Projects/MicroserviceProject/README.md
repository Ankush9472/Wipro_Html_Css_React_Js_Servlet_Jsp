# MicroserviceProject

A Spring Cloud microservices demo with three independent projects:

```
MicroserviceProject/
├── EurekaServer/      → Service registry  (port 8761)
├── UserService/       → User microservice (port 8081, registers as "user-ms")
└── ContactService/    → Contact microservice (port 8082, registers as "contact-ms")
```

- **Spring Boot:** 3.5.5
- **Spring Cloud:** 2025.0.0
- **Java:** 17
- **Database:** MySQL (database name: `wiprotraining`)
- **Communication:** UserService calls ContactService via OpenFeign + Resilience4j Circuit Breaker

---

## 1. Prerequisites

- **JDK 17** installed and configured.
- **Maven** (optional — each project includes the Maven Wrapper `mvnw` / `mvnw.cmd`).
- **MySQL** running on `localhost:3306`. The services use username `root` and password `root` — change these in each `application.properties` if yours differ.
- **IntelliJ IDEA** (Community or Ultimate).

### MySQL Setup

Open MySQL Workbench (or any MySQL client) and run:

```sql
CREATE DATABASE wiprotraining;
```

Hibernate will create the `user` and `contact` tables automatically on first run (`ddl-auto=update`).

---

## 2. Opening in IntelliJ

The three projects are **independent Maven projects**. You can either:

**Option A — One window per project (recommended for clarity):**
- File → Open → `MicroserviceProject/EurekaServer` → New Window
- File → Open → `MicroserviceProject/UserService` → New Window
- File → Open → `MicroserviceProject/ContactService` → New Window

**Option B — Open the parent folder once:**
- File → Open → `MicroserviceProject`
- Then right-click each child `pom.xml` → "Add as Maven Project" so IntelliJ recognizes all three.

---

## 3. Running the Services (Order Matters!)

Start them in this order:

### Step 1 — Eureka Server
1. Open `EurekaServer/src/main/java/com/example/eurekaserver/EurekaServerApplication.java`.
2. Run it.
3. Open `http://localhost:8761` in your browser — Eureka dashboard appears.

### Step 2 — ContactService
1. Open `ContactService/src/main/java/com/example/ContactServiceApplication.java`.
2. Run it.
3. Wait until you see `registration status: 204` in the console.

### Step 3 — UserService
1. Open `UserService/src/main/java/com/example/UserServiceApplication.java`.
2. Run it.
3. Wait until you see `registration status: 204` in the console.

### Step 4 — Verify
Refresh `http://localhost:8761`. Under "Instances currently registered with Eureka" you should see:

| Application | Status |
|---|---|
| **USER-MS**    | UP (1) — localhost:user-ms:8081 |
| **CONTACT-MS** | UP (1) — localhost:contact-ms:8082 |

---

## 4. Testing the Endpoints

| URL | Description |
|---|---|
| `http://localhost:8761`            | Eureka dashboard |
| `http://localhost:8082/contacts/all`   | All contacts |
| `http://localhost:8082/contacts/1`     | Contacts belonging to user 1 |
| `http://localhost:8081/user/all`       | All users (no contacts) |
| `http://localhost:8081/user/1`         | User 1 **with contacts** (UserService calls ContactService via Feign) |

The `http://localhost:8081/user/{id}` endpoint is the interesting one — it fetches a user from MySQL, then calls ContactService through the Feign Client to attach that user's contacts.

If ContactService is down, the Resilience4j Circuit Breaker on `UserService.getUserById` falls back to an empty user — try stopping ContactService and hitting `/user/1` to see the fallback behaviour.

---

## 5. Notes on the Configuration Fix

The original `application.properties` files in both UserService and ContactService had **YAML syntax pasted at the end of a `.properties` file**, which Spring Boot cannot parse. The combined project here has those properties files rewritten in pure `.properties` syntax (and the duplicate `application.yml` files removed).

The Eureka URL is also written with a **trailing slash** (`http://localhost:8761/eureka/`) — both forms work, but the trailing slash matches Spring Cloud's documented convention.

---

## 6. Common Issues

| Problem | Fix |
|---|---|
| `Connection refused: localhost:8761` | Eureka Server isn't running. Start it first. |
| `Access denied for user 'root'@'localhost'` | Wrong MySQL password — edit `spring.datasource.password` in each `application.properties`. |
| `Unknown database 'wiprotraining'` | Run `CREATE DATABASE wiprotraining;` in MySQL. |
| Lombok red errors in IntelliJ | Install the Lombok plugin (Settings → Plugins) and enable annotation processing (Settings → Build → Compiler → Annotation Processors). |
| Service not on dashboard | Wait ~30 seconds, then refresh — Eureka registration isn't instant. |
| Port already in use | Stop the conflicting process or change `server.port`. |

