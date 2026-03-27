# Spring Cloud 微服务示例项目

## 项目结构

```
microservice-demo/
├── pom.xml                            # 父工程POM
├── eureka-server/                     # Eureka注册中心 (端口: 8761)
├── user-service/                      # 用户服务（聚合模块）
│   ├── pom.xml
│   ├── user-service-api/              # API模块（Feign接口 + DTO）
│   │   └── src/main/java/.../
│   │       ├── UserClient.java
│   │       └── entity/UserDTO.java
│   └── user-service-server/           # 服务实现 (端口: 8081)
│       └── src/main/
│           ├── java/.../
│           │   ├── UserServiceApplication.java
│           │   └── controller/UserController.java
│           └── resources/application.yml
└── order-service/                     # 订单服务 (端口: 8082)
    ├── pom.xml
    └── src/main/
        ├── java/.../
        │   ├── OrderServiceApplication.java
        │   ├── controller/OrderController.java
        │   └── entity/
        └── resources/application.yml
```

## 技术栈

- **JDK**: 1.8
- **Spring Boot**: 2.3.12.RELEASE
- **Spring Cloud**: Hoxton.SR12
- **Eureka Server**: 服务注册与发现
- **Hystrix**: 服务熔断
- **OpenFeign**: 声明式服务调用

## 设计说明

`user-service` 采用聚合工程结构：
- `user-service-api`: 定义Feign接口和DTO，供消费方依赖
- `user-service-server`: 服务实现，依赖api模块

其他服务想调用user-service时，只需引入：
```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>user-service-api</artifactId>
</dependency>
```

## 启动顺序

1. 启动 Eureka Server (端口 8761)
2. 启动 User Service Server (端口 8081)
3. 启动 Order Service (端口 8082)

## 服务地址

| 服务 | 地址 |
|------|------|
| Eureka Dashboard | http://localhost:8761 |
| User Service | http://localhost:8081/users |
| Order Service | http://localhost:8082/orders |

## API 接口

### User Service
- `GET /users` - 获取所有用户
- `GET /users/{id}` - 根据ID获取用户

### Order Service
- `GET /orders` - 获取所有订单
- `GET /orders/{id}` - 根据ID获取订单
- `GET /orders/{id}/with-user` - 获取订单及关联用户信息(服务间调用)

## 构建命令

```bash
# 编译项目
mvn clean package

# 跳过测试编译
mvn clean package -DskipTests
```