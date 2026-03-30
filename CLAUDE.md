# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build all modules
mvn clean package

# Build without running tests
mvn clean package -DskipTests

# Build a specific module
mvn clean package -pl <module-name> -am
```

## Startup Order

Services must be started in this order:
1. **eureka-server** (port 8761) - Service registry
2. **user-service-server** (port 8081) - User service implementation
3. **order-service** (port 8082) - Order service (depends on user-service)

## Architecture

This is a Spring Cloud microservice demo using the Netflix OSS stack:

```
microservice-demo/
├── eureka-server/         # Eureka service registry (port 8761)
├── user-service/          # Aggregator module
│   ├── user-service-api/     # Feign interfaces + DTOs (shared contract)
│   └── user-service-server/  # Service implementation (port 8081)
└── order-service/         # Consumes user-service via Feign (port 8082)
```

### Module Dependency Pattern

`user-service` uses an aggregator pattern separating API from implementation:
- **user-service-api**: Contains `UserClient` (Feign interface) and `UserDTO`. Other services depend on this JAR to call user-service.
- **user-service-server**: Implements the service, depends on its own api module.

When `order-service` needs to call `user-service`, it only depends on `user-service-api`, not the server implementation.

### Service Communication

- Services register with Eureka at `http://localhost:8761/eureka/`
- Inter-service calls use OpenFeign with service names (e.g., `@FeignClient(name = "user-service")`)
- Hystrix circuit breakers are configured with fallback classes in the api module

### Key Annotations

- `@EnableEurekaServer` - Eureka server
- `@EnableDiscoveryClient` - Eureka client
- `@EnableFeignClients` - Enable Feign clients
- `@EnableCircuitBreaker` - Enable Hystrix

## Technology Stack

- **JDK**: 1.8
- **Spring Boot**: 2.3.12.RELEASE
- **Spring Cloud**: Hoxton.SR12
- **Components**: Eureka Server, OpenFeign, Hystrix

## Service Endpoints

| Service | URL |
|---------|-----|
| Eureka Dashboard | http://localhost:8761 |
| User Service | http://localhost:8081/users |
| Order Service | http://localhost:8082/orders |
| Order with User | http://localhost:8082/orders/{id}/with-user |