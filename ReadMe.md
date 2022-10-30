#Star Wars Quote Application - For ToxiProxy Demo 
(Built by cannibalising the project: https://github.com/emreozcan3320/star-wars-quote-web-application)

This project is a simple SpringBoot project that interfaces with a MySQL database.

The project has 5 endpoints

* GET /api/v1/quotes - Get All Quotes
* GET /{quoteId} - Get A singular Quote back if the ID matches
* POST /api/v1/quotes - Add a Quote
* PUT /{quoteId} - Edit a Quote
* DELETE /{quoteId} - Delete a Quote

The purpose of this repo is to demonstrate how ToxiProxy works in action.

To start the dependencies navigate to the project directory and run 

`docker-compose down` 
then
`docker-compose up`

* Make note that in application.properties the MySQL url is localhost:3306, 
  which is the port the ToxiProxy is configured on in the docker compose, 
  to start up the application without any errors the proxy must be in present linking the localhost:3306 port to the localhost:3307  port.

`curl --location --request POST 'http://localhost:8474/proxies' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "mysql",
"listen": "[::]:3306",
"upstream": "mysql:3306",
"enabled": true,
"toxics": []
}'`

^The above is always created on the 'docker-compose up' command as the toxiproxyConfig.json is mounted -
To make changes to the proxy setup make changes in the toxiProxy config.


Then run the class 
`src/main/java/com/qabound/spring/core/CrudApplication.java`

Now you can run the feature file:
`src/test/resources/features/Resiliency-Scenarios.feature`


