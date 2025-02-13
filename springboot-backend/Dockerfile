FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copy the project files
COPY . .

# Grant execution permissions to mvnw
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

# Use a lightweight JRE base image
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Set the entry point
ENTRYPOINT ["java", "-jar", "app.jar"]

# Cloud Run requires the container to listen on $PORT
ENV PORT=8080
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
