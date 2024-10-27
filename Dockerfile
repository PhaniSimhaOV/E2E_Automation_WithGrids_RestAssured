# Start from the official Maven base image
FROM bellsoft/liberica-openjdk-alpine:17

#to add utility
#RUN apk add curl jq

# Set the working directory inside the container
WORKDIR /home/selenium-docker



#ADD files
ADD target/docker-resources ./
ADD pom.xml ./
ADD testng.xml ./
#ADD runner.sh ./

#RUN dos2unix runner.sh

#ENTRYPOINT sh runner.sh
ENTRYPOINT java -cp "libs/*" -Dcucumber.options="--tags ${CUCUMBER_TAG}" \
                                -Dselenium.grid.enabled=true \
                                -Dselenium.grid.hubHost=${HUB_HOST} \
                                -Dbrowser=${BROWSER} \
                                org.testng.TestNG testng.xml