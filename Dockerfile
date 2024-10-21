# Start from the official Maven base image
FROM maven:3.8.4-openjdk-17 as build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and the source files into the container
COPY pom.xml .
COPY src ./src

# Build the project and compile tests
RUN mvn clean package -DskipTests

# Create a new image to run the compiled tests
FROM openjdk:17-jdk-slim

# Set the working directory in the new container
WORKDIR /app

# Copy only the necessary files from the build image (compiled classes, feature files, etc.)
COPY --from=build /app/target/docker-resources /app/target/docker-resources

# Set the entry point to run tests via Maven
ENTRYPOINT ["mvn", "test", "-f", "target/docker-resources/testng.xml"]