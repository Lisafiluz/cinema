# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Add Maintainer Info
#LABEL maintainer="your-email@example.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/cinema-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} cinema.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/cinema.jar"]