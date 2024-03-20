# GFT Test Inditex: technical test

Api of the Inditex application


## Getting started
Clone the project into your environment

## Prerequisites
Java 17
Maven
Docker

## Compile
- Compile the source code of the project
```
./mvnw compile
```

## Package
- Take the compiled code and package it in a Jar
```
./mvnw clean package
```

## Skip test
- Skip the test adding ```-DskipTests``` in the command line.
```
./mvnw clean package -DskipTests
```

## Run application in local
```
./mvnw spring-boot:run
```

## Run application with docker
```
docker-compose up --build
```

## Tests the Endpoint with swagger
http://localhost:8080/swagger-inditex.html

## Run the H2 Console UI
http://localhost:8080/h2-console
With this data:
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:inditex
User Name: gft
Password:

## Tests with Postman
Import the Collection and Environment from the postman folder.
