Description
-----------

A simple microservice for user auth. built using Dropwizard and MongoDB.

JWT is used as the auth token returned to a authenticated user.

Technologies
------------
- Dropwizard Core - JAXRS, Jersey
- Dropwizard Auth
- Mongojack
- MongoDB
- JJWT

Setup
-----

Install MongoDB (easiest way is as a Docker container e.g. via Kitematic)

Configure local.yml with MongoDB host, port and database name

Running
-------

Run the service:

    gradle run

POST http://localhost:8080/signup

    curl -H "Content-Type: application/json" -X POST -d '{"email": "bob@gmail.com", "password": "test123"}' http://localhost:8080/signup

POST http://localhost:8080/login

    curl -H "Content-Type: application/json" -X POST -d '{"email": "bob@gmail.com", "password": "test123"}' http://localhost:8080/login

POST Invalid password

    curl -H "Content-Type: application/json" -X POST -d '{"email": "bob@gmail.com", "password": "test1234"}' http://localhost:8080/login
