# Build stage
FROM maven:3.8.6 AS builder

# Set working directory inside container
WORKDIR /app

# Copy your pom.xml and source code
COPY pom.xml .
COPY src ./src

# Run Maven to build the application and create a jar file
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the generated JAR file from the builder stage
COPY --from=builder /app/target/spring-health-api-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port the app will run on
EXPOSE 8080

# Command to run the JAR file
CMD ["java", "-jar", "/app/app.jar"]
