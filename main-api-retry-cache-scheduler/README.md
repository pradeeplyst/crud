# Main API (Retry + Cache + Scheduler)

Endpoints:
- GET /api/weather/{city}   -> fetches (cached 10m)
- POST /api/weather/{city}/refresh -> refresh cache

Configuration (application.properties):
- mock.api.url=http://localhost:8081/mock/weather
- scheduler.cities=chennai,delhi,mumbai

Build & Run:
  mvn clean package -DskipTests
  java -jar target/mainapi-1.0.0.jar

Generated: 2025-08-21T12:04:42.462692Z
