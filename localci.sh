#!/bin/bash

if [ -z "$API_KEY" ]; then
  echo "Missing API_KEY!!!!"
  echo "Please copy and paste API Key (input will be hidden)"
  read -s API_KEY_VAR
else
  API_KEY_VAR=${API_KEY}
fi

./gradlew clean build integrationTest && { {
    docker-compose up -d --build graphql-api-spr-boot &&
    docker-compose up google_api_newman graphql_newman
  } || docker-compose down
}

