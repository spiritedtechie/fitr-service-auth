Description
-----------

A microservice for users built using Dropwizard and MongoDB.


Setup
-----

Install MongoDB (easiest way is as a Docker container e.g. via Kitematic)

Configure local.yml with MongoDB host, port and database name

Running
-------

Run the service:

    gradle run

POST http://localhost:8080/users

    curl -H "Content-Type: application/json" -X POST -d '{"_id": "Bob", "emailAddress": "bob@gmail.com"}' http://localhost:8080/users

GET http://localhost:8080/users

    curl http://localhost:8080/users?email_address=bob@gmail.com
