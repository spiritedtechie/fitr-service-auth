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

POST https://localhost:8443/signup

    curl -k -H "Content-Type: application/json" -X POST -d '{"email": "bob1@gmail.com", "password": "test123"}' https://localhost:8443/signup

POST http://localhost:8443/login

    curl -k -H "Content-Type: application/json" -X POST -d '{"email": "bob1@gmail.com", "password": "test123"}' https://localhost:8443/login

POST Invalid password

    curl -k -H "Content-Type: application/json" -X POST -d '{"email": "bob1@gmail.com", "password": "test124"}' https://localhost:8443/signup