# Start from the official Maven base image
FROM bellsoft/liberica-openjdk-alpine:17

# Set the working directory inside the container
WORKDIR /home/selenium-docker

#ADD files
ADD target/docker-resources ./
ADD pom.xml ./
ADD testng.xml ./

