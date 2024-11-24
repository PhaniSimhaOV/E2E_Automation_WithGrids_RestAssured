# Start from the official Maven base image
FROM bellsoft/liberica-openjdk-alpine:17

#to add utility
RUN apk --no-cache add curl jq

# Set the working directory inside the container
WORKDIR /home/selenium-docker

#ADD files
COPY target/docker-resources ./
COPY pom.xml ./
COPY testng.xml ./
COPY runner.sh runner.sh

RUN dos2unix runner.sh

ENTRYPOINT ["sh", "runner.sh"]