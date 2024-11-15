#!/bin/bash

# Let's print what we have received
echo "-------------------------------------------"
echo "HUB_HOST      : ${HUB_HOST}"
echo "BROWSER       : ${BROWSER}"
echo "CUCUMBER_TAG  : ${CUCUMBER_TAG}"
echo "URL           : http://${HUB_HOST}:4444"
echo "-------------------------------------------"

# Do not start the tests immediately. Hub has to be ready with browser nodes
echo "Checking if hub is ready..!"
count=0
while [ "$( curl -s http://"${HUB_HOST}":4444/status | jq -r .value.ready )" != "true" ]
do
  count=$((count+1))
  echo "Attempt: ${count}"
  if [ "$count" -ge 30 ]
  then
      echo "**** HUB IS NOT READY WITHIN 30 SECONDS ****"
      exit 1
  fi
  sleep 1
done

# At this point, selenium grid should be up!
echo "Selenium Grid is up and running. Running the test...."

# Start the java command
java -cp "libs/*" -Dcucumber.filter.tags="${CUCUMBER_TAG}" \
                                -Dselenium.grid.enabled=true \
                                -Dselenium.grid.hubHost=${HUB_HOST} \
                                -Dbrowser=${BROWSER} \
                                org.testng.TestNG testng.xml
