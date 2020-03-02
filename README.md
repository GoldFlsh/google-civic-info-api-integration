# Integrating with Google's Civic Information API

I am creating this project to practice different technologies 
fetching data from the [Google Civic Information API] as the back-end API.

[Google Civic Information API]: https://developers.google.com/civic-information

### Prerequisites
##### Required:

###### API-Key
You must have a Google API credential in the form of an API Key.\
Please follow the instructions [here](https://support.google.com/googleapi/answer/6158862?hl=en&ref_topic=7013279)

Once you have your api-key set it in your environment with the following command
```bash
#Remove the {} and put your api-key after the '=' 
export API_KEY={your api-key here}
```

###### Docker
[Docker](https://docs.docker.com/install/) and [docker-compose CLI](https://docs.docker.com/compose/install/)\ is required to run automated tests.

##### Optional:

###### Postman UI
I used postman for a lot of my testing in this. If there is interest in opening tests manually
and for exploratory testing, you can install [postman](https://www.postman.com/downloads/).

###### CircleCI
There is a CircleCI pipeline configuration in [.circleci/config.yml](.circleci/config.yml). 
If you want to view that pipeline in the Circle CI Application you can go   
[here](https://circleci.com/gh/GoldFlsh/google-civic-info-api-integration).

The pipeline was generated just for fun as I've been wanting to learn Circle CI the past few months. 
There was some interest in using the Circle CI CLI to create the local automation 
but it turns out their CLI tool is not sophisticated enough yet to handle this use-case and I had
to pivot to using a docker-compose file discussed later in the readme.

### Exploratory Testing
I have created a suite of postman tests to investigate the usage of 
Google's Civic Information API. These can be found in the 
[Google_Civic_Information_API collection](postman/Google_Civic_Information_API.postman_collection.json) in the postman folder 
at the project root directory.

You can import the collection into Postman to view these tests by following 
the directions [here](https://learning.postman.com/docs/postman/collection-runs/working-with-data-files/#importing-sample-collection-files).\
NOTE when running via postman UI, be sure to import the [environment file](postman/local.postman_environment.json) and set the
API_KEY environment variable to your own API Key.  

### Developing with the API
I've developed a simple GraphQL layer on top of the Google Civic Information API which can be queried
through Postman. I chose to keep it simple and expose a trivial transformation of the underlying data
The emphasis was to demonstrate a slice of local testing at the unit and functional level.

These tests are run as part of the automation at a later stage.

However, if interested, newer versions of Postman can be used to query GraphQL. 
Manually start the application locally through either of the two following commands

```bash
#Remove the {} and put your api-key after the '=' 
export API_KEY={your api-key here}
./gradlew bootRun
```
or 
```bash
#Remove the {} and put your api-key after the '=' 
export API_KEY={your api-key here}
docker-compose up graphql-api-spr-boot
```

NOTE if running on windows, the command might be `docker-compose.exe ...`

Open [Civic Information GraphQL collection](postman/Civic_Information_GraphQL.postman_collection.json)
to run the GraphQL queries. Ensure you import the [environment](postman/local.postman_environment.json) 
and set the API_KEY environment variable to your own API Key.  

Look [here](https://graphql.org/) to learn more about GraphQL. Hint, it's pretty cool.

### Automated Tests via CLI
I've created a script and docker-compose file that runs through all of the test scenarios created for this project
1. Unit tests against the GraphQL implementation
2. Functional tests against GraphQL Implementation using wiremock to mock Google's API
3. Postman tests against Google's Civic Information API
4. Postman integration GraphQL Tests against Google's live servers.

Tests 1, 2, and 4 are mostly there to demonstrate the infrastructure around automation 
and the number of tests are limited.

To run the full CI please run -  
```bash
# You will be prompted for API_KEY if it's not in the environment
export API_KEY={your api-key here}
./localci.sh
```