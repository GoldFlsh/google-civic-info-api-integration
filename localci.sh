#!/bin/bash

RED='\033[0;31m'
NO_COLOR='\033[0m'
BLUE='\033[0;36m'
if [ -z "$API_KEY" ]; then
  echo -e "${RED}Missing API_KEY!!!!"
  echo -e "Please run: ${BLUE}export API_KEY=''${NO_COLOR}"
  exit
fi

./gradlew clean build integrationTest && { {
    docker-compose up -d --build graphql-api-spr-boot &&
    docker-compose run google_api_newman &&
    docker-compose run graphql_newman &&
    docker-compose down
  } || {
    echo -e "${RED}************************************************************${NO_COLOR}"
    echo -e "${RED}** Docker TEST FAILED!!! Scroll up to see postman results **${NO_COLOR}"
    echo -e "${RED}************************************************************${NO_COLOR}"
    docker-compose down
  }
}