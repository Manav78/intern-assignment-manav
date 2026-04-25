# Internship Assignment: Virality Engine & Bot Guardrails

## Tech Stack
- **Backend:** Java 21, Spring Boot 3
- **Database:** PostgreSQL (Relational data)
- **Cache/Locking:** Redis (Atomic counters and rate limiting)
- **Environment:** Docker & Docker Compose

## How to Run
1. Navigate to the root folder.
2. Run `docker-compose up -d` to start the database and Redis.
3. Run the Spring Boot application via IntelliJ or `./mvnw spring-boot:run`.
4. The API is available at `http://localhost:8080`.

## Implementation Details
- **Atomic Operations:** Used Redis `INCR` to prevent race conditions during the "Spam Test."
- **Bot Guardrails:** Implemented a 10-minute cooldown using Redis `SETNX` and a horizontal cap of 100 replies per post.
- **Async Notifications:** Used `@EnableAsync` to ensure the API stays responsive while processing viral alerts.
