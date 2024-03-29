# Fetching latest version of Java
FROM openjdk:17

# Setting up work directory
WORKDIR /app

# Copy the jar file into our app
COPY ./target/Localib-0.0.1-SNAPSHOT.jar /app

# Starting the application
CMD ["java", "-jar", "Localib-0.0.1-SNAPSHOT.jar"]