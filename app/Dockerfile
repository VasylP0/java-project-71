# Build stage
FROM gradle:8.7-jdk21 AS builder
WORKDIR /app

# Copy the project files into the container
COPY . .

# Install required tools and build the project
RUN apt-get update && apt-get install -y findutils && \
    ./gradlew clean build && \
    ls -l /app/build/libs

# Runtime stage
FROM openjdk:21
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/build/libs/app-1.0-SNAPSHOT.jar app.jar

# Set the entrypoint to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
