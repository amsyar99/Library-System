FROM openjdk:17.0.2-jdk
WORKDIR /app

# Build the application skip tests for faster bui(ld)
RUN gradlew bootJar

# Copy the JAR file from the build stage
COPY build/libs/library-system-0.0.1-SNAPSHOT.jar /app/library-system.jar

# Expose port (if your application listens on a specific port)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/library-system.jar"]
