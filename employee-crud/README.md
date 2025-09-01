# Employee CRUD (Spring Boot 3.5.5)

A clean, runnable CRUD API for Employees with Lombok and Swagger UI.

## Run (H2 default)

```bash
./mvnw spring-boot:run
# or with Maven installed
mvn spring-boot:run
```

Open Swagger UI:
- http://localhost:8080/swagger-ui.html

H2 Console:
- http://localhost:8080/h2  (JDBC URL: `jdbc:h2:mem:employee_db`)

## Switch to MySQL (optional)

Uncomment and use these in `application.properties` or create `application-mysql.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

Add DB:
```sql
CREATE DATABASE employee_db;
```

## Endpoints

- `GET /api/employees`
- `GET /api/employees/{id}`
- `POST /api/employees`
- `PUT /api/employees/{id}`
- `DELETE /api/employees/{id}`
```

Entity fields: `id, name, department, email, status`.
