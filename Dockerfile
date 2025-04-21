# Use an official Gradle image with Java 17 as the builder stage
FROM gradle:8.11.1-jdk17 AS build
WORKDIR /home/gradle/project
# Copy your project files into the container
COPY . .
# Build the application (adjust the task if needed)
RUN ./gradlew bootJar

FROM openjdk:17.0.2-jdk
WORKDIR /app
# Copy the JAR file from the build stage
COPY --from=build /home/gradle/project/build/libs/library-system-0.0.1-SNAPSHOT.jar /app/library-system.jar
# Expose port (if your application listens on a specific port)
EXPOSE 8080
# Run the application
ENTRYPOINT ["java", "-jar", "/app/library-system.jar"]
